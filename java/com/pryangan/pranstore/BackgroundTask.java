package com.pryangan.pranstore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

/**
 * Created by Pryangan Chowdhury on 20-10-2017.
 */

public class BackgroundTask extends AsyncTask<String,Void,String>{
    AlertDialog alertDialog;
    Context ctx;
    BackgroundTask(Context ctx)
    {
        this.ctx = ctx;
    }
    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information...");
    }

    @Override
    protected String  doInBackground(String... params) {
        String reg_url = "http://192.168.0.3/PranStore/register.php";
        String medicine_url = "http://192.168.0.3/PranStore/Add_medicine.php";
        String login_url = "http://192.168.0.3/PranStore/login.php";
        String pruchase_url = "http://192.168.0.3/PranStore/purchase.php";
        String method = params[0];
        if(method.equals("Add_medicine")){
            String name = params[1];
            String desc = params[2];
            String type = params[3];
            String cost = params[4];
            String mfg = params[5];
            String exp = params[6];
            String com = params[7];

            try {
                URL url = new URL(medicine_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data = URLEncoder.encode("title","UTF-8") +"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("description","UTF-8") +"="+URLEncoder.encode(desc,"UTF-8")+"&"+
                        URLEncoder.encode("type","UTF-8") +"="+URLEncoder.encode(type,"UTF-8")+"&"+
                        URLEncoder.encode("cost","UTF-8") +"="+URLEncoder.encode(cost,"UTF-8")+"&"+
                        URLEncoder.encode("mfg_date","UTF-8") +"="+URLEncoder.encode(mfg,"UTF-8")+"&"+
                        URLEncoder.encode("exp_date","UTF-8") +"="+URLEncoder.encode(exp,"UTF-8")+"&"+
                        URLEncoder.encode("com","UTF-8") +"="+URLEncoder.encode(com,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Add Medicine Successfully...";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(method.equals("register")){
            String namestr = params[1];
            String emailstr = params[2];
            String unamestr = params[3];
            String pass1str = params[4];
            String user_type = params[5];
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data = URLEncoder.encode("namestr","UTF-8") +"="+URLEncoder.encode(namestr,"UTF-8")+"&"+
                        URLEncoder.encode("emailstr","UTF-8") +"="+URLEncoder.encode(emailstr,"UTF-8")+"&"+
                        URLEncoder.encode("unamestr","UTF-8") +"="+URLEncoder.encode(unamestr,"UTF-8")+"&"+
                        URLEncoder.encode("pass1str","UTF-8") +"="+URLEncoder.encode(pass1str,"UTF-8")+"&"+
                        URLEncoder.encode("user_type","UTF-8") +"="+URLEncoder.encode(user_type,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Registration Successful...";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if(method.equals("login")){
            String login_name = params[1];
            String login_pass = params[2];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("login_name","UTF-8")+"="+URLEncoder.encode(login_name,"UTF-8")+"&"+
                        URLEncoder.encode("login_pass","UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+=line;
                }
                bufferedReader.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        else if(method.equals("purchase")){
            String user_name = params[1];
            String contactNumber = params[2];
            String customerAddress = params[3];
            String medicine_name = params[4];
            String cost = params[5];
            try {
                URL url = new URL(pruchase_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data = URLEncoder.encode("user_name","UTF-8") +"="+URLEncoder.encode(user_name,"UTF-8")+"&"+
                        URLEncoder.encode("contactNumber","UTF-8") +"="+URLEncoder.encode(contactNumber,"UTF-8")+"&"+
                        URLEncoder.encode("customerAddress","UTF-8") +"="+URLEncoder.encode(customerAddress,"UTF-8")+"&"+
                        URLEncoder.encode("medicine_name","UTF-8") +"="+URLEncoder.encode(medicine_name,"UTF-8")+"&"+
                        URLEncoder.encode("cost","UTF-8") +"="+URLEncoder.encode(cost,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Your Order Placed Successfully...";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Add Medicine Successfully...")) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if (result.equals("Registration Successful...")){
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if(result.equals("Your Order Placed Successfully...")) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else {

            if(result.equals("Login Failed...... Try Again...")){
                alertDialog.setMessage(result);
                alertDialog.show();
            }
            else {

                //alertDialog.setMessage(result);
                //alertDialog.show();
               // ctx.startActivity(new Intent(ctx,AddMedicine.class));
                Intent i = new Intent(ctx,welcomeActivity.class);
                i.putExtra("Username",result);
                ctx.startActivity(i);
            }
        }
    }


}
