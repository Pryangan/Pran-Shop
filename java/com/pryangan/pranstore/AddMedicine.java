package com.pryangan.pranstore;

import android.app.DatePickerDialog;
import java.util.Calendar;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddMedicine extends AppCompatActivity {

    private static final String TAG = "AddMedicineActivity";

    private DatePickerDialog.OnDateSetListener mfgDateSetListener,expDateSetListener;
    EditText mfg;
    EditText name;
    EditText type;
    EditText cost;
    EditText desc;
    EditText exp;
    EditText com;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        mfg = (EditText) findViewById(R.id.medicine_mfg);
        exp = (EditText) findViewById(R.id.medicine_exp);

        mfg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddMedicine.this,android.R.style.Theme_Holo_Dialog_MinWidth,mfgDateSetListener,year,month,day);
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
                mfg.setTextColor(Color.BLACK);
            }
        };

        exp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddMedicine.this,android.R.style.Theme_Holo_Dialog_MinWidth,expDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        expDateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month+=1;
                Log.d(TAG,"onSetDate: mm/dd/yyy: "+ day + "/" + month + "/" + year);
                String date = day + "/" + month + "/" + year;
                exp.setText(date);
                exp.setTextColor(Color.BLACK);
            }
        };
    }


    public void onAdd_Medicine_Button(View v)
    {
        name = (EditText) findViewById(R.id.medicine_name);
        desc = (EditText) findViewById(R.id.medicine_desp);
        type = (EditText) findViewById(R.id.medicine_type);
        cost = (EditText) findViewById(R.id.medicine_cost);
        com = (EditText) findViewById(R.id.medicine_com);

        String namestr = name.getText().toString();
            String typestr = type.getText().toString();
            String coststr = cost.getText().toString();
            String descstr = desc.getText().toString();
            String mfgstr = mfg.getText().toString();
            String expstr = exp.getText().toString();
            String comstr = com.getText().toString();

        String method = "Add_medicine";

        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,namestr,descstr,typestr,coststr,mfgstr,expstr,comstr);
        finish();
    }
}
