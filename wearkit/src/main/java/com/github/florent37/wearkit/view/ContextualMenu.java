package com.github.florent37.wearkit.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.github.florent37.R;
import com.github.florent37.wearkit.utils.FastBlurHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by florentchampigny on 16/04/15.
 */
public class ContextualMenu extends RelativeLayout implements View.OnLongClickListener {

    List<Drawable> mDrawables = new ArrayList<>();
    List<String> mNames = new ArrayList<>();

    View table;
    View button1;
    View button2;
    View button3;
    View button4;

    public ContextualMenu(Context context) {
        super(context);
    }

    public ContextualMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

        addView(LayoutInflater.from(context).inflate(R.layout.wearkit_menu, this, false));

        if (isInEditMode()) {
            setMenuEntries(new String[]{
                    "Accept",
                    "Accept",
                    "Accept"
            }, new Drawable[]{
                    getContext().getDrawable(R.drawable.wearkit_menu_accept),
                    getContext().getDrawable(R.drawable.wearkit_menu_accept),
                    getContext().getDrawable(R.drawable.wearkit_menu_accept)
            });
        }

    }

    public ContextualMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ContextualMenu(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setMenuEntries(String[] names, Drawable[] drawables) {
        if (names != null && drawables != null)
            setMenuEntries(Arrays.asList(names), Arrays.asList(drawables));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        setOnLongClickListener(this);

        table = findViewById(R.id.wearkit_menu_table);
        button1 = findViewById(R.id.wearkit_menu_row_1_cell_1);
        button2 = findViewById(R.id.wearkit_menu_row_1_cell_2);
        button3 = findViewById(R.id.wearkit_menu_row_2_cell_1);
        button4 = findViewById(R.id.wearkit_menu_row_2_cell_2);

        table.setVisibility(GONE);

    }

    public void setMenuEntries(List<String> names, List<Drawable> drawables) {
        if (names != null && drawables != null) {

            this.mDrawables = drawables;
            this.mNames = names;

            int count = Math.min(mNames.size(), mDrawables.size());
            {
                if (count <= 3)
                    button4.setVisibility(GONE);
                if (count <= 2)
                    findViewById(R.id.wearkit_menu_row_2).setVisibility(GONE);
                if (count <= 1) {
                    button2.setVisibility(GONE);
                }

                if (count >= 1) {
                    ImageButton button = (ImageButton) button1.findViewById(android.R.id.icon);
                    button.setImageDrawable(mDrawables.get(0));
                    button.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            toggle();
                        }
                    });
                }
                if (count >= 2) {
                    ImageButton button = (ImageButton) button2.findViewById(android.R.id.icon);
                    button.setImageDrawable(mDrawables.get(1));
                    button.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            toggle();
                        }
                    });
                }
                if (count >= 3) {
                    ImageButton button = (ImageButton) button3.findViewById(android.R.id.icon);
                    button.setImageDrawable(mDrawables.get(2));
                    button.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            toggle();
                        }
                    });
                }
                if (count >= 4) {
                    ImageButton button = (ImageButton) button4.findViewById(android.R.id.icon);
                    button.setImageDrawable(mDrawables.get(3));
                    button.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            toggle();
                        }
                    });
                }
            }
        }

    }

    public void toggle() {

        if (table.getVisibility() == VISIBLE){
            table.animate().alpha(0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    table.setVisibility(GONE);
                    //setBackground(new ColorDrawable(Color.TRANSPARENT));
                }
            }).start();
        }else {
            //setBackground(new BitmapDrawable(FastBlurHelper.doBlur(getBitmapFromView((View)getParent()),10,false)));
            table.animate().alpha(1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    table.setVisibility(VISIBLE);

                }
            }).start();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        toggle();
        return true;
    }

    public static Bitmap getBitmapFromView(View view) {
        view.setDrawingCacheEnabled(true);

        Bitmap returnedBitmap = Bitmap.createBitmap(view.getDrawingCache());

        view.setDrawingCacheEnabled(false);
        return returnedBitmap;
    }
}
