package com.blog.shypal;

import com.blog.shypal.Asynctask.CategoryAsynctask;
import com.blog.shypal.Asynctask.GetlatestPostAsynctask;
import com.blog.shypal.tools.ConnectionDetector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class CategoryFragment extends Fragment {

	String categoryslug;
	static Boolean isInternetPresent = false;
	ConnectionDetector cd;

	public CategoryFragment(String categoryslug) {
		this.categoryslug = categoryslug;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_navigation_drawer,
				container, false);

		// connection checking
		cd = new ConnectionDetector(getActivity().getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();
		System.out.println("Network states:" + isInternetPresent);

		if (isInternetPresent) {

			new CategoryAsynctask(getActivity(), categoryslug).execute();

		}

		else {
			Toast.makeText(getActivity(), "No Internet Connection!",
					Toast.LENGTH_SHORT).show();
			getActivity().finish();

		}

		return rootView;
	}

}
