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

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blog.shypal.Constant;
import com.blog.shypal.Custom;
import com.blog.shypal.HomePageFragment;
import com.blog.shypal.R;
import com.blog.shypal.adapter.HomePageAdapter;
import com.etsy.android.grid.StaggeredGridView;

public class LoadMoreAsyncTask extends AsyncTask<Void, Void, Void> {
	long offset;
	FragmentActivity activity;

	int flag;
	HttpResponse resp = null;
	String value = null;
	JSONArray posts;
	StaggeredGridView mGridView;
	HomePageAdapter homePageadpter;
	ArrayList<Custom> listdata;
	RelativeLayout loadmorelayout;
	View myfooter;

	String title, featured_image, category, found;

	public LoadMoreAsyncTask(long offset, FragmentActivity activity,
			StaggeredGridView mGridView, ArrayList<Custom> listdata) {
		this.activity = activity;
		this.offset = offset;
		this.mGridView = mGridView;
		this.listdata = listdata;

	}

	@Override
	protected void onPreExecute() {

		super.onPreExecute();
		
		
		//	adding the footer
		myfooter = View.inflate(activity, R.layout.loadmorefooter, null);
		loadmorelayout = (RelativeLayout) myfooter.findViewById(R.id.loadmorelayout);
		mGridView.addFooterView(myfooter);
		System.out.println("offset---->" + offset);
		
		// listdata=new ArrayList<Custom>();
	}

	@Override
	protected Void doInBackground(Void... params) {

		HttpClient client = new DefaultHttpClient();
		String url = new Constant().getHomeURlwithOffset() + offset;
		System.out.println(url);
		HttpGet get = new HttpGet(new Constant().getHomeURlwithOffset()
				+ offset);
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
				found=jsonObj.getString("found");
				posts = jsonObj.getJSONArray("posts");
				System.out.println("Load More Post----->" + posts);

				for (int i = 0; i < posts.length(); i++) {
					JSONObject c = posts.getJSONObject(i);
					
					title = c.getString("title");
					featured_image = c.getString("featured_image");

					System.out.println("Title--->" + title);
					System.out.println("featured_image--->" + featured_image);

					listdata.add(new Custom(title, featured_image));

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

		if (value != null) {

			activity.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					
					if(found.equals("0")){
						Toast.makeText(activity,"No More Articles !",Toast.LENGTH_LONG).show();
					}
				
					else {
						homePageadpter = new HomePageAdapter(listdata, activity,mGridView);
						mGridView.setAdapter(homePageadpter);
						HomePageFragment.setftrvalue(0);
						
						//removing the loading more footer after the asynctask
						mGridView.removeFooterView(myfooter);
					}
					
					
				}
			});

		} else {
			Toast.makeText(activity,
					"Oops! Something Went Wrong. Try Again Later",
					Toast.LENGTH_LONG).show();
			activity.finish();
		}

	}

}
