package com.blog.shypal;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NavigationDrawerFragment extends Fragment {

	private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

	private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

	private NavigationDrawerCallbacks mCallbacks;

	private ActionBarDrawerToggle mDrawerToggle;

	private static DrawerLayout mDrawerLayout;
	private ListView mDrawerListView;
	private View mFragmentContainerView;

	private int mCurrentSelectedPosition = 0;
	private boolean mFromSavedInstanceState;
	private boolean mUserLearnedDrawer;
	private TypedArray micon;
	private String[] mMenuTitles;
	ArrayList<Constant> categorylist;
	MycategoryListAdapter categoryAdapter;

	public NavigationDrawerFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getActivity());
		mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

		
		if (savedInstanceState != null) {
			mCurrentSelectedPosition = savedInstanceState
					.getInt(STATE_SELECTED_POSITION);
			mFromSavedInstanceState = true;
		}

		// Select either the default item (0) or the last selected item.
		selectItem(mCurrentSelectedPosition);

		mMenuTitles = getResources().getStringArray(R.array.nav_drawer);
		micon = getResources().obtainTypedArray(R.array.nav_drawer_icons);
		categorylist = new ArrayList<Constant>();
		for (int i = 0; i < mMenuTitles.length; i++) {
			categorylist.add(new Constant(mMenuTitles[i], micon.getResourceId(i,
					-1)));
		}

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mDrawerListView = (ListView) inflater.inflate(
				R.layout.fragment_navigation_drawer, container, false);
		
		View myheader = View.inflate(getActivity(), R.layout.navdrawerheader, null);

		 mDrawerListView.addHeaderView(myheader);
		 
		 mDrawerListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				 
				 if(position==1){			 
					 getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new CategoryFragment("apps")).commit();   
					 ShyPal.setActionBarTitle("SHYPAL :: APPS");
				 }
				 if(position==2){					 
					 getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new CategoryFragment("bikes")).commit();   
					 ShyPal.setActionBarTitle("SHYPAL :: BIKES");
				 }
				 if(position==3){			 
					 getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new CategoryFragment("cars")).commit();   
					 ShyPal.setActionBarTitle("SHYPAL :: CARS");
				 }
				 if(position==4){					 
					 getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new CategoryFragment("entertainment")).commit();   
					 ShyPal.setActionBarTitle("SHYPAL :: ENTERTAINMENT");
				 }
				 if(position==5){			 
					 getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new CategoryFragment("internet")).commit();   
					 ShyPal.setActionBarTitle("SHYPAL :: INTERNET");
				 }
				 if(position==6){					 
					 getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new CategoryFragment("laptop")).commit();   
					 ShyPal.setActionBarTitle("SHYPAL :: LAPTOP");
				 }
				 if(position==7){			 
					 getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new CategoryFragment("mobile")).commit();   
					 ShyPal.setActionBarTitle("SHYPAL :: MOBILE");
				 }
				 if(position==8){		
					 
					 getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new CategoryFragment("tablets")).commit();   
					 ShyPal.setActionBarTitle("SHYPAL :: TABLETS");
				 }
				 if(position==9){					 
					 getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container, new CategoryFragment("telecom")).commit();   
					 ShyPal.setActionBarTitle("SHYPAL :: TELECOM");
				 }
				 
				 
				 mDrawerLayout.closeDrawers();
			}
			 
		});
	


		categoryAdapter = new MycategoryListAdapter(getActivity(),
				categorylist, mDrawerListView);

		mDrawerListView.setAdapter(categoryAdapter);

		mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);
		return mDrawerListView;
	}

	public boolean isDrawerOpen() {
		return mDrawerLayout != null
				&& mDrawerLayout.isDrawerOpen(mFragmentContainerView);
	}

	
	public void setUp(int fragmentId, DrawerLayout drawerLayout) {
		mFragmentContainerView = getActivity().findViewById(fragmentId);
		mDrawerLayout = drawerLayout;

	
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

	
		mDrawerToggle = new ActionBarDrawerToggle(getActivity(), /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				if (!isAdded()) {
					return;
				}

				getActivity().supportInvalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				if (!isAdded()) {
					return;
				}

				if (!mUserLearnedDrawer) {
				
					mUserLearnedDrawer = true;
					SharedPreferences sp = PreferenceManager
							.getDefaultSharedPreferences(getActivity());
					sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true)
							.commit();
				}

				getActivity().supportInvalidateOptionsMenu(); 
			}
		};

		
		if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
			mDrawerLayout.openDrawer(mFragmentContainerView);
		}

		// Defer code dependent on restoration of previous instance state.
		mDrawerLayout.post(new Runnable() {
			@Override
			public void run() {
				mDrawerToggle.syncState();
			}
		});

		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	private void selectItem(int position) {
		mCurrentSelectedPosition = position;
		if (mDrawerListView != null) {
			mDrawerListView.setItemChecked(position, true);
		}
		if (mDrawerLayout != null) {
			mDrawerLayout.closeDrawer(mFragmentContainerView);
		}
		if (mCallbacks != null) {
			mCallbacks.onNavigationDrawerItemSelected(position);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mCallbacks = (NavigationDrawerCallbacks) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(
					"Activity must implement NavigationDrawerCallbacks.");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = null;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		if (mDrawerLayout != null && isDrawerOpen()) {
			inflater.inflate(R.menu.global, menu);
			showGlobalContextActionBar();
		}
		super.onCreateOptionsMenu(menu, inflater);
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		// if (item.getItemId() == R.id.action_example) {
		// Toast.makeText(getActivity(), "Example action.", Toast.LENGTH_SHORT)
		// .show();
		// return true;
		// }

		return super.onOptionsItemSelected(item);
	}

	private void showGlobalContextActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setTitle(R.string.app_name);
	}

	private ActionBar getActionBar() {
		return ((ActionBarActivity) getActivity()).getSupportActionBar();
	}

	public static interface NavigationDrawerCallbacks {

		void onNavigationDrawerItemSelected(int position);
	}

	public class MycategoryListAdapter extends BaseAdapter {
		FragmentActivity activity;
		ArrayList<Constant> categorylist;
		ListView mDrawerListView;
		TextView tv;
		ImageView iv;

		public MycategoryListAdapter(FragmentActivity activity,
				ArrayList<Constant> categorylist, ListView mDrawerListView) {

			this.activity = activity;
			this.categorylist = categorylist;
			this.mDrawerListView = mDrawerListView;

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return categorylist.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflator = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflator.inflate(R.layout.navigationdraweritem,
					parent, false);
			tv = (TextView) convertView.findViewById(R.id.text);
			iv = (ImageView) convertView.findViewById(R.id.icon);
			

			tv.setText(categorylist.get(position).getMtitle());
			iv.setImageResource(categorylist.get(position).getMicon());

			return convertView;
		}

	}

	
	
	public static void closethedrawer() {
		 mDrawerLayout.closeDrawers();
		
	}

}
