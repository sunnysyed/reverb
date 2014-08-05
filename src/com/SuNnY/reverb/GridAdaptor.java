package com.SuNnY.reverb;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.SuNnY.reverb.obj.ListingElement;
import com.loopj.android.image.SmartImageView;

public class GridAdaptor extends BaseAdapter {


	private Context _context;
	ArrayList<ListingElement> _data = new ArrayList<ListingElement>(); 
	public GridAdaptor(Context context) {
		super();
		this._context = context;
	}

	@Override
	public int getCount() {
		return _data.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null){  
			convertView = LayoutInflater.from(_context).inflate(R.layout.browse_item, null);
		}

		ListingElement item = _data.get(position);

		SmartImageView imageView= (SmartImageView)convertView.findViewById(R.id.item_image);


		imageView.setImageUrl(item.getPhotos()[0].get_links().getLarge_crop().getHref());

		TextView titleTV = (TextView)convertView.findViewById(R.id.item_title);
		TextView priceTV = (TextView)convertView.findViewById(R.id.item_price);
		titleTV.setText(_data.get(position).getTitle());
		priceTV.setText(_data.get(position).getPrice().getSymbol() + _data.get(position).getPrice().getAmount());

		return convertView;
	}
	public void addData(ListingElement[] listingElements) {
		for (ListingElement item : listingElements){
			_data.add(item);
		}
		notifyDataSetChanged();
	}
}