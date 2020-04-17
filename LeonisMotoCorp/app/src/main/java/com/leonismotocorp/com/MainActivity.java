package com.leonismotocorp.com;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity  {
    SwipeRefreshLayout swipe;
    private WebView mywebView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if ("leonismotocorp.com".equals(Uri.parse(url).getHost())) {
                // This is my website, so do not override; let my WebView load the page
                return false;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Code for link opening
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipe=(SwipeRefreshLayout)findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Load();
            }
        });
        Load();

        /*//code for swipe to refresh
        //setContentView(R.layout.activity_main);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        super.onCreate(savedInstanceState);*/
    }


   public void Load(){
       mywebView = (WebView) findViewById(R.id.webview);
       WebSettings webSettings= mywebView.getSettings();
       webSettings.setJavaScriptEnabled(true);
       mywebView.loadUrl("https://leonismotocorp.com/");
       // Line of Code for opening links in app
       swipe.setRefreshing(true);
       mywebView.setWebViewClient(new MyWebViewClient(){
           @Override
           public void onPageFinished(WebView view, String url) {
               super.onPageFinished(view, url);
               swipe.setRefreshing(false);
           }
       });
       //Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();

       /*new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               swipe.setRefreshing(false);
               //mywebView.setWebViewClient(new MyWebViewClient());
           }
       }, 2000);*/

   }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mywebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings= mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mywebView.loadUrl("https://leonismotocorp.com/");
        // Line of Code for opening links in app
        mywebView.setWebViewClient(new MyWebViewClient());
    }*/
    //Code For Back Button
    @Override
    public void onBackPressed() {
        if(mywebView.canGoBack())
        {
            mywebView.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }

}