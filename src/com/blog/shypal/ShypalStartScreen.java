package com.blog.shypal;

import java.util.Timer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.blog.shypal.animations.Techniques;
import com.blog.shypal.animations.YoYo;

public class ShypalStartScreen extends Activity {
	Intent in;
	ImageView iv;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shypalstartscreen);
		iv = (ImageView) findViewById(R.id.shypallogo);

		Handler handle = new Handler();
		handle.postDelayed(new Runnable() {

			@Override
			public void run() {

				YoYo.with(Techniques.Zoominup)
				// .duration(2000)
						.playOn(findViewById(R.id.shypallogo));

				hingeanimation();

			}
		}, 0);

	}

	protected void startshypal() {
		
		Handler handle = new Handler();
		handle.postDelayed(new Runnable() {

			@Override
			public void run() {

				in = new Intent(ShypalStartScreen.this, ShyPal.class);
				startActivity(in);
				finish();

			}
		}, 100);
		

		
	}

	protected void hingeanimation() {
		
		
		Handler handle = new Handler();
		handle.postDelayed(new Runnable() {

			@Override
			public void run() {

				YoYo.with(Techniques.HingeAnimator).duration(3000).playOn(iv);

				startshypal();
			}
		}, 1500);
		

	}
}
