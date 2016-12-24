package com.example.administrator.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.tools.MD5;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;

/**
 * Created by Administrator on 2016/12/19.
 */
public class LoginActivity extends Activity {
    private Button btnLogin;
    private TextView tvRegister;
    private EditText etname, etpwd;
    private CheckBox cbRemember;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String username, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        boolean flag = sharedPreferences.getBoolean("remember", false);
        btnLogin = (Button) findViewById(R.id.btm_login);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        etname = (EditText) findViewById(R.id.et_name);
        etpwd = (EditText) findViewById(R.id.et_pwd);
        cbRemember = (CheckBox) findViewById(R.id.cb_remember);
        if (flag) {
            username = sharedPreferences.getString("username", null);
            pwd = sharedPreferences.getString("password", null);
            etname.setText(username);
            etpwd.setText(pwd);
            cbRemember.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etname.getText().toString();
                pwd = etpwd.getText().toString();
                try {
                    pwd = MD5.getMD5(pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (username.equals(sharedPreferences.getString("username", "没得到")) && pwd.equals(sharedPreferences.getString("pwd", "没得到"))) {
                    if(cbRemember.isChecked()){
                        editor.putBoolean("remember", true);
                        editor.commit();
                    }else{
                        editor.putBoolean("remember", false);
                        editor.commit();
                    }
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "帐号或密码不正确", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
