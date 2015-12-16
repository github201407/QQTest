package com.example.qqtest;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.example.qqtest.adapter.RecyclerAdapter;
import com.example.qqtest.utils.AppMarketUtil;
import com.example.qqtest.utils.VersionUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private RadioGroup mRadioGroup;
	private ImageButton mAddButton;
	private boolean isPass = false;
	private boolean isShow = false;
	private Animation fade_in;
	private Animation fade_out;
	private LinearLayout mAppMarketBar;

	private RecyclerView mRecyclerView;
	private SwipeRefreshLayout swipeRefreshLayout;
	private TextView tv;
	private ListView listView;
	private Toast toast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		toast = Toast.makeText(getApplicationContext(), "确定退出？", 0);
		mRadioGroup = (RadioGroup) findViewById(R.id.bottom_bar);
		mAddButton = (ImageButton) findViewById(R.id.iv_add);
		fade_in = AnimationUtils.loadAnimation(this, R.anim.translate_down);
		// fade_in = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
		fade_out = AnimationUtils.loadAnimation(this, R.anim.translate_up);
		mAddButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				int id = mRadioGroup.getCheckedRadioButtonId();
				if (id != -1) {
					isPass = true;
					mRadioGroup.clearCheck();
					isPass = false;
				}
				isShow = !isShow;
				if (isShow) {
					mAddButton.setImageResource(R.drawable.menu_add_rotate);
					Toast.makeText(getBaseContext(), "Show", Toast.LENGTH_SHORT).show();
					// mAddButton.startAnimation(fade_in);
				} else {
					mAddButton.setImageResource(R.drawable.memu_add_selector);
					Toast.makeText(getBaseContext(), "Close", Toast.LENGTH_SHORT).show();
					// mAddButton.startAnimation(fade_out);
				}
			}
		});

		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				if (isPass)
					return;
				switch (checkedId) {
				case R.id.tv_homepage:
					showVersionInfo();
					break;
				case R.id.tv_mine:
					Intent intent = new Intent();
					intent.setClassName("com.android.settings", "com.android.settings.Settings");
					startActivity(intent);
					
					break;
				case R.id.tv_check:
					Intent intent1 =  new Intent(Settings.ACTION_SETTINGS);  
			        startActivity(intent1);
					break;
				case R.id.tv_message:

					break;

				default:
					break;
				}
				Toast.makeText(getBaseContext(), ((RadioButton) findViewById(checkedId)).getText().toString(), Toast.LENGTH_SHORT).show();
			}
		});

		ArrayList<String> mAppMarkets = AppMarketUtil.showLocalMarket(getBaseContext());
		mAppMarketBar = (LinearLayout) findViewById(R.id.appMarket);
		for (String string : mAppMarkets) {
			Button mButton = new Button(getBaseContext());
			mButton.setText(string);
			mButton.setOnClickListener(mBtnClick);
			LinearLayout.LayoutParams lp = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
			lp.weight = 1;
			mAppMarketBar.addView(mButton, lp);

		}

		recyclerViewUsage();
		swipeRefreshLayout();
	}

	private void swipeRefreshLayout() {
		tv = (TextView) findViewById(R.id.text);
		listView = (ListView) findViewById(R.id.listView);
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			list.add("item " + i);
		}
		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, list);
		listView.setAdapter(mAdapter);

		swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
		// 设置刷新时动画的颜色，可以设置4个
		swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light,
				android.R.color.holo_green_light);
		swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				tv.setVisibility(View.VISIBLE);
				tv.setText("正在刷新");
				tv.startAnimation(fade_in);

				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						tv.setText("刷新完成");
						swipeRefreshLayout.setRefreshing(false);
						// tv.postDelayed(new Runnable() {
						//
						// @Override
						// public void run() {
						tv.setVisibility(View.GONE);
						tv.startAnimation(fade_out);

						// }
						// }, 500);
					}
				}, 3000);

			}
		});

	}

	private OnClickListener mBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			AppMarketUtil.openAppAtMarket(getBaseContext(), getPackageName(), ((Button) v).getText().toString());

		}
	};
	private boolean isState = true;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void showVersionInfo() {
		int verCode = VersionUtils.getVersionCode(getBaseContext());
		String verName = VersionUtils.getVersionName(getBaseContext());
		showInfo("code:" + verCode + ";name:" + verName);
	}

	private void showInfo(String info) {
		Toast.makeText(getBaseContext(), info, Toast.LENGTH_SHORT).show();
	}

	private void recyclerViewUsage() {
		mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		// 设置布局管理器
		mRecyclerView.setLayoutManager(layoutManager);

		String[] dataset = new String[100];
		for (int i = 0; i < dataset.length; i++) {
			dataset[i] = "item" + i;
		}
		RecyclerAdapter mAdapter = new RecyclerAdapter(dataset);
		mRecyclerView.setAdapter(mAdapter);

	}

	@Override
	public void onBackPressed() {
//		if (isState) { // isState初始值为true
//			isState = false;
//			Toast.makeText(this, "请双击back退出程序", Toast.LENGTH_SHORT).show();
//			new Timer().schedule(new TimerTask() {
//
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//					isState = true;
//				}
//			}, 2000);
//		} else {
//			finish();
//		}
		quitToast();
	}

	private void quitToast() {
		if (null == toast.getView().getParent()) {
			toast.show();
		} else {
			System.exit(0);
		}
	}

}
