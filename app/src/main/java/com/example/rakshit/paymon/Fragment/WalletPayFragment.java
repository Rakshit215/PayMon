package com.example.rakshit.paymon.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rakshit.paymon.NetworkController.Bus;
import com.example.rakshit.paymon.NetworkController.Dth;
import com.example.rakshit.paymon.NetworkController.Metro;
import com.example.rakshit.paymon.NetworkController.Recharge;
import com.example.rakshit.paymon.NetworkController.Wallet;
import com.example.rakshit.paymon.NetworkController.WalletPay;
import com.example.rakshit.paymon.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalletPayFragment extends android.support.v4.app.Fragment {

    public Button pay;
    public WalletPay walletPay;


    public WalletPayFragment() {
        // Required empty public constructor
    }


    public static WalletPayFragment newInstance()
    {
        WalletPayFragment fragment = new WalletPayFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_wallet_pay, container, false);

        pay=(Button)rootView.findViewById(R.id.btn_pay);


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                walletPay=new WalletPay(getActivity(),getArguments().getString("amt"));
                walletPay.execute();


            }
        });
        return rootView;
    }



}
