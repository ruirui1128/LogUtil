package com.mind.log

import android.Manifest
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.permissionx.guolindev.PermissionX
import com.tan.log.LogUtils
import com.tan.log.engine.LogFileActionEngineFactory
import com.tan.log.engine.LogFileEngineFactory
import com.tan.log.engine.LogFileHttpEngineFactory
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PermissionX.init(this)
            .permissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    allGranted()
                }
            }

        thread {
            val name = Thread.currentThread().name
            while (true) {
                SystemClock.sleep(500L)
                LogUtils.a("${name}:=============我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志===============")
                LogUtils.h(
                    "${name}===================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志================"
                )
                LogUtils.e("${name}=============我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志===============")
            }
        }
        thread {
            val name = Thread.currentThread().name
            while (true) {
                SystemClock.sleep(500L)
                LogUtils.a("${name}:=============我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志===============")
                LogUtils.h(
                    "${name}===================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志================"
                )
                LogUtils.e("${name}=============我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志===============")
            }
        }
        thread {
            val name = Thread.currentThread().name
            while (true) {
                SystemClock.sleep(500L)
                LogUtils.a("${name}:=============我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志===============")
                LogUtils.h(
                    "${name}===================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志================"
                )
                LogUtils.e("${name}=============我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志===============")
            }
        }
        thread {
            val name = Thread.currentThread().name
            while (true) {
                SystemClock.sleep(500L)
                LogUtils.a("${name}:=============我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志===============")
                LogUtils.h(
                    "${name}===================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志================"
                )
                LogUtils.e("${name}=============我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志===============")
            }
        }


    }

    private fun allGranted() {
        val fileDir = this.getExternalFilesDir(null)
        if (fileDir?.exists() == false) {
            fileDir.mkdirs()
        }
        val path = fileDir?.absolutePath
        // 支持输入日志到文件
        LogUtils.getLog2FileConfig()
            .configLog2FileEnable(true)
            .configLog2FilePath("$path/a/log/err")
            .configLog2HttpFilePath("$path/a/log/http")
            .configLog2ActionFilePath("$path/a/log/action")
            .configLogFileEngine(LogFileEngineFactory(this))
            .configHttpLogFileEngine(LogFileHttpEngineFactory(this))
            .configActionLogFileEngine(LogFileActionEngineFactory(this))
            .configDaysOfExpire(7) // 设置过期天数
            .flushAsync()

    }


    override fun onDestroy() {
        super.onDestroy()
        LogUtils.getLog2FileConfig().release()
    }
}