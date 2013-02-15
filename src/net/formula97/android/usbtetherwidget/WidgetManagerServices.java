/**
 *
 */
package net.formula97.android.usbtetherwidget;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author kazutoshi
 *
 */
public class WidgetManagerServices extends Service {

	private static String ACTION_WIDGET_TOUCH = "net.formula97.intent.ACTION_WIDGET_TOUCH";

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

}
