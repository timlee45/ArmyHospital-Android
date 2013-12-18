package com.system.mmi.widget.navigation;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.system.mmi.home.R;

public class AHExpandableListAdapter extends BaseExpandableListAdapter{

	private Context mContext;
	private LayoutInflater mInflater;
	private ArrayList<AHExpandableListCell> groupList;
	
	public AHExpandableListAdapter(Context context, ArrayList<AHExpandableListCell> dataList){
		this.mContext = context;
		this.groupList = dataList;
		mInflater = LayoutInflater.from(mContext);
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return this.groupList.get(groupPosition).getChildren().get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return this.groupList.get(groupPosition).getChildren().get(childPosition).getId();
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		Holder holder = null;
		if (convertView == null) {
			holder = new Holder();
			convertView = mInflater.inflate(R.layout.cell_expandable_list_child,null, false);
			holder.title = (TextView) convertView.findViewById(R.id.child_title);
			convertView.setTag(holder);
		}else{
			holder = (Holder) convertView.getTag();
		}
		String str_title = groupList.get(groupPosition).getChildren().get(childPosition).getTitle();
		holder.title.setText(str_title);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.groupList.get(groupPosition).getChildren().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.groupList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.groupList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return this.groupList.get(groupPosition).getId();
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		Holder holder = null;
		if (convertView == null) {
			holder = new Holder();
			convertView = mInflater.inflate(R.layout.cell_expandable_list_group, null, false);
			holder.title = (TextView) convertView.findViewById(R.id.group_title);
			holder.image = (ImageView) convertView.findViewById(R.id.group_image);
			convertView.setTag(holder);
		}else{
			holder = (Holder) convertView.getTag();
		}
		String str_title = groupList.get(groupPosition).getTitle();
		int imageId = groupList.get(groupPosition).getImageId();
		holder.title.setText(str_title);
		if(imageId>=0){
			holder.image.setImageResource(imageId);
		}
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	class Holder{
		public TextView title;
		public ImageView image;
	}

}
