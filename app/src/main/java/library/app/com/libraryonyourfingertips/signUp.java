package library.app.com.libraryonyourfingertips;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gc.materialdesign.views.Button;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;


public class signUp extends ActionBarActivity {

    EditText username,firstname,lastname,password,phoneNumber,email;

Button signUpl;
    SmoothProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        android.support.v7.app.ActionBar actionBar =  getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));
        username = (EditText) findViewById(R.id.username);
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        password = (EditText) findViewById(R.id.password);
        phoneNumber = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.emailid);
        progressBar = (SmoothProgressBar) findViewById(R.id.progid2);
        progressBar.applyStyle(R.style.GNowProgressBar);


        signUpl = (Button) findViewById(R.id.signUpn);

        signUpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                ParseUser user = new ParseUser();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.setEmail(email.getText().toString());

// other fields can be set just like with ParseObject
                user.put("phone", phoneNumber.getText().toString());
                user.put("FirstName", firstname.getText().toString());
                user.put("LastName", lastname.getText().toString());


                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        progressBar.setVisibility(View.INVISIBLE);

                        if (e == null) {
                            Toast.makeText(getApplicationContext()," Succesfully Signed Up! ",Toast.LENGTH_LONG).show();

                            // Intent
                            // Hooray! Let them use the app now.
                        } else {

                            Toast.makeText(getApplicationContext(),"Something went wrong!"+e.getMessage(),Toast.LENGTH_LONG).show();
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                        }
                    }
                });


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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
}
