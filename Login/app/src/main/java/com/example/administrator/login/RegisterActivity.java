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
import android.widget.Toast;

import com.example.administrator.tools.MD5;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2016/12/21.
 */
public class RegisterActivity extends Activity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private EditText etUsername, etPwd, etRepwd;
    private Button btnRegister;
    private String username, pwd, repwd, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        etUsername = (EditText) findViewById(R.id.et_username);
        etPwd = (EditText) findViewById(R.id.et_password);
        etRepwd = (EditText) findViewById(R.id.et_repwd);
        btnRegister = (Button) findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString();
                password = etPwd.getText().toString();
                pwd = etPwd.getText().toString();
                try {
                    pwd = MD5.getMD5(pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                repwd = etRepwd.getText().toString();
                try {
                    repwd = MD5.getMD5(repwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (pwd.equals(repwd)) {

                    editor.putString("username", username);
                    editor.putString("pwd", pwd);
                    editor.putString("password", password);
                    editor.putBoolean("remember", false);
                    editor.commit();

                    Toast.makeText(RegisterActivity.this, "注册成功，请记好您的帐号和密码", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "俩次输入密码不正确", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
