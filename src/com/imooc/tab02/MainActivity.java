package com.imooc.tab02;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private LinearLayout mTabWeixin;
	private LinearLayout mTabFrd;
	private LinearLayout mTabAddress;
	private LinearLayout mTabSettings;

	private ImageView mImgWeixin;
	private ImageView mImgFrd;
	private ImageView mImgAddress;
	private ImageView mImgSettings;

	private TextView mTvWeixin;
	private TextView mTvFrd;
	private TextView mTvAddress;
	private TextView mTvSettings;

	private Fragment mTab01;
	private Fragment mTab02;
	private Fragment mTab03;
	private Fragment mTab04;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initEvent();
		setSelect(0);
	}

	private void initEvent() {
		mTabWeixin.setOnClickListener(this);
		mTabFrd.setOnClickListener(this);
		mTabAddress.setOnClickListener(this);
		mTabSettings.setOnClickListener(this);
	}

	private void initView() {
		mTabWeixin = (LinearLayout) findViewById(R.id.id_tab_weixin);
		mTabFrd = (LinearLayout) findViewById(R.id.id_tab_frd);
		mTabAddress = (LinearLayout) findViewById(R.id.id_tab_address);
		mTabSettings = (LinearLayout) findViewById(R.id.id_tab_settings);

		mImgWeixin = (ImageView) findViewById(R.id.id_tab_weixin_img);
		mImgFrd = (ImageView) findViewById(R.id.id_tab_frd_img);
		mImgAddress = (ImageView) findViewById(R.id.id_tab_address_img);
		mImgSettings = (ImageView) findViewById(R.id.id_tab_settings_img);

		mTvWeixin = (TextView) findViewById(R.id.id_tab_weixin_tv);
		mTvFrd = (TextView) findViewById(R.id.id_tab_frd_tv);
		mTvAddress = (TextView) findViewById(R.id.id_tab_address_tv);
		mTvSettings = (TextView) findViewById(R.id.id_tab_settings_tv);
	}

	private void setSelect(int i) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		hideFragment(transaction);
		// 把图片设置为亮的
		// 设置内容区域
		switch (i) {
		case 0:
			if (mTab01 == null) {
				mTab01 = new WeixinFragment();
				transaction.add(R.id.id_content, mTab01);
			} else {
				transaction.show(mTab01);
			}
			mImgWeixin.setImageResource(R.drawable.tab_weixin_pressed);
			mTvWeixin.setTextColor(getResources().getColor(R.color.tab_text_color_selected));
			break;
		case 1:
			if (mTab02 == null) {
				mTab02 = new FrdFragment();
				transaction.add(R.id.id_content, mTab02);
			} else {
				transaction.show(mTab02);

			}
			mImgFrd.setImageResource(R.drawable.tab_find_frd_pressed);
			mTvFrd.setTextColor(getResources().getColor(R.color.tab_text_color_selected));
			break;
		case 2:
			if (mTab03 == null) {
				mTab03 = new AddressFragment();
				transaction.add(R.id.id_content, mTab03);
			} else {
				transaction.show(mTab03);
			}
			mImgAddress.setImageResource(R.drawable.tab_address_pressed);
			mTvAddress.setTextColor(getResources().getColor(R.color.tab_text_color_selected));
			break;
		case 3:
			if (mTab04 == null) {
				mTab04 = new SettingFragment();
				transaction.add(R.id.id_content, mTab04);
			} else {
				transaction.show(mTab04);
			}
			mImgSettings.setImageResource(R.drawable.tab_settings_pressed);
			mTvSettings.setTextColor(getResources().getColor(R.color.tab_text_color_selected));
			break;
		default:
			break;
		}
		transaction.commit();
	}

	private void hideFragment(FragmentTransaction transaction) {
		if (mTab01 != null) {
			transaction.hide(mTab01);
		}
		if (mTab02 != null) {
			transaction.hide(mTab02);
		}
		if (mTab03 != null) {
			transaction.hide(mTab03);
		}
		if (mTab04 != null) {
			transaction.hide(mTab04);
		}
	}

	@Override
	public void onClick(View v) {
		resetImgTexts();
		switch (v.getId()) {
		case R.id.id_tab_weixin:
			setSelect(0);
			break;
		case R.id.id_tab_frd:
			setSelect(1);
			break;
		case R.id.id_tab_address:
			setSelect(2);
			break;
		case R.id.id_tab_settings:
			setSelect(3);
			break;
		default:
			break;
		}
	}

	/**
	 * 切换图片至暗色，文字为暗色
	 */
	private void resetImgTexts() {
		mImgWeixin.setImageResource(R.drawable.tab_weixin_normal);
		mImgFrd.setImageResource(R.drawable.tab_find_frd_normal);
		mImgAddress.setImageResource(R.drawable.tab_address_normal);
		mImgSettings.setImageResource(R.drawable.tab_settings_normal);

		mTvWeixin.setTextColor(getResources().getColor(R.color.tab_text_color_unselected));
		mTvFrd.setTextColor(getResources().getColor(R.color.tab_text_color_unselected));
		mTvAddress.setTextColor(getResources().getColor(R.color.tab_text_color_unselected));
		mTvSettings.setTextColor(getResources().getColor(R.color.tab_text_color_unselected));
	}

	private boolean isExit = false;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (isExit == false) {
				isExit = true;
				Resources resource = (Resources) getBaseContext().getResources();
				String exit = resource.getString(R.string.again_exit);
				ToastView toast = new ToastView(getApplicationContext(), exit);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				handler.sendEmptyMessageDelayed(0, 3000);
				return true;
			} else {
				finish();
				ToastView.cancel();
				return false;
			}
		}
		return true;
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			isExit = false;
		}
	};

}
