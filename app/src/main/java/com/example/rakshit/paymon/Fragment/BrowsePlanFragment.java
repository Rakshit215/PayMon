package com.example.rakshit.paymon.Fragment;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rakshit.paymon.Adapter.CustomListViewAdapter;
import com.example.rakshit.paymon.Adapter.DataModel;
import com.example.rakshit.paymon.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrowsePlanFragment extends DialogFragment {
    ArrayList<DataModel> dataModels;
    ListView listView;
    private static CustomListViewAdapter adapter;
    public BrowsePlanFragment b;


    public RechargeFragment frag;

    public BrowsePlanFragment() {
        // Required empty public constructor
    }


    public BrowsePlanFragment newInstance() {
        BrowsePlanFragment fragment = new BrowsePlanFragment();
 //       frag=(RechargeFragment)f;

        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v =inflater.inflate(R.layout.fragment_browse_plan, container, false);

        frag=(RechargeFragment)getActivity().getSupportFragmentManager().findFragmentByTag("recharge");

        listView=(ListView)v.findViewById(R.id.custom_listview);

        dataModels= new ArrayList<>();

        dataModels.add(new DataModel("Talktime : 100", "Validity : 28", "100"));
        dataModels.add(new DataModel("Talktime : 220", "Validity : 15", "210"));
        dataModels.add(new DataModel("Internet : 40", "Validity : 18", "40"));


        adapter= new CustomListViewAdapter(dataModels,getContext());
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setClickable(true);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DataModel dataModel= dataModels.get(position);
                Log.e("Item ",dataModel.getAmt());
               frag.updateResult(dataModel.getAmt());
                dismiss();


            }
        });





        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

getDialog().setCancelable(true);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme);

        getDialog().setCanceledOnTouchOutside(true);


        return v;
    }

    public interface EditDialogListener {
        void updateResult(String inputText);
    }
}
