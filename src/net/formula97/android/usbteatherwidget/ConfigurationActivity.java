package net.formula97.android.usbteatherwidget;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import mediba.ad.sdk.android.openx.MasAdListener;
import mediba.ad.sdk.android.openx.MasAdView;

public class ConfigurationActivity extends Activity {

	// Mediba Ad表示用
	MasAdView masAdView = null;
	private static final String TAG = new String("MedibaAdUseXmlActivity");
	private static final String MY_AUID = new String("61a0a296472d71e05a72de4e665a3d697e4d457ab324253e");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configuration);

		// adviewのリソース取得
		masAdView = (MasAdView) findViewById(R.id.adview);
		masAdView.setAuid(MY_AUID);
		masAdView.setAdListener(new adListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_configuration, menu);
		return true;
	}

	@Override
	protected void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();

		// 広告表示の停止
		masAdView.stop();
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();

		// 広告のロード開始
		masAdView.start();
	}

	/**
	 * 広告コントロール用クラス
	 * @author kazutoshi
	 *
	 */
	private class adListener extends MasAdListener {

		@Override
		public void onFailedToReceiveAd() {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void onFailedToReceiveRefreshedAd() {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void onInternalBrowserClose() {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void onInternalBrowserOpen() {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void onReceiveAd() {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void onReceiveRefreshedAd() {
			// TODO 自動生成されたメソッド・スタブ

		}

	}

}
