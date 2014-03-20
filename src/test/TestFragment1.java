/**
 * Filename : TestFragment1.java Author : CX Date : 2013-9-6
 * 
 * Copyright(c) 2011-2013 Mobitide Android Team. All Rights Reserved.
 */
package test;

import com.mobitide.lib.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


/**
 * @author CX
 * 
 */
public class TestFragment1 extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout ll = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.tab_item, null);
        ImageView imgView = (ImageView) ll.findViewById(R.id.icon);
        imgView.setBackgroundResource(R.drawable.icon);
        return ll;
    }
}
