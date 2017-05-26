package com.example.rakshit.paymon.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rakshit.paymon.Adapter.ElementDisplayAdapter;
import com.example.rakshit.paymon.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ElementDisplayFragment extends android.support.v4.app.Fragment {
    private RecyclerView elementDisplayView;
    public RecyclerView.LayoutManager layoutManager;


    public ElementDisplayFragment() {
        // Required empty public constructor
    }


    public static ElementDisplayFragment newInstance()
    {
       ElementDisplayFragment fragment = new ElementDisplayFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView=inflater.inflate(R.layout.fragment_element_display, container, false);

        elementDisplayView = (RecyclerView) rootView.findViewById(R.id.element_display_view);
        elementDisplayView .setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 2);
        elementDisplayView.setLayoutManager(layoutManager);

        ElementDisplayAdapter adapter=new ElementDisplayAdapter(getContext());

        elementDisplayView.setAdapter(adapter);


        return rootView;
    }


}
