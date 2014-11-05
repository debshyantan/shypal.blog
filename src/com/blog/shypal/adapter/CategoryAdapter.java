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
import com.etsy.android.grid.StaggeredGridView;
import com.squareup.picasso.Picasso;

public class CategoryAdapter extends BaseAdapter{
	ArrayList<Custom> categorylistdata;
	FragmentActivity activity;
	StaggeredGridView mGridView;

	public CategoryAdapter(ArrayList<Custom> categorylistdata,
			FragmentActivity activity, StaggeredGridView mGridView) {
		this.activity=activity;
		this.categorylistdata=categorylistdata;
		this.mGridView=mGridView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return categorylistdata.size();
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
		convertView = inflater.inflate(com.blog.shypal.R.layout.categorystageredlistviewelement, parent, false);
		holder.title=(TextView)convertView.findViewById(R.id.ctextview);
		holder.featured_image=(ImageView)convertView.findViewById(R.id.cfeaturedImg);
		
		
		convertView.setTag(holder);
		}
		else {
		holder=(viewholder)convertView.getTag();
		}
		
		Picasso.with(activity)
        .load(categorylistdata.get(position).getFeatured_image())
//        .fit()
        .resize(150	, 200)
//        .centerCrop()
//        .centerInside()
        
        .into(holder.featured_image);
			
			holder.title.setText(Html.fromHtml(categorylistdata.get(position).getTitle()));

//			holder.featured_image.setImageResource(R.drawable.ic_launcher);
	
		
	
		
		return convertView;
	}
	class viewholder{
		TextView title;
		ImageView featured_image;
	}

}
