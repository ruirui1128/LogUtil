
package com.tan.log.engine;


import android.content.Context;

import androidx.annotation.NonNull;

import com.tan.log.LogLevel;
import com.tan.log.file.LogFileEngine;
import com.tan.log.file.LogFileParam;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.pqpo.librarylog4a.LogBuffer;

/**
 * 日志写入文件的默认引擎
 */

public class LogFileCrashEngineFactory implements LogFileEngine {

    private static final String LOG_CONTENT_FORMAT = "[%s][%s][%s:%s]%s\n";
    private static final String LOG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
    private DateFormat dateFormat;
    private volatile LogBuffer buffer;

    private int capacity = 4096;
    private Context context;

    public LogFileCrashEngineFactory(@NonNull Context context) {
        if (context == null) {
            throw new NullPointerException("Context must not null!");
        }
        this.context = context.getApplicationContext();
        dateFormat = new SimpleDateFormat(LOG_DATE_FORMAT, Locale.getDefault());
    }

    public LogFileCrashEngineFactory(@NonNull Context context, int capacity) {
        if (context == null) {
            throw new NullPointerException("Context must not null!");
        }
        this.context = context.getApplicationContext();
        dateFormat = new SimpleDateFormat(LOG_DATE_FORMAT, Locale.getDefault());
        this.capacity = capacity;
    }

    @Override
    public void writeToFile(File logFile, @LogLevel.LogLevelType int logLevel, String logContent, LogFileParam params) {
        if (buffer == null) {
            synchronized (LogFileCrashEngineFactory.class) {
                if (buffer == null) {
                    File bufferFile = new File(context.getFilesDir(), ".log4aCache-crash");
                    buffer = new LogBuffer(bufferFile.getAbsolutePath(), capacity,
                            logFile.getAbsolutePath(), false);
                }
            }
        }
        buffer.write(getWriteString(logContent, params));
    }

    /**
     * 写入文件的内容
     *
     * @param logContent log value
     * @param params     LogFileParam
     * @return file log content
     */
    private String getWriteString(String logContent, LogFileParam params) {
        String time = dateFormat.format(new Date(params.getTime()));
        return String.format(LOG_CONTENT_FORMAT, time, getLogLevelString(params.getLogLevel()),
                params.getThreadName(), params.getTagName(), logContent);
    }

    /**
     * 日志等级
     *
     * @param level level
     * @return level string
     */
    private String getLogLevelString(int level) {
        switch (level) {
            case LogLevel.TYPE_VERBOSE:
                return "V";
            case LogLevel.TYPE_ERROR:
                return "E";
            case LogLevel.TYPE_INFO:
                return "I";
            case LogLevel.TYPE_WARM:
                return "W";
            case LogLevel.TYPE_WTF:
                return "Wtf";

            case LogLevel.TYPE_ACTION:
                return "action";
            case LogLevel.TYPE_HTTP:
                return "http";
            case LogLevel.TYPE_CRASH:
                return "crash";
        }
        return "D";
    }

    @Override
    public void flushAsync() {
        if (buffer != null) {
            buffer.flushAsync();
        }

    }

    @Override
    public void release() {
        if (buffer != null) {
            buffer.release();
            buffer = null;
        }


    }

}
