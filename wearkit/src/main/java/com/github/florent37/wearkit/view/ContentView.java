package com.github.florent37.wearkit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
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
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if(getHeight()<getWidth()){
                    ViewGroup.LayoutParams params = getLayoutParams();
                    params.height = getMeasuredWidth();
                    setLayoutParams(params);
                }
                return true;
            }
        });
    }

    public ContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ContentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onLongClick(View v) {
        ContextualMenu.toggleFromContext(getContext());
        return false;
    }

}
