package net.formula97.android.usbtetherwidget;

import net.formula97.android.usbtetherwidget.R;
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
public class TetherChangeWidghet extends AppWidgetProvider {

	// 自分へのインテントフィルター
	final String filter = "net.formula97.android.usbteatherwidget.BUTTON_CLICKED";

	/* (非 Javadoc)
	 * @see android.appwidget.AppWidgetProvider#onUpdate(android.content.Context, android.appwidget.AppWidgetManager, int[])
	 */
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO 自動生成されたメソッド・スタブ
		super.onUpdate(context, appWidgetManager, appWidgetIds);

		// リモートビューのインスタンスを取得
		RemoteViews rViews = new RemoteViews(context.getPackageName(), R.layout.layout_1x1);

		// 自分自身へのインテントインスタンスを生成
		Intent intent = new Intent(filter);

		// イベント駆動にするための、ペンディングインテントのインスタンスを
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

		// リモートビューのボタンにひもづける
		rViews.setOnClickPendingIntent(R.id.ImageButton_WidgetMainBody, pendingIntent);

		// ウィジェットを更新する
		appWidgetManager.updateAppWidget(appWidgetIds, rViews);
	}

	/* (非 Javadoc)
	 * @see android.appwidget.AppWidgetProvider#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO 自動生成されたメソッド・スタブ
		super.onReceive(context, intent);

		// ウィジェットがタップされた際の挙動
		//   LogCatに状況を出す
		String action = intent.getAction();

		if (action.equals(filter)) {
			Log.d(context.getPackageName() + "#onReceive", "Button clicked.");
		}
	}
}
