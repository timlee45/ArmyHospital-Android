package com.system.mmi.widget.base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.Button;

public class BaseButton extends Button{

	public BaseButton(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public BaseButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public BaseButton(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		this.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
	}

}
