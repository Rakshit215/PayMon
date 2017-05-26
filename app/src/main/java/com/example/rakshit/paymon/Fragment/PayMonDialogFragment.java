package com.example.rakshit.paymon.Fragment;


import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;

import com.example.rakshit.paymon.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayMonDialogFragment extends android.support.v4.app.DialogFragment {


    public PayMonDialogFragment() {
        // Required empty public constructor
    }

    public PayMonDialogFragment newInstance() {
        PayMonDialogFragment fragment = new PayMonDialogFragment();
        return fragment;
    }


        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_pay_mon_dialog, container, false);
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            setStyle(android.support.v4.app.DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);
            getDialog().setCanceledOnTouchOutside(false);
            ProgressBar rr = (ProgressBar) v.findViewById(R.id.pbloading);
            return v;
        }


}
