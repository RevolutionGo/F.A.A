package com.example01.myapplicationtest01.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example01.myapplicationtest01.MainActivity;
import com.example01.myapplicationtest01.R;
import com.example01.myapplicationtest01.util.Constants;
import com.example01.myapplicationtest01.util.RegexUtil;
import com.example01.myapplicationtest01.util.SharedPreferencesUtil;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_username;
    private EditText et_password;
    private Button bt_login;
    //private Button bt_login2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        bt_login = findViewById(R.id.bt_login);
        //bt_login2 = findViewById(R.id.bt_login2);

        bt_login.setOnClickListener(this);
        //bt_login2.setOnClickListener(this);

    }

    public void onClick(View view) {
       //若有多个按钮，则可以匹配多个按钮
        switch (view.getId()) {
            case R.id.bt_login:
                login();
                break;
            //case R.id.bt_login2:
                //login();
                //break;
        }
    }

    private void login() {
        //获取用户输入的邮箱、密码，做校验
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        //判断是否输入了邮箱
        if(TextUtils.isEmpty(username)) {
            Toast.makeText(this,R.string.email_hint,Toast.LENGTH_SHORT).show();
            return;
        }

        //通过正则表达式判断邮箱格式是否正确
        if (RegexUtil.isEmail(username)) {
            Toast.makeText(this,R.string.email_error,Toast.LENGTH_SHORT).show();
            return;
        }

        //判断是否输入了密码
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this,R.string.password_hint,Toast.LENGTH_SHORT).show();
            return;
        }

        //判断密码长度是否正确
        if (password.length() < 6 || password.length() > 15) {
            Toast.makeText(this,R.string.password_length_error,Toast.LENGTH_SHORT).show();
            return;
        }

        //TODO调用服务端的登录接口
        //此处将用户和密码写在本地
        if ((Constants.USERNAME.equals(username) && Constants.PASSWORD.equals(password))) {
            //TODO登录完成后保存一个标志，记住登录状态
            sp.setLogin(true);

            //登录成功，进入首页
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            //关闭当前界面
            finish();
        }else {
            //登录失败提示
            Toast.makeText(this,R.string.username_or_password_error,Toast.LENGTH_SHORT).show();
        }
    }
}
