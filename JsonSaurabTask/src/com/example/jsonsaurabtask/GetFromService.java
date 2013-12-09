package com.example.jsonsaurabtask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.SyncStateContract.Constants;

public class GetFromService {
	static StringBuilder builder;
	Context context;

	ProgressDialog progressDialog;

	HttpClient httpclient;
	int statusCode = 0;
	String urlPath, temp, status, message;
	JSONObject jsonObject, jsonObj, response;

	public GetFromService(Context context, String url, String dialogMessage) {

		this.context = context;

		this.urlPath = url;


	}

	public static String callGetMethod(String url) throws Exception {

		builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		/*httpGet.setHeader("Content-type", "application/json");
		httpGet.addHeader("Application-Type", "ANDROID");
		httpGet.addHeader("Application-Token",
				"AGDNSSON4SG0DV8O62RIT3KQBPMVNDA3P8L6LLBVS9GUA2CO57H6PPEBMKSQ1TAH");*/

		HttpResponse response = client.execute(httpGet);
		System.out.println("inside callGetMethod response is" + response.toString());
		StatusLine statusLine = response.getStatusLine();
		System.out.println("status line is" + statusLine);
		int statusCode = statusLine.getStatusCode();
		System.out.println("status code is" + statusCode);
		/* Method Get */
		if (statusCode == 200) {
			HttpEntity entity = response.getEntity();
			InputStream content = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					content));
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} else {
			System.err.println("error is service");
		}

		System.out.println("builder is" + builder);
		return builder.toString();

	}

	
	

}
