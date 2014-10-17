package com.blog.shypal.tools;

import com.blog.shypal.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class TransparentFragment extends Fragment implements AnimationListener {

	ImageView iv;
	// Animation
	Animation animRotate;
	FragmentActivity activity;

	public TransparentFragment(FragmentActivity activity) {
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.transparentfragent,
				container, false);
		 iv=(ImageView)rootView.findViewById(R.id.imageView12);
		iv.setVisibility(View.VISIBLE);
		System.out.println("kjhvadfjvffjvfu");

		animRotate = AnimationUtils.loadAnimation(activity, R.anim.rotate);
		// set animation listener
		animRotate.setAnimationListener(this);
		iv.startAnimation(animRotate);

		return rootView;
	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation arg0) {
		// TODO Auto-generated method stub

	}

}
