package com.pryangan.pranstore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Pryangan Chowdhury on 28-09-2017.
 */

public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";
    private Button btnTest;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment,container,false);
        btnTest =(Button) view.findViewById(R.id.btnTest2);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Testing Button Click 2",Toast.LENGTH_SHORT).show();}
            });
        return  view;
    }
}
