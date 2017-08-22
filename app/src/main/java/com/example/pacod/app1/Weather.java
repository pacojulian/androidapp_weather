package com.example.pacod.app1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Weather extends AppCompatActivity {


    private TextView txtciudad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);


        txtciudad = (TextView) findViewById(R.id.txtCiudad);




        Intent intent = getIntent();
        String idCiudad = intent.getStringExtra("itemValue");

        txtciudad.setText(idCiudad);

        String restURL = "http://api.openweathermap.org/data/2.5/weather?q="+idCiudad+"&units=metric&APPID=5b2ce6af88dcfd168c76a975306c9acb";
        Log.d("URL", restURL);
        new RestOperation().execute(restURL);
    }




        private class RestOperation extends AsyncTask<String, Void, Void> {

            final HttpClient httpClient = new DefaultHttpClient();
            String content;
            String error;
            ProgressDialog progressDialog = new ProgressDialog(Weather.this);
            String data = "";
            TextView serverDataReceived = (TextView) findViewById(R.id.txttemp);

            TextView mainweather = (TextView) findViewById(R.id.txtweather);



            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog.setTitle("Please wait ...");
                progressDialog.show();

                try {
                    data += "&" + URLEncoder.encode("data", "UTF-8") + "=";
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            protected Void doInBackground(String... params) {
                BufferedReader br = null;

                URL url;
                try {
                    url = new URL(params[0]);

                    URLConnection connection = url.openConnection();
                    connection.setDoOutput(true);

                    OutputStreamWriter outputStreamWr = new OutputStreamWriter(connection.getOutputStream());
                    outputStreamWr.write(data);
                    outputStreamWr.flush();

                    br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                        sb.append(System.getProperty("line.separator"));
                    }

                    content = sb.toString();

                } catch (MalformedURLException e) {
                    error = e.getMessage();
                    e.printStackTrace();
                } catch (IOException e) {
                    error = e.getMessage();
                    e.printStackTrace();
                } finally {
                    try {
                        br.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                return null;
            }


            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);





                if (error != null) {
                    serverDataReceived.setText("Error " + error);
                } else {
                    serverDataReceived.setText(content);

                String output = "";
                JSONObject jsonResponse;
                   // RelativeLayout ll = (RelativeLayout) findViewById(R.id.activity_weather);


                try {
                    JSONObject jsonObject = new JSONObject(content);

                    String dataWeather = jsonObject.getString("weather");
                    ImageView ivImagen= (ImageView)findViewById(R.id.ivImagen);
                    JSONObject jsonObjectMain = new JSONObject(jsonObject.getString("main"));


                    String name = jsonObject.getString("name");
                    String temp =jsonObjectMain.getString("temp");

                    JSONArray jsonArray = new JSONArray(dataWeather);
                    JSONObject jsonObjectWeather = jsonArray.optJSONObject(0);
                    String desc = jsonObjectWeather.getString("description");
                    String descCode = jsonObjectWeather.getString("id");
                    String main = jsonObjectWeather.getString("main");
                    double tempDouble= Double.parseDouble(descCode);

                    if(tempDouble>=200 && tempDouble<300)
                    {
                        try {
                            InputStream stream = getAssets().open("thunder.png");
                            Drawable d =Drawable.createFromStream(stream,null);
                            ivImagen.setImageDrawable(d);

                        }catch (IOException ioe)
                        {

                        }
                    } else if(tempDouble>=300 && tempDouble<500)
                    {
                        try {
                            InputStream stream = getAssets().open("drizzle.png");
                            Drawable d =Drawable.createFromStream(stream,null);
                            ivImagen.setImageDrawable(d);

                        }catch (IOException ioe)
                        {

                        }
                    }else if(tempDouble>=500 && tempDouble<600)
                    {
                        try {
                            InputStream stream = getAssets().open("rainy.png");
                            Drawable d =Drawable.createFromStream(stream,null);
                            ivImagen.setImageDrawable(d);

                        }catch (IOException ioe)
                        {

                        }
                    }else if(tempDouble>=600 && tempDouble<700)
                    {
                        try {
                            InputStream stream = getAssets().open("snow.png");
                            Drawable d =Drawable.createFromStream(stream,null);
                            ivImagen.setImageDrawable(d);

                        }catch (IOException ioe)
                        {

                        }
                    }else if(tempDouble==800 )
                    {
                        try {
                            InputStream stream = getAssets().open("sunny.png");
                            Drawable d =Drawable.createFromStream(stream,null);
                            ivImagen.setImageDrawable(d);

                        }catch (IOException ioe)
                        {

                        }
                    }else if(tempDouble>=801 && tempDouble<=899)
                    {
                        try {
                            InputStream stream = getAssets().open("Cloudy.png");
                            Drawable d =Drawable.createFromStream(stream,null);
                            ivImagen.setImageDrawable(d);

                        }catch (IOException ioe)
                        {

                        }
                    }

                   //showParsedJSON.setText(output);

                    switch (main)
                    {
                        case "Clear":
                           try {
                               InputStream stream = getAssets().open("sunny.png");
                               Drawable d =Drawable.createFromStream(stream,null);
                               ivImagen.setImageDrawable(d);

                           }catch (IOException ioe)
                           {

                           }
                            break;
                        case "Rain":

                            break;
                    }




                    serverDataReceived.setText(temp);
                    mainweather.setText(main);


                } catch (JSONException e) {
                    //
                    e.printStackTrace();
                }
                    progressDialog.dismiss();

                }
            }


        }






    }
