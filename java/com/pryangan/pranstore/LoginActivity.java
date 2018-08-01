package com.pryangan.pranstore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {
    String json_string;

    final String TAG = this.getClass().getName();
    PranStoreDatabaseHelper helper = new PranStoreDatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void onlogin_button(View view){
        if(view.getId()==R.id.login_button){
            EditText usrname = (EditText)findViewById(R.id.username_login);
            String usr = usrname.getText().toString();
            EditText password = (EditText)findViewById(R.id.password_login);
            String pass = password.getText().toString();

            if(usr.isEmpty()){usrname.setError("Username is Empty");}
            else if(pass.isEmpty()){password.setError("Password is Empty");}
            else if(usr.equals("admin") && pass.equals("admin"))
            {
                Intent i = new Intent(LoginActivity.this, AdminActivity.class);
                i.putExtra("Username", usr);
                startActivity(i);
            }
            else {
//                String password1 = helper.searchPass(usr);
//                String type = helper.searchType(usr);
//                if (pass.equals(password1)) {
//                   if(type.equals("Vendor")){
//                       Intent i = new Intent(LoginActivity.this, VendorActivity.class);
//                       i.putExtra("Username", usr);
//                       startActivity(i);
//                   }
//                   if(type.equals("User")){
//                       new BackgroundTask1().execute();
//                   }
//                } else {
//                    Toast temp = Toast.makeText(LoginActivity.this, "Username and Password don't match!", Toast.LENGTH_SHORT);
//                    temp.show();
//                }

                String method = "login";
                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute(method,usr,pass);
                }
            }
        }
    public void onSignup_Button_clicked(View view)
    {
        if(view.getId()==R.id.sign_up_page)
        {
            Intent intent = new Intent(this,SignUpActivity.class);
            startActivity(intent);
        }
    }

    boolean twice;
    @Override
    public void onBackPressed()
    {
        Log.d(TAG,"click");
        if(twice==true)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }
        twice=true;
        Log.d(TAG,"twice: "+twice);
        Toast.makeText(LoginActivity.this,"Please press BACK Again to exit",Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
                Log.d(TAG,"twice: "+twice);
            }
        },3000);
    }


    class BackgroundTask1 extends AsyncTask<Void,Void,String> {

        String json_url;
        String JSON_STRING;
        @Override
        protected void onPreExecute() {
            json_url = "http://192.168.0.3/PranStore/json_get_data.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(JSON_STRING+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            json_string = result;
            parse_json();
        }
    }

    public void parse_json(){
        if(json_string==null){
            Toast.makeText(getApplicationContext(),"First Get JSON",Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("json_data",json_string);
            startActivity(intent);
        }
    }
}
