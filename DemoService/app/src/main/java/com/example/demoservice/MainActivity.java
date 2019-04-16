package com.example.demoservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent firstIntent = new Intent(this, FirstService.class);
        startService(firstIntent);

//        Intent secondIntent = new Intent(this, SecondService.class);
//        startService(secondIntent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(Constants.ACTION_SEND);
        registerReceiver(mReceiver, intentFilter);

    }

    @Override
    protected void onPause() {
        unregisterReceiver(mReceiver);
        super.onPause();
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constants.ACTION_SEND)) {
                Log.d(TAG, "onReceive: from onTick");
                String message = intent.getStringExtra(Constants.EXTRA_MESSAGE);
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        }
    };
}
