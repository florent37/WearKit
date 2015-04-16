package com.github.florent37.wearkit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.florent37.R;

/**
 * Created by florentchampigny on 16/04/15.
 */
public class DismissButton extends FrameLayout {

    private String text = "Dismiss";
    private TextView textView;

    private void handleAtts(Context context, AttributeSet attrs) {
        try {
            TypedArray a = context.obtainStyledAttributes(attrs, new int[]{
                    android.R.attr.text        // idx 0
            });
            text = a.getText(0).toString();
            a.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        addView(LayoutInflater.from(getContext()).inflate(R.layout.wearkit_button_dismiss, this, false));
        textView = (TextView) findViewById(android.R.id.text1);
        textView.setText(text);
    }

    public DismissButton(Context context) {
        super(context);
    }

    public DismissButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        handleAtts(context,attrs);
    }

    public DismissButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DismissButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}
