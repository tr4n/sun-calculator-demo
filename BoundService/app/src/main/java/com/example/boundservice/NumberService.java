package com.example.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class NumberService extends Service {

    private final IBinder mBinder = new NumberBinder();
    private final Random mGenerator = new Random();

    public NumberService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return mBinder;
    }

    public int getRandomNumber(){
        return mGenerator.nextInt(100);
    }

    public class NumberBinder extends Binder {
        NumberService getService(){
            return NumberService.this;
        }
    }
}
