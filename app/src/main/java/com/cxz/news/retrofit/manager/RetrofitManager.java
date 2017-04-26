package com.cxz.news.retrofit.manager;

import android.util.SparseArray;

import com.cxz.news.App;
import com.cxz.news.base.BaseSchedulerTransformer;
import com.cxz.news.bean.Photos.BeautyPhotos;
import com.cxz.news.bean.Photos.GankPhotos;
import com.cxz.news.bean.Photos.LifePhotoInfo;
import com.cxz.news.bean.WeatherInfo;
import com.cxz.news.bean.news.DailyStories;
import com.cxz.news.bean.news.Story;
import com.cxz.news.retrofit.Api;
import com.cxz.news.retrofit.ApiStores;
import com.cxz.news.retrofit.HostType;
import com.cxz.news.utils.NetUtil;
import com.cxz.news.utils.XLog;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by chenxz on 2017/3/30.
 */
public class RetrofitManager {

    //设置缓存有效期为两天
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    //30秒直接读缓存
    private static final long CACHE_AGE_SEC = 30;

    private static volatile OkHttpClient mOkHttpClient;

    //管理不同的Host的单例
    private static SparseArray<RetrofitManager> mRetrofitManager = new SparseArray<>(HostType.TYPE_COUNT);

    private ApiStores mApiStores;

    // 云端响应头拦截器，用来配置缓存策略
    private Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            // 在这里统一配置请求头缓存策略以及响应头缓存策略
            if (NetUtil.isConnected(App.getApp())) {
                // 在有网的情况下CACHE_AGE_SEC秒内读缓存，大于CACHE_AGE_SEC秒后会重新请求数据
                request = request.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control").header("Cache-Control", "public, max-age=" + CACHE_AGE_SEC).build();
                Response response = chain.proceed(request);
                return response.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control").header("Cache-Control", "public, max-age=" + CACHE_AGE_SEC).build();
            } else {
                // 无网情况下CACHE_STALE_SEC秒内读取缓存，大于CACHE_STALE_SEC秒缓存无效报504
                request = request.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC).build();
                Response response = chain.proceed(request);
                return response.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC).build();
            }
        }
    };

    // 打印返回的json数据拦截器
    private Interceptor mLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();

            Request.Builder requestBuilder = request.newBuilder();
            requestBuilder.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
            request = requestBuilder.build();

            final Response response = chain.proceed(request);

            XLog.e("请求网址: \n" + request.url() + " \n " + "请求头部信息：\n" + request.headers() + "响应头部信息：\n" + response.headers());

            final ResponseBody responseBody = response.body();
            final long contentLength = responseBody.contentLength();

            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(charset);
                } catch (UnsupportedCharsetException e) {
                    XLog.e("Couldn't decode the response body; charset is likely malformed.");
                    return response;
                }
            }

            if (contentLength != 0) {
                XLog.v("--------------------------------------------开始打印返回数据----------------------------------------------------");
                XLog.v(buffer.clone().readString(charset));
                XLog.v("--------------------------------------------结束打印返回数据----------------------------------------------------");
            }
            return response;
        }
    };

    private RetrofitManager() {
    }

    private RetrofitManager(@HostType.HostTypeChecker int hostType) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.getHost(hostType))
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mApiStores = retrofit.create(ApiStores.class);
    }

    public static RetrofitManager getInstance(int hostType) {
        RetrofitManager instance = mRetrofitManager.get(hostType);
        if (instance == null) {
            instance = new RetrofitManager(hostType);
            mRetrofitManager.put(hostType, instance);
        }
        return instance;
    }

    /**
     * 配置OKHttpClient
     *
     * @return
     */
    private OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (mOkHttpClient == null) {
                    //指定缓存路径
                    //XLog.e("----------"+App.getApp().getCacheDir());
                    //File file = new File(App.getApp().getCacheDir().getAbsolutePath(), "HttpCache");
                    //Cache cache = new Cache(file, 1024 * 1024 * 10);
                    mOkHttpClient = new OkHttpClient.Builder()
                            //.cache(cache)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(mRewriteCacheControlInterceptor).addInterceptor(mLoggingInterceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(CACHE_AGE_SEC, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return mOkHttpClient;
    }

    /**
     * 天气情况 例子：http://wthrcdn.etouch.cn/weather_mini?city=%E5%8C%97%E4%BA%AC
     *
     * @param city 城市名称
     * @return 被观察者
     */
    public Observable<WeatherInfo> getWeatherInfoObservable(String city) {
        return mApiStores.getWeatherInfo(city).compose(new BaseSchedulerTransformer<WeatherInfo>());
    }

    /**
     * 最新的新闻
     *
     * @return
     */
    public Observable<DailyStories> getLatestDailyStories() {
        return mApiStores.getLatestDailyStories().compose(new BaseSchedulerTransformer<DailyStories>());
    }

    /**
     * 过去的新闻
     *
     * @param date
     * @return
     */
    public Observable<DailyStories> getBeforeDailyStories(String date) {
        return mApiStores.getBeforeDailyStories(date).compose(new BaseSchedulerTransformer<DailyStories>());
    }

    /**
     * 根据ID获取新闻详情
     *
     * @param storyId
     * @return
     */
    public Observable<Story> getStoryDetailById(String storyId) {
        return mApiStores.getStoryDetailById(storyId).compose(new BaseSchedulerTransformer<Story>());
    }

    /**
     * 获取干货图片
     *
     * @param pageSize
     * @return
     */
    public Observable<GankPhotos> getGankPhotos(int pageSize) {
        return mApiStores.getGankPhotos(pageSize).compose(new BaseSchedulerTransformer<GankPhotos>());
    }

    /**
     * 获取生活图片
     *
     * @return
     */
    public Observable<List<LifePhotoInfo>> getLifePhotos() {
        return mApiStores.getLifePhotos().compose(new BaseSchedulerTransformer<List<LifePhotoInfo>>());
    }

    /**
     * 获取更多生活图片
     *
     * @param setId
     * @return
     */
    public Observable<List<LifePhotoInfo>> getMoreLifePhotos(String setId) {
        return mApiStores.getMoreLifePhotos(setId).compose(new BaseSchedulerTransformer<List<LifePhotoInfo>>());
    }

    /**
     * @param pageSize
     * @return
     */
    public Observable<BeautyPhotos> getBeautyPhotos(int pageSize) {
        return mApiStores.getBeautyPhotos(pageSize).compose(new BaseSchedulerTransformer<BeautyPhotos>());
    }

}
