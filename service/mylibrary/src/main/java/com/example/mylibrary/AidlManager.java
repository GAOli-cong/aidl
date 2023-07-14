package com.example.mylibrary;

import static android.content.Context.BIND_AUTO_CREATE;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.glc.service.MyAidlInterface;

/**
 * @description: 统一管理
 * @date: 2023/7/14 16:03
 * @author: gaolicong
 * @email: gaolicong6@gmail.com
 */
public class AidlManager {
    private static final String TAG = "AidlManager";
    private Context mContext;

    private MyAidlInterface myAidlService;

    private IBinder b;
    private static AidlManager mInstance;
    private boolean connected;

    private AidlManager(Context context) {
        mContext = context.getApplicationContext();
        bindService(connection);
    }

    public static synchronized AidlManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new AidlManager(context);
        }
        return mInstance;
    }

    public void unBind() {
        if (connected) {
            mContext.unbindService(connection);
        }
    }

    public MyAidlInterface getMyAidlService() {
        if (myAidlService != null) {
            return myAidlService;
        }
        myAidlService = MyAidlInterface.Stub.asInterface(b);
        return myAidlService;
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: 绑定成功");
            b = service;
            connected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: 绑定失败");
            connected = false;
        }
    };


    private void bindService(ServiceConnection serviceConnection) {
        Log.d(TAG, "开始绑定服务bindService: ");
        Intent intent = new Intent();
        //包名、类名
        intent.setComponent(new ComponentName("com.glc.service", "com.glc.service.MyService"));
        mContext.bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }
}
