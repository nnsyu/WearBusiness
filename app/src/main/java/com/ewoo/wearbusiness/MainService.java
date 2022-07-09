package com.ewoo.wearbusiness;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MainService extends Service {
    private final String TAG = "MainService";

    private final MainBinder mainBinder = new MainBinder();

    private Context mContext;
    private MainActivity mainActivity;

    private SharedPreferenceManager pref;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate !!");
    }

    public void setContext(Context context) {
        Log.d(TAG, "setContext !!");
        mContext = context;
        mainActivity = (MainActivity)mContext;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind !!");
        return mainBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand !!");

        return Service.START_STICKY;
    }

    public class MainBinder extends Binder {
        public MainService getService() { return MainService.this; }
        public void setContext(Context context) {
            mContext = context;
            mainActivity = (MainActivity) mContext;

            // 여기에서 하는게 맞을까나
            pref = SharedPreferenceManager.getInstance(mContext);
        }
    }

}
