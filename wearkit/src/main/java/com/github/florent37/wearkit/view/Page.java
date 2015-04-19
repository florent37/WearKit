package com.github.florent37.wearkit.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

import com.github.florent37.R;
import com.github.florent37.wearkit.Actions;


/**
 * Created by florentchampigny on 17/04/15.
 */
public abstract class Page extends Fragment {
    ScrollView pageScroll;
    ViewGroup mainContent;
    ViewGroup secondaryContent;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wearkit_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pageScroll = (ScrollView) view.findViewById(R.id.page_scroll);

        mainContent = (ViewGroup) view.findViewById(R.id.page_content);
        mainContent.addView(onCreatePageContent(getLayoutInflater(savedInstanceState),mainContent));
        mainContent.requestLayout();

        secondaryContent = (ViewGroup) view.findViewById(R.id.page_actions);
        secondaryContent.addView(onCreatePageSecondaryContent(getLayoutInflater(savedInstanceState),secondaryContent));
        secondaryContent.requestLayout();
    }

    public abstract View onCreatePageContent(LayoutInflater inflater, @Nullable ViewGroup container);
    public abstract View onCreatePageSecondaryContent(LayoutInflater inflater, @Nullable ViewGroup container);
}
