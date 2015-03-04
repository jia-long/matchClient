package com.example.matchclient;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.plus.RequestManager;
import com.android.volley.plus.VolleyErrorHelper;
import com.android.volley.toolbox.ImageLoader;
import com.common.tools.L;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class BaseActivity extends Activity {
	protected Activity mActivity;
	protected String TAG ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mActivity = this;
		TAG = mActivity.getClass().getSimpleName();
	}
	
	@Override
	public void onStop() {
		super.onStop();
		RequestManager.cancelAll(this);
	}

	protected void executeRequest(Request<?> request) {
		RequestManager.addRequest(request, this);
	}
	
	protected Response.ErrorListener errorListener() {
		return new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				L.d(TAG, error.getMessage());
				Toast.makeText(mActivity, VolleyErrorHelper.getErrorType(error), Toast.LENGTH_LONG).show();
			}
		};
	}
	
	protected static ImageLoader.ImageListener getImageListener(ImageView imageView) {
		return RequestManager.getImageListener(imageView);
	}
		
	
}
