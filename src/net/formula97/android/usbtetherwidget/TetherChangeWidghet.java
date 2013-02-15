package net.formula97.android.usbtetherwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

/**
 * @author kazutoshi
 *
 */
public class TetherChangeWidghet extends AppWidgetProvider {

	/* (非 Javadoc)
	 * @see android.appwidget.AppWidgetProvider#onEnabled(android.content.Context)
	 */
	@Override
	public void onEnabled(Context context) {
		// TODO 自動生成されたメソッド・スタブ
		super.onEnabled(context);

		// 主たる処理を行うサービスを開始する
		Intent i = new Intent(context, WidgetManagerServices.class);
		context.startService(i);
	}

	/* (非 Javadoc)
	 * @see android.appwidget.AppWidgetProvider#onDisabled(android.content.Context)
	 */
	@Override
	public void onDisabled(Context context) {
		// TODO 自動生成されたメソッド・スタブ
		super.onDisabled(context);

		// 開始されている（はずの）サービスを停止する
		Intent i = new Intent(context, WidgetManagerServices.class);
		context.stopService(i);
	}

	/* (非 Javadoc)
	 * @see android.appwidget.AppWidgetProvider#onUpdate(android.content.Context, android.appwidget.AppWidgetManager, int[])
	 */
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO 自動生成されたメソッド・スタブ
		super.onUpdate(context, appWidgetManager, appWidgetIds);

	}

	/* (非 Javadoc)
	 * @see android.appwidget.AppWidgetProvider#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO 自動生成されたメソッド・スタブ
		super.onReceive(context, intent);

	}
}
