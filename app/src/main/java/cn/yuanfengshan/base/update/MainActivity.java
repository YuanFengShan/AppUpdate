package cn.yuanfengshan.base.update;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;

import java.io.File;

import cn.yuanfengshan.base.update.listener.ExceptionHandler;
import cn.yuanfengshan.base.update.utils.OkGoUpdateHttpUtil;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "yfs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_btn_demo_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取下载地址
                File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                String downloadFileDir = externalStoragePublicDirectory.getAbsolutePath();
                ///storage/emulated/0/Download
                Log.d(TAG, "onClick: 路径:" + downloadFileDir);
                new UpdateAppManager.Builder()
                        .setActivity(MainActivity.this)
                        .setUpdateUrl("http://www.baidu.com")
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

                                UpdateAppBean updateAppBean = new UpdateAppBean();
                                try {
                                    updateAppBean.setUpdate("Yes")
                                            //存放json，方便自定义解析
                                            .setOriginRes("")
                                            .setNewVersion("2.0")
                                            .setApkFileUrl("http://www.baidu.com")
                                            .setTargetSize("1888KB")
                                            .setUpdateLog("有新功能\r\n1.xxx\r\n2.xxx")
                                            .setConstraint(false)
                                            .setNewMd5("123456789");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                return updateAppBean;
                            }
                        });

            }
        });
    }
}