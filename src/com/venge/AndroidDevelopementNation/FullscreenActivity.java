package com.venge.AndroidDevelopementNation;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;
import com.venge.AndroidDevelopementNation.util.SystemUiHider;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;



public class FullscreenActivity extends SlidingFragmentActivity implements AdapterView.OnItemClickListener
{

    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;


    private SystemUiHider mSystemUiHider;
    
    public ListView lv = (ListView) findViewById(R.id.sliding_menu);


    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);
        setBehindContentView(R.layout.slidingmenu);

        final View controlsView = findViewById(R.id.fullscreen_content_controls);
        final View contentView = findViewById(R.id.fullscreen_content);
        
        
        //Sliding Menu Customizations
        SlidingMenu menu = getSlidingMenu();
        menu.setBehindOffsetRes(R.dimen.slideout);
        menu.setBackgroundColor(Color.BLACK);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setFadeEnabled(true);
        menu.setFadeDegree(0.5f);
        
        //Setup shop for navigation menu
        lv.setOnItemClickListener(this);
        
        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.
        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
        mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    // Cached values.
                    int mControlsHeight;
                    int mShortAnimTime;

                    @Override
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
                    public void onVisibilityChange(boolean visible) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                            // If the ViewPropertyAnimator API is available
                            // (Honeycomb MR2 and later), use it to animate the
                            // in-layout UI controls at the bottom of the
                            // screen.
                            if (mControlsHeight == 0) {
                                mControlsHeight = controlsView.getHeight();
                            }
                            if (mShortAnimTime == 0) {
                                mShortAnimTime = getResources().getInteger(
                                        android.R.integer.config_shortAnimTime);
                            }
                            controlsView.animate()
                                    .translationY(visible ? 0 : mControlsHeight)
                                    .setDuration(mShortAnimTime);
                        } else {
                            // If the ViewPropertyAnimator APIs aren't
                            // available, simply show or hide the in-layout UI
                            // controls.
                            controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
                        }

                        if (visible && AUTO_HIDE) {
                            // Schedule a hide().
                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });
    }
    
	public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
	{
		if (p3 == 0) {
			//devarea
		}
		else if (p3 == 1){
			//devtalk
		}
		else if (p3 == 2) {
			//about
		}
		else if (p3 == 3) {
			//settings
		}
	}
    
	public void transformCanvas(Canvas canvas, float percentOpen) {
		float scale = (float) (percentOpen*0.25 + 0.75);
		canvas.scale(scale, scale, canvas.getWidth()/2, canvas.getHeight()/2);
	}



	@Override
	public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        delayedHide(100);
    }
    
    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
        }
    };


    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
