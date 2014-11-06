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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blog.shypal.CategoryFragment;
import com.blog.shypal.Constant;
import com.blog.shypal.Custom;
import com.blog.shypal.R;
import com.blog.shypal.adapter.CategoryAdapter;
import com.blog.shypal.tools.CustomToast;
import com.etsy.android.grid.StaggeredGridView;

public class CategoryLoadmoreAsyncktask extends AsyncTask<Void , Void, Void> {
	long offset;
	FragmentActivity activity;

	int flag;
	HttpResponse resp = null;
	String value = null;
	JSONArray posts;
	StaggeredGridView mGridView;
	CategoryAdapter categoryAdapter;
	ArrayList<Custom> categorylistdata;
	RelativeLayout loadmorelayout;
	View myfooter;
	String categoryslug;

	String title, featured_image, category, found,avatar_URL;

	public CategoryLoadmoreAsyncktask(long offset, FragmentActivity activity,
			StaggeredGridView mGridView, ArrayList<Custom> categorylistdata, String categoryslug) {
		this.activity = activity;
		this.offset = offset;
		this.mGridView = mGridView;
		this.categorylistdata = categorylistdata;
		this.categoryslug=categoryslug;

	}

	@Override
	protected void onPreExecute() {

		super.onPreExecute();
	
		//	adding the footer
		myfooter = View.inflate(activity, R.layout.loadmorefooter, null);
		loadmorelayout = (RelativeLayout) myfooter.findViewById(R.id.loadmorelayout);
		mGridView.addFooterView(myfooter);
		
		System.out.println("offset---->" + offset);
		
		String toast=activity.getResources().getString(R.string.customToastText2);
		new CustomToast(toast,activity, R.drawable.loadingicon);

		
		
	}

	@Override
	protected Void doInBackground(Void... params) {

		HttpClient client = new DefaultHttpClient();
//		String url = new Constant().getHomeURlwithOffset() + offset;
//		System.out.println(url);
		System.out.println("category url with offset--->" +new Constant().getCategoryURLwithoffset(offset, categoryslug));
		HttpGet get = new HttpGet(new Constant().getCategoryURLwithoffset(offset, categoryslug));
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
//				System.out.println("Load More Post----->" + posts);

				for (int i = 0; i < posts.length(); i++) {
					JSONObject c = posts.getJSONObject(i);
					
					title = c.getString("title");
					featured_image = c.getString("featured_image");
					JSONObject author=c.getJSONObject("author");
					 avatar_URL=author.getString("avatar_URL");
					 
//					System.out.println("Title--->" + title);
//					System.out.println("featured_image--->" + featured_image);

					categorylistdata.add(new Custom(title, featured_image,avatar_URL));

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
						mGridView.removeFooterView(myfooter);
					}
				
					else {
						categoryAdapter = new CategoryAdapter(categorylistdata, activity,mGridView);
						mGridView.setAdapter(categoryAdapter);
						CategoryFragment.setftrvalue(0);
						
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
