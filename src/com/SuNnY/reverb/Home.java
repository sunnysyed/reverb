package com.SuNnY.reverb;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.SuNnY.reverb.fragment.BrowseFragment;
import com.SuNnY.reverb.fragment.NavigationDrawerFragment;
import com.SuNnY.reverb.fragment.WatchFragment;

public class Home extends ActionBarActivity implements
NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment _mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		_mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		_mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		switch (position){
		case 0:
			fragmentManager
			.beginTransaction()
			.replace(R.id.container,
					BrowseFragment.newInstance(position + 1)).commit();
			break;
			//For testing purpose
		default :
			fragmentManager
			.beginTransaction()
			.replace(R.id.container,
					WatchFragment.newInstance(position + 1)).commit();
			break;
		}
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.browse_gear);
			break;
		case 2:
			mTitle = getString(R.string.watching);
			break;
		case 3:
			mTitle = getString(R.string.my_listings);
			break;
		case 4:
			mTitle = getString(R.string.my_drafts);
			break;
		case 5:
			mTitle = getString(R.string.offers);
			break;
		case 6:
			mTitle = getString(R.string.orders);
			break;
		case 7:
			mTitle = getString(R.string.messages);
			break;
		case 8:
			mTitle = getString(R.string.help);
			break;
		case 9:
			mTitle = getString(R.string.sell_your_gear);
			break;
		case 10:
			mTitle = getString(R.string.log_out);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!_mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.home, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
