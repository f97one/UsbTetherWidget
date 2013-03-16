/**
 *
 */
package net.formula97.android.usbtetherwidget;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

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

		// PreferenceActivity#addPreferencefromResourceがAPI Level 11から
		// Deprecatedになっていることへの対策
		//addPreferencesFromResource(R.xml.settings_main);
		getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
	}

	/**
	 * 手っ取り早い対策として、PreferenceFragmentを継承したfragmentクラスを
	 * 作成した。
	 * @author kazutoshi
	 * @see http://stackoverflow.com/questions/6822319/what-to-use-instead-of-addpreferencesfromresource-in-a-preferenceactivity
	 */
	public static class MyPreferenceFragment extends PreferenceFragment {

		/* (非 Javadoc)
		 * @see android.preference.PreferenceFragment#onCreate(android.os.Bundle)
		 */
		@Override
		public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);

            // PreferenceFragment#addPreferencesFromResourceでプリファレンスを
            // 呼び出す
            addPreferencesFromResource(R.xml.settings_main);
        }
	}
}
