package com.lewa.chyang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lewa.chyang.bspapilib.ServiceHelper;
import com.lewa.chyang.bspapilib.entity.UpgradeInfo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ServiceHelper mServiceHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mServiceHelper = new ServiceHelper();
        mServiceHelper.bindToService(this);
        mServiceHelper.setRequestUpgradeInfoListen(mRequestUpgradeInfoListen);
        findViewById(R.id.call_install).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.call_install) {
            mServiceHelper.toAcquireUpgradeInfo("com.sina.weibo");
        }
    }

    private ServiceHelper.RequestUpgradeInfoListen mRequestUpgradeInfoListen = new ServiceHelper.RequestUpgradeInfoListen() {
        @Override
        public void toAcquireUpgradeInfoError(String errorInfo) {

        }

        @Override
        public void toAcquireUpgradeInfoComplete(UpgradeInfo upgradeInfo) {
            System.out.println(upgradeInfo);
        }
    };
}
