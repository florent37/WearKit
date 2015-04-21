package com.github.florent37.wearkit.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.florent37.R;
import com.github.florent37.wearkit.utils.FastBlurHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by florentchampigny on 16/04/15.
 */
public class ContextualMenu extends RelativeLayout {

    public interface OnMenuClickListener {
        public void onMenuClick(int position);
    }

    private OnMenuClickListener onMenuClickListener;

    List<Drawable> mDrawables = new ArrayList<>();
    List<String> mNames = new ArrayList<>();

    View table;
    ImageView background;
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
    }

    public ContextualMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ContextualMenu(Context context, AttributeSet attrs, int defStyleAttr,
                          int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setMenuEntries(String[] names, Drawable[] drawables) {
        if (names != null && drawables != null)
            setMenuEntries(Arrays.asList(names), Arrays.asList(drawables));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        table = findViewById(R.id.wearkit_menu_table);
        background = (ImageView) findViewById(R.id.wearkit_menu_background);
        button1 = findViewById(R.id.wearkit_menu_row_1_cell_1);
        button2 = findViewById(R.id.wearkit_menu_row_1_cell_2);
        button3 = findViewById(R.id.wearkit_menu_row_2_cell_1);
        button4 = findViewById(R.id.wearkit_menu_row_2_cell_2);

        table.setVisibility(GONE);
        //avoid scroll behind this view
        table.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        if (isInEditMode()) {
            setMenuEntries(new String[]{
                    "Accept",
                    "Accept",
                    "Accept",
                    "Accept"
            }, new Drawable[]{
                    getContext().getDrawable(R.drawable.wearkit_menu_accept),
                    getContext().getDrawable(R.drawable.wearkit_menu_accept),
                    getContext().getDrawable(R.drawable.wearkit_menu_accept),
                    getContext().getDrawable(R.drawable.wearkit_menu_accept)
            });
        }
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
                    TextView textView = (TextView) button1.findViewById(android.R.id.text1);
                    textView.setText(names.get(0));
                    ImageButton button = (ImageButton) button1.findViewById(android.R.id.icon);
                    button.setImageDrawable(mDrawables.get(0));
                    button.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            toggle();
                            if (onMenuClickListener != null)
                                onMenuClickListener.onMenuClick(0);
                        }
                    });
                }
                if (count >= 2) {
                    TextView textView = (TextView) button2.findViewById(android.R.id.text1);
                    textView.setText(names.get(1));
                    ImageButton button = (ImageButton) button2.findViewById(android.R.id.icon);
                    button.setImageDrawable(mDrawables.get(1));
                    button.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            toggle();
                            if (onMenuClickListener != null)
                                onMenuClickListener.onMenuClick(1);
                        }
                    });
                }
                if (count >= 3) {
                    TextView textView = (TextView) button3.findViewById(android.R.id.text1);
                    textView.setText(names.get(2));
                    ImageButton button = (ImageButton) button3.findViewById(android.R.id.icon);
                    button.setImageDrawable(mDrawables.get(2));
                    button.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            toggle();
                            if (onMenuClickListener != null)
                                onMenuClickListener.onMenuClick(2);
                        }
                    });
                }
                if (count >= 4) {
                    TextView textView = (TextView) button4.findViewById(android.R.id.text1);
                    textView.setText(names.get(3));
                    ImageButton button = (ImageButton) button4.findViewById(android.R.id.icon);
                    button.setImageDrawable(mDrawables.get(3));
                    button.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            toggle();
                            if (onMenuClickListener != null)
                                onMenuClickListener.onMenuClick(3);
                        }
                    });
                }
            }
        }

    }

    public void toggle() {
        if (table.getVisibility() == VISIBLE) {
            table.animate().alpha(0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    table.setVisibility(GONE);
                    background.setBackground(new ColorDrawable(Color.TRANSPARENT));
                }
            }).start();
        } else {
            background.setBackground(new BitmapDrawable(FastBlurHelper.doBlur(getBitmapFromView((View) getParent()), 10, false)));
            table.animate().alpha(1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    table.setVisibility(VISIBLE);

                }
            }).start();
        }
    }

    public static Bitmap getBitmapFromView(View view) {
        view.setDrawingCacheEnabled(true);

        Bitmap returnedBitmap = Bitmap.createBitmap(view.getDrawingCache());

        view.setDrawingCacheEnabled(false);
        return returnedBitmap;
    }

    public static ContextualMenu findFrom(Context context) {
        if (context != null) {
            return findFrom(((Activity) context).getWindow().getDecorView());
        } else
            return null;
    }

    public static ContextualMenu findFrom(View v) {
        if (v instanceof ContextualMenu)
            return (ContextualMenu) v;
        else if (v instanceof ViewGroup) {
            ViewGroup viewGroup = ((ViewGroup) v);
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; ++i) {
                View childView = findFrom(viewGroup.getChildAt(i));
                if (childView != null)
                    return (ContextualMenu) childView;
            }
        }
        return null;
    }

    public static void toggleFromContext(Context context) {
        if (context != null) {
            ContextualMenu contextualMenu = findFrom(context);
            if (contextualMenu != null) {
                contextualMenu.toggle();
            }
        }
    }

    public OnMenuClickListener getOnMenuClickListener() {
        return onMenuClickListener;
    }

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        this.onMenuClickListener = onMenuClickListener;
    }
}
