package net.formula97.android.usbtetherwidget;

//import mediba.ad.sdk.android.openx.MasAdListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_showstatus)
public class ShowStatus extends Activity {

	// Mediba Ad表示用
//	MasAdView masAdView = null;
//	private static final String TAG = new String("MedibaAdUseXmlActivity");
//	private static final String MY_AUID = new String("61a0a296472d71e05a72de4e665a3d697e4d457ab324253e");

	// ウィジェット類の宣言
	@ViewById(R.id.tv_tether_capability)
	TextView tv_tether_capability;
	@ViewById(R.id.tv_usb_connection_status)
	TextView tv_usb_connection_status;
	@ViewById(R.id.tv_wifi_control_status)
	TextView tv_wifi_control_status;
	@ViewById(R.id.tv_wimax_control_status)
	TextView tv_wimax_control_status;

	// プリファレンスの読み書き用
	SharedPreferences spm;
	SharedPreferences.Editor spmEditor;
	private String prefName = "TETHER_CONTROLLER";

	/* (非 Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_showstatus);

		// プリファレンスの読み書き用宣言
		//   PreferenceManagerを使わないと、どういうわけかプリファレンスの
		//   読み書きができないのだが？？？
		spm = PreferenceManager.getDefaultSharedPreferences(this);
		spmEditor = spm.edit();

		// ステータス更新情報を取得するため、WidgetManagerServiceを
		// サービス起動する
		SharedPreferences pre = this.getSharedPreferences(prefName, MODE_PRIVATE);
		boolean tested = pre.getBoolean("IsAlreadyTestedTether", false);

		if (!tested) {
			Context ctx = getBaseContext();
			Intent i = new Intent(ctx, WidgetManagerServices.class);
			ctx.startService(i);
		}

		// adviewのリソース取得
//		masAdView = (MasAdView) findViewById(R.id.adview);
//		masAdView.setAuid(MY_AUID);
//		masAdView.setAdListener(new adListener());
	}

	/* (非 Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_configuration, menu);
		return true;
	}

	/* (非 Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO 自動生成されたメソッド・スタブ

		switch (item.getItemId()) {
		case R.id.menu_settings:
			startActivity(new Intent(this, EditPreference.class));
			return true;
			//break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	/* (非 Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();

		// 広告表示の停止
//		masAdView.stop();
	}

	/* (非 Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();

		showAvaliableHardwares();

		// Wi-FiとWiMAXの制御設定を取得して画面に反映する
		setWifiControlStatusToTv();
		setWimaxControlStatusToTv();

		// テザリングAPIが使用可能かを調べて、画面に反映する
		SharedPreferences pre = this.getSharedPreferences(prefName, MODE_PRIVATE);
		boolean tested = pre.getBoolean("IsAlreadyTestedTether", false);
		boolean apiOk = pre.getBoolean("IsTetheringEnable", false);
		int bannerId = 0;

		if (apiOk) {
			bannerId = R.string.status_valid;
		} else {
			bannerId = R.string.status_invalid;
		}

		if (tested) {
			tv_tether_capability.setText(bannerId);
		} else {
			tv_tether_capability.setText(R.string.status_unknown);
		}

		// 広告のロード開始
//		masAdView.start();

		// サービスを停止する
		Context ctx = getBaseContext();
		Intent i = new Intent(ctx, WidgetManagerServices.class);

		if (!tested) {
			ctx.stopService(i);
		}
	}

	/**
	 * Wi-Fi制御設定をプリファレンスから取得し、TextViewにセットする。
	 */
	private void setWifiControlStatusToTv() {
		//SharedPreferences pre = this.getSharedPreferences(prefName, MODE_PRIVATE);
		boolean wifiControl = spm.getBoolean("WiFi_control", false);

		Log.d("setWiFiControlStatusToTv", "Wi-Fi control setting is " + String.valueOf(wifiControl));

		if (wifiControl) {
			tv_wifi_control_status.setText(R.string.staus_enabled);
		} else {
			tv_wifi_control_status.setText(R.string.status_disabled);
		}
	}

	/**
	 * WiMAX制御設定をプリファレンスから取得し、TextViewにセットする。
	 */
	private void setWimaxControlStatusToTv() {
		//SharedPreferences pre = this.getSharedPreferences(prefName, MODE_PRIVATE);
		boolean wimaxControl = spm.getBoolean("WiMAX_control", false);

		Log.d("setWiFiControlStatusToTv", "WiMAX control setting is " + String.valueOf(wimaxControl));

		if (wimaxControl) {
			tv_wimax_control_status.setText(R.string.staus_enabled);
		} else {
			tv_wimax_control_status.setText(R.string.status_disabled);
		}
	}

	/**
	 * サポートしているハードウェアのリストをLogCatに流す。主にデバッグ用。
	 */
	private void showAvaliableHardwares() {

		String tag1 = "showAvailableHardwares";

		// PackageManagerを取得
		PackageManager pm = getPackageManager();

		// システムがサポートする機能一覧を取得
		FeatureInfo fi[] = pm.getSystemAvailableFeatures();

		// 機能一覧の取得に失敗している場合はFetureInfo[]がnullになっているので、
		// nullでない場合だけLogCatに流す。
		if (fi != null) {
			Log.i(tag1, "Available Hardware information list obtained, List of these as follow:");

			// 配列を先頭からLogCatに流す
			for (int i = 0; i < fi.length; i++) {
				if (fi[i].name != null) {
					Log.i(tag1, "    " + fi[i].name);
				} else {
					Log.w(tag1, "    FeatureInfo Index(" + String.valueOf(i) + ") is nothing.");
				}
			}
		} else {
			Log.w(tag1, "Could not obtain available Hardware feature.");
		}
	}

	/**
	 * 広告コントロール用クラス
	 * @author kazutoshi
	 *
	 */
/*	private class adListener extends MasAdListener {

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
*/
}
