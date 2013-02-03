package com.venge.AndroidDevelopementNation;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;


public class FullscreenActivity extends SlidingFragmentActivity implements AdapterView.OnItemClickListener
{

    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        //set layout to empty fragment container
        setContentView(R.layout.fragmentcontainer);
        setBehindContentView(R.layout.slidingmenu);
        

        ListView lv = (ListView) findViewById(R.id.sliding_menu);
        
        //Sliding Menu Customizations
        SlidingMenu menu = getSlidingMenu();
        menu.setBehindOffsetRes(R.dimen.slideout);
        menu.setBackgroundColor(Color.BLACK);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setFadeEnabled(true);
        menu.setFadeDegree(0.5f);
        setSlidingActionBarEnabled(false);
        
        //Customize the actionbar
        ActionBar abs = getSupportActionBar();
        abs.setDisplayShowHomeEnabled(true);
        abs.setHomeButtonEnabled(true);
        abs.setTitle("Dev Nation");
        
        //Setup shop for navigation Menu
        lv.setOnItemClickListener(this);
        
        //Set Content View to splash screen
        SplashFragement splash = new SplashFragement();
        FragmentTransaction fg = getSupportFragmentManager().beginTransaction();
        fg.replace(R.id.container, splash);
        fg.addToBackStack(null);
        fg.commit();
        

    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.abs_menu, menu);
        return true;
    }
    
    //On Slide Menu Click
	public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
	{
		FragmentTransaction fg = getSupportFragmentManager().beginTransaction();
		SlidingMenu menu = getSlidingMenu();
		if (p3 == 0) {
			//devarea
			devarea devarea = new devarea();
			fg.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			fg.replace(R.id.container, devarea);
			fg.addToBackStack(null);
			fg.commit();
		}
		else if (p3 == 1){
			//devtalk
			devtalk devtalk = new devtalk();
			fg.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			fg.replace(R.id.container, devtalk);
			fg.addToBackStack(null);
			fg.commit();
		}
		else if (p3 == 2) {
			//about
			about about = new about();
			fg.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			fg.replace(R.id.container, about);
			fg.addToBackStack(null);
			fg.commit();
		}
		menu.showContent();
	}
	
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

	    int itemId = item.getItemId();
	    switch (itemId) {
	    
	    //On Actionbar Home Button Click
	    case android.R.id.home:
	        toggle();
	        
	        SplashFragement splash = new SplashFragement();
	        FragmentTransaction fg = getSupportFragmentManager().beginTransaction();
	        fg.replace(R.id.container, splash);
	        fg.commit();
	        
	        //Close Sliding Menu
	        SlidingMenu menu = getSlidingMenu();
	        menu.showContent();
	        
	        //Delete Back Stack
	        getSupportFragmentManager().popBackStackImmediate();
	        
	        break;

	    }

	    return true;
	}
	
    
	public void transformCanvas(Canvas canvas, float percentOpen) {
		float scale = (float) (percentOpen*0.25 + 0.75);
		canvas.scale(scale, scale, canvas.getWidth()/2, canvas.getHeight()/2);
	}

}
