package com.blog.shypal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PostFragment extends Fragment {
	String title;

	public PostFragment(String title) {
		this.title=title;
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState) 
	{
		View rootView=inflater.inflate(R.layout.postfragment,container , false);
		
		TextView tv=(TextView)rootView.findViewById(R.id.posttitle);
		tv.setText(Html.fromHtml(title));

		
		return rootView;
	}
	
	
	
}
