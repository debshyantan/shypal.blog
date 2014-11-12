package com.blog.shypal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PostFragment extends Fragment {
	String title;
	Menu menu;
	String posturl;
	public PostFragment(String title, String posturl) {
		this.title=title;
		this.posturl=posturl;
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState) 
	{
		View rootView=inflater.inflate(R.layout.postfragment,container , false);
		setHasOptionsMenu(true);
		TextView tv=(TextView)rootView.findViewById(R.id.posttitle);
		tv.setText(Html.fromHtml(title));

		
		return rootView;
	}
	
	 public void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
	  {
		  this.menu = paramMenu;
	    paramMenuInflater.inflate(R.menu.actionbarmenu, paramMenu);
	  }
	 @Override
	public boolean onOptionsItemSelected(MenuItem item) {

		 int id = item.getItemId();
			 if (id == R.id.share) {
				 
				 Intent localIntent = new Intent("android.intent.action.SEND");
			        localIntent.setType("text/plain");
			        localIntent.putExtra("android.intent.extra.SUBJECT", Html.fromHtml(title) + "- ShyPal.Com");
			        localIntent.putExtra("android.intent.extra.TEXT", "Have a look at - " +Html.fromHtml(title) + " on "+ posturl);
			        PostFragment.this.startActivity(Intent.createChooser(localIntent, "Share via"));
				 
			 return true;
			 }
		return super.onOptionsItemSelected(item);
	}

	
	
	
}
