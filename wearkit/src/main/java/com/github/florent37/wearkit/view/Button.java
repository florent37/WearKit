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
public class Button extends FrameLayout {

    protected String text = "";
    protected int textColor = -1;

    protected TextView textView;

    protected void addTextView(){
        if(textView == null) {
            addView(LayoutInflater.from(getContext()).inflate(R.layout.wearkit_button, this, false));
            textView = (TextView) findViewById(android.R.id.text1);
            textView.setText(text);

            if (textColor != -1)
                textView.setTextColor(textColor);
        }
    }

    private void handleAtts(Context context, AttributeSet attrs) {
        try {
            TypedArray a = context.obtainStyledAttributes(attrs, new int[]{
                    android.R.attr.text,        // idx 0
                    android.R.attr.textColor        // idx 1
            });
            text = a.getText(0).toString();
            textColor = a.getInt(1, -1);
            a.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Button(Context context) {
        super(context);
        addTextView();
    }

    public Button(Context context, AttributeSet attrs) {
        super(context, attrs);
        handleAtts(context, attrs);
        addTextView();
    }

    public Button(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Button(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setText(String title) {
        if(textView != null)
            textView.setText(title);
    }
}
