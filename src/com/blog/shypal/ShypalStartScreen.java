package com.blog.shypal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ShypalStartScreen extends Activity {
	Intent in;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shypalstartscreen);
		
		Handler handle=new Handler();
		handle.postDelayed(new Runnable() {
			
			@Override
			public void run() {

				 in=new Intent(ShypalStartScreen.this, ShyPal.class);
				startActivity(in);	
				finish();
			}
		}, 3000);
		
		
		
		
	}
}
