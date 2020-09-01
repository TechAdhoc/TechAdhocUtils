package com.techadhoc.techadhocutils.features.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
    private static final String TAG = LogUtil.makeLogTag(TimeUtils.class);
    private static String timePattern24 = "HH:mm";
    private String inputPattern = "yyyy-MM-dd hh:mm:ss";
    private String outputPattern = "dd-MM-yyyy";
    private String timePattern = "hh:mm a";


    public String getFormattedDate(String dateTime) {
        String newDateStr = "";
        try {
            SimpleDateFormat form = new SimpleDateFormat(inputPattern);
            java.util.Date date = null;
            try {
                // 2011-03-27T09:39:01.607
                date = form.parse(dateTime);
            } catch (ParseException e) {

                e.printStackTrace();
            }
            SimpleDateFormat postFormater = new SimpleDateFormat(outputPattern);
            newDateStr = postFormater.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return newDateStr;

    }

    String getFormattedTime(String time) {
        SimpleDateFormat form = new SimpleDateFormat(inputPattern);
        java.util.Date date = null;
        try {
            // 2011-03-27T09:39:01.607
            date = form.parse(time);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        SimpleDateFormat postFormater = new SimpleDateFormat(timePattern);
        String newTimeStr = postFormater.format(date);
        return newTimeStr;

    }

    public static String getFormattedAmount(double amnt) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String formattedAmount = formatter.format(amnt);
        return formattedAmount;
    }

    public static long getCurrentTime() {
        return Calendar.getInstance().getTime().getTime();
    }

    public static Boolean isTimeExceed(long lastSaveTime) {
        if (getMinuteDiff(getCurrentTime(), lastSaveTime) > 10) {
            return true;
        } else {
            return false;
        }
    }

    private static long getMinuteDiff(long currentTime, long lastSaveTime) {
        long mils = currentTime - lastSaveTime;
        int minuteFor = (int) (mils / (1000 * 60) % 60);
        LogUtil.LOGD("getMinuteDiff:", "" + minuteFor);
        return minuteFor;
    }

    public static boolean checkBetweenTime(String startTime, String endTime) {
        try {
            Date time1 = new SimpleDateFormat(timePattern24).parse(startTime);
            Calendar startTimecalendar = Calendar.getInstance();
            startTimecalendar.setTime(time1);
            startTimecalendar.add(Calendar.DATE, 1);

            Date time2 = new SimpleDateFormat(timePattern24).parse(endTime);
            Calendar endTimeCalendar = Calendar.getInstance();
            endTimeCalendar.setTime(time2);
            endTimeCalendar.add(Calendar.DATE, 1);


            Calendar currentTimeCalendar = Calendar.getInstance();
            String currentTimes = new SimpleDateFormat(timePattern24).
                    format(currentTimeCalendar.getTime());
            Date currentTime = new SimpleDateFormat(timePattern24).
                    parse(currentTimes);
            currentTimeCalendar.setTime(currentTime);
            currentTimeCalendar.add(Calendar.DATE, 1);

            Date mTime = currentTimeCalendar.getTime();
            if (mTime.after(startTimecalendar.getTime()) && mTime.before(endTimeCalendar.getTime())) {
                //checkes whether the current time is between start and end
                return true;
            }
        } catch (ParseException e) {
            LogUtil.LOGE(TAG, "ParseException", e);
        }
        return false;
    }
}
