package test;

import java.lang.reflect.Field;

import android.app.Activity;
import android.os.Bundle;
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
        testImage();
        MLogUtil.d("have run");


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
