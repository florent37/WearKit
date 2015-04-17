package com.tutosandroidfrance.wearsample;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.wearkit.Actions;
import com.github.florent37.wearkit.view.Page;

/**
 * Created by florentchampigny on 17/04/15.
 */
public class CustomPage extends Page {
    @Override
    public View onCreatePageContent(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.content2, container, false);
    }

    @Override
    public Actions onCreatePageActions() {
        return new Actions(new String[]{"show", "don't show"}, false);
    }

    @Override
    protected void clickedOnAction(int position) {

    }
}
