package com.pryangan.pranstore;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class VendorActivity extends AppCompatActivity {

    private static final String TAG = "VendorActivity";
    private TextView mfg;
    private DatePickerDialog.OnDateSetListener mfgDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);

        mfg = (TextView)findViewById(R.id.ch);
        mfg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(VendorActivity.this,android.R.style.Theme_Holo_Dialog_MinWidth,mfgDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mfgDateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month+=1;
                Log.d(TAG,"onSetDate: mm/dd/yyy: "+ day + "/" + month + "/" + year);
                String date = day + "/" + month + "/" + year;
                mfg.setText(date);
            }
        };
    }
}
