package cn.yuanfengshan.base.update.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import cn.yuanfengshan.base.update.R;
import cn.yuanfengshan.base.update.demo.utils.AppUpdateUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "yfs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_btn_demo_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUpdateUtils.checkAppVersion(MainActivity.this);
            }
        });
    }
}