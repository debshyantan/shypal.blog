package com.blog.shypal;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.blog.shypal.Asynctask.GetlatestPostAsynctask;
import com.blog.shypal.Asynctask.LoadMoreAsyncTask;
import com.blog.shypal.tools.ConnectionDetector;
import com.blog.shypal.tools.CustomToast;
import com.etsy.android.grid.StaggeredGridView;

public class HomePageFragment extends Fragment implements
		AbsListView.OnScrollListener, AbsListView.OnItemClickListener {

	private static final String ARG_SECTION_NUMBER = "section_number";
	static StaggeredGridView mGridView;

	static Boolean isInternetPresent = false;
	ConnectionDetector cd;
	ImageView iv;
	long offset = 0;
	LoadMoreAsyncTask loadMoreAsyntask;
	ArrayList<Custom> listdata;
	int found = 0;
	static int ftr = 0;

	public static HomePageFragment newInstance(int sectionNumber) {
		HomePageFragment fragment = new HomePageFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		 ShyPal.setActionBarTitle("SHYPAL");
		listdata = new ArrayList<Custom>();

		// connection checking
		cd = new ConnectionDetector(getActivity().getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();
		System.out.println("Network states:" + isInternetPresent);

		iv = (ImageView) rootView.findViewById(R.id.imageView1);

		mGridView = (StaggeredGridView) rootView.findViewById(R.id.grid_view);

		if (isInternetPresent) {

			new GetlatestPostAsynctask(getActivity(), iv, mGridView, listdata)
					.execute();

		}

		else {
//			Toast.makeText(getActivity(), "No Internet Connection!",
//					Toast.LENGTH_SHORT).show();
			
			String toast=getActivity().getResources().getString(R.string.nointernet);
			new CustomToast(toast,getActivity(), R.drawable.nointernetconnection);
			
			getActivity().finish();

		}
		


		mGridView.setOnScrollListener(this);
		mGridView.setOnItemClickListener(this);

		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((ShyPal) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long id) {
		Toast.makeText(getActivity(), "Item Clicked: " + position,
				Toast.LENGTH_SHORT).show();
		
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		
		ft.setCustomAnimations(R.anim.popenter, R.anim.exit, R.anim.enter, R.anim.popexit);
//		PostFragment newFragment = PostFragment.newInstance();
		ft.replace(R.id.container, new PostFragment(listdata.get(position).getTitle()));
		ft.addToBackStack(null);
		// Start the animated transition.
		ft.commit();
		
		
		
//		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new PostFragment(listdata.get(position).getTitle())).addToBackStack(null).commit();
		
	}

	@Override
	public void onScroll(final AbsListView view, final int firstVisibleItem,
			final int visibleItemCount, final int totalItemCount) {

	}

	@Override
	public void onScrollStateChanged(final AbsListView view,
			final int scrollState) {

		int i = mGridView.getCount();
		found = listdata.size();
		Log.e("Count", " " + i);
		if (mGridView.getLastVisiblePosition() >= i - 1) {

			if (ftr == 0) {
				ftr = 1;
				System.out.println("offset---->" + offset);

				offset = offset + 5;
				System.out.println("offset---->" + offset);

				if (isInternetPresent) {
					
					try {
						
					
					new LoadMoreAsyncTask(offset, getActivity(), mGridView,
							listdata).execute();
					
					} catch (Exception e) {
						Toast.makeText(getActivity(), "SomeThing Went Wrong! Try Again Later",
								Toast.LENGTH_SHORT).show();	
						}

				}else {
					
					getActivity().finish();

				}

			}
		}

		Log.e("over", "list completed");

	}

	public static void setftrvalue(int i) {

		ftr = i;
	}
	
	

}
