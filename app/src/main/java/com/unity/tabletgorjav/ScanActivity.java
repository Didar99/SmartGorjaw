package com.unity.tabletgorjav;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import static com.unity.tabletgorjav.Commands.gas_sensor_off;
import static com.unity.tabletgorjav.Commands.gerKon_sensor_off;
import static com.unity.tabletgorjav.Commands.water_sensor_off;

public class ScanActivity extends Activity {
    WebView webView;
    String ip_address, strPort, strZeroParam, strFirstParam, strSecondParam, strHttp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        //Get webView
        webView = findViewById(R.id.webView1);
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(this);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(this);
        strZeroParam = PrefConfig.loadZeroParam(this);
        strFirstParam = PrefConfig.loadFirstParam(this);
        strSecondParam = PrefConfig.loadSecondParam(this);
        strHttp = PrefConfig.loadHttpPref(this);

        startWebView(strHttp + "://" + ip_address + ":" + strPort + "/scanNetwork/");
    }

    private void startWebView(String url) {
        //Create new webView Client to show progress dialog
        //When opening a url or click on link
        webView.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog;
            //If you will not use this method url links are open in new browser not in webView
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            //Show loader on url load
            public void onLoadResource (WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    progressDialog = new ProgressDialog(ScanActivity.this);
                    progressDialog.setMessage("Biraz Garaşyň! Maglumatlar ýüklenilýär...");
                    progressDialog.show();
                }
            }
            public void onPageFinished(WebView view, String url) {
                try{
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                        progressDialog = null;
                        water_sensor_off(getApplicationContext());
                        gas_sensor_off(getApplicationContext());
                        gerKon_sensor_off(getApplicationContext());
                        finish();
                    }
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        });
        // Javascript enabled on webView
        webView.getSettings().setJavaScriptEnabled(true);
        // Other webView options
        /*
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);

         String summary = "<html><body>You scored <b>192</b> points.</body></html>";
         webView.loadData(summary, "text/html", null);
         */

        //Load url in webView
        webView.loadUrl(url);
    }
    // Open previous opened link from history on webView when back button pressed
    @Override
    // Detect when the back button is pressed
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            // Let the system handle the back button
            super.onBackPressed();
        }
    }

}