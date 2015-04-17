package com.github.florent37;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.wearkit.view.ContextualMenu;
import com.github.florent37.wearkit.view.Pager;


public class MainActivity extends FragmentActivity {

    private Pager viewPager;
    private GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (Pager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new Fragment() {
                    @Override
                    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                        return inflater.inflate(R.layout.page, container, false);
                    }
                };
            }

            @Override
            public int getCount() {
                return 10;
            }
        });

        final ContextualMenu contextualMenu = ((ContextualMenu) findViewById(R.id.menu));
        if (contextualMenu != null)
            contextualMenu.setMenuEntries(new String[]{
                    "Accept",
                    "Decline"
            }, new Drawable[]{
                    getDrawable(R.drawable.wearkit_menu_accept),
                    getDrawable(R.drawable.wearkit_menu_decline)
            });

    }

}
