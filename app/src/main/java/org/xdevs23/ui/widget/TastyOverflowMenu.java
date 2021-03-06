package org.xdevs23.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import org.xdevs23.android.app.XquidCompatActivity;

import io.xdevs23.cornowser.browser.R;

public class TastyOverflowMenu extends ImageButton {

    public int
        mainColor    = Color.BLACK,
        pressedColor = Color.BLACK;

    private Context ccontext;

    public TastyOverflowMenu(Context context) {
        super(context);
        init(context);
    }

    public TastyOverflowMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TastyOverflowMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TastyOverflowMenu(Context context, AttributeSet attrs, int defStyleAttrs, int defStyleRes) {
        super(context, attrs, defStyleAttrs, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        final Context fContext = context;
        ccontext = context;
        pressedColor = ContextCompat.getColor(fContext, R.color.dark_semi_more_transparent);

        int ah = (int)XquidCompatActivity.dp2px(context, 32);
        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(
                        ah,
                        ah);

        this.setLayoutParams(params);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_overflow_default);

        // Tree dot overflow menu icon from UltimateBrowserProject
        this.setImageBitmap(Bitmap.createScaledBitmap(bitmap, ah, ah, false));

        this.setColorFilter(ContextCompat.getColor(context, R.color.black),
                PorterDuff.Mode.MULTIPLY);
        this.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.full_transparent));
        this.setMaxHeight(ah);
        this.setMaxWidth(ah);

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_HOVER_ENTER:
                    case MotionEvent.ACTION_DOWN:
                        ((ImageButton)v).setColorFilter(pressedColor,
                                PorterDuff.Mode.MULTIPLY);
                        break;
                    case MotionEvent.ACTION_HOVER_EXIT:
                    case MotionEvent.ACTION_OUTSIDE:
                    case MotionEvent.ACTION_UP:
                        ((ImageButton)v).setColorFilter(mainColor,
                                PorterDuff.Mode.MULTIPLY);
                        break;
                    default: break;
                }
                return false;
            }
        });
    }

    public void setMainColor(int color) {
        mainColor = color;
        setColorFilter(mainColor, PorterDuff.Mode.MULTIPLY);
    }

    public void setPressedColor(int color) {
        pressedColor = color;
    }

    public void applyLightTheme() {
        setPressedColor(ContextCompat.getColor(ccontext, R.color.white_semi_more_transparent));
        setMainColor(Color.WHITE);
    }

    public void applyDarkTheme() {
        setMainColor(Color.BLACK);
        setPressedColor(ContextCompat.getColor(ccontext, R.color.dark_semi_more_transparent));
    }

    public void applyTheme(boolean theme) {
        if(theme) applyLightTheme();
        else applyDarkTheme();
    }

}
