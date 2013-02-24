package com.venge.AndroidDevelopementNation;

import java.io.IOException;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.venge.AndroidDevelopementNation.irc.*;

public class devtalk extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.devtalk,
	        container, false);
	    view.setBackgroundColor(Color.BLACK);
		return view;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    // TODO Auto-generated method stub
	    irc();
	    
	}
	public void irc() {
		// TODO Auto-generated method stub
		base irc = new base();
		
		try {
			irc.connect("irc.light_venge.com");
		} catch (NickAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IrcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		irc.joinChannel("#VengeMobile/DevNation");
	}
	
}
