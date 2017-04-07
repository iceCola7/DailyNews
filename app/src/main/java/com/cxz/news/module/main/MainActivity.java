package com.cxz.news.module.main;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.cxz.news.R;
import com.cxz.news.base.BaseActivity;
import com.cxz.news.module.news.main.NewsMainFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainContract.IPresenter> implements MainContract.IView, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;
    @BindView(R.id.nav_view)
    NavigationView mNaView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private int mItemId = -1;
    private long mExitTime = 0;
    private SparseArray<String> mSparseTags = new SparseArray<>();

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case R.id.nav_news:
                    replaceFragment(R.id.fl_container,new NewsMainFragment(),mSparseTags.get(R.id.nav_news));
                    break;
                case R.id.nav_photos:
                    break;
                case R.id.nav_videos:
                   break;
                case R.id.nav_setting:

                    break;
            }
            mItemId = -1;
            return true;
        }
    });

    @Override
    protected MainContract.IPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        initDrawerLayout(mDrawerLayout,mNaView);
        mSparseTags.put(R.id.nav_news,"News");
        mSparseTags.put(R.id.nav_photos,"Photos");
        mSparseTags.put(R.id.nav_videos,"Videos");
        initToolBar(mToolbar,false,"News");
        initDrawerToggle(mDrawerLayout,mToolbar);
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mNaView.setCheckedItem(R.id.nav_news);
        addFragment(R.id.fl_container,new NewsMainFragment(),"News");
    }

    /**
     * 初始化DrawerLayout
     * @param mDrawerLayout
     * @param mNaView
     */
    private void initDrawerLayout(DrawerLayout mDrawerLayout, NavigationView mNaView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            //将侧边栏顶部延伸至status bar
            mDrawerLayout.setFitsSystemWindows(true);
            //将主页面顶部延伸至status bar
            mDrawerLayout.setClipToPadding(false);
        }
        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                mHandler.sendEmptyMessage(mItemId);
            }
        });
        mNaView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        if (item.isChecked()){
            return true;
        }
        mItemId = item.getItemId();
        return true;
    }


    @Override
    public void onBackPressed() {
        // 获取堆栈里有几个
        final int stackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (stackEntryCount == 1) {
            // 如果剩一个说明在主页，提示按两次退出app
            exit();
        } else {
            // 获取上一个堆栈中保存的是哪个页面，根据name来设置导航项的选中状态
            final String tagName = getSupportFragmentManager().getBackStackEntryAt(stackEntryCount - 2).getName();
            mNaView.setCheckedItem(mSparseTags.keyAt(mSparseTags.indexOfValue(tagName)));
            super.onBackPressed();
        }
    }
    /**
     * 退出
     */
    private void exit() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

}
