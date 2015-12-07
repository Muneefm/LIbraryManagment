package library.app.com.libraryonyourfingertips;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import library.app.com.libraryonyourfingertips.Adapters.BooksList;


public class BooSearchResult extends ActionBarActivity {


    String[] bookName= new String[20];
    String[] author= new String[20];
    String[] publisher= new String[20];
    ListView list;
    EditText search;
    BooksList adapter;
    private Spinner spinner1;
    protected ProgressWheel progressWheel;
    TextView searchBy;

    private ArrayList<String> sortBook = new ArrayList<String>();
    private ArrayList<String> sortAuthor = new ArrayList<String>();
    private ArrayList<String> sortPublisher = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boo_search_result);
        android.support.v7.app.ActionBar actionBar =  getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));

        progressWheel = (ProgressWheel) findViewById(R.id.progress_wheel);
        searchBy = (TextView) findViewById(R.id.txt);
        searchBy.setVisibility(View.INVISIBLE);

        list= (ListView) findViewById(R.id.booklist);
        search = (EditText) findViewById(R.id.searchEdit);
        search.setVisibility(View.INVISIBLE);
        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner1.setVisibility(View.INVISIBLE);
        List<String> listIt = new ArrayList<String>();
        listIt.add("Book Name");
        listIt.add("Author");
        listIt.add("Publisher");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,listIt);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);


        spinner1.setAdapter(dataAdapter);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {




            }

            @Override
            public void afterTextChanged(Editable s) {

                String text = search.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);



            }
        });
        ParseQuery<ParseObject> queryTwo = ParseQuery.getQuery("books");
        queryTwo.findInBackground(new FindCallback<ParseObject>() {

            public void done(List<ParseObject> results, ParseException e) {
                progressWheel.setVisibility(View.INVISIBLE);

                //hideProgress();
                // hideProgress();
                search.setVisibility(View.VISIBLE);
                spinner1.setVisibility(View.VISIBLE);
                searchBy.setVisibility(View.VISIBLE);
                int i;
                if (e == null) {

               /*    if(newBundle.getInt("position")==0){

                    pref.setActiveCount(results.size());
                    }else{
                        pref.setWinnersCount(results.size());
                    }


*/
                    //  pref.setCount(results.size());
                    int total = results.size();
                    for(i=0;i<results.size();i++) {
                        bookName[i] = results.get(i).getString("BookName");


                        author[i] = results.get(i).getString("Author");
                        publisher[i] = results.get(i).getString("Publishers");

                    }
                    adapter  = new BooksList(getApplicationContext(),bookName,author,publisher,total);
                    list.setAdapter(adapter);
                         /*if(newBundle.getInt("position")==0){
                             Log.e("this is log","value of pos is =  if");

                             adapterWinner = new AdapterWinner(getActivity(),startTime,currencyPair,price,direction,exitTime);
                        lv.setAdapter(adapterWinner);

                         }

                        else {*/
                    Log.e("this is log", "value of pos is =  else");
                    //adapterWinnerReal = new AdapterRealWinner(getActivity(), startTime, currencyPair, price, direction, exitTime,results.size());
                  //  adapterWinner = new AdapterWinner(getActivity(),startTime,currencyPair,price,direction,exitTime,results.size());

                    //lv.setAdapter(adapterWinner);
                    // }


                    // There was an error
                    //  String re = results.get(1).getString("Price");
                    //
                    //results.
                    //Toast.makeText(getApplicationContext(), " inside "+re, Toast.LENGTH_LONG).show();

                    // String re = results.get(0).getString("Price");
                    //tv.setText(re);
                    // Toast.makeText(getApplicationContext(), " noooooooo    error ", Toast.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(), " sizr  "+size, Toast.LENGTH_LONG).show();


                } else {
                    // results have all the Posts the current user liked.
                    //  String error =  e.getMessage();

                    Toast.makeText(getApplicationContext(), " error  " + e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        });










    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_boo_search_result, menu);
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
