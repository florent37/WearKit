package com.github.florent37.wearkit.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.github.florent37.R;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by florentchampigny on 16/04/15.
 */
public class Pager extends FrameLayout {

    private ViewPager viewPager;
    private CirclePageIndicator pageIndicator;
    private ContextualMenu contextualMenu;

    private void handleAtts(Context context, AttributeSet attrs) {
        addView(LayoutInflater.from(getContext()).inflate(R.layout.wearkit_viewpager, this, false));
    }

    public Pager(Context context) {
        super(context);
    }

    public Pager(Context context, AttributeSet attrs) {
        super(context, attrs);
        handleAtts(context, attrs);
    }

    public Pager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Pager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        viewPager = (ViewPager) findViewById(R.id.wearkit_viewPager);
        pageIndicator = (CirclePageIndicator) findViewById(R.id.wearkit_circle_pager_indicator);
    }

    public void setAdapter(FragmentStatePagerAdapter adapter) {
        if (viewPager != null)
            viewPager.setAdapter(adapter);
        pageIndicator.setViewPager(viewPager);
    }

}
