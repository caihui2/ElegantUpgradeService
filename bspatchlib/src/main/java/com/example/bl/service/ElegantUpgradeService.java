package com.example.bl.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ElegantUpgradeService extends Service {


    public ElegantUpgradeService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
