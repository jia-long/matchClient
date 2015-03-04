package com.example.matchclient;

import com.android.volley.RequestQueue;
import com.android.volley.plus.RequestManager;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.common.tools.T;
import com.example.matchclient.constant.APIConstant;

import android.os.Bundle;
import android.widget.ImageView;

public class ImageActivity extends BaseActivity{
	private ImageView iv_img;
	private ImageLoader imageLoader;
	private RequestQueue requestQueue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		imageLoader = RequestManager.getImageLoader();
		requestQueue  = RequestManager.getRequestQueue();
		 
		init();
		
		imageLoader.get(APIConstant.SERVIER+"resources/images/logo.jpg", getImageListener(iv_img)); 
	}
	
	private void init(){
		iv_img = (ImageView) findViewById(R.id.iv_img); 
	}
	
}
