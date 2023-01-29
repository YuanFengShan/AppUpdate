package cn.yuanfengshan.base.update.demo.utils;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import cn.yuanfengshan.base.update.UpdateAppBean;
import cn.yuanfengshan.base.update.UpdateAppManager;
import cn.yuanfengshan.base.update.UpdateCallback;
import cn.yuanfengshan.base.update.listener.ExceptionHandler;

/**
 * 封装调用app更新的工具
 */
public class AppUpdateUtils {
    private static final String TAG = "AppUpdateUtils";

    public static void checkAppVersion(Activity activity) {
        checkAppVersion(activity, "http://www.baidu.com");

    }

    public static void checkAppVersion(Activity activity, String url) {
        //获取下载地址
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String downloadFileDir = externalStoragePublicDirectory.getAbsolutePath();
        ///storage/emulated/0/Download
//        Log.d(TAG, "路径:" + downloadFileDir);
        new UpdateAppManager.Builder()
                .setActivity(activity)
                .setUpdateUrl(url)
                .setTargetPath(downloadFileDir)
                .setHttpManager(new OkGoUpdateHttpUtil())
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {

                    }
                })
                .build()
                .checkNewApp(new UpdateCallback() {
                    @Override
                    protected UpdateAppBean parseJson(String json) {
                        //这里要将服务器请求回来的数据，转换成需要使用的数据

                        UpdateAppBean updateAppBean = new UpdateAppBean();
                        try {
                            updateAppBean.setUpdate("Yes")
                                    //存放原始的json，方便自定义解析
                                    .setOriginRes(json)
                                    //新的版本名
                                    .setNewVersion("2.0")
                                    //apk下载地址
                                    .setApkFileUrl("http://www.baidu.com")
                                    //目标的大小
                                    .setTargetSize("1888KB")
                                    //显示的更新内容
                                    .setUpdateLog("有新功能\r\n1.xxx\r\n2.xxx")
                                    //是否强制更新
                                    .setConstraint(false)
                                    //新的文件的md5值
                                    .setNewMd5("123456789");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return updateAppBean;
                    }
                });
    }

}
