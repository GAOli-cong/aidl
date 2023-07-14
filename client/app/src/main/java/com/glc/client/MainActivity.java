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
import android.widget.Toast;

import com.example.mylibrary.AidlManager;
import com.glc.client.databinding.ActivityMainBinding;
import com.glc.service.MyAidlInterface;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AidlManager aidlManager = AidlManager.getInstance(getApplicationContext());

        //获取结果按钮
        binding.btnGetResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int add = aidlManager.getMyAidlService().getAdd(1, 1);
                    binding.tvResult.setText("结果：" + add);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}