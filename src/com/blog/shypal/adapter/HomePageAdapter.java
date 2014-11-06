package com.blog.shypal.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blog.shypal.Custom;
import com.blog.shypal.R;
import com.blog.shypal.animations.Techniques;
import com.blog.shypal.animations.YoYo;
import com.etsy.android.grid.StaggeredGridView;
import com.squareup.picasso.Picasso;

public class HomePageAdapter extends BaseAdapter{
	
	ArrayList<Custom> listdata;
	FragmentActivity activity;
	StaggeredGridView mGridView;

	public HomePageAdapter(ArrayList<Custom> listdata,
			FragmentActivity activity, StaggeredGridView mGridView) {
		this.listdata=listdata;
		this.activity=activity;
		this.mGridView=mGridView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listdata.size();
	}

	@Override
	public Object getItem(int postion) {
		// TODO Auto-generated method stub
		return postion;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		viewholder holder;
		if (convertView==null) {
			holder=new viewholder();
		LayoutInflater inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(com.blog.shypal.R.layout.stageredlistviewelement, parent, false);
		holder.title=(TextView)convertView.findViewById(R.id.textview);
		holder.featured_image=(ImageView)convertView.findViewById(R.id.featuredImg);
//		holder.avatar_URL=(ImageView)convertView.findViewById(R.id.authorImg);
		YoYo.with(Techniques.DropOut)
	    .duration(1000)
	    .playOn(holder.title);
		
		convertView.setTag(holder);
		}
		else {
		holder=(viewholder)convertView.getTag();
		}
		//loading post features image
		Picasso.with(activity).load(listdata.get(position).getFeatured_image()).resize(150	, 200)
		
     /* .fit()     
        .centerCrop()
        .centerInside()*/
        .into(holder.featured_image);
		
		
	//loading author Image	
//		Picasso.with(activity)
//        .load(listdata.get(position).getAvatar_URL())
////        .fit()
//        .resize(150	, 200)
//        .centerCrop()
//        .centerInside()
        
//        .into(holder.avatar_URL);
			
			holder.title.setText(Html.fromHtml(listdata.get(position).getTitle()));

//			holder.featured_image.setImageResource(R.drawable.ic_launcher);
	
		
	
		
		return convertView;
	}
	class viewholder{
		TextView title;
		ImageView featured_image,avatar_URL;
	}
	
}
