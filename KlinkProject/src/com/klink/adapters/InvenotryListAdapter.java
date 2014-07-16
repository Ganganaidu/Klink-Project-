package com.klink.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.klink.activity.InventoryListActivity;
import com.klink.activity.MyApplication;
import com.klink.activity.R;
import com.klink.model.CatalogProductListModel;
import com.klnk.views.BadgeView;

/** {@link InventoryListActivity}*/
public class InvenotryListAdapter extends ArrayAdapter<CatalogProductListModel> {

	private LayoutInflater layoutInflater;

	private Context context;
	private ArrayList<CatalogProductListModel> invenotryLists;
	private CatalogProductListModel mCatalogProductListModel;

	public InvenotryListAdapter(Context context, ArrayList<CatalogProductListModel> invenotryLists) {
		super(context, R.layout.row_invenotry_list,R.id.crop_imageView,invenotryLists);

		layoutInflater = LayoutInflater.from(context);
		this.context = context;
		this.invenotryLists = invenotryLists;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder mViewHolder = null;

		mCatalogProductListModel = (CatalogProductListModel) this.getItem(position);
		
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.row_invenotry_list, null);
			mViewHolder = new ViewHolder();

			mViewHolder.name_textView = (TextView) convertView.findViewById(R.id.name_textView);
			mViewHolder.price_textView  = (TextView)convertView.findViewById(R.id.price_textView);
			mViewHolder.crop_imageView  = (ImageView)convertView.findViewById(R.id.crop_imageView);
			mViewHolder.pluse_imageView  = (ImageView)convertView.findViewById(R.id.pluse_imageView);

			convertView.setTag(mViewHolder);

		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}

		mViewHolder.name_textView.setText(invenotryLists.get(position).getName());
		mViewHolder.price_textView.setText("$ "+String.valueOf(invenotryLists.get(position).getPrice()));

		mViewHolder.badge = new BadgeView(context, mViewHolder.crop_imageView);
		mViewHolder.badge.setBadgeBackgroundColor(Color.RED);
		mViewHolder.badge.setTextColor(Color.WHITE);

		MyApplication.imageLoader.displayImage(invenotryLists.get(position).getImage(), 
				mViewHolder.crop_imageView, MyApplication.options);

		mViewHolder.pluse_imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(context, "ONE "+position, Toast.LENGTH_SHORT).show();
			}
		});

		return convertView;
	}

	private class ViewHolder {

		private TextView name_textView,price_textView;
		private ImageView crop_imageView,pluse_imageView;
		private BadgeView badge;
	}
}
