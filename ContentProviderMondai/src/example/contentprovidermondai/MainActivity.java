package example.contentprovidermondai;

import android.os.Bundle;
import android.provider.CallLog;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	ListView listview = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			Cursor cur = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null,
					null);

			if (cur.getCount() != 0) {
				int i = 0;
				String[] number = new String[cur.getCount()];
				while (cur.moveToNext()) {
					number[i] = cur.getString(cur.getColumnIndex(CallLog.Calls.NUMBER));
					i++;
				}
				setItems(number);
			} else {
				TextView message = new TextView(this);
				message.setText("データが取得できませんでした。");
				LinearLayout linearlayout = (LinearLayout) findViewById(R.id.ll_linearlayout);
				linearlayout.addView(message);
			}
		} catch (Exception e) {
			Log.e("ERROR", e.getMessage());
		}
	}

	private void setItems(String[] number) {
		listview = new ListView(this);
		setContentView(listview);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_list, number);

		listview.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
