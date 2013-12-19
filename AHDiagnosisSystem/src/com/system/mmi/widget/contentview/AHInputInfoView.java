package com.system.mmi.widget.contentview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.system.mmi.home.R;
import com.system.mmi.widget.base.BaseButton;
import com.system.mmi.widget.base.BaseContentView;
import com.system.mmi.widget.base.BaseEditText;

public class AHInputInfoView extends BaseContentView implements OnClickListener{
	
	private final String url = "content://com.system.data.provider.SQLiteProvider/presence_record";
	
	private Context mContext;
	
	private BaseEditText editName;
	private BaseEditText editId;
	private BaseEditText editSex;
	private BaseEditText editBirthday;
	private BaseEditText editMoblie;
	private BaseEditText editAddress;
	private BaseEditText editArmyTime;
	private BaseEditText editArmyNum;
	
	private BaseButton btnReadInfo;
	private BaseButton btnSave;
	private BaseButton btnClear;
	private BaseButton btnDiagnosis;
	public AHInputInfoView(Context context) {
		super(context);
		mContext = context;
		LayoutInflater mInflater = LayoutInflater.from(context);
		View rootView = mInflater.inflate(R.layout.view_input_info, null, false);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		this.addView(rootView,params);
		
		editName = (BaseEditText) rootView.findViewById(R.id.edit_name);
		editId = (BaseEditText) rootView.findViewById(R.id.edit_id);
		editSex = (BaseEditText) rootView.findViewById(R.id.edit_sex);
		editBirthday = (BaseEditText) rootView.findViewById(R.id.edit_birthdy);
		editMoblie = (BaseEditText) rootView.findViewById(R.id.edit_mobile);
		editAddress = (BaseEditText) rootView.findViewById(R.id.edit_address);
		editArmyTime = (BaseEditText) rootView.findViewById(R.id.edit_army_time);
		editArmyNum = (BaseEditText) rootView.findViewById(R.id.edit_army_num);
		
		btnReadInfo = (BaseButton) rootView.findViewById(R.id.button_read_info);
		btnSave = (BaseButton) rootView.findViewById(R.id.button_save);
		btnClear = (BaseButton) rootView.findViewById(R.id.button_clear);
		btnDiagnosis = (BaseButton) rootView.findViewById(R.id.button_diagnosis);
		
		btnReadInfo.setOnClickListener(this);
		btnSave.setOnClickListener(this);
		btnClear.setOnClickListener(this);
		btnDiagnosis.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_read_info:
			readInfo();
			break;
		case R.id.button_save:
			saveInfo();
			break;
		case R.id.button_clear:
			
			break;
		case R.id.button_diagnosis:
			
			break;
		default:
			break;
		}
		
	}
	
	private void saveInfo(){
		ContentValues values = new ContentValues();
		values.put("id", editId.getText().toString());
		values.put("name", editName.getText().toString());
		values.put("birthday", editBirthday.getText().toString());
		values.put("sex", editSex.getText().toString());
		values.put("mobile", editMoblie.getText().toString());
		values.put("address", editAddress.getText().toString());
		values.put("armyTime", editArmyTime.getText().toString());
		values.put("armyNum", editArmyNum.getText().toString());
		mContext.getContentResolver().insert(Uri.parse(url), values);
	}
	
	//TODO: for test
	private void readInfo(){
		String[] selection = {"1","YeDing","11"};
		Cursor cursor = mContext.getContentResolver().query(Uri.parse(url), null, null, selection, null);
		if (cursor != null && cursor.moveToFirst()) {
			for (int i = 0; i < cursor.getCount(); i++) {
				editBirthday.setText(cursor.getString(cursor.getColumnIndex("birthday")));
				editSex.setText(cursor.getString(cursor.getColumnIndex("sex")));
				Log.e("",cursor.getString(cursor.getColumnIndex("id")));
				Log.e("",cursor.getString(cursor.getColumnIndex("name")));
				Log.e("",cursor.getString(cursor.getColumnIndex("sex")));
			}
			cursor.close();
		}
		
		
	}

}
