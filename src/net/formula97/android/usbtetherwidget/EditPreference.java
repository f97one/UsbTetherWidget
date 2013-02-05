/**
 *
 */
package net.formula97.android.usbtetherwidget;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * @author kazutoshi
 *
 */
public class EditPreference extends PreferenceActivity {

	/* (非 Javadoc)
	 * @see android.preference.PreferenceActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings_main);
	}

}
