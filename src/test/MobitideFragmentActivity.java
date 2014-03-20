/**
 * Filename : MobitideFragmentActivity.java Author : CX Date : 2013-9-6
 * 
 * Copyright(c) 2011-2013 Mobitide Android Team. All Rights Reserved.
 */
package test;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mobitide.lib.R;

/**
 * @author CX
 * 
 */
public class MobitideFragmentActivity extends FragmentActivity {
    private MyFragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragments_tabs);
        mTabHost = (MyFragmentTabHost) findViewById(R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(),android.R.id.tabcontent);


        LinearLayout ll = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        ImageView imgView = (ImageView) ll.findViewById(R.id.icon);
        imgView.setBackgroundResource(R.drawable.icon);

        LinearLayout ll2 = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        ImageView imgView2 = (ImageView) ll2.findViewById(R.id.icon);
        imgView2.setBackgroundResource(R.drawable.icon);
        mTabHost.addTab(mTabHost.newTabSpec("simple").setIndicator(ll), TestFragment1.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("contacts").setIndicator(ll2), TestFragment2.class, null);
        // mTabHost.addTab(mTabHost.newTabSpec("custom").setIndicator("Custom"), TestActivity.class,
        // null);
        // mTabHost.addTab(mTabHost.newTabSpec("throttle").setIndicator("Throttle"),
        // TestActivity.class, null);
    }
}
