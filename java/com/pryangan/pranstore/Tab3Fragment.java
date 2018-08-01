package com.pryangan.pranstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Pryangan Chowdhury on 28-09-2017.
 */

public class Tab3Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";
    private Button btnTest;
    FloatingActionButton fab_vendor_add;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_fragment,container,false);
        fab_vendor_add = (FloatingActionButton) view.findViewById(R.id.fab_vendor_add);

        fab_vendor_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),AddVendor.class));
            }
        });
//        btnTest =(Button) view.findViewById(R.id.btnTest3);
//        btnTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(),"Testing Button Click 3",Toast.LENGTH_SHORT).show();}
//            });
        return  view;
    }
}
