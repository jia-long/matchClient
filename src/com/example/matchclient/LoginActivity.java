package com.example.matchclient;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.plus.URLTools;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.common.tools.L;
import com.example.matchclient.constant.APIConstant;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
	private EditText et_userName, et_password;
	private Button b_login;
	private String TAG = "LoginActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		init();
	}

	private void init() {
		et_userName = (EditText) findViewById(R.id.et_userName);
		et_password = (EditText) findViewById(R.id.et_password);
		b_login = (Button) findViewById(R.id.b_login);
		b_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String userName = et_userName.getText().toString();
				String password = et_password.getText().toString();
				if (!TextUtils.isEmpty(userName)
						&& !TextUtils.isEmpty(password)) {
					login(userName, password);
				}
			}
		});
	}

	private void login(final String userName, final String password) {
		String url = URLTools.buildURL(APIConstant.SERVIER + "android/login",
				new String[] { "userName", "password" }, new String[] {
						"jialong", "123" });
		if (url == null) {
			L.e(TAG, "login url is null");

		} else {
			L.d(TAG, url);
			StringRequest joRequest = new StringRequest(Method.GET, url,
					responseListener(), errorListener());

			executeRequest(joRequest);
		}

	}

	public Response.Listener<String> responseListener() {
		return new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Toast.makeText(mActivity, response.toString(),
						Toast.LENGTH_SHORT).show();
				try {
					JSONObject json = new JSONObject(response);
					int state = json.getInt("state");

					if (state == -1) {
						Toast.makeText(mActivity, state + "",
								Toast.LENGTH_SHORT).show();
					}
					if (state == 1) {
						Intent intent = new Intent(mActivity,
								UserInfoActivity.class);
						startActivity(intent);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
}
