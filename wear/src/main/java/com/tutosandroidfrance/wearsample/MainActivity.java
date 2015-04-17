package com.tutosandroidfrance.wearsample;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.github.florent37.wearkit.view.ContextualMenu;
import com.github.florent37.wearkit.view.Pager;


public class MainActivity extends FragmentActivity {

    private Pager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (Pager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(final int position) {
                if (position % 2 == 0) {
                    return new PageWithImage();
                } else {
                    return new CustomPage();
                }
            }

            @Override
            public int getCount() {
                return 5;
            }
        });

        ContextualMenu contextualMenu = ((ContextualMenu) findViewById(R.id.menu));
        contextualMenu.setMenuEntries(new String[]{
                "Accept",
                "Decline"
        }, new Drawable[]{
                getResources().getDrawable(R.drawable.wearkit_menu_accept),
                getResources().getDrawable(R.drawable.wearkit_menu_decline)
        });

        contextualMenu.setOnMenuClickListener(new ContextualMenu.OnMenuClickListener() {
            @Override
            public void onMenuClick(int position) {

            }
        });
    }

}
