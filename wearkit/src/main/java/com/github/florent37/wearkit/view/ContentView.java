package com.github.florent37.wearkit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by florentchampigny on 16/04/15.
 */
public class ContentView extends FrameLayout implements View.OnLongClickListener {

    public ContentView(Context context) {
        super(context);
    }

    public ContentView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOnLongClickListener(this);
    }

    public ContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ContentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private int widthSize = -1;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if(widthSize == -1) {
            widthSize = MeasureSpec.getSize(widthMeasureSpec);
            setMinimumHeight(widthSize);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        ContextualMenu.toggleFromContext(getContext());
        return false;
    }

}
