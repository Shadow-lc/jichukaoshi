package com.example.administrator.login;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvInfo;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        tvInfo = (TextView) findViewById(R.id.tv_info);
        sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        tvInfo.setText(sharedPreferences.getString("username", null)+" 欢迎登录!");
    }
}
