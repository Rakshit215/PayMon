package com.example.rakshit.paymon.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
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
public class PayFragment extends android.support.v4.app.Fragment {
    public TextView amt;
    public Button pay;


    public PayFragment() {
        // Required empty public constructor
    }

    public static PayFragment newInstance( )
    {
        PayFragment fragment = new PayFragment();

               return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView=inflater.inflate(R.layout.fragment_pay, container, false);
        amt=(TextView)rootView.findViewById(R.id.wallet_amt);
        pay=(Button)rootView.findViewById(R.id.btn_pay);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), CardActivity.class);
                i.putExtra("frag","wall");
                i.putExtra("amt",amt.getText().toString());
                getActivity().finish();

                startActivity(i);
            }
        });



        return rootView;
    }


}
