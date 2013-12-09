package com.example.jsonsaurabtask;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomExpandListAdapter extends BaseExpandableListAdapter {

	Context context;  
	ArrayList<String> email, names, country, userID, gender, photo, phone,
			postalcode;
	ArrayList<BeanClass_Group> group_data;
	ArrayList<BeanClass_Child> child_data;
	LayoutInflater inflater;
	ViewHolderChild holderchild;
	ViewHolderGroup holdergroup;
	TextView tv, tv2;

	public CustomExpandListAdapter(Context context,
			ArrayList<BeanClass_Group> group_data,
			ArrayList<BeanClass_Child> child_data) {
		super();
		this.context = context;
		this.group_data = group_data;
		this.child_data = child_data;

		email = new ArrayList<String>();
		phone = new ArrayList<String>();
		names = new ArrayList<String>();
		photo = new ArrayList<String>();
		userID = new ArrayList<String>();
		country = new ArrayList<String>();
		postalcode = new ArrayList<String>();
		gender = new ArrayList<String>();

		names = group_data.get(0).getNames();
		email = child_data.get(0).getEmail();
		phone = child_data.get(0).getPhone();
		photo = child_data.get(0).getPhoto();
		userID = child_data.get(0).getUserID();
		country = child_data.get(0).getCountry();
		gender = child_data.get(0).getGender();
		postalcode = child_data.get(0).getPostalcode();

	}

	@Override
	public Object getChild(int arg0, int arg1) {

		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {

		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		if (convertView == null) {
			holderchild = new ViewHolderChild();
			inflater = (LayoutInflater) context
					.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.child_row, null);

			holderchild.layout_child = (LinearLayout) convertView
					.findViewById(R.id.linear_layout_child);

			holderchild.txt_child_userid = (TextView) convertView
					.findViewById(R.id.txt_child_userid);
			holderchild.txt_child_name = (TextView) convertView
					.findViewById(R.id.txt_child_name);
			holderchild.txt_child_email = (TextView) convertView
					.findViewById(R.id.txt_child_email);
			holderchild.txt_child_gender = (TextView) convertView
					.findViewById(R.id.txt_child_gender);
			holderchild.txt_child_phone = (TextView) convertView
					.findViewById(R.id.txt_child_phone);

			holderchild.txt_child_postalcode = (TextView) convertView
					.findViewById(R.id.txt_child_postalcode);

			holderchild.txt_child_country = (TextView) convertView
					.findViewById(R.id.txt_child_country);

			convertView.setTag(holderchild);
		}

		else {
			holderchild = (ViewHolderChild) convertView.getTag();
		}

		holderchild.txt_child_userid.setText("userID : "
				+ userID.get(groupPosition));
		holderchild.txt_child_name.setText("names : "
				+ names.get(groupPosition));
		holderchild.txt_child_email.setText("email : "
				+ email.get(groupPosition));
		holderchild.txt_child_gender.setText("gender : "
				+ gender.get(groupPosition));
		holderchild.txt_child_country.setText("country : "
				+ country.get(groupPosition));
		holderchild.txt_child_phone.setText("phone : "
				+ phone.get(groupPosition));
		holderchild.txt_child_postalcode.setText("postalcode : "
				+ postalcode.get(groupPosition));

		return convertView;
	}

	public class ViewHolderGroup {
		LinearLayout layout_group;
		TextView txt_name;

		ImageView img_group_1;
	}

	public class ViewHolderChild {
		LinearLayout layout_child;

		TextView txt_child_userid;
		TextView txt_child_name;
		TextView txt_child_email;
		TextView txt_child_country;
		TextView txt_child_gender;
		TextView txt_child_postalcode;
		TextView txt_child_phone;

	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;

	}

	@Override
	public Object getGroup(int groupPosition) {

		return null;
	}

	@Override
	public int getGroupCount() {

		return names.size();
	}

	@Override
	public long getGroupId(int groupPosition) {

		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		if (convertView == null) {
			holdergroup = new ViewHolderGroup();
			inflater = (LayoutInflater) context
					.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.group_row, null);

			holdergroup.txt_name = (TextView) convertView
					.findViewById(R.id.txt_name);

			holdergroup.img_group_1 = (ImageView) convertView
					.findViewById(R.id.imgage_view_group);

			convertView.setTag(holdergroup);
		}

		else {
			holdergroup = (ViewHolderGroup) convertView.getTag();
		}

		/**
		 * error due to this _ | | V V
		 */

		holdergroup.txt_name.setText(names.get(groupPosition));

		holdergroup.img_group_1.setImageResource(R.drawable.ic_launcher);
		// Log.v(names.get(groupPosition), "hello");
		// System.out.println("names::::" + names.get(groupPosition));

		return convertView;
	}

	@Override
	public boolean hasStableIds() {

		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {

		return true;
	}

}
