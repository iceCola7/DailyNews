package com.cxz.news.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by chenxz on 2017/4/5.
 */
public class DateUtils {

    public static boolean isToday(String date) {
        String today = new SimpleDateFormat("yyyyMMdd", Locale.CHINA).format(Calendar.getInstance().getTime());
        return today.equals(date);
    }

    public static String getMainPageDate(String date) {
        String mDate = "";
        try {
            Date tmpDate = new SimpleDateFormat("yyyyMMdd", Locale.CHINA).parse(date);
            mDate = DateFormat.getDateInstance(DateFormat.FULL).format(tmpDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String thisYear = Calendar.getInstance().get(Calendar.YEAR) + "年";
        if (mDate.startsWith(thisYear)) {
            return mDate.replace(thisYear, "");
        }
        return mDate;
    }
}
