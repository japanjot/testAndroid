package jj.singh.jsongoogledirection;

import android.R.integer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	// url to make request
			private static String url ="http://maps.googleapis.com/maps/api/distancematrix/json?origins=30.7353,76.7911&destinations=30.7800,76.6900&mode=driving&language=en-EN&sensor=false";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public class asyncclass extends AsyncTask<Void, integer, Void>
	{

		@Override
		protected Void doInBackground(Void... params) {
			
			return null;
		}}

}
