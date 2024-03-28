// MyAidlInterface.aidl
package com.glc.service;
import com.glc.service.ICallBack;


interface MyAidlInterface {
      //求和
        int getAdd(int num1,int num2);
        //获取结果字符串通过callBack返回
        void getAddCallBack(int num1,int num2,ICallBack callBack);
}