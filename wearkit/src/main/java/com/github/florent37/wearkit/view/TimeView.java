package com.github.florent37.wearkit.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;

import com.github.florent37.wearkit.utils.TypefacedTextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by florentchampigny on 16/04/15.
 */
public class TimeView extends TypefacedTextView {

    private static final String TAG = TimeView.class.getSimpleName();

    private static Calendar calendar = Calendar.getInstance();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("H:mm");

    private String getCurrentDate(){
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    public TimeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        updateDateText();
    }

    private void updateDateText(){
        setText(getCurrentDate());
        Log.d(TAG,"updateDateText");
    }

    Timer timer = new Timer("TimeTimer", true);

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        updateDateText();
                    }
                });
            }
        }, (60 - calendar.get(Calendar.SECOND)) * 1000, 1000 * 60);
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        timer.cancel();
    }
}
