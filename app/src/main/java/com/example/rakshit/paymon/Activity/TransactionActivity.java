
package com.example.rakshit.paymon.Activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rakshit.paymon.Adapter.ElementDisplayAdapter;
import com.example.rakshit.paymon.Adapter.TransactionDisplayAdapter;
import com.example.rakshit.paymon.NetworkController.Transaction;
import com.example.rakshit.paymon.R;

import org.json.JSONArray;
import org.json.JSONException;

public class TransactionActivity extends ActionBarActivity {
    private RecyclerView elementDisplayView;
    public RecyclerView.LayoutManager layoutManager;
    Bundle bundle;
    public JSONArray jsonArray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.app_icon);
        bundle=getIntent().getExtras();

        String arr=bundle.getString("arr");
        try {
            jsonArray = new JSONArray(arr);
         int len=jsonArray.length();

            String msj[]=new String[len];
            String dat[]=new String[len];

            for(int i=0;i<len;i++)
            {
                msj[i]=jsonArray.getJSONObject(i).getString("msj");
                dat[i]=jsonArray.getJSONObject(i).getString("trans_date");
            }







        elementDisplayView = (RecyclerView) findViewById(R.id.element_display_view);
        elementDisplayView .setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        elementDisplayView.setLayoutManager(layoutManager);


        TransactionDisplayAdapter adapter=new TransactionDisplayAdapter(this,msj,dat);

        elementDisplayView.setAdapter(adapter);

        }catch (JSONException e)
        {

        }




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transaction, menu);
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
}
