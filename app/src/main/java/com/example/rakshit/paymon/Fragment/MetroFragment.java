package com.example.rakshit.paymon.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.rakshit.paymon.Activity.CardActivity;
import com.example.rakshit.paymon.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MetroFragment extends android.support.v4.app.Fragment {

    public TextView card;
    public Button pay;
    public TextView amt;


    public MetroFragment() {
        // Required empty public constructor
    }

    public static MetroFragment newInstance()
    {
        MetroFragment f=new MetroFragment();
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_metro, container, false);

        card=(TextView)rootView.findViewById(R.id.metro_card);
        pay=(Button )rootView.findViewById(R.id.btn_pay);
        amt=(TextView)rootView.findViewById(R.id.metro_amt);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), CardActivity.class);
                i.putExtra("frag","metro");
                i.putExtra("card",card.getText().toString());
                i.putExtra("amt",amt.getText().toString());
                getActivity().finish();
                startActivity(i);
            }
        });

        return  rootView;
    }


}
