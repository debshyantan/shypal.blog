package com.blog.shypal.tools;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomFont extends TextView{
	Typeface tt;
	private void init(Context context) {
		// TODO Auto-generated method stub
		tt=Typeface.createFromAsset(context.getAssets(),"fonts/georgia.ttf");
		setTypeface(tt);
		
	}
	public CustomFont(Context context) {
		super(context);
		init(context);
		// TODO Auto-generated constructor stub
	}

	

	public CustomFont(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
		// TODO Auto-generated constructor stub
	}

	public CustomFont(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
		// TODO Auto-generated constructor stub
	}
	
}
