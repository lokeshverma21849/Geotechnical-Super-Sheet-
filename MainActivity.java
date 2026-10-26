package com.lokesh.minisheet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import com.getcapacitor.BridgeActivity;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class MainActivity extends BridgeActivity {
  private volatile String pending = null;

  public class LKBridge {
    @JavascriptInterface
    public String take() { String p = pending; pending = null; return p; }
  }

  @Override
  public void onCreate(Bundle b) {
    super.onCreate(b);
    getBridge().getWebView().addJavascriptInterface(new LKBridge(), "LK");
    handle(getIntent());
  }

  @Override
  protected void onNewIntent(Intent i) {
    super.onNewIntent(i);
    setIntent(i);
    handle(i);
    getBridge().getWebView().post(() ->
      getBridge().getWebView().evaluateJavascript("window.lkCheck&&lkCheck()", null));
  }

  private void handle(Intent i) {
    if (i == null || !Intent.ACTION_VIEW.equals(i.getAction()) || i.getData() == null) return;
    try (InputStream in = getContentResolver().openInputStream(i.getData())) {
      ByteArrayOutputStream o = new ByteArrayOutputStream();
      byte[] buf = new byte[8192];
      int n;
      while ((n = in.read(buf)) > 0) o.write(buf, 0, n);
      pending = Base64.encodeToString(o.toByteArray(), Base64.NO_WRAP);
    } catch (Exception e) { }
  }
}
