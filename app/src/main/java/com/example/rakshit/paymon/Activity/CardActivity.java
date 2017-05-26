package com.example.rakshit.paymon.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rakshit.paymon.Fragment.CardPaymentFragment;
import com.example.rakshit.paymon.Fragment.WalletPayFragment;
import com.example.rakshit.paymon.Listener.OnFragmentCommunicationListener;
import com.example.rakshit.paymon.R;
import com.example.rakshit.paymon.Utilities.Constants;

public class CardActivity extends ActionBarActivity implements OnFragmentCommunicationListener {
    private Fragment fragment;
    private FragmentTransaction fragmentTransaction;
    public Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
//        getSupportActionBar().hide();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setIcon(R.drawable.app_icon);

        bundle=getIntent().getExtras();


        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        onFragmentCommunicated(2,null);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_card, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentCommunicated(int reqCode, Object data)
    {
        switch(reqCode)
        {
            case Constants.DEBIT_CARD:
                //Toast.makeText(this,String.valueOf(data),Toast.LENGTH_LONG).show();

                fragment=new CardPaymentFragment().newInstance();

                fragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.frag_container,fragment).commit();

                break;



        }

    }
}
