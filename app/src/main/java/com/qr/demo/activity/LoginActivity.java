package com.qr.demo.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.qr.demo.MainActivity;
import com.qr.demo.R;
import com.qr.demo.utils.SharedPreferencesUtil;
import com.qr.demo.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sun on 2017/12/28.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.pwd)
    EditText pwd;

    public String superAdminAccount = "admin";
    public String superAdminPwd = "admin";

    public String normalAccount = "normal";
    public String normalPwd = "123456";


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        if (checkLogin()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @OnClick(R.id.btn_login)
    public void login(View v) {

        String ac = account.getText().toString();
        String pw = pwd.getText().toString();

        if (superAdminAccount.equals(ac) && superAdminPwd.equals(pw)) {
            SharedPreferencesUtil.getInstance(this).putString(SharedPreferencesUtil.ACCOUNT, SharedPreferencesUtil.SUPER_ACCOUNT);
            skipMain();
        } else if (normalAccount.equals(ac) && normalPwd.equals(pw)) {
            SharedPreferencesUtil.getInstance(this).putString(SharedPreferencesUtil.ACCOUNT, SharedPreferencesUtil.NORMAL_ACCOUNT);
            skipMain();
        } else {
            ToastUtils.show(this, "账号或密码错误");
        }
    }

    public boolean checkLogin() {
        String account = SharedPreferencesUtil.getInstance(this).getString(SharedPreferencesUtil.ACCOUNT, "");
        if (!TextUtils.isEmpty(account)) {
            if (account.equals(SharedPreferencesUtil.SUPER_ACCOUNT) || account.equals(SharedPreferencesUtil.NORMAL_ACCOUNT)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void skipMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
