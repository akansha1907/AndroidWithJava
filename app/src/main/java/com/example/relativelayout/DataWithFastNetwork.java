package com.example.relativelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataWithFastNetwork extends AppCompatActivity {
ListView listView;
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_with_fast_network);
        webView = findViewById(R.id.web_view);
        Log.d("fast ","fast");
        String URL = "https://jsonplaceholder.typicode.com/users";
        String baseUrl = "https://jsonplaceholder.typicode.com/todos";
        AndroidNetworking.initialize(this);
        ArrayList<String> nameList = new ArrayList<>();

        //get api
        AndroidNetworking.get(URL)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Akanksha ",response.toString());
                        try {
                            for(int i=0;i<response.length();i++){
                                JSONObject obj = response.getJSONObject(i);
                                String name = obj.getString("name");
                                nameList.add(name);
                            }

                           Log.e("Names",nameList.toString());
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                      //  anError.printStackTrace();
                        Log.e("Himanshu Singh ",anError.toString());
                    }
                });
//        //Post API
//        AndroidNetworking.post(baseUrl)
//                .addBodyParameter("id","1")
//                .setPriority(Priority.HIGH)
        //fetching some data with Web View

        //enabling javascript is compulsory to load website
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl("https://www.myntra.com/");

        //below method is prevent to open new website when in one website i.e. handle loading new page in same site
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

    }

    @Override
    public void onBackPressed() {
        /**
         * by default if in webview we navigate to different pages but on pressing back button of
         * device we back to our activity instead of going to last visited web page
         * to resolve this isssue we can override onBackPressed() method
         */
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }
    }
}