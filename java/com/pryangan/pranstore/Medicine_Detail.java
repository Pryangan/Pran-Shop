package com.pryangan.pranstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.pryangan.pranstore.R.id.textView;

public class Medicine_Detail extends AppCompatActivity {

    TextView medicine_name,medicine_price,medicine_desc,medicine_type,medicine_mfg,medicine_exp,medicine_company;
    String user_name;
    String Title;
    String price;
    String desc;
    String type;
    String mfg;
    String exp;
    String com;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine__detail);
        Intent intent = getIntent();
        user_name = intent.getStringExtra("user_name");
         Title = intent.getStringExtra("Title");
         price = intent.getStringExtra("cost");
         desc = intent.getStringExtra("Description");
         type = intent.getStringExtra("type");
         mfg = intent.getStringExtra("mfg");
         exp = intent.getStringExtra("exp");
         com = intent.getStringExtra("com");
        medicine_name = (TextView)findViewById(R.id.detail_medicine_name);
        medicine_name.setText(Title);

        medicine_price = (TextView)findViewById(R.id.detail_medicine_price);
        medicine_price.setText(price);

        medicine_desc = (TextView)findViewById(R.id.detail_medicine_description);
        medicine_desc.setText(desc);

        medicine_type = (TextView)findViewById(R.id.detail_medicine_type);
        medicine_type.setText(type);

        medicine_mfg = (TextView)findViewById(R.id.detail_medicine_mfg_date);
        medicine_mfg.setText(mfg);

        medicine_exp = (TextView)findViewById(R.id.detail_medicine_exp_date);
        medicine_exp.setText(exp);

        medicine_company = (TextView)findViewById(R.id.detail_medicine_com);
        medicine_company.setText(com);
    }

    public void onPurchase(View view){
        Intent intent = new Intent(this,PurchaseMedicine.class);
        intent.putExtra("user_name",user_name);
        intent.putExtra("medicine_name",Title);
        intent.putExtra("cost",price);
        startActivity(intent);
    }
}
