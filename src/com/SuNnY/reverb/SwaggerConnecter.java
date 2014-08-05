package com.SuNnY.reverb;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class SwaggerConnecter {
 
    private static final String BASE_URL = "https://reverb.com:443/api/";
    private static AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);
 
    public static void get(String url, RequestParams params,
            AsyncHttpResponseHandler responseHandler) {
    	Log.d("LOG", getAbsoluteUrl(url));
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }
 
    public static void post(String url, RequestParams params,
            AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }
 
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}