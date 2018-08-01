package com.pryangan.pranstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PurchaseMedicine extends AppCompatActivity {
    String user_name;
    String medicine_name;
    String cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_medicine);
        Intent intent = getIntent();
        user_name = intent.getStringExtra("user_name");
        medicine_name = intent.getStringExtra("medicine_name");
        cost = intent.getStringExtra("cost");
        TextView username = (TextView)findViewById(R.id.purchase_customer_name);
        username.setText(user_name);
        TextView medicinename = (TextView)findViewById(R.id.purchase_medicine);
        medicinename.setText(medicine_name);
        TextView price = (TextView)findViewById(R.id.purchase_price);
        price.setText(cost);
    }

    public void onPurchase_Button(View view){
        EditText contact_number = (EditText) findViewById(R.id.contact_number);
        EditText address = (EditText) findViewById(R.id.address);

        String contactNumber = contact_number.getText().toString();
        String customerAddress = address.getText().toString();

        String method = "purchase";

        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,user_name,contactNumber,customerAddress,medicine_name,cost);
        finish();
    }
}
