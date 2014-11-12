package com.blog.shypal;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ShyPal extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;
	static ActionBar actionBar;
	String Actiontitle="SHYPAL";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 actionBar = getSupportActionBar();
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		

	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						HomePageFragment.newInstance(position + 1)).commit();
	}

	public void onSectionAttached(int number) {
		switch (number) {
		// case 1:
		// Toast.makeText(this, "Apps", Toast.LENGTH_LONG).show();
		// mTitle = getString(R.string.title_section1);
		// break;
		// case 2:
		// mTitle = getString(R.string.title_section2);
		// break;
		// case 3:
		// mTitle = getString(R.string.title_section3);
		// break;
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (mNavigationDrawerFragment.isVisible()) {
			NavigationDrawerFragment.closethedrawer();

		}

		else {
			super.onBackPressed();

		}

	}

	public void restoreActionBar() {
		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
////		if (!mNavigationDrawerFragment.isDrawerOpen()) {
////			// Only show items in the action bar relevant to this screen
////			// if the drawer is not showing. Otherwise, let the drawer
////			// decide what to show in the action bar.
////			getMenuInflater().inflate(R.menu.main, menu);
////			restoreActionBar();
////			return true;
////		}
////		return super.onCreateOptionsMenu(menu);
//		MenuInflater inflater = getMenuInflater();
//	    inflater.inflate(R.menu.actionbarmenu, menu);
//
//	    return super.onCreateOptionsMenu(menu);
//	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		// if (id == R.id.action_settings) {
		// return true;
		// }
		return super.onOptionsItemSelected(item);
	}

	public static void setActionBarTitle(String Actiontitle) {
		System.out.println("Action bar title ---->" + Actiontitle);
		actionBar.setTitle(Actiontitle);
	}

}
