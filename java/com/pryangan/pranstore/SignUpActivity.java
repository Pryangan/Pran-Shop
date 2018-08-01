package com.pryangan.pranstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

  //  PranStoreDatabaseHelper helper = new PranStoreDatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void onSignup_Button(View v)
    {
        if(v.getId()==R.id.Sign_up_Button) {
            EditText name = (EditText) findViewById(R.id.name);
            EditText email = (EditText) findViewById(R.id.Email);
            EditText uname = (EditText) findViewById(R.id.Username);
            EditText pass1 = (EditText) findViewById(R.id.password);
            EditText pass2 = (EditText) findViewById(R.id.ConfirmPassword);

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String unamestr = uname.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();

            if(namestr.isEmpty()){
                name.setError("Name is Empty");
            }
            else if(emailstr.isEmpty()){
                email.setError("Email Address is Empty");
            }
            else if(unamestr.isEmpty()){
                uname.setError("Username is Empty");
            }
            else if(pass1str.isEmpty()){
                pass1.setError("Password is Empty");
            }
            else if(pass2str.isEmpty()){
                pass2.setError("Confirm Password is Empty");
            }
            else {
                if (!pass1str.equals(pass2str)) {
                    Toast pass = Toast.makeText(SignUpActivity.this, "Password don't match", Toast.LENGTH_SHORT);
                    pass.show();
                } else {
//                    if (helper.isUserNameAlreadyExist(unamestr)) {
//                        uname.setError("Username Already Exist");
//                    }
//                    else
                    //{//insert the details in database
//                        Contact c = new Contact();
//                        c.setName(namestr);
//                        c.setEmail(emailstr);
//                        c.setUname(unamestr);
//                        c.setPass(pass1str);
//                        c.setType("User");
//
//                        helper.insertContact(c);
//                        Toast pass = Toast.makeText(SignUpActivity.this,namestr+"!! Your Registration is Successful",Toast.LENGTH_SHORT);
//                        pass.show();
                   // }

                    String method = "register";

                    BackgroundTask backgroundTask = new BackgroundTask(this);
                    backgroundTask.execute(method,namestr,emailstr,unamestr,pass1str,"user");
                    finish();
                }
            }
        }
    }

    public void onlogin_button_page(View view)
    {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
