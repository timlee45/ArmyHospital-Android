package com.system.mmi.widget.navigation;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListView;

import com.system.mmi.home.R;

public class AHExpandableListView extends ExpandableListView{

	private Context mContext;
	private AHExpandableListAdapter adapter;
	
	public AHExpandableListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public AHExpandableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public AHExpandableListView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		mContext = context;
		this.setGroupIndicator(null);
		this.setSelector(R.drawable.list_selector);
		this.setFocusable(true);
		this.setSelected(true);
		this.setOnGroupExpandListener(expandListener);
		
		ArrayList<AHExpandableListCell> dataList = new ArrayList<AHExpandableListCell>();
		String[] groupArray = mContext.getResources().getStringArray(R.array.home_menu);
		int[] childArrayId = {R.array.info_manager, R.array.diagnose, R.array.settings};
		int[] childArrayIcon = {R.drawable.info_manager, R.drawable.diagnose_icon, R.drawable.default_user};
		for (int i = 0; i< groupArray.length; i++){
			String[] childArray = mContext.getResources().getStringArray(childArrayId[i]);
			AHExpandableListCell cell = new AHExpandableListCell(i, childArrayIcon[i], groupArray[i], childArray);
			dataList.add(cell);
		}
		
		adapter = new AHExpandableListAdapter(mContext, dataList);
		this.setAdapter(adapter);
		this.expandGroup(0);
	}

	@Override
	public boolean performItemClick(View v, int position, long id) {
		// TODO Auto-generated method stub
		return super.performItemClick(v, position, id);
	}


	private OnGroupExpandListener expandListener = new OnGroupExpandListener() {
		
		@Override
		public void onGroupExpand(int groupPosition) {
			for (int i = 0; i < adapter.getGroupCount(); i++) {
				if (groupPosition != i && isGroupExpanded(groupPosition)) {
			        collapseGroup(i);
			    }
			}
		}
	};
	
}
