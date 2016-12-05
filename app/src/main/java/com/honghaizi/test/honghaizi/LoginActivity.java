package com.honghaizi.test.honghaizi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_login_back;
    private EditText login_et_phone;
    private EditText login_et_password;
    private Button login_but;
    private Button button_clear_shouji;
    private Button button_clear_mima;
    private ImageView iv_login_mf;
    private boolean isHidden=true;
    private ImageView togglebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        //初始化控件
        initCtrl();
    }

    private void initCtrl() {

        iv_login_back = (ImageView)findViewById(R.id.iv_login_back);//返回
        login_et_phone = (EditText)findViewById(R.id.login_et_phone);
        login_et_password = (EditText)findViewById(R.id.login_et_password);
        login_but = (Button)findViewById(R.id.login_but);//登录
        //清除输入框信息
        button_clear_shouji = (Button)findViewById(R.id.button_clear_shouji);
        button_clear_mima = (Button)findViewById(R.id.button_clear_mima);
        iv_login_mf = (ImageView)findViewById(R.id.iv_login_mf);

//
        togglebutton = (ImageView)findViewById(R.id.togglebutton);

        iv_login_back.setOnClickListener(this);
        login_et_phone.setOnClickListener(this);
        login_et_password.setOnClickListener(this);
        login_but.setOnClickListener(this);
        button_clear_shouji.setOnClickListener(this);
        button_clear_mima.setOnClickListener(this);
        iv_login_mf.setOnClickListener(this);
        togglebutton.setOnClickListener(this);

        password();
        phone();
    }

    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.login_but:

                break;
            case R.id.button_clear_shouji:
                login_et_phone.setText("");
                break;
            case R.id.button_clear_mima:
                login_et_password.setText("");
                break;

            case R.id.iv_login_mf:
                startActivity(new Intent(LoginActivity.this,ZhuceActivity.class));//跳转注册页面
                break;

            case R.id.togglebutton:
                togglebutton();//显示隐藏
                break;
        }
    }

    private void password() {
        login_et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    login_but.setBackgroundColor(Color.rgb(202, 202, 197));
                    button_clear_mima.setVisibility(View.GONE);
                } else {
                    button_clear_mima.setVisibility(View.VISIBLE);
                    String AN = login_et_phone.getText().toString();
                    int length = AN.length();
                    if (length > 0) {
                        login_but.setBackgroundColor(Color.rgb(251, 203, 61));
                    }
                }
            }
        });
    }

    private void phone() {
        login_et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null || s.length() == 0) return;
                StringBuilder sb = new StringBuilder();
                for (int i= 0; i < s.length(); i++) {
                    if (i != 3 && i != 8 && s.charAt(i) == ' ') {
                        continue;
                    } else {
                        sb.append(s.charAt(i));
                        if ((sb.length() == 4 || sb.length() == 9) && sb.charAt(sb.length() - 1) != ' ') {
                            sb.insert(sb.length() - 1, ' ');
                        }
                    }
                }
                if (!sb.toString().equals(s.toString())) {
                    int index = start + 1;
                    if (sb.charAt(start) == ' ') {
                        if (before == 0) {
                            index++;
                        } else {
                            index--;
                        }
                    } else {
                        if (before == 1) {
                            index--;
                        }
                    }
                    login_et_phone.setText(sb.toString());
                    login_et_phone.setSelection(index);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    login_but.setBackgroundColor(Color.rgb(202, 202, 197));
                    button_clear_shouji.setVisibility(View.GONE);
                } else if (s.length() == 13) {
                    String text = login_et_phone.getText().toString().trim();
                    String phone_num = text.replace(" ", "");
                    boolean b = isPhone.isMobileNO(phone_num);
                    if (b == false) {
                        Toast.makeText(LoginActivity.this, "输入的号码有误!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    button_clear_shouji.setVisibility(View.VISIBLE);
                    String PS = login_et_password.getText().toString();
                    int length = PS.length();
                    if (length > 0) {
                        login_but.setBackgroundColor(Color.rgb(251, 203, 61));
                    }
                }
            }
        });
    }

    private void togglebutton() {
        if (isHidden) {
            //设置EditText文本为可见的
            login_et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            togglebutton.setImageResource(R.mipmap.icon_display);
        } else {
            //设置EditText文本为隐藏的
            login_et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            togglebutton.setImageResource(R.mipmap.icon_hidden);
        }
        isHidden = !isHidden;
        login_et_password.postInvalidate();
        //切换后将EditText光标置于末尾
        CharSequence charSequence = login_et_password.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
    }





}
