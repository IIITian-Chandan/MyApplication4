package com.example.opensource.myapplication.classes;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.Toast;

/**
 * Created by OpenSource on 4/6/2018.
 * <p>
 * Created by OpenSource on 4/6/2018.
 */

/**
 * Created by OpenSource on 4/6/2018.
 */


public class MyService extends Service {
    public static long startTime = 0L;
    public static long stopTime = 0L;
    public static long timeInMilliseconds = 0L;
    public static String x;
    long mins = 0L;
    long secs = 0L, hours = 0L;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Time Starts", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onStart(Intent intent, int startid) {

        startTime = SystemClock.uptimeMillis();

    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Time Ends", Toast.LENGTH_LONG).show();
        stopTime = SystemClock.uptimeMillis();
        timer();

    }

    public String timer() {
        timeInMilliseconds = (stopTime - startTime);
        secs = (timeInMilliseconds / 1000) % 60;
        mins = ((timeInMilliseconds / (1000 * 60)) % 60);
        hours = ((timeInMilliseconds / (1000 * 60 * 60)));
        Toast.makeText(this, String.valueOf(mins) + ":" + String.valueOf(secs), Toast.LENGTH_LONG).show();
        x = String.valueOf(hours) + ":" + String.valueOf(mins) + ":" + String.valueOf(secs);

        return x;
    }

}