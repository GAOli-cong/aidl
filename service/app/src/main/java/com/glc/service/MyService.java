package com.glc.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

      return  new MyAidl();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

   private class MyAidl extends MyAidlInterface.Stub{


       @Override
       public int getAdd(int num1, int num2) throws RemoteException {
           return num1+num2;
       }

       @Override
       public void getAddCallBack(int num1, int num2, ICallBack callBack) throws RemoteException {

           String addStr =  (num1+num2)+"";
           callBack.onCallBack("getAddCallBack",addStr);
       }
   }
}