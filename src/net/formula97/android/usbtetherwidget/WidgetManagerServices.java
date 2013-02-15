/**
 *
 */
package net.formula97.android.usbtetherwidget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
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

}
