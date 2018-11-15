package com.romantic.dreamaccount.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.bean.LoginResult;
import com.romantic.dreamaccount.config.Comment;
import com.romantic.dreamaccount.present.LoginP;
import com.romantic.dreamaccount.util.ToastUtil;
import com.sensology.framelib.cache.SharedPref;
import com.sensology.framelib.kit.Kits;
import com.sensology.framelib.router.Router;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<LoginP> {
    @BindView(R.id.back)
    public LinearLayout mBack;
    @BindView(R.id.title)
    public TextView mTitle;
    @BindView(R.id.userName)
    public EditText mUserName;
    @BindView(R.id.userPwd)
    public EditText mUserPwd;
    @BindView(R.id.login)
    public TextView mLogin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginP newP() {
        return new LoginP();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mTitle.setText(getString(R.string.login));
        mBack.setOnClickListener(this);
        mBack.setVisibility(View.GONE);
        mLogin.setOnClickListener(this);

        mUserName.setText(SharedPref.getInstance(context).getString(Comment.PrefKey.USER_NAME,""));
        mUserPwd.setText(SharedPref.getInstance(context).getString(Comment.PrefKey.USER_PWD,""));
    }

    @Override
    public void onClick(View v) {
        if (v == mBack)finish();
        if (v == mLogin){
            if (TextUtils.isEmpty(mUserName.getText().toString())){
                ToastUtil.showBottomShort(getString(R.string.login_user_name_no_empty));
                return;
            }
            if (TextUtils.isEmpty(mUserPwd.getText().toString())){
                ToastUtil.showBottomShort(getString(R.string.login_user_pwd_no_empty));
                return;
            }
            getP().login(mUserName.getText().toString(),mUserPwd.getText().toString());
        }
    }

    @Override
    public void onBackPressed() {
        exitApp();
    }

    public void login(LoginResult result,String pwd){
        SharedPref.getInstance(context).putString(Comment.PrefKey.USER_NAME,result.getName());
        SharedPref.getInstance(context).putString(Comment.PrefKey.USER_PWD,pwd);

        Router.newIntent(context)
                .to(MainActivity.class)
                .launch();

        finish();
    }
}
