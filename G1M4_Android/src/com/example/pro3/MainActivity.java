package com.example.pro3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//commit test

public class MainActivity extends Activity {

	public static final int REQUEST_CODE_MENU = 1001;
	
	EditText usernameInput;
	EditText passwordInput;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		Button loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new OnClickListener(){
		
			public void onClick(View v){
				
				String username = usernameInput.getText().toString();
				String password = passwordInput.getText().toString();
				
				//Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
				Intent intent = new Intent(getApplicationContext(), CalendarMonthViewActivity.class);
				
				
				intent.putExtra("username", username);
				intent.putExtra("password", password);
				
				startActivityForResult(intent, REQUEST_CODE_MENU); 
				
			}
		});
		
		usernameInput = (EditText) findViewById(R.id.usernameInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
	}
	
	 protected void onActivityResult(int requestCode, int resultCode, Intent intent) { 
	        super.onActivityResult(requestCode, resultCode, intent); 
	  
	        if (requestCode == REQUEST_CODE_MENU) { 
	        	
	        	Log.d("menu_activity_log","this close");
	        	
	        	if( resultCode ==  RESULT_OK ){
		        	
	        		String menu = intent.getStringExtra("menu"); 
		            String message = intent.getStringExtra("message"); 
		  
		            Toast toast = Toast.makeText(getBaseContext(), "result code : " + resultCode + ", menu : " + menu + ", message : " + message, Toast.LENGTH_LONG); 
		        	toast.show(); 
	        	}
	        } 
	  
	    } 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
