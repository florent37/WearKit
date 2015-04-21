package com.github.florent37.wearkit.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.florent37.R;

/**
 * Created by florentchampigny on 16/04/15.
 */
public class StatusBarView extends FrameLayout implements View.OnClickListener {

    int mTitleColor = -1;
    boolean mBackEnabled = false;

    View mBackView;
    TextView mTitle;
    TextView mTimeView;

    private void handleAttributes(Context context, AttributeSet attrs) {
        TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.StatusBarView);
        try {
            mTitleColor = styledAttrs.getColor(R.styleable.StatusBarView_titleColor, -1);
            mBackEnabled = styledAttrs.getBoolean(R.styleable.StatusBarView_backEnabled, false);
        } finally {
            styledAttrs.recycle();
        }

        addView(LayoutInflater.from(getContext()).inflate(R.layout.wearkit_statusbar_view, this, false));
    }

    public StatusBarView(Context context) {
        super(context);
    }

    public StatusBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        handleAttributes(context, attrs);
    }

    public StatusBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StatusBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mBackView = findViewById(R.id.wearkit_statusbar_back);
        mTitle = (TextView) findViewById(R.id.wearkit_statusbar_title);
        mTimeView = (TextView) findViewById(R.id.wearkit_statusbar_timeView);

        String title = mTitle.getText().toString();

        if(!isInEditMode())
            title = ((Activity) getContext()).getTitle().toString();

        //update
        this.setBackEnabled(mBackEnabled);
        this.setTitle(title);
        this.setTitleColor(mTitleColor);
    }

    public int getTitleColor() {
        return mTitleColor;
    }

    public void setTitleColor(int titleColor) {
        this.mTitleColor = titleColor;
        if (mTitleColor != -1)
            mTitle.setTextColor(mTitleColor);
    }

    public boolean isBackEnabled() {
        return mBackEnabled;
    }

    public void setBackEnabled(boolean backEnabled) {
        this.mBackEnabled = backEnabled;

        if (mBackEnabled) {
            mBackView.setVisibility(VISIBLE);
            setOnClickListener(this);
        }else{
            mBackView.setVisibility(GONE);
            setOnClickListener(null);
        }
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void onClick(View v) {
        if(mBackEnabled)
            ((Activity) getContext()).finish();
    }
}
