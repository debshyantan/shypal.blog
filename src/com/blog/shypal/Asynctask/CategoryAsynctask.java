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
import com.blog.shypal.adapter.HomePageAdapter;
import com.etsy.android.grid.StaggeredGridView;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.view.animation.Animation;
import android.widget.ImageView;

public class CategoryAsynctask extends AsyncTask<Void, Void, Void>{
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
	HomePageAdapter homePageadpter;
	ArrayList<Custom> listdata;
	

	String title, featured_image, category;
	
	public CategoryAsynctask(FragmentActivity activity, String categoryslug) {
	this.activity=activity;
	this.categoryslug=categoryslug;
	}

	@Override
	protected Void doInBackground(Void... params) {

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

}
