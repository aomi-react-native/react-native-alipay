package tech.aomi.react.alipay;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.alipay.sdk.app.PayTask;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;

import java.util.Map;

@ReactModule(name = AlipayModule.NAME)
public class AlipayModule extends ReactContextBaseJavaModule {
  public static final String NAME = "AMAliPay";

  public AlipayModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }


  @RequiresApi(api = Build.VERSION_CODES.N)
  @ReactMethod
  public void pay(String orderString, ReadableMap options, Promise promise) {
    new Thread(() -> {

      // 是否显示弹框
      boolean showLoading = false;
      if (options.hasKey("showLoading")) {
        showLoading = options.getBoolean("showLoading");
      }

      Activity currentActivity = this.getCurrentActivity();
      PayTask alipay = new PayTask(currentActivity);

      Map<String, String> result = alipay.payV2(orderString, showLoading);

      WritableMap writableMap = Arguments.createMap();
      result.forEach(writableMap::putString);

      promise.resolve(writableMap);
    }).start();
  }

}
