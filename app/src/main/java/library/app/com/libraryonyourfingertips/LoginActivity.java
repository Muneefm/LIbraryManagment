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
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.pnikosis.materialishprogress.ProgressWheel;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;


public class LoginActivity extends ActionBarActivity {

    EditText username,password;

Button signIn;

    protected ProgressWheel progressWheel;
    SmoothProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        android.support.v7.app.ActionBar actionBar =  getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));
        progressBar = (SmoothProgressBar) findViewById(R.id.progid);
        progressBar.applyStyle(R.style.GNowProgressBar);


        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        signIn = (Button) findViewById(R.id.signIn);
        //progressWheel = (ProgressWheel) findViewById(R.id.progress_wheel);


        signIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                //showProgress();
                ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {

                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            //hideProgress();
                            progressBar.setVisibility(View.INVISIBLE);


                            Toast.makeText(getApplicationContext()," Succesfully Signed In! ",Toast.LENGTH_LONG).show();

                            //Intent login = new Intent(LoginActivity.this,);

                            // Hooray! The user is logged in.
                        } else {
                              //  hideProgress();
                            progressBar.setVisibility(View.INVISIBLE);

                            Toast.makeText(getApplicationContext(),"Invalid Credentials! ",Toast.LENGTH_LONG).show();
                            // Signup failed. Look at the ParseException to see what happened.
                        }
                    }
                });


            }
        });






    }
    public void showProgress() {
        if (progressWheel != null)
            progressWheel.setVisibility(View.VISIBLE);
    }
    public void hideProgress() {
        if (progressWheel != null)
            progressWheel.setVisibility(View.GONE);
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
            Intent user = new Intent(LoginActivity.this,UserDetails.class);
            startActivity(user);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
