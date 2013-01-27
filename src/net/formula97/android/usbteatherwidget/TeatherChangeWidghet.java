package net.formula97.android.usbteatherwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * @author kazutoshi
 *
 */
public class TeatherChangeWidghet extends AppWidgetProvider {

	// 自分へのインテントフィルター
	final String filter = "net.formula97.android.usbteatherwidget.BUTTON_CLICKED";

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO 自動生成されたメソッド・スタブ
		super.onUpdate(context, appWidgetManager, appWidgetIds);

		// リモートビューのインスタンスを取得
		RemoteViews rViews = new RemoteViews(context.getPackageName(), R.layout.layout_1x1);

		Intent intent = new Intent(filter);

		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

		rViews.setOnClickPendingIntent(R.id.ImageButton_WidgetMainBody, pendingIntent);

		appWidgetManager.updateAppWidget(appWidgetIds, rViews);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO 自動生成されたメソッド・スタブ
		super.onReceive(context, intent);

		String action = intent.getAction();

		if (action.equals(filter)) {
			Log.d(context.getPackageName() + "#onReceive", "Button clicked.");
		}
	}



}
