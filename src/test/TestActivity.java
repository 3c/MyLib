package test;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.mobitide.common.utils.MActivityUtil;
import com.mobitide.common.utils.MAsyncImageLoaderUtil;
import com.mobitide.common.utils.MLogUtil;
import com.mobitide.lib.R;

public class TestActivity extends Activity {

    ImageView iv_main;
    ScrollView sl_main;
    private String imageUrl = "http://www.enixorigin.com/wp-content/uploads/2013/03/ff7.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        testImage();
        MLogUtil.d("have run");
       String info= getDeviceInfo(this);
       System.out.println(info);
    }


    public static String getDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm =
                    (android.telephony.TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            String device_id = tm.getDeviceId();

            android.net.wifi.WifiManager wifi =
                    (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            String mac = wifi.getConnectionInfo().getMacAddress();
            json.put("mac", mac);

            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }

            if (TextUtils.isEmpty(device_id)) {
                device_id =
                        android.provider.Settings.Secure.getString(context.getContentResolver(),
                                android.provider.Settings.Secure.ANDROID_ID);
            }

            json.put("device_id", device_id);

            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void testImage() {
        ImageView iv_test = (ImageView) findViewById(R.id.iv_test);
        MAsyncImageLoaderUtil mAsyncImageLoaderPhotoUtil = new MAsyncImageLoaderUtil();
        mAsyncImageLoaderPhotoUtil.loadImageAsync(imageUrl, iv_test);
        iv_test.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                MActivityUtil.startActivity(TestActivity.this, MobitideFragmentActivity.class);
            }
        });
    }

    private static int getImageViewFieldValue(Object object, String fieldName) {
        int value = 0;
        try {
            Field field = ImageView.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            int fieldValue = (Integer) field.get(object);
            if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
                value = fieldValue;
            }
        } catch (Exception e) {
            Log.e("a", e.toString());
        }
        return value;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
