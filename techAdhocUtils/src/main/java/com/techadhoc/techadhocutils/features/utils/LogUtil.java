package com.techadhoc.techadhocutils.features.utils;

import retrofit2.Call;
import rx.android.BuildConfig;

public class LogUtil {

    private static final String LOG_PREFIX = "SONFLOWER:";
    private static final String REQUEST_PREFIX = "***** HTTP: REQUEST ***** ";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;
    private static final int maxLogSize = 1000;
    private static StringBuilder sb0 = new StringBuilder();

    public static String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }
        return LOG_PREFIX + str;
    }

    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }

    public static String getTAG(Object o) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        int position = 0;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].getFileName().contains(o.getClass().getSimpleName())
                    && !elements[i+1].getFileName().contains(o.getClass().getSimpleName())){
                position = i;
                break;
            }
        }
        StackTraceElement element = elements[position];
        String className = element.getFileName().replace(".java", "");
        return "[" + className + "](" + element.getMethodName() + ":" + element.getLineNumber() + ")";
        // Log.v(Utils.getTAG(this), "hello world");
    }

    public static void LOGD(final String tag, String msg) {
        if (BuildConfig.DEBUG)
            android.util.Log.d(tag, msg);
    }

    public static void LOGD(final String tag, String msg, Throwable cause) {
        if (BuildConfig.DEBUG)
            android.util.Log.d(tag, msg, cause);
    }

    public static void LOGD(final String tag, Call reqCall) {
        if (BuildConfig.DEBUG)
            android.util.Log.d(tag, REQUEST_PREFIX + reqCall.request().url());
    }

    public static void LOGE(final String tag, String msg) {
        if (BuildConfig.DEBUG)
            android.util.Log.e(tag, msg);
    }

    public static void LOGE(final String tag, String msg, Throwable cause) {
        if (BuildConfig.DEBUG)
            android.util.Log.e(tag, msg, cause);
    }


    public static void LOGI(final String tag, String msg) {
        if (BuildConfig.DEBUG)
            android.util.Log.i(tag, msg);
    }

    public static void LOGI(final String tag, String msg, Throwable cause) {
        if (BuildConfig.DEBUG)
            android.util.Log.i(tag, msg, cause);
    }


    public static void LOGW(final String tag, String msg) {
        if (BuildConfig.DEBUG)
            android.util.Log.w(tag, msg);
    }

    public static void LOGW(final String tag, String msg, Throwable cause) {
        if (BuildConfig.DEBUG)
            android.util.Log.w(tag, msg, cause);
    }

    public static void LOGV(final String tag, String msg) {
        if (BuildConfig.DEBUG)
            android.util.Log.w(tag, msg);
    }

    public static void LOGWV(final String tag, String msg, Throwable cause) {
        if (BuildConfig.DEBUG)
            android.util.Log.w(tag, msg, cause);
    }

    public static void logWithLink(String TAG, Object param) {
        StackTraceElement stack = Thread.currentThread().getStackTrace()[3];
        sb0.setLength(0);
        String c = stack.getFileName().substring(
                0, stack.getFileName().length() - 5); // removes the ".java"
        sb0.append(c).append(":");
        sb0.append(stack.getMethodName()).append('(');
        if (param != null) {
            sb0.append(param);
        }
        sb0.append(") ");
        sb0.append(" (").append(stack.getFileName())
                .append(':')
                .append(stack.getLineNumber()).append(')');
        android.util.Log.d(TAG, sb0.toString());
    }

    public static void LOGLONG(final String tag, String veryLongString) {
        if (BuildConfig.DEBUG) {
            for (int i = 0; i <= veryLongString.length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i + 1) * maxLogSize;
                end = end > veryLongString.length() ? veryLongString.length() : end;
                android.util.Log.i(tag, veryLongString.substring(start, end));
            }
        }
    }
}
