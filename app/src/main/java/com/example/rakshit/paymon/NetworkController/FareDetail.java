package com.example.rakshit.paymon.NetworkController;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Rakshit on 20-04-2017.
 */
public class FareDetail extends AsyncTask<String, String, String> {
    public String link="http://192.168.137.1/paymon/fare_detail_api.php";
    StringBuilder result;
    String msj="no";
    public Activity activity;
    public JSONObject jsonObject;
    public JSONArray jsonArray;
    public View to;
    public AutoCompleteTextView station;
    public TextView amt;





    public FareDetail(Activity activity,String from,String to,TextView tv)
    {


        result=new StringBuilder();

        this.activity=activity;

        this.amt=tv;



        try {
            result.append("&");
            result.append(URLEncoder.encode("from", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(from, "UTF-8"));


            result.append("&");
            result.append(URLEncoder.encode("to", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(to, "UTF-8"));


        }
        catch (UnsupportedEncodingException e)
        {
        }

    }

    @Override
    protected String doInBackground(String... params) {

        try {


            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);


            OutputStream os = connection.getOutputStream();

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(result.toString());

            writer.flush();
            writer.close();
            os.close();

            connection.connect();

            int i=connection.getResponseCode();


            InputStream inputStream = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line = "abc";

            StringBuilder br=new StringBuilder();

            while ((line=rd.readLine()) != null)
            {
                br.append(line);

            }

            msj = br.toString();
            Log.e("Msj  ", msj);


            jsonArray = new JSONArray(msj);




        }catch (MalformedURLException e)
        {

            Log.e("hello ",e.toString());

        }
        catch (IOException e)
        {
            // writing exception to log
            e.printStackTrace();

            Log.e("hello ", e.toString());

        } catch (Exception e) {
            e.printStackTrace();

            Log.e("hello ", e.toString());

        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);


        if (jsonArray == null)
        {

        }
        else {




            try {



                    amt.setText(jsonArray.getJSONObject(0).getString("amt"));


            }
            catch (JSONException e)
            {

            }

        }

    }
}
