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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings_main);
	}

}
