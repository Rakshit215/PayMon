package com.example.rakshit.paymon.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rakshit.paymon.Adapter.DrawerElementAdapter;
import com.example.rakshit.paymon.Adapter.ElementDisplayAdapter;
import com.example.rakshit.paymon.Fragment.BusFragment;
import com.example.rakshit.paymon.Fragment.CardPaymentFragment;
import com.example.rakshit.paymon.Fragment.DebitCreditCardFragment;
import com.example.rakshit.paymon.Fragment.DthFragment;
import com.example.rakshit.paymon.Fragment.ElementDisplayFragment;
import com.example.rakshit.paymon.Fragment.MetroFragment;
import com.example.rakshit.paymon.Fragment.PayFragment;
import com.example.rakshit.paymon.Fragment.PayMonDialogFragment;
import com.example.rakshit.paymon.Fragment.RechargeFragment;
import com.example.rakshit.paymon.Listener.OnFragmentCommunicationListener;
import com.example.rakshit.paymon.Listener.onNetworkControllerListener;
import com.example.rakshit.paymon.NetworkController.BusFrom;
import com.example.rakshit.paymon.R;
import com.example.rakshit.paymon.Utilities.Constants;
import com.example.rakshit.paymon.Utilities.PrefUtils;

public class MainActivity extends ActionBarActivity implements OnFragmentCommunicationListener,View.OnClickListener,onNetworkControllerListener {
    private RecyclerView drawerRecyclerView;
    public DrawerElementAdapter drawerElementAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private Fragment fragment;

    public FloatingActionButton fab;
    private PayMonDialogFragment dialogFragment;
    public BusFrom busFrom;
    public final int RC_SHARE = 101;




    private TabLayout tabLayout;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.app_icon);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);



        onFragmentCommunicated(5,null);

        drawerRecyclerView = (RecyclerView) findViewById(R.id.drawerView);
        drawerRecyclerView.setHasFixedSize(true);
        drawerElementAdapter = new DrawerElementAdapter(this);
        drawerRecyclerView.setAdapter(drawerElementAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Wallet : ₹ "+ PrefUtils.getWalletAmt()+".0");

        tabLayout.addTab(tabLayout.newTab().setCustomView(tabOne));

        linearLayoutManager = new LinearLayoutManager(this);
        drawerRecyclerView.setLayoutManager(linearLayoutManager);

        drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);


        drawerToggle=new ActionBarDrawerToggle(this, drawerLayout,null, R.string.app_name, R.string.app_name);


        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });

        drawerLayout.setDrawerListener(drawerToggle);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentCommunicated(int reqCode, Object data) {
        switch(reqCode)
        {
            case Constants.WALLET:
                fragmentTransaction=getSupportFragmentManager().beginTransaction();

                fab.setVisibility(View.INVISIBLE);

                fragment=new PayFragment().newInstance();

                fragmentTransaction.replace(R.id.detail_frag, fragment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


                break;


            case Constants.ELEMENT_DISPLAY:
                fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fab.setVisibility(View.VISIBLE);



                fragment=new ElementDisplayFragment().newInstance();

                fragmentTransaction.replace(R.id.detail_frag, fragment);
                fragmentTransaction.commit();

                break;

            case Constants.DTH:

                fragmentTransaction=getSupportFragmentManager().beginTransaction();

                fab.setVisibility(View.INVISIBLE);

                fragment=new DthFragment().newInstance();

                fragmentTransaction.replace(R.id.detail_frag, fragment);

                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();

                break;


            case Constants.RECHARGE:

                fragmentTransaction=getSupportFragmentManager().beginTransaction();

                fab.setVisibility(View.INVISIBLE);

                fragment=new RechargeFragment().newInstance();

                fragmentTransaction.replace(R.id.detail_frag, fragment,"recharge");

                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();

                break;
            case Constants.METRO:

                fragmentTransaction=getSupportFragmentManager().beginTransaction();

                fab.setVisibility(View.INVISIBLE);

                fragment=new MetroFragment().newInstance();

                fragmentTransaction.replace(R.id.detail_frag, fragment);

                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();

                break;

            case Constants.BUS:
                
                busFrom=new BusFrom(this,"hello");
                busFrom.execute();
                dialogFragment =new PayMonDialogFragment().newInstance();
                dialogFragment.show(getSupportFragmentManager(), "Elements");


                break;
            case Constants.DIALOG_BOX:
                //Toast.makeText(this,String.valueOf(data),Toast.LENGTH_LONG).show();

                dialogFragment =new PayMonDialogFragment().newInstance();
                dialogFragment.show(getSupportFragmentManager(), "Elements");

                break;




        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


        FragmentManager fm = getSupportFragmentManager();
        ElementDisplayFragment myFragment = (ElementDisplayFragment) fm.findFragmentById(R.id.detail_frag);

        if((myFragment.isVisible()))
        {
            fab.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                String invite_message = "Check Out the New App PAYMON";
                // below is dummy msg
                // String invite_message =
                // "Hi , I am using this awesome app. Get it Here https://play.google.com/";
                StringBuilder sbuilder = new StringBuilder(invite_message);

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, sbuilder.toString());
                sendIntent.setType("text/plain");
                startActivityForResult(Intent.createChooser(sendIntent, "PayMon Invitation"), 101);

                break;
        }
    }

    @Override
    public void onNetworkController(int reqCode, Object data) {
        switch(reqCode) {
            case Constants.BUS:

                dialogFragment.dismiss();

                fragmentTransaction=getSupportFragmentManager().beginTransaction();

                fab.setVisibility(View.INVISIBLE);

                fragment=new BusFragment().newInstance(data);

                fragmentTransaction.replace(R.id.detail_frag, fragment);

                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();



        }
    }
}
