/**
 *
 */
package net.formula97.android.usbtetherwidget;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.widget.RemoteViews;

/**
 * @author kazutoshi
 *
 */
public class WidgetManagerServices extends Service {

	private static String ACTION_WIDGET_TOUCH = "net.formula97.intent.ACTION_WIDGET_TOUCH";
	private boolean isTetherConnected = false;
	/**
	 *
	 */
	public WidgetManagerServices() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/* (非 Javadoc)
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	/* (非 Javadoc)
	 * @see android.app.Service#onStartCommand(android.content.Intent, int, int)
	 */
	@Override
	@Deprecated
	public void onStart(Intent intent, int flags) {
		// TODO 自動生成されたメソッド・スタブ
		super.onStart(intent, flags);

		RemoteViews rv = new RemoteViews(getPackageName(), R.layout.layout_1x1);

		// インテントの定義
		Intent touchIntent = new Intent();
		touchIntent.setAction(ACTION_WIDGET_TOUCH);

		PendingIntent pendingIntent = PendingIntent.getService(this, 0, touchIntent, flags);
		rv.setOnClickPendingIntent(R.id.iv_connection_status, pendingIntent);

		if (ACTION_WIDGET_TOUCH.equals(intent.getAction())) {
			onClick(rv);
		}

		ComponentName cn = new ComponentName(getPackageName(), TetherChangeWidghet.class.getName());
		AppWidgetManager awm = AppWidgetManager.getInstance(this);
		awm.updateAppWidget(cn, rv);
	}

	private void onClick(RemoteViews rv) {
		// TODO 自動生成されたメソッド・スタブ

		int viewId = R.id.iv_connection_status;
		int srcId = 0;

		if (isTetherConnected) {
			srcId = R.drawable.pc_connected;
		} else {
			srcId = R.drawable.ic_launcher;
		}

		rv.setImageViewResource(viewId, srcId);
		isTetherConnected = !isTetherConnected;
	}

	/* (非 Javadoc)
	 * @see android.app.Service#onDestroy()
	 */
	@Override
	public void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
		startService(new Intent(this, WidgetManagerServices.class));
	}

	/**
	 * USBテザリングAPIを呼び出す。
	 * 非公開APIをリフレクションで呼び出しているので、動作しない場合もあるかもしれない。
	 * @return Object型、呼び出しに成功している場合はUSbテザリングAPIのオブジェクトを、失敗している場合はnullを返す。
	 */
	@SuppressWarnings("rawtypes")
	public Object getUsbTetherApi() {
		Class c = null;
		Field f = null;
		Object iconn = null;

		// ConnectivityManagerのサービス一覧を呼び出す
		ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);

		// CONNECTIVITY_SERVICEのクラスを呼び出す
		try {
			c = Class.forName(cm.getClass().getName());
		} catch (ClassNotFoundException e) {
			// スタックトレースをLogCatに流す
			e.printStackTrace();
		}

		// 呼びだしたクラスからフィールドを取得する
		try {
			f = c.getDeclaredField("mService");
			f.setAccessible(true);
		} catch (NoSuchFieldException e) {
			// スタックトレースをLogCatに流す
			e.printStackTrace();
		}

		try {
			iconn = f.get(cm);
		} catch (IllegalArgumentException e) {
			// スタックトレースをLogCatに流す
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// スタックトレースをLogCatに流す
			e.printStackTrace();
		}

		return iconn;
	}

	/**
	 * WiMAX接続のON/OFFを設定する。
	 * 非公開APIをリフレクションで呼び出しているので、動作しない場合もあるかもしれない。
	 * なお、WiMAXが使用可能か否かの判定は行っていないが、APIが用意されていない場合は
	 * NoSuchMethodExceptionが発生するので、一応はわかるかも？
	 * @param connectionEnabled boolean型、接続をONにするときはtrue、OFFにするときはfalseをセットする。
	 * @return boolean型、WiMAX接続の切り替えに成功したらtrue、失敗したらfalseを返す。
	 */
	public boolean enableWimaxConnection(boolean connectionEnabled) {
		boolean ret = false;

		// WiMAXのサービスを呼び出す
		Object wimaxmgr = (Object)getBaseContext().getSystemService("wimax");

		// WiMAX接続を切り替える
		// キャッチ可能な例外発生時は、スタックトレースをLogCatに流す
		try {
			Method wimaxEnable = wimaxmgr.getClass().getMethod("setWimaxEnabled", new Class[] { Boolean.TYPE });

			wimaxEnable.invoke(wimaxmgr, new Object[] { connectionEnabled });

			ret = true;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return ret;
	}
}
