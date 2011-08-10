package idv.dwi2;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class IMEAndMenuDemoActivity extends Activity {
	/** Called when the activity is first created. */
	EditText editText1 = null, editText2 = null, editText3 = null,
			editText4 = null, editText5 = null, editText6 = null;

	Menu theMenu = null;
	private boolean isPasswordVisible;
	public static String TAG="IMEAndMenuDemoActivity";
	
	public void makeToast(String msg)
	{
		Log.i(TAG, "makeToast "+msg);
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
		editText6 = (EditText) findViewById(R.id.editText6);
		editText6.setOnEditorActionListener(new BasicEditorActionListener("去!!"));
		isPasswordVisible = false;
	}
	
//	@Override
//	public void onCreateContextMenu(ContextMenu menu, View v,
//			ContextMenuInfo menuInfo) {
//		// TODO Auto-generated method stub
//		super.onCreateContextMenu(menu, v, menuInfo);
//	}

	
	// option menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.i(TAG, "onCreateOptionsMenu()");
		theMenu = menu;
		new MenuInflater(getApplication()).inflate(R.menu.option_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.i(TAG, "onOptionsItemSelected()");
		Log.i(TAG, "item.getItemId()="+item.getItemId());
		
		switch (item.getItemId())
		{
			case R.id.visible_password:
				isPasswordVisible = true;
				setPasswordVisibility();
				break;
			case R.id.invisible_password:
				isPasswordVisible = false;
				setPasswordVisibility();
				break;
			case R.id.submenu:
				if (theMenu != null){
					MenuItem checkedItem;
					if (isPasswordVisible) {
						Log.i(TAG, "isPasswordVisible="+isPasswordVisible+" set R.id.visible_password as checked");
						checkedItem = (MenuItem)theMenu.findItem(R.id.visible_password);
					}
					else {
						Log.i(TAG, "isPasswordVisible="+isPasswordVisible+" set R.id.invisible_password as checked");
						checkedItem = (MenuItem)theMenu.findItem(R.id.invisible_password);
					}
					checkedItem.setChecked(true);
				}
				break;
			case R.id.close:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void setPasswordVisibility()
	{
		Log.i(TAG, "setPasswordVisibility()");
		Log.i(TAG, "set password visibility to "+isPasswordVisible);
		if (editText6 == null)
			editText6 = (EditText) findViewById(R.id.editText6);
		int inputType = InputType.TYPE_CLASS_TEXT;
		if (isPasswordVisible) {
			inputType |= InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
		}
		else {
			inputType |= InputType.TYPE_TEXT_VARIATION_PASSWORD;
		}
		editText6.setInputType(inputType);
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
			Log.i(TAG, "actionId="+actionId);
			makeToast(toastMessage);
			return false;
		}
		
	}
}