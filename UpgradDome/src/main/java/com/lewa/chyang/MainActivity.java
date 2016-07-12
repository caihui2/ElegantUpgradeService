package com.lewa.chyang;

import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lewa.bl.IUpgradeStateListen;
import com.lewa.chyang.bspapilib.ServiceHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ServiceHelper mServiceHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mServiceHelper = new ServiceHelper();
        mServiceHelper.bindToService(this);
        findViewById(R.id.call_install).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.call_install) {
            mServiceHelper.UpgradeApk("com.sina.weibo", false);
        }
    }
}
