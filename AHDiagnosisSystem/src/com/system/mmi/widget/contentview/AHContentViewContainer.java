package com.system.mmi.widget.contentview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

public class AHContentViewContainer extends RelativeLayout{

	private Context mContext;
	
	public AHContentViewContainer(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public AHContentViewContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public AHContentViewContainer(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context){
		
	}
	
	public void setViewById(int group, int child){
		
	}
	
	private void readContentFromAsset() throws IOException{
		InputStream is = mContext.getAssets().open("home_navigation");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuffer input = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null) {
			input.append(line);
		}
		parserJsonContent(input);
	}
	
	private void parserJsonContent(StringBuffer sb){
		String result = sb.toString();
		result = result.replaceAll("\r", "");
		result = result.replaceAll("	", "");
		try {
			JSONObject json = new JSONObject(result);
			JSONArray turorialArray = json.getJSONArray("nowplaying");
			for (int i = 0; i < turorialArray.length(); i++) {
				JSONArray categoryArray = turorialArray.getJSONObject(i).getJSONArray("category");
				for (int j = 0; j < categoryArray.length(); j++) {
					JSONObject imageObject = categoryArray.getJSONObject(j);
					String image = imageObject.getString("image_"+j);
					Log.e("", "image:  "+ image);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
