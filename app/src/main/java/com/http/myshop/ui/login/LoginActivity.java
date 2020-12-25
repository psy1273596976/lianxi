package com.http.myshop.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.http.myshop.MainActivity;
import com.http.myshop.R;
import com.http.myshop.SplaceActivity;
import com.http.myshop.base.BaseActivity;
import com.http.myshop.interfaces.login.ILogin;
import com.http.myshop.model.login.LoginBean;
import com.http.myshop.presenter.login.LoginPresenter;
import com.http.myshop.utils.SpUtils;
import com.luck.picture.lib.tools.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity<ILogin.Presenter> implements ILogin.View {
    @BindView(R.id.input_username)
    EditText inputUsername;
    @BindView(R.id.input_pw)
    EditText inputPw;
    @BindView(R.id.img_pw)
    ImageView imgPw;
    @BindView(R.id.layout_pw)
    FrameLayout layoutPw;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private String name;
    private String pwd;
    private int code;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected ILogin.Presenter createPrenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initView() {
        inputPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        imgPw.setOnClickListener(new View.OnClickListener() {
            int count=0;
            @Override
            public void onClick(View v) {
                count++;
                if (count % 2 == 1) {
                    imgPw.setImageResource(R.mipmap.ic_pw_open);
                    inputPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    imgPw.setImageResource(R.mipmap.ic_pw_close);
                    inputPw.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            }
        });
    }

    @Override
    protected void initData() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到输入框的值
                name = inputUsername.getText().toString();
                pwd = inputPw.getText().toString();
                //判断非空
                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)){
                    presenter.login(name,pwd);
                }else{
                    ToastUtils.s(LoginActivity.this,getString(R.string.tips_login));
                }
            }
        });
    }

    @Override
    public void loginReturn(LoginBean loginBean) {
        if(!TextUtils.isEmpty(loginBean.getData().getToken())){
            SpUtils.getInstance().setValue("token",loginBean.getData().getToken());
            SpUtils.getInstance().setValue("uid",loginBean.getData().getUserInfo().getUid());
            finish();
        }
    }
}
