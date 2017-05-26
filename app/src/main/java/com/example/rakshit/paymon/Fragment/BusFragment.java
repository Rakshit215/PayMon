package com.example.rakshit.paymon.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.text.method.CharacterPickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rakshit.paymon.Activity.CardActivity;
import com.example.rakshit.paymon.NetworkController.BusDetail;
import com.example.rakshit.paymon.NetworkController.FareDetail;
import com.example.rakshit.paymon.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusFragment extends android.support.v4.app.Fragment {
    AutoCompleteTextView station_from;

    AutoCompleteTextView station_to;


    public ArrayAdapter<String> adapter,toadapter;
    public int len=0,i=0;
    public BusDetail busDetail;
    public FareDetail fareDetail;
    public TextView amt;
    public Button pay;


    public static JSONArray jsonArray;

    public BusFragment() {
        // Required empty public constructor
    }


    public static BusFragment newInstance(Object data)
    {
        BusFragment f=new BusFragment();
        jsonArray=(JSONArray)data;
        return f;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_bus, container, false);
        len=jsonArray.length();
        String from[]=new String[len];
        pay=(Button)rootView.findViewById(R.id.btn_pay);

        try {


            for (i = 0; i < len; i++) {
                from[i] = jsonArray.getJSONObject(i).getString("start_station");



            }
        }catch (JSONException e)
        {

        }

        station_from=(AutoCompleteTextView)rootView.findViewById(R.id.station_from);
        amt=(TextView)rootView.findViewById(R.id.bus_amt);

        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,from);
        station_from.setAdapter(adapter);
        station_from.setThreshold(1);
        String to[]=new String[len-1];
        station_to=(AutoCompleteTextView)rootView.findViewById(R.id.station_to);



        station_from.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                busDetail=new BusDetail(getActivity(),station_from.getText().toString().trim(),station_to);

                busDetail.execute();
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(station_from.getWindowToken(), 0);
            }
        });

        station_to.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(station_to.getWindowToken(), 0);
                fareDetail=new FareDetail(getActivity(),station_from.getText().toString(),station_to.getText().toString(),amt);
                fareDetail.execute();
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), CardActivity.class);
                i.putExtra("frag","bus");
                i.putExtra("from",station_from.getText().toString());
                i.putExtra("to",station_to.getText().toString());
                i.putExtra("amt",amt.getText().toString());

                getActivity().finish();
                getActivity().startActivity(i);
            }
        });

        return rootView;
    }


}
