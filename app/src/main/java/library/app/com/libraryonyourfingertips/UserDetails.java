package library.app.com.libraryonyourfingertips;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Instant;
import org.joda.time.Interval;

import java.util.Date;
import java.util.List;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;


public class UserDetails extends ActionBarActivity {

    TextView name,id,fine,bookName,fineId,booknameId,libHead,lastDate;

SmoothProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        android.support.v7.app.ActionBar actionBar =  getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));
        progressBar = (SmoothProgressBar) findViewById(R.id.progidu);
        progressBar.applyStyle(R.style.GNowProgressBar);
        progressBar.progressiveStart();

        name = (TextView) findViewById(R.id.nameUser);
        id = (TextView) findViewById(R.id.regId);

        fine = (TextView) findViewById(R.id.fineAmount);
        bookName = (TextView) findViewById(R.id.bookName);
        fineId = (TextView) findViewById(R.id.fi);
        booknameId = (TextView) findViewById(R.id.bookn);
        libHead = (TextView) findViewById(R.id.libHead);
        lastDate = (TextView) findViewById(R.id.lastDate);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/RobotoCondensed-Light.ttf");

        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/Roboto-Regular.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/RobotoCondensed-Regular.ttf");

        name.setTypeface(custom_font1);
        fineId.setTypeface(custom_font2);
        booknameId.setTypeface(custom_font2);
        fine.setTypeface(custom_font1);
        bookName.setTypeface(custom_font1);
        libHead.setTypeface(custom_font2);
        lastDate.setTypeface(custom_font1);
        final Date currentDate =  new Date();

        //Toast.makeText(getApplicationContext()," date "+currentDate.getDate(),Toast.LENGTH_LONG ).show();



        final DateTime jcurrentDate = new DateTime(currentDate);


        //   Data collecting  from database and updating the views...

        ParseUser user  = ParseUser.getCurrentUser();
        final String nameUser = user.getString("FirstName");
        String LastName = user.getString("LastName");
        String regNo = user.getUsername();

        name.setText(nameUser+" "+LastName);
        id.setText(regNo);



        ParseQuery<ParseObject> query = ParseQuery.getQuery("RecordBook");
        query.whereEqualTo("regno", regNo);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> list, ParseException e) {
                progressBar.progressiveStop();
                if (e == null) {
                    //Log.d("score", "Retrieved " + scoreList.size() + " scores");
                    if(list.size()==1) {
                        String bookname = list.get(0).get("bookname").toString();
                        String datetime = list.get(0).get("lastdate").toString();
                        Date time = list.get(0).getDate("lastdate");
                        time.getDay();
                        DateTime jtime = new DateTime(time);
                        if(currentDate.after(time)){
                            Days days = Days.daysBetween(jtime,jcurrentDate);
                            days.toString();
                            days.getDays();
                            int fin = days.getDays()*10;
                            fine.setText("RS "+fin);
                            fine.setTextColor(getResources().getColor(R.color.holo_red_dark));
                            //Toast.makeText(getApplicationContext()," day count "+days.getDays(),Toast.LENGTH_LONG ).show();
                        }
                        Log.e("this is log", "  the date is "+ time.getDay());
                        //Toast.makeText(getApplicationContext()," date "+time.getDate(),Toast.LENGTH_LONG ).show();


                        if (bookname != null) {
                            bookName.setText(bookname);
                            lastDate.setText(datetime);
                            //Toast.makeText(getApplicationContext(), "loaded", Toast.LENGTH_LONG).show();


                        }
                    }else if(list.size()==0){
                        bookName.setText("**** No Books In Record ****");
                        bookName.setTextColor(getResources().getColor(R.color.holo_red_dark));

                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });






        //Toast.makeText(getApplicationContext(),"name is "+nameUser,Toast.LENGTH_LONG ).show();












    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_details, menu);
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
