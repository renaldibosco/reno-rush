package com.renaldibosco.renorush;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
  private WebView web;

  @Override
  protected void onCreate(Bundle state) {
    super.onCreate(state);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    web = new WebView(this);
    web.setBackgroundColor(Color.parseColor("#0e121c"));
    WebSettings s = web.getSettings();
    s.setJavaScriptEnabled(true);
    s.setDomStorageEnabled(true);
    s.setMediaPlaybackRequiresUserGesture(false);
    s.setAllowFileAccess(true);
    web.setWebChromeClient(new WebChromeClient());
    web.setWebViewClient(new WebViewClient());
    setContentView(web);
    hideBars();
    web.loadUrl("file:///android_asset/index.html");
  }

  private void hideBars() {
    getWindow().getDecorView().setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN
        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
  }

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
    if (hasFocus) hideBars();
  }

  @Override
  protected void onPause() { super.onPause(); web.onPause(); web.pauseTimers(); }

  @Override
  protected void onResume() { super.onResume(); web.onResume(); web.resumeTimers(); }

  @Override
  public void onBackPressed() { moveTaskToBack(true); }
}
