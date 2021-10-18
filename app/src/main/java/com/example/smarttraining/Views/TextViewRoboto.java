package com.example.smarttraining.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.smarttraining.R;

public class TextViewRoboto extends AppCompatTextView {

	public TextViewRoboto(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		this.applyStyle(context, attrs);
	}

	public TextViewRoboto(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.applyStyle(context, attrs);
	}

	private void applyStyle(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextViewRoboto);
		int cf = a.getInteger(R.styleable.TextViewRoboto_fontName, 0);
		int fontName;
		switch (cf) {
			case 1:
				fontName = R.string.Roboto_Bold;
				break;
			case 2:
				fontName = R.string.Roboto_Light;
				break;
			case 3:
				fontName = R.string.Roboto_Medium;
				break;
			case 4:
				fontName = R.string.Roboto_Thin;
				break;
			default:
				fontName = R.string.Roboto_Medium;
				break;
		}

		String customFont = getResources().getString(fontName);

		Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + customFont + ".ttf");
		setTypeface(tf);
		a.recycle();
	}
}
