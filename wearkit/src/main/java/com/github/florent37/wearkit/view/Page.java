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
public abstract class Page extends Fragment implements View.OnClickListener {

    private static final String TAG_DISMISS = "DISMISS";

    ScrollView pageScroll;
    ViewGroup pageContent;
    ViewGroup pageActions;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wearkit_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pageScroll = (ScrollView) view.findViewById(R.id.page_scroll);

        pageContent = (ViewGroup) view.findViewById(R.id.page_content);
        pageContent.addView(onCreatePageContent(getLayoutInflater(savedInstanceState),pageContent));
        pageContent.requestLayout();

        pageActions = (ViewGroup) view.findViewById(R.id.page_actions);
        Actions actions = onCreatePageActions();
        if(actions != null){
            int count = actions.getActionsNames().size();
            for(int i=0;i<count;++i){
                String title = actions.getActionsNames().get(i);
                if(title != null){
                    Button button = (Button) LayoutInflater.from(getActivity()).inflate(R.layout.wearkit_button,pageActions,false);
                    pageActions.addView(button);

                    button.setText(title);
                    button.setTag(i);
                    button.setOnClickListener(this);
                }
            }
            if(actions.isCancelButtonEnabled()){
                Button dismissButton = (Button) LayoutInflater.from(getActivity()).inflate(R.layout.wearkit_button_dismiss,pageActions,false);
                pageActions.addView(dismissButton);

                dismissButton.setText(getString(R.string.dismiss));
                dismissButton.setTag(TAG_DISMISS);
                dismissButton.setOnClickListener(this);
            }
        }else{
            pageActions.setVisibility(View.GONE);
        }
    }

    public abstract View onCreatePageContent(LayoutInflater inflater, @Nullable ViewGroup container);
    public abstract Actions onCreatePageActions();

    protected void clickedOnDismiss(){
        pageScroll.smoothScrollTo(0,0);
    }
    protected abstract void clickedOnAction(int position);

    @Override
    public void onClick(View v) {
        Object tag = v.getTag();
        if(tag != null){
            if(tag.equals(TAG_DISMISS)){
                clickedOnDismiss();
            }
            else if(tag instanceof Integer){
                Log.d("click","click");
                clickedOnAction((Integer) tag);
            }
        }
    }
}
