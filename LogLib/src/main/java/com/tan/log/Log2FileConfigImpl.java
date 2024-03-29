package com.tan.log;


import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tan.log.config.Log2FileConfig;
import com.tan.log.file.LogFileEngine;
import com.tan.log.file.LogFileFilter;
import com.tan.log.pattern.LogPattern;
import com.tan.log.utils.FileUtil;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Log2FileConfigImpl implements Log2FileConfig {

    private static final String DEFAULT_LOG_NAME_FORMAT = "%d{yyyyMMdd}.txt";


    private LogFileEngine engine;

    private LogFileEngine httpEngine;

//    private LogFileEngine actionEngine;

    private LogFileFilter fileFilter;
    private @LogLevel.LogLevelType int logLevel = LogLevel.TYPE_ERROR;
    private boolean enable = false;
    private String logFormatName = DEFAULT_LOG_NAME_FORMAT;
    private String logHttpFormatName = DEFAULT_LOG_NAME_FORMAT;
    //    private String logActionFormatName = DEFAULT_LOG_NAME_FORMAT;
    private String logPath;
    private String httpLogPath;
    //    private String actionLogPath;
    private static Log2FileConfigImpl singleton;
    private String customFormatName;

    // 设置过期天数  小于等于0则不开启 大于等于60按照60天算
    private int daysOfExpire = 0;

    public static Log2FileConfigImpl getInstance() {
        if (singleton == null) {
            synchronized (Log2FileConfigImpl.class) {
                if (singleton == null) {
                    singleton = new Log2FileConfigImpl();
                }
            }
        }
        return singleton;
    }

    @Override
    public Log2FileConfig configLog2FileEnable(boolean enable) {
        this.enable = enable;
        return this;
    }

    boolean isEnable() {
        return enable;
    }

    @Override
    public Log2FileConfig configLog2FilePath(String logPath) {
        this.logPath = logPath;
        return this;
    }

    @Override
    public Log2FileConfig configLog2HttpFilePath(String path) {
        if (TextUtils.isEmpty(path)) {
            this.httpLogPath = logPath;
        } else {
            this.httpLogPath = path;
        }

        return this;
    }

//    @Override
//    public Log2FileConfig configLog2ActionFilePath(String path) {
//        if (TextUtils.isEmpty(path)) {
//            this.actionLogPath = logPath;
//        } else {
//            this.actionLogPath = path;
//        }
//        return this;
//    }

    /**
     * 获取日志路径
     *
     * @return 日志路径
     */
    @NonNull
    String getLogPath() {
        if (TextUtils.isEmpty(logPath)) {
            throw new RuntimeException("Log File Path must not be empty");
        }
        File file = new File(logPath);
        if (file.exists() || file.mkdirs()) {
            return logPath;
        }
        throw new RuntimeException("Log File Path is invalid or no sdcard permission");
    }

    String getHttpLogPath() {
        if (TextUtils.isEmpty(httpLogPath)) {
            throw new RuntimeException("httpLogPath  must not be empty");
        }
        File file = new File(httpLogPath);
        if (file.exists() || file.mkdirs()) {
            return httpLogPath;
        }
        throw new RuntimeException("Log File Path is invalid or no sdcard permission");
    }

//    String getActionLogPath() {
//        if (TextUtils.isEmpty(actionLogPath)) {
//            throw new RuntimeException("actionLogPath  must not be empty");
//        }
//        File file = new File(actionLogPath);
//        if (file.exists() || file.mkdirs()) {
//            return actionLogPath;
//        }
//        throw new RuntimeException("Log File Path is invalid or no sdcard permission");
//    }


    /**
     * 此方法重置日期格式,在不关机的情况下，重新自动分割文件
     */
    public Log2FileConfig resetFormatName() {
        String logFormat = new LogPattern.Log2FileNamePattern(logFormatName).doApply();
        String httpFormat = new LogPattern.Log2FileNamePattern(logHttpFormatName).doApply();
        if ((customFormatName != null && !customFormatName.equals(logFormat)) && (httpLogFormatName != null && !httpLogFormatName.equals(httpFormat))) {
            flushAsync();
            customFormatName = null;
            httpLogFormatName = null;
        }
        return this;

    }

    String getLogFormatName() {
        if (customFormatName == null) {
            customFormatName = new LogPattern.Log2FileNamePattern(logFormatName).doApply();
        }
        return customFormatName;
    }

    private String httpLogFormatName;

    String getLogHttpFormatName() {
        if (httpLogFormatName == null) {
            httpLogFormatName = new LogPattern.Log2FileNamePattern(logHttpFormatName).doApply();
        }
        return httpLogFormatName;
    }

//    private String actionLogFormatName;
//
//    String getActionLogFormatName() {
//        if (actionLogFormatName == null) {
//            actionLogFormatName = new LogPattern.Log2FileNamePattern(logActionFormatName).doApply();
//        }
//        return actionLogFormatName;
//    }


    @Override
    public Log2FileConfig configLog2FileLevel(@LogLevel.LogLevelType int level) {
        this.logLevel = level;
        return this;
    }

    int getLogLevel() {
        return logLevel;
    }

    @Override
    public Log2FileConfig configLogFileEngine(LogFileEngine engine) {
        this.engine = engine;
        return this;
    }

    @Override
    public Log2FileConfig configHttpLogFileEngine(LogFileEngine engine) {
        this.httpEngine = engine;
        return this;
    }

//    @Override
//    public Log2FileConfig configActionLogFileEngine(LogFileEngine engine) {
//        this.actionEngine = engine;
//        return this;
//    }


    @Override
    public Log2FileConfig configLogFileFilter(LogFileFilter fileFilter) {
        this.fileFilter = fileFilter;
        return this;
    }

    /**
     * 获取的是日志文件夹
     *
     * @param level
     * @return
     */
    @Override
    @Nullable
    public File getLogFile(@LogLevel.LogLevelType int level) {
        if (level <= LogLevel.TYPE_ACTION) {
            String path = getLogPath();
            if (!TextUtils.isEmpty(path)) {
                return new File(path);
            }
        } else if (level == LogLevel.TYPE_HTTP) {
            return new File(getHttpLogPath());
        } else {
//            return new File(getActionLogPath());
        }
        return null;
    }

    @Override
    public Log2FileConfig configDaysOfExpire(int daysOfExpire) {
        this.daysOfExpire = daysOfExpire;
        return this;
    }

    private ScheduledExecutorService scheduler;

    @Override
    public Log2FileConfigImpl configSplitFile(int min) {

        if (scheduler != null) {
            scheduler.shutdown();
            scheduler = null;
        }
        scheduler = Executors.newScheduledThreadPool(1);
        Runnable runnableCode = this::resetFormatName;
        scheduler.scheduleAtFixedRate(runnableCode, min, min, TimeUnit.MINUTES);

        return this;

    }


    public void cancelSplitFile() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
            scheduler = null;
        }
    }


    public int getDaysOfExpire() {
        return daysOfExpire;
    }

    @Override
    public void flushAsync() {
        if (engine != null) {
            engine.flushAsync();
        }
        if (httpEngine != null) {
            httpEngine.flushAsync();
        }
        FileUtil.checkLog();
    }

    @Override
    public void release() {
        if (engine != null) {
            engine.release();
        }
        if (httpEngine != null) {
            httpEngine.release();
        }
        cancelSplitFile();
    }

    LogFileFilter getFileFilter() {
        return fileFilter;
    }

    LogFileEngine getEngine() {
        return engine;
    }

    LogFileEngine getHttpEngine() {
        return httpEngine;
    }


}
