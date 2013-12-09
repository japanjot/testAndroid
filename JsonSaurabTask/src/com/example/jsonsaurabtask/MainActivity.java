package com.example.jsonsaurabtask;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;

import android.view.Menu;
import android.widget.ExpandableListView;

public class MainActivity extends Activity {
	ExpandableListView exlv;
	ArrayList<String> email, names, country, userID, gender, photo, phone,
			postalcode;
	ArrayList<BeanClass_Group> group_data;
	ArrayList<BeanClass_Child> child_data;
	ArrayList<String> dummy;

	BeanClass_Group bean_Group;
	BeanClass_Child bean_Child;

	String response = null;
	// url to make request
	private static String url = "http://dotnetstg.seasiaconsulting.com/SpinnerclubServices/Friends.svc/GetFriendRequestCount/7";
	ArrayList<ArrayList<String>> jsondata;
	// JSON Node names
	// private static final String TAG_CONTACTS =
	// "RespondToFriendRequestResult";

	private static final String TAG_RESPOND = "RespondToFriendRequestResult";
	private static final String TAG_CONTACTS = "GetFriendRequestCountResult";
	private static final String TAG_ID = "UserId";
	private static final String TAG_NAME = "UserFullName";
	private static final String TAG_EMAIL = "UserEmail";
	private static final String TAG_GENDER = "UserGender";
	private static final String TAG_PHONE = "UserPhone";
	private static final String TAG_POSTCODE = "UserPostCode";
	private static final String TAG_PHOTO = "UserPhoto";
	private static final String TAG_COUNTRY = "UserCountry";

	// contacts JSONArray
	JSONArray contacts = null;

	JSONArray contacts2 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		exlv = (ExpandableListView) findViewById(R.id.expandableListView1);
		email = new ArrayList<String>();
		phone = new ArrayList<String>();
		names = new ArrayList<String>();
		photo = new ArrayList<String>();
		userID = new ArrayList<String>();
		country = new ArrayList<String>();
		postalcode = new ArrayList<String>();
		gender = new ArrayList<String>();

		group_data = new ArrayList<BeanClass_Group>();
		child_data = new ArrayList<BeanClass_Child>();
		bean_Group = new BeanClass_Group();
		

	

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		asyncclass as = new asyncclass();
		as.execute();
	}

	public void addmember(String email, String names, String country,
			String userID, String gender, String photo, String postalcode,
			String phone) {
		this.email.add(email);
		this.names.add(names);
		this.country.add(country);
		this.userID.add(userID);
		this.gender.add(gender);
		this.photo.add(photo);
		this.phone.add(phone);
		this.postalcode.add(postalcode);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class asyncclass extends AsyncTask<Void, Integer, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			try {
				response = GetFromService.callGetMethod(url);
				JSONObject jsonObject = new JSONObject(response);
				contacts = jsonObject.getJSONArray(TAG_CONTACTS);
				for (int i = 0; i < contacts.length(); i++) {
					JSONObject jsonObject2 = contacts.getJSONObject(i);
					contacts2 = jsonObject2.getJSONArray(TAG_RESPOND);

				}
				// System.out.println(".......................real contacts is ... is"+contacts2);
				for (int i = 0; i < contacts2.length(); i++) {
					JSONObject jsonObject3 = contacts2.getJSONObject(i);
					String name = jsonObject3.getString(TAG_NAME);
					String userid = jsonObject3.getString(TAG_ID);
					String email = jsonObject3.getString(TAG_EMAIL);
					String gender = jsonObject3.getString(TAG_GENDER);
					String phone = jsonObject3.getString(TAG_PHONE);
					String postalcode = jsonObject3.getString(TAG_POSTCODE);
					String photo = jsonObject3.getString(TAG_PHOTO);
					String country = jsonObject3.getString(TAG_COUNTRY);
					addmember(email, name, country, userid, gender, photo,
							postalcode, phone);

					System.out
							.println(".......................final contacts are  "
									+ userid
									+ "     :    "
									+ name
									+ "  :  "
									+ email
									+ "   :   "
									+ gender
									+ "  :  "
									+ country
									+ "  :  "
									+ phone
									+ "  :  "
									+ photo + "  :  " + postalcode);

				}

			} catch (Exception e) {
				System.out.println("li60error is is " + e.toString());
				e.printStackTrace();
			}
			// System.out.println("Response from service is "+response);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		
			bean_Child = new BeanClass_Child();

			bean_Child.setEmail(email);
			bean_Child.setPhone(phone);
			bean_Child.setCountry(country);
			bean_Child.setGender(gender);
			bean_Child.setUserID(userID);
			bean_Child.setNames(names);
			bean_Child.setPostalcode(postalcode);
			bean_Child.setPhoto(photo);

			bean_Group.setNames(names);

			group_data.add(bean_Group);

			child_data.add(bean_Child);
			
			CustomExpandListAdapter cad = new CustomExpandListAdapter(
					MainActivity.this, group_data, child_data);

			exlv.setAdapter(cad);
			
		}
		
	}

}
