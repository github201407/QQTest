package com.example.qqtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class JumpActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		String data = intent.getDataString();
		System.out.println(data);
	}
}
