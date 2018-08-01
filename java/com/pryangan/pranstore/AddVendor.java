package com.pryangan.pranstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddVendor extends AppCompatActivity {

    PranStoreDatabaseHelper helper = new PranStoreDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vendor);
    }

    public void Vendor_onSignup_Button(View v){
        EditText Vendor_name = (EditText)findViewById(R.id.vendor_name);
        EditText Vendor_Email = (EditText)findViewById(R.id.Vendor_Email);
        EditText Vendor_Username = (EditText)findViewById(R.id.Vendor_Username);
        EditText Vendor_Password = (EditText)findViewById(R.id.Vendor_password);
        EditText Vendor_ConfirmPassword = (EditText)findViewById(R.id.Vendor_ConfirmPassword);

        String Vendor_namestr = Vendor_name.getText().toString();
        String Vendor_Emailstr = Vendor_Email.getText().toString();
        String Vendor_Usernamestr = Vendor_Username.getText().toString();
        String Vendor_Passwordstr = Vendor_Password.getText().toString();
        String Vendor_ConfirmPasswordstr = Vendor_ConfirmPassword.getText().toString();

        if(Vendor_namestr.isEmpty()){
            Vendor_name.setError("Name is Empty");
        }
        else if(Vendor_Emailstr.isEmpty()){
            Vendor_Email.setError("Email Address is Empty");
        }
        else if(Vendor_Usernamestr.isEmpty()){
            Vendor_Username.setError("Username is Empty");
        }
        else if(Vendor_Passwordstr.isEmpty()){
            Vendor_Password.setError("Password is Empty");
        }
        else if(Vendor_ConfirmPasswordstr.isEmpty()){
            Vendor_ConfirmPassword.setError("Confirm Password is Empty");
        }
        else {
            if (!Vendor_Passwordstr.equals(Vendor_ConfirmPasswordstr)) {
                Toast pass = Toast.makeText(AddVendor.this, "Password don't match", Toast.LENGTH_SHORT);
                pass.show();
            } else {
//                if (helper.isUserNameAlreadyExist(Vendor_Usernamestr)) {
//                    Vendor_Username.setError("Username Already Exist");
//                }
//                else
//                {//insert the details in database
//                    Contact c = new Contact();
//                    c.setName(Vendor_namestr);
//                    c.setEmail(Vendor_Emailstr);
//                    c.setUname(Vendor_Usernamestr);
//                    c.setPass(Vendor_Passwordstr);
//                    c.setType("Vendor");
//
//                    helper.insertContact(c);
//                    Toast pass = Toast.makeText(AddVendor.this,"Registration Successful",Toast.LENGTH_SHORT);
//                    pass.show();

                String method = "register";

                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute(method,Vendor_namestr,Vendor_Emailstr,Vendor_Usernamestr,Vendor_Passwordstr,"vendor");
                finish();
//                }
            }
        }
    }
}
