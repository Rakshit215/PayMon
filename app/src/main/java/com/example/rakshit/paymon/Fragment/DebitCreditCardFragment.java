package com.example.rakshit.paymon.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rakshit.paymon.NetworkController.Bus;
import com.example.rakshit.paymon.NetworkController.Dth;
import com.example.rakshit.paymon.NetworkController.Metro;
import com.example.rakshit.paymon.NetworkController.Recharge;
import com.example.rakshit.paymon.NetworkController.Wallet;
import com.example.rakshit.paymon.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DebitCreditCardFragment extends android.support.v4.app.Fragment {

    private EditText inputName, inputEmail, inputPassword;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword;
    private Button btnSignUp;
    public String[] card_month = {"1","2","3","4","5","6","7","8","9","10","11","12"};
public int yy;
    public String year[]=new String[40];
    public Dth dth;
    public Recharge recharge;
    public Bus bus;
    public Wallet wallet;
    public Metro metro;


    public DebitCreditCardFragment() {
        // Required empty public constructor
    }


    public static DebitCreditCardFragment newInstance()
    {
        DebitCreditCardFragment f=new DebitCreditCardFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_debit_credit_card, container, false);
        inputLayoutName = (TextInputLayout) rootView.findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout)rootView.findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) rootView.findViewById(R.id.input_layout_password);
        inputName = (EditText) rootView.findViewById(R.id.card_name);
        inputEmail = (EditText) rootView.findViewById(R.id.input_card_num);
        inputPassword = (EditText) rootView.findViewById(R.id.input_cvv);
        btnSignUp = (Button) rootView.findViewById(R.id.btn_pay);




        yy=Calendar.getInstance().get(Calendar.YEAR);
        for (int i=0;i<40;i++)
        {
            year[i]=String.valueOf(yy+i);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, card_month);
        MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                rootView.findViewById(R.id.card_month);
        materialDesignSpinner.setAdapter(arrayAdapter);



        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, year);
        MaterialBetterSpinner yearSpinner = (MaterialBetterSpinner)
                rootView.findViewById(R.id.card_year);
      yearSpinner.setAdapter(yearAdapter);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(inputEmail.getText().toString().length()>0&&inputName.getText().toString().length()>0&&inputPassword.getText().toString().length()>0) {


                    paymentProcess();
                }
                else
                    Toast.makeText(getActivity(),"All Fields Are Mandatory",Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }



    public void paymentProcess()

    {

        String frag=getArguments().getString("frag");

        if(frag.equalsIgnoreCase("dth"))
        {
            dth=new Dth(getActivity(),getArguments().getString("card_no"),getArguments().getString("amt"));
            dth.execute();
        }
        else         if(frag.equalsIgnoreCase("recharge"))
        {
            recharge=new Recharge(getActivity(),getArguments().getString("num"),getArguments().getString("op"),getArguments().getString("amt"));
            recharge.execute();
        }
        else         if(frag.equalsIgnoreCase("bus"))
        {
            bus=new Bus(getActivity(),getArguments().getString("from"),getArguments().getString("to"),getArguments().getString("amt"));
            bus.execute();
        }
        else         if(frag.equalsIgnoreCase("wall"))
        {
            wallet=new Wallet(getActivity(),getArguments().getString("amt"));
            wallet.execute();
        }
        else         if(frag.equalsIgnoreCase("metro"))
        {
            metro=new Metro(getActivity(),getArguments().getString("card"),getArguments().getString("amt"));
            metro.execute();
        }


    }

}
