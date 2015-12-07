package library.app.com.libraryonyourfingertips;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gc.materialdesign.views.Button;
import com.parse.ParseException;
import com.parse.ParseUser;


public class MainActivity extends ActionBarActivity {


    public Button login,signUp,userProfile,signOut;
    EditText search;
    TextView welcome;
    Boolean signInOrNot=false;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar actionBar =  getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));
        login = (Button) findViewById(R.id.login);
        signUp = (Button) findViewById(R.id.signUp);
        search  = (EditText) findViewById(R.id.search);
        welcome = (TextView) findViewById(R.id.welcome);
        userProfile = (Button) findViewById(R.id.userProf);
        signOut = (Button) findViewById(R.id.signOut);

           String currentUser = ParseUser.getCurrentUser().getUsername();


        if(currentUser != null){

            signInOrNot = true;
            userProfile.setVisibility(View.VISIBLE);
            signOut.setVisibility(View.VISIBLE);

            Log.e("this is log", " main act  user not  "+currentUser);


        }else{
            /// add user first name to welcome user
//            String userFirstName =  currentUser.getString("FirstName").toString();
  //          welcome.setText("Welcome Mr."+userFirstName);


            signInOrNot = false;
            login.setVisibility(View.VISIBLE);
            signUp.setVisibility(View.VISIBLE);
            Log.e("this is log", " main act  user null");

        }


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchPage  = new Intent(MainActivity.this,BooSearchResult.class);
                startActivity(searchPage);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent next = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(next);

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nexts = new Intent(MainActivity.this,signUp.class);
                startActivity(nexts);

            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//currentUser.logOut();
                ParseUser nowUser = ParseUser.getCurrentUser();
                nowUser.logOut();


               // ser.
                Intent main = new Intent(MainActivity.this,MainActivity.class);
                startActivity(main);
                finish();
            }
        });

        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userProf = new Intent(MainActivity.this,UserDetails.class);
                startActivity(userProf);
            }
        });

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
