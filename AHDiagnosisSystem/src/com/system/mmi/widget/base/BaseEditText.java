package com.system.mmi.widget.base;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.EditText;

public class BaseEditText extends EditText{
	
	public BaseEditText(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public BaseEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public BaseEditText(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){

	}
}
