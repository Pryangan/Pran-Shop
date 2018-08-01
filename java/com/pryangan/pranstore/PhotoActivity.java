package com.pryangan.pranstore;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.io.BufferedReader;
import java.net.URLEncoder;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

public class PhotoActivity extends AppCompatActivity implements  View.OnClickListener {

//    ImageView ivImage;
//    Integer REQUEST_CAMERA = 1,SELECT_FILE = 0;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_photo);
//        ivImage = (ImageView) findViewById(R.id.ivImage);
//        SelectImage();
//    }

//    private void SelectImage(){
//        final CharSequence[] items={"Camera","Gallery","Cancel"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(PhotoActivity.this);
//        builder.setTitle("Add Image");
//        builder.setItems(items,new DialogInterface.OnClickListener(){
//            @Override
//            public void onClick(DialogInterface dialogInterface,int i){
//                if(items[i].equals("Camera")){
//
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(intent,REQUEST_CAMERA);
//
//                }else if(items[i].equals("Gallery")){
//
//                    Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    intent.setType("image/*");
//                    startActivityForResult(intent.createChooser(intent,"Select File"),SELECT_FILE);
//                }else if(items[i].equals("Cancel")){
//                    dialogInterface.dismiss();
//                }
//            }
//        });
//        builder.show();
//    }
//
//    @Override
//    public void onActivityResult(int requestCode,int resultCode,Intent data){
//        super.onActivityResult(requestCode,resultCode,data);
//
//        if(resultCode == Activity.RESULT_OK) {
//            if (requestCode== REQUEST_CAMERA) {
//
//                Bundle bundle = data.getExtras();
//                final Bitmap bmp = (Bitmap) bundle.get("data");
//                ivImage.setImageBitmap(bmp);
//            } else if (requestCode == SELECT_FILE) {
//
//                Uri selectImageUri = data.getData();
//                ivImage.setImageURI(selectImageUri);
//            }
//        }
//    }

    private Button uploadbn, choosebn;
    private EditText name;
    private ImageView imgview;
    private final int IMG_REQUEST = 1;
    private Bitmap bitmap;
    private String uploadUrl  = "http://192.168.0.3/PranStore/upload_image_medicine.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        uploadbn = (Button) findViewById(R.id.uploadbn);
        choosebn = (Button) findViewById(R.id.choosebn);
        name = (EditText) findViewById(R.id.name);
        imgview = (ImageView) findViewById(R.id.imageView);
        choosebn.setOnClickListener(this);
        uploadbn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choosebn:
                selectImage();
                break;

            case R.id.uploadbn:
               // uploadImage();
               // String t = imageToString(bitmap);
                //Toast.makeText(PhotoActivity.this, name.getText().toString().trim()+t, Toast.LENGTH_LONG).show();
                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute(name.getText().toString().trim(),imageToString(bitmap));
                finish();
                break;
        }
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imgview.setImageBitmap(bitmap);
                imgview.setVisibility(View.VISIBLE);
                name.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadImage() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, uploadUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String Response = jsonObject.getString("response");
                    Toast.makeText(PhotoActivity.this, Response, Toast.LENGTH_LONG).show();
                    imgview.setImageResource(0);
                    imgview.setVisibility(View.GONE);
                    name.setText("");
                    name.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name.getText().toString().trim());
                params.put("image", imageToString(bitmap));
                return params;
            }
        };

        MySingleton.getInstance(PhotoActivity.this).addToRequestQue(stringRequest);
    }

    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }

    public class BackgroundTask extends AsyncTask<String, Void, String> {
        Context ctx;

        BackgroundTask(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... params) {
            uploadUrl  = "http://192.168.0.3/PranStore/upload_image_medicine.php";
            String name = params[0];
                String image = params[1];
            try {
                    URL url = new URL(uploadUrl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                            URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(image, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    IS.close();
                    return "Sent Image Successfully...";
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
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
            if (result.equals("Sent Image Successfully...")) {
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            }
            }
        }

    }