package com.romantic.dreamaccount.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.romantic.dreamaccount.R;
import com.romantic.dreamaccount.adapter.AddAccountKindAdapter;
import com.romantic.dreamaccount.application.MyApplication;
import com.romantic.dreamaccount.config.Comment;
import com.romantic.dreamaccount.db.AccountsBean;
import com.romantic.dreamaccount.bean.KindResult;
import com.romantic.dreamaccount.eventBus.RefreshEvent;
import com.romantic.dreamaccount.present.ui.AddAccountP;
import com.romantic.dreamaccount.service.LocationService;
import com.romantic.dreamaccount.util.ToastUtil;
import com.romantic.dreamaccount.view.keyboard.KeyboardUtil;
import com.romantic.dreamaccount.view.keyboard.MyKeyBoardView;
import com.sensology.framelib.cache.SharedPref;
import com.sensology.framelib.event.BusProvider;
import com.sensology.framelib.kit.Kits;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AddAccountActivity extends BaseActivity<AddAccountP> implements AddAccountKindAdapter.OnCallBackKind, KeyboardUtil.OnKeyBoardCallBack {
    @BindView(R.id.back)
    public LinearLayout mBack;
    @BindView(R.id.recyclerView)
    public RecyclerView mRecyclerView;
    private AddAccountKindAdapter mAdapter;
    @BindView(R.id.expenses)
    public TextView mExpenses;
    @BindView(R.id.income)
    public TextView mIncome;
    @BindView(R.id.note)
    public EditText mNote;
    @BindView(R.id.keyboard)
    public MyKeyBoardView mKeyboard;
    @BindView(R.id.money)
    public EditText mMoney;

    private int type = 1;// 1 expenses; 0 income
    private List<KindResult.Data> mKindList = new ArrayList<>();
    private int currKindPosition = 0;
    private LocationService locationService;
    private KeyboardUtil mKeyUtil;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_account;
    }

    @Override
    public AddAccountP newP() {
        return new AddAccountP();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mBack.setOnClickListener(this);
        mExpenses.setOnClickListener(this);
        mIncome.setOnClickListener(this);
        mExpenses.setSelected(true);
        mIncome.setSelected(false);

        initKindRecycleView();

        mKeyUtil = new KeyboardUtil(this, mKeyboard);
        mKeyUtil.setCallBack(this);
        mKeyUtil.attachTo(mMoney);

        KindResult result = SharedPref.getInstance(context).getPreferences(Comment.PrefKey.KIND_DATA);

        if (result != null && result.getData() != null && result.getData().size()>0){
            mKindList = result.getData();
            mAdapter.setData(getKindData(type));
        }
        getP().getKind();
    }

    private void initKindRecycleView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(context, 5));
        mAdapter = new AddAccountKindAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initLocationService();
    }

    @Override
    protected void onStop() {
        locationService.unregisterListener(mLocationListener);
        locationService.stop();
        super.onStop();
    }

    /**
     * init location
     */
    private void initLocationService() {
        locationService = MyApplication.getInstance().locationService;
        locationService.registerListener(mLocationListener);
        //注册监听
        int type = getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }
        locationService.start();// 定位SDK
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == mBack) finish();
        if (v == mExpenses) {
            if (mExpenses.isSelected()) return;
            mExpenses.setSelected(!mExpenses.isSelected());
            mIncome.setSelected(!mExpenses.isSelected());
            type = 1;
            mAdapter.setData(getKindData(type));
        }
        if (v == mIncome) {
            if (mIncome.isSelected()) return;
            mIncome.setSelected(!mIncome.isSelected());
            mExpenses.setSelected(!mIncome.isSelected());
            type = 0;
            mAdapter.setData(getKindData(type));
        }
    }

    public void getKindSuccess(KindResult result) {
        mKindList.clear();
        if (result.getData().size() > 0) {
            for (int i = 0, len = result.getData().size(); i < len; i++) {
                result.getData().get(i).setSelect(i == 0);
            }

            mKindList = result.getData();
            SharedPref.getInstance(context).setPreferences(Comment.PrefKey.KIND_DATA,result);
        }
        mAdapter.setData(getKindData(type));
    }

    private List<KindResult.Data> getKindData(int type) {
        List<KindResult.Data> list = new ArrayList<>();
        int i = 0;
        for (KindResult.Data data : mKindList) {//2 other all expenses and income
            if (data.getType() == type || data.getType() == 2) {
                data.setSelect(i == 0);
                list.add(data);
                i++;
            }
        }
        return list;
    }


    @Override
    public void onClickKindListener(int position) {
        currKindPosition = position;
        for (int i = 0, len = mAdapter.getData().size(); i < len; i++) {
            mAdapter.getData().get(i).setSelect(position == i);
        }
        mAdapter.notifyDataSetChanged();
    }

    private BDLocationListener mLocationListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (null != bdLocation && bdLocation.getLocType() != BDLocation.TypeServerError) {
                MyApplication.getInstance().lat = bdLocation.getLatitude();
                MyApplication.getInstance().lng = bdLocation.getLongitude();
                MyApplication.getInstance().address = bdLocation.getAddrStr();
            }
        }
    };

    @Override
    public void onClickSure() {
        if (Kits.Empty.check(mNote.getText().toString())){
            mNote.setFocusable(true);
            mNote.setCursorVisible(true);
            return;
        }

        if (Kits.Empty.check(mMoney.getText().toString())){
            ToastUtil.showBottomShort(getString(R.string.please_input_money));
            return;
        }

        AccountsBean bean = new AccountsBean();
        bean.setType(type);
        bean.setMoney(Double.parseDouble(mMoney.getText().toString()));
        bean.setKind(mAdapter.getData().get(currKindPosition).getKind());
        bean.setNote(mNote.getText().toString());
        bean.setTime(Kits.Date.getCurrentTime());
        bean.setLat(MyApplication.getInstance().lat);
        bean.setLng(MyApplication.getInstance().lng);
        bean.setAddress(MyApplication.getInstance().address);

        MyApplication.getInstance().Db.insert(bean);

        BusProvider.getBus().post(new RefreshEvent(true));

        finish();
    }

    @Override
    public void onClickCancel() {
    }
}
