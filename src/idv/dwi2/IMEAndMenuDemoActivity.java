package idv.dwi2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class IMEAndMenuDemoActivity extends Activity {
	/** Called when the activity is first created. */
	EditText editText1 = null, editText2 = null, editText3 = null,
			editText4 = null, editText5 = null;

	public void makeToast(String msg)
	{
		Log.i("IMEAndMenuDemoActivity", "makeToast "+msg);
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		editText2 = (EditText) findViewById(R.id.editText2);
		editText2.setOnEditorActionListener(
				new BasicEditorActionListener("送!!"));
		editText3 = (EditText) findViewById(R.id.editText3);
		editText3.setOnEditorActionListener(
				new BasicEditorActionListener());
	}
	
	class BasicEditorActionListener implements OnEditorActionListener {
		String toastMessage;
		
		BasicEditorActionListener() {
			toastMessage = "好!!";
		};
		
		BasicEditorActionListener(String msg) {
			toastMessage = msg;
		};
		
		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			Log.i("IMEAndMenuDemoActivity", "actionId="+actionId);
			makeToast(toastMessage);
			return false;
		}
		
	}
}