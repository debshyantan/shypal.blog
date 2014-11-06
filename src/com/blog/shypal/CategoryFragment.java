package com.blog.shypal;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.blog.shypal.Asynctask.CategoryAsynctask;
import com.blog.shypal.Asynctask.CategoryLoadmoreAsyncktask;
import com.blog.shypal.tools.ConnectionDetector;
import com.blog.shypal.tools.CustomToast;
import com.etsy.android.grid.StaggeredGridView;

public class CategoryFragment extends Fragment implements
AbsListView.OnScrollListener, AbsListView.OnItemClickListener{
	
	static StaggeredGridView mGridView;
	CategoryLoadmoreAsyncktask loadmoreasyntask;
	String categoryslug;
	static Boolean isInternetPresent = false;
	ConnectionDetector cd;
	ImageView iv;
	long offset = 0;
	
	ArrayList<Custom> categorylistdata;
	static int ftr = 0;
	int found = 0;


	public CategoryFragment(String categoryslug) {
		this.categoryslug = categoryslug;

	}

	
	public CategoryFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.categorylistview,
				container, false);
		
//		FragmentManager fm = getActivity().getSupportFragmentManager();
		
//		if(fm.getBackStackEntryCount()>1){
//			System.out.println("backstack count before--->"+fm.getBackStackEntryCount());
//			 fm.popBackStack();
////				System.out.println("backstack count after--->"+fm.getBackStackEntryCount());
//
//		}
		

		
		categorylistdata = new ArrayList<Custom>();
		
		iv = (ImageView) rootView.findViewById(R.id.imageView1);

		mGridView = (StaggeredGridView) rootView.findViewById(R.id.cgrid_view);
		
		
		// connection checking
		cd = new ConnectionDetector(getActivity().getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();
		System.out.println("Network states:" + isInternetPresent);

		if (isInternetPresent) {
			
			try {
				
			
			new CategoryAsynctask(getActivity(), categoryslug, categorylistdata, mGridView,iv ).execute();
			
			} catch (Exception e) {
				Toast.makeText(getActivity(), "Some Thing Went Wrong! Try Again Later.",
						Toast.LENGTH_SHORT).show();
			}
		}

		else {
//			Toast.makeText(getActivity(), "No Internet Connection!",
//					Toast.LENGTH_SHORT).show();
//			getActivity().finish();
			String toast=getActivity().getResources().getString(R.string.nointernet);
			new CustomToast(toast,getActivity(), R.drawable.nointernetconnection);

		}

		
		mGridView.setOnScrollListener(this);
		mGridView.setOnItemClickListener(this);
		
		return rootView;
	}



	@Override
	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long id) {
		Toast.makeText(getActivity(), "Item Clicked: " + position,
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onScroll(final AbsListView view, final int firstVisibleItem,
			final int visibleItemCount, final int totalItemCount) {

	}

	@Override
	public void onScrollStateChanged(final AbsListView view,
			final int scrollState) {

		int i = mGridView.getCount();
		found = categorylistdata.size();
		Log.e("Count", " " + mGridView.getLastVisiblePosition());
		if (mGridView.getLastVisiblePosition() >= i - 1) {

			if (ftr == 0) {
				ftr = 1;
				System.out.println("offset---->" + offset);

				offset = offset + 5;
				System.out.println("offset---->" + offset);

				if (isInternetPresent) {
					
					try {
						loadmoreasyntask=new CategoryLoadmoreAsyncktask(offset, getActivity(), mGridView,
								categorylistdata,categoryslug);
						
						System.out.println("Asynctassk status--->"+loadmoreasyntask.getStatus());
						
						 if(loadmoreasyntask.getStatus()==AsyncTask.Status.PENDING)
								
							{
							 loadmoreasyntask.execute();
							}
							else if(loadmoreasyntask.getStatus()==AsyncTask.Status.RUNNING)
							{
								
							}
							else if(loadmoreasyntask.getStatus()==AsyncTask.Status.FINISHED)
							{
								loadmoreasyntask=null;
								loadmoreasyntask.execute();
										
							}
					
						
					
					
					} catch (Exception e) {
						Toast.makeText(getActivity(), "SomeThing Went Wrong! Try Again Later",
								Toast.LENGTH_SHORT).show();					}

				}else {
					
					getActivity().finish();

				}

			}
		}

//		Log.e("over", "list completed");

	}

	public static void setftrvalue(int i) {

		ftr = i;
	}

}
