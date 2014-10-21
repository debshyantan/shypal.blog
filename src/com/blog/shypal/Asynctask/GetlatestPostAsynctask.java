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

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.blog.shypal.Constant;
import com.blog.shypal.Custom;
import com.blog.shypal.R;
import com.blog.shypal.adapter.HomePageAdapter;
import com.etsy.android.grid.StaggeredGridView;



public class GetlatestPostAsynctask extends AsyncTask<Void, Void, Void>
		implements AnimationListener
{
	FragmentActivity activity;
	ImageView iv;
	// Animation
	Animation animRotate;
	int flag;
	HttpResponse resp = null;
	String value = null;
	JSONArray posts;
	StaggeredGridView mGridView;
	HomePageAdapter homePageadpter;
	ArrayList<Custom> listdata;

	String title, featured_image, category;

	public GetlatestPostAsynctask(FragmentActivity activity, ImageView iv, StaggeredGridView mGridView) {

		this.activity = activity;
		this.iv = iv;
		this.mGridView=mGridView;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

		iv.setVisibility(View.VISIBLE);

		animRotate = AnimationUtils.loadAnimation(activity, R.anim.rotate);
		// set animation listener
		animRotate.setAnimationListener(this);
		iv.startAnimation(animRotate);

		listdata=new ArrayList<Custom>();

	}

	@Override
	protected Void doInBackground(Void... params) {

		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(new Constant().getMyurl());
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
		System.out.println(value);

		if (value != null) {
			try {
				JSONObject jsonObj = new JSONObject(value);

				posts = jsonObj.getJSONArray("posts");
				System.out.println("posts----->" + posts);

				for (int i = 0; i < posts.length(); i++) {
					JSONObject c = posts.getJSONObject(i);

					title = c.getString("title");
					featured_image = c.getString("featured_image");
					
					System.out.println("Title--->" +title);
					System.out.println("featured_image--->" +featured_image);
					
					
					
					listdata.add(new Custom(title,featured_image));

				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		// pd.dismiss();
		
	
		
		
		
		
		if (value != null) {

			activity.runOnUiThread(new Runnable() {

				@Override
				public void run() {

					iv.clearAnimation();

					iv.setVisibility(View.GONE);
					
					mGridView.setVisibility(View.VISIBLE);
					homePageadpter=new HomePageAdapter(listdata,activity,mGridView);
					mGridView.setAdapter(homePageadpter);
					
					
					

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
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

}
