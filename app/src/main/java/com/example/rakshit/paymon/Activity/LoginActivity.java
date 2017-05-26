package com.example.rakshit.paymon.Activity;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rakshit.paymon.Fragment.CardPaymentFragment;
import com.example.rakshit.paymon.Fragment.PayMonDialogFragment;
import com.example.rakshit.paymon.Listener.OnFragmentCommunicationListener;
import com.example.rakshit.paymon.NetworkController.Login;
import com.example.rakshit.paymon.NetworkController.Register;
import com.example.rakshit.paymon.R;
import com.example.rakshit.paymon.Utilities.Constants;


public class LoginActivity extends ActionBarActivity implements OnFragmentCommunicationListener {
    EditText email,password,reg_name,reg_email,reg_pass,reg_num;
    TextView login,sign_up;
    LinearLayout login_ll,register_ll;
    public Login login_ob;
    public Register register_ob;
    private PayMonDialogFragment dialogFragment;
    public android.support.v4.app.FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        email=(EditText)findViewById(R.id.input_email);
        password=(EditText)findViewById(R.id.input_password);
        login_ll=(LinearLayout)findViewById(R.id.login_ll);
        register_ll=(LinearLayout)findViewById(R.id.register_ll);
        sign_up=(TextView)findViewById(R.id.sign_up);
        login_ll.setVisibility(View.VISIBLE);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(email, InputMethodManager.SHOW_FORCED);
        login=(TextView)findViewById(R.id.login);

        reg_name=(EditText)findViewById(R.id.reg_name);
        reg_email=(EditText)findViewById(R.id.reg_email);
        reg_pass=(EditText)findViewById(R.id.reg_pass);
        reg_num=(EditText)findViewById(R.id.reg_num);

        fragmentTransaction=getSupportFragmentManager().beginTransaction();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( (((TextView)v).getText().toString()).equals("Login")) {

                    String em=email.getText().toString();
                    String pas=password.getText().toString();
                    if(em.length()>0&&pas.length()>0)

                    {
                     if(em.contains("@")&&em.contains("."))
                     { login_ob = new Login(LoginActivity.this, email.getText().toString(), password.getText().toString());

                        login_ob.execute();

                        onFragmentCommunicated(12, null);
                    }
                        else
                         Toast.makeText(LoginActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(LoginActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    String na=reg_name.getText().toString();
                    String em=reg_email.getText().toString();
                    String pa=reg_pass.getText().toString();
                    String nu=reg_num.getText().toString();
                    if(na.length()>0&&em.length()>0&&pa.length()>0&&nu.length()>0) {
                        if(em.contains("@")&&em.contains(".")) {
                            register_ob = new Register(LoginActivity.this, reg_name.getText().toString(), reg_email.getText().toString(), reg_pass.getText().toString(), reg_num.getText().toString());

                            register_ob.execute();
                            onFragmentCommunicated(12, null);
                        }
                        else
                            Toast.makeText(LoginActivity.this,"Enter Valid Credentials",Toast.LENGTH_SHORT).show();

                    }
                    else
                        Toast.makeText(LoginActivity.this,"Enter Valid Credentials",Toast.LENGTH_SHORT).show();


                }         }
        });


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_ll.setVisibility(View.GONE);
                login.setText("Register");
                register_ll.setVisibility(View.VISIBLE);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentCommunicated(int reqCode, Object data) {

        switch(reqCode)
        {
            case Constants.DIALOG_BOX:
                //Toast.makeText(this,String.valueOf(data),Toast.LENGTH_LONG).show();

                dialogFragment =new PayMonDialogFragment().newInstance();
                dialogFragment.show(getSupportFragmentManager(), "Elements");

                break;
        }

    }
}
