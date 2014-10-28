package com.blog.shypal;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blog.shypal.Asynctask.GetlatestPostAsynctask;
import com.blog.shypal.Asynctask.LoadMoreAsyncTask;
import com.blog.shypal.tools.ConnectionDetector;
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
			Toast.makeText(getActivity(), "No Internet Connection!",
					Toast.LENGTH_SHORT).show();
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
	}

	@Override
	public void onScroll(final AbsListView view, final int firstVisibleItem,
			final int visibleItemCount, final int totalItemCount) {
		
	}

	// private void onLoadMoreItems() {
	// System.out.println("offset---->"+offset);
	// offset = offset + 5;
	// System.out.println("offset---->"+offset);
	// loadMoreAsyntask=null;
	// loadMoreAsyntask = new LoadMoreAsyncTask(offset, getActivity(),
	// mGridView,listdata);
	// Toast.makeText(getActivity(), "Wait Guyz",
	// Toast.LENGTH_SHORT).show();
	// System.out.println(loadMoreAsyntask.getStatus());
	//
	// if (loadMoreAsyntask.getStatus() == AsyncTask.Status.RUNNING) {
	// Toast.makeText(getActivity(), "Getting Updates...",
	// Toast.LENGTH_SHORT).show();
	//
	// } else if (loadMoreAsyntask.getStatus() == AsyncTask.Status.PENDING) {
	//
	// loadMoreAsyntask.execute();
	// } else if (loadMoreAsyntask.getStatus() == AsyncTask.Status.FINISHED)
	//
	// {
	// loadMoreAsyntask = null;
	//
	// loadMoreAsyntask.execute();
	// }
	//
	// // mHasRequestedMore = false;
	//
	// }

	@Override
	public void onScrollStateChanged(final AbsListView view,
			final int scrollState) {

		int i = mGridView.getCount();
		found = listdata.size();
		Log.e("Count", " " + i);
		if (mGridView.getLastVisiblePosition() >= i - 1) {
			// if (found > 0)
			// {
			if (ftr == 0) {
				ftr = 1;
				System.out.println("offset---->" + offset);

				offset = offset + 5;
				System.out.println("offset---->" + offset);
				new LoadMoreAsyncTask(offset, getActivity(), mGridView,
						listdata).execute();
				// }
			}
		}

		Log.e("over", "list completed");

	}

	public static void setftrvalue(int i) {

		ftr = i;
	}

}
