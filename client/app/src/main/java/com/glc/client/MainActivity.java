package com.glc.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.glc.client.databinding.ActivityMainBinding;
import com.glc.service.MyAidlInterface;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private  MyAidlInterface myAidlInterface;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //绑定Aidl服务
        bindMyService();


        //获取结果按钮
        binding.btnGetResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int add = myAidlInterface.getAdd(1, 1);
                    binding.tvResult.setText("结果："+add);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void bindMyService() {
        Log.d(TAG, "bindMyService: ");
        Intent intent=new Intent();
        //包名、类名
        intent.setComponent(new ComponentName("com.glc.service","com.glc.service.MyService"));
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d(TAG, "onServiceConnected: ");
                 myAidlInterface = MyAidlInterface.Stub.asInterface(iBinder);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(TAG, "onServiceDisconnected: ");
                myAidlInterface=null;
            }
        },BIND_AUTO_CREATE);
    }


}