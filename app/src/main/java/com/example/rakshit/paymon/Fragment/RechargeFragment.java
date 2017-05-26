package com.example.rakshit.paymon.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
public class RechargeFragment extends android.support.v4.app.Fragment  implements BrowsePlanFragment.EditDialogListener{


    public TextView mobile_num;
    public TextView amt;
    public TextView operator;
    public Button pay;

    public TextView plan;
    public static     RechargeFragment fragment;
    public  BrowsePlanFragment dialogFragment;
    public RechargeFragment() {
        // Required empty public constructor
    }


    public static RechargeFragment newInstance()
    {
     fragment = new RechargeFragment();

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView=inflater.inflate(R.layout.fragment_recharge, container, false);

        mobile_num=(TextView)rootView.findViewById(R.id.recharge);
        amt=(TextView)rootView.findViewById(R.id.plan);
        plan=(TextView)rootView.findViewById(R.id.browse_plan);
        operator=(TextView)rootView.findViewById(R.id.operator);
        pay=(Button)rootView.findViewById(R.id.btn_pay);



        plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment=new BrowsePlanFragment().newInstance();
                dialogFragment.show(getActivity().getSupportFragmentManager(), "Plan");



            }
        });
        mobile_num.addTextChangedListener(new MyTextWatcher(mobile_num));

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), CardActivity.class);
                intent.putExtra("frag","recharge");
                intent.putExtra("num",mobile_num.getText().toString());
                intent.putExtra("op",operator.getText().toString());
                intent.putExtra("amt",amt.getText().toString());
                getActivity().finish();
                startActivity(intent);
            }
        });

        return  rootView;
    }


    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
           String str=((TextView)view).getText().toString();

            if(str.length()!=0)
            {    if (view.getId()==R.id.recharge) {
                switch (str.charAt(0)) {
                    case '8':
                        operator.setText("Airtel");
                        break;
                    case '9':
                        operator.setText("Vodafone");
                        break;
                    case '7':
                        operator.setText("Idea");
                        break;


                }

            }


            }
            else
                operator.setText("");
        }
    }
    @Override
    public void updateResult(String inputText) {
       amt.setText("₹ "+inputText);

    }
}
