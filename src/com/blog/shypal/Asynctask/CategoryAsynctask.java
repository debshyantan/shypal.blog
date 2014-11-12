package com.blog.shypal.Asynctask;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.blog.shypal.Constant;
import com.blog.shypal.Custom;
import com.blog.shypal.R;
import com.blog.shypal.adapter.CategoryAdapter;
import com.blog.shypal.adapter.HomePageAdapter;
import com.blog.shypal.tools.CustomToast;
import com.etsy.android.grid.StaggeredGridView;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.Toast;

public class CategoryAsynctask extends AsyncTask<Void, Void, Void> implements AnimationListener{
	FragmentActivity activity;
	String categoryslug;

	
	ImageView iv;
	// Animation
	Animation animRotate;
	int flag;
	HttpResponse resp = null;
	String value = null;
	JSONArray posts;
	StaggeredGridView mGridView;
	CategoryAdapter categoryAdapter;
	ArrayList<Custom> categorylistdata;	

	String title, featured_image, category,found, posturl, avatar_URL;
	
	public CategoryAsynctask(FragmentActivity activity, String categoryslug, ArrayList<Custom> categorylistdata, StaggeredGridView mGridView, ImageView iv) {
	
		this.activity=activity;
	this.categoryslug=categoryslug;
	this.mGridView=mGridView;
	this.iv=iv;
	this.categorylistdata=categorylistdata;
	
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		iv.setVisibility(View.VISIBLE);

		animRotate = AnimationUtils.loadAnimation(activity, R.anim.rotate);
		// set animation listener
		animRotate.setAnimationListener(this);
		iv.startAnimation(animRotate);
//		Toast.makeText(activity, "Wait While we Load the Data", Toast.LENGTH_LONG).show();
		
		String toast=activity.getResources().getString(R.string.customToastText);
		new CustomToast(toast,activity, R.drawable.loadingicon);
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		try {
			
		

		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(new Constant().getCategoryUrl(categoryslug));
		try {
			resp = client.execute(get);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			value = EntityUtils.toString(resp.getEntity());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(value);

		if (value != null) {
			try {
				JSONObject jsonObj = new JSONObject(value);
				found=jsonObj.getString("found");

				posts = jsonObj.getJSONArray("posts");
				

				for (int i = 0; i < posts.length(); i++) {
					JSONObject c = posts.getJSONObject(i);

					title = c.getString("title");
					featured_image = c.getString("featured_image");
					posturl = c.getString("URL");

					JSONObject author=c.getJSONObject("author");
					 avatar_URL=author.getString("avatar_URL");
//					System.out.println("Title--->" +title);
//					System.out.println("featured_image--->" +featured_image);
//					
					
					
					categorylistdata.add(new Custom(title,featured_image,avatar_URL , posturl));

				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		} catch (Exception e) {
			Toast.makeText(activity,
					"Oops! Something Went Wrong. Try Again Later",
					Toast.LENGTH_LONG).show();
		}

		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		

		if (value != null) {

			activity.runOnUiThread(new Runnable() {

				@Override
				public void run() {

					iv.clearAnimation();

					iv.setVisibility(View.GONE);

					mGridView.setVisibility(View.VISIBLE);
					categoryAdapter = new CategoryAdapter(categorylistdata, activity,
							mGridView);
					mGridView.setAdapter(categoryAdapter);

				}
			});

		} else {
			Toast.makeText(activity,
					"Oops! Something Went Wrong. Try Again Later",
					Toast.LENGTH_LONG).show();
			activity.finish();
		}

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
