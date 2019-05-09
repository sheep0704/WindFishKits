package com.windfish.utils;

import android.util.Log;

public class WFLogger {

    public static final String WF_TAG = "WindFish";
    private static boolean showLog = false;
    private static final int CALL_LOG_CLASS_INDEX = 4;

    private static final int LOG_TYPE_V = 0x01;
    private static final int LOG_TYPE_D = 0x02;
    private static final int LOG_TYPE_I = 0x03;
    private static final int LOG_TYPE_W = 0x04;
    private static final int LOG_TYPE_E = 0x05;


    private static WFLogger windFishLogger = new WFLogger();

    private WFLogger() {

    }

    public static WFLogger getInstance() {
        if (windFishLogger == null) {
            windFishLogger = new WFLogger();
        }

        return windFishLogger;
    }

    public void setDebugMode(boolean shouldShow) {
        if (shouldShow) {
            showLog = true;
        }
    }

    public void v(String tag, String msg) {
        analyze(LOG_TYPE_V, tag, msg);
    }

    public void d(String tag, String msg) {
        analyze(LOG_TYPE_D, tag, msg);
    }

    public void i(String tag, String msg) {
        analyze(LOG_TYPE_I, tag, msg);
    }

    public void w(String tag, String msg) {
        analyze(LOG_TYPE_W, tag, msg);
    }

    public void e(String tag, String msg) {
        analyze(LOG_TYPE_E, tag, msg);
    }

    private static void analyze(int type, String tag, String msg) {
        if (!showLog) {
            return;
        }

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        msg = "[(" + stackTraceElements[CALL_LOG_CLASS_INDEX].getFileName() + ":" + stackTraceElements[CALL_LOG_CLASS_INDEX].getLineNumber() + ") ==> " + stackTraceElements[CALL_LOG_CLASS_INDEX].getMethodName() + "] : " + msg;

        print(type, tag, msg);
    }

    private static void print(int type, String tag, String msg) {
        switch (type) {
            case LOG_TYPE_V:
                Log.v(tag, msg);
                break;
            case LOG_TYPE_D:
                Log.d(tag, msg);
                break;
            case LOG_TYPE_I:
                Log.i(tag, msg);
                break;
            case LOG_TYPE_W:
                Log.w(tag, msg);
                break;
            case LOG_TYPE_E:
                Log.e(tag, msg);
                break;
        }
    }
}
