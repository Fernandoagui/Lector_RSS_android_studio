package com.example.fers.journal.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView = (WebView) findViewById(R.id.webViewNoticia);
        webView.getSettings().setJavaScriptEnabled(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            String urlPost = bundle.get("URL").toString();
            webView.loadUrl(urlPost);
            webView.setWebViewClient(new WebViewClient(){
                public boolean shouldOverrideUrlLoading(WebView webView, String url){
                    return false;
                }
            });
        }
    }
}
