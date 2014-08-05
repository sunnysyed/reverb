package com.SuNnY.reverb.fragment;

import org.apache.http.Header;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.GridView;
import android.widget.Toast;

import com.SuNnY.reverb.GridAdaptor;
import com.SuNnY.reverb.Home;
import com.SuNnY.reverb.R;
import com.SuNnY.reverb.SwaggerConnecter;
import com.SuNnY.reverb.obj.Result;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;


public class BrowseFragment extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";

	//TODO keep track of items shown and the page
	public int _total = 0;
	public int _page = 1;
	GridAdaptor _adapter;
	Boolean _loadingMore = false;

	/**
	 * Returns a new instance of this fragment
	 */
	public static BrowseFragment newInstance(int sectionNumber) {
		BrowseFragment fragment = new BrowseFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_browse, container,
				false);
		final GridView view =(GridView) rootView.findViewById(R.id.browse_gridview);
		_adapter = new GridAdaptor(this.getActivity().getApplicationContext());
		view.setAdapter(_adapter);
		if (_adapter.isEmpty()){
			getListings();
		}
		view.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				int lastInScreen = firstVisibleItem + visibleItemCount;    
				if((lastInScreen == totalItemCount) && !(_loadingMore)){     
					getListings();
					Toast.makeText(getActivity(), "Get more", Toast.LENGTH_SHORT).show();
				}

			}
		});
		return rootView;
	}

	public void getListings() {
		_loadingMore = true;
		SwaggerConnecter.get("listings?page=" + _page, null, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				Gson gson = new Gson();
				Result res = gson.fromJson(response.toString(), Result.class);
				_adapter.addData(res.getListings());
				_page++;
				_loadingMore = false;
			}

		});
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((Home) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}
}
