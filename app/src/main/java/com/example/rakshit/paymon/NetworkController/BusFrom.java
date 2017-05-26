package com.example.rakshit.paymon.NetworkController;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.example.rakshit.paymon.Activity.MainActivity;
import com.example.rakshit.paymon.Fragment.BusFragment;
import com.example.rakshit.paymon.R;
import com.example.rakshit.paymon.Utilities.Constants;

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
 * Created by Rakshit on 14-04-2017.
 */
public class BusFrom extends AsyncTask<String, String, String> {
    public String link = "http://192.168.137.1/paymon/bus_from_api.php";
    StringBuilder result;
    String msj = "no";
    MainActivity activity;
    public JSONObject jsonObject;
    public JSONArray jsonArray;


    public BusFrom(Activity activity, String from) {
        result = new StringBuilder();

        this.activity = (MainActivity) activity;
        try {
            result.append("&");
            result.append(URLEncoder.encode("from", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(from, "UTF-8"));


            Log.e("hello ", " Entered");

        } catch (UnsupportedEncodingException e) {
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

            int i = connection.getResponseCode();


            InputStream inputStream = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line = "abc";

            StringBuilder br = new StringBuilder();

            while ((line = rd.readLine()) != null) {
                br.append(line);

            }

            msj = br.toString();


            jsonArray = new JSONArray(msj);





        } catch (MalformedURLException e) {

            Log.e("hello ", e.toString());

        } catch (IOException e) {
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

        } else {

            activity.onNetworkController(Constants.BUS,jsonArray);




        }
    }
}
