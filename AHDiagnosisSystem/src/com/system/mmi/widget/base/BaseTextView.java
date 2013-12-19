package com.system.mmi.widget.base;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

public class BaseTextView extends TextView{
	
	public BaseTextView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public BaseTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public BaseTextView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		this.setTextColor(Color.GRAY);
		this.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
	}
}
