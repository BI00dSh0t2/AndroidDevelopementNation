package com.venge.AndroidDevelopementNation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class devarea extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.devarea,
	        container, false);
	    view.setBackgroundColor(Color.DKGRAY);
		return view;
	}
}
