package library.app.com.libraryonyourfingertips.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Locale;

import library.app.com.libraryonyourfingertips.PreferensHandler;
import library.app.com.libraryonyourfingertips.R;

/**
 * Created by Muneef on 27/03/15.
 */
public class BooksList extends BaseAdapter {

    Context c;
    String[] books;
    String[] author;
    String[] publisher;
    TextView booksText,authorText,publisherText;
    int totalCount;
int realTotal;
    PreferensHandler pref;
    String[] booksDupli,authorDupli,publisherDupli;



    public BooksList(Context context,String[] books,String[] author,String[] publisher,int total){

        this.c = context;
        this.books = books;
        this.author = author;
        this.publisher = publisher;
        this.totalCount = total;
        this.realTotal=total;
        this.booksDupli = books;
        this.authorDupli = author;
        this.publisherDupli = publisher;



    }



    @Override
    public int getCount() {
        return totalCount;
    }

    @Override
    public Object getItem(int position) {
        return books[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        pref = new PreferensHandler(c);

        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.books_items,parent,false);

        booksText = (TextView) v.findViewById(R.id.bookname);
        authorText = (TextView) v.findViewById(R.id.author);
        publisherText = (TextView) v.findViewById(R.id.publisher);
        Typeface custom_font1 = Typeface.createFromAsset(c.getAssets(),
                "fonts/RobotoCondensed-Light.ttf");

        Typeface custom_font2 = Typeface.createFromAsset(c.getAssets(),
                "fonts/Roboto-Black.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(c.getAssets(),
                "fonts/Roboto-Thin.ttf");
        //booksText.setTypeface(custom_font1);
        authorText.setTypeface(custom_font1);
        publisherText.setTypeface(custom_font1);


        if(books[position]!=null){
        booksText.setText(books[position].toString());

    }if(author[position]!=null){
                authorText.setText(author[position].toString());

            }if(publisher[position]!=null){
                publisherText.setText(publisher[position].toString());
            }






        return v;
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        /*worldpopulationlist.clear();
        if (charText.length() == 0) {
            worldpopulationlist.addAll(arraylist);
        } else {
            for (WorldPopulation wp : arraylist) {
                if (wp.getCountry().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    worldpopulationlist.add(wp);
                }
            }
        }*/
        int newTot = 0;
        int lenghtChar = charText.length();
        Log.e("this is log", " lenght char value=  " + charText.length());
        books = new String[100];
        author = new String[100];
        publisher = new String[100];

        for (int i = 0; i < realTotal; i++) {
            // Log.e("this is log", " inside filter loop  i =  " + i);
            if (pref.getOption() == 1) {
                if (lenghtChar <= booksDupli.length) {
                    //  Log.e("this is log", " lenght duplicate value=  " + booksDupli.length);
                    if (booksDupli[i].toString().toLowerCase(Locale.getDefault()).contains(charText)) {
                        newTot = newTot + 1;

                        books[newTot - 1] = booksDupli[i];
                        author[newTot - 1] = authorDupli[i];
                        publisher[newTot - 1] = publisherDupli[i];

                        //  Log.e("this is log", " equal value=  " + booksDupli[i]);
                        Log.e("this is log", " inside 1 ");


                    }
                }
            } else if (pref.getOption() == 2) {
                if (lenghtChar <= authorDupli.length) {
                    //  Log.e("this is log", " lenght duplicate value=  " + booksDupli.length);
                    if (authorDupli[i].toString().toLowerCase(Locale.getDefault()).contains(charText)) {
                        newTot = newTot + 1;

                        books[newTot - 1] = booksDupli[i];
                        author[newTot - 1] = authorDupli[i];
                        publisher[newTot - 1] = publisherDupli[i];

                        // Log.e("this is log", " equal value=  " + booksDupli[i]);

                        Log.e("this is log", " inside 2");
                    }

                }} else if (pref.getOption() == 3) {
                    if (lenghtChar <= publisherDupli.length) {
                        //  Log.e("this is log", " lenght duplicate value=  " + booksDupli.length);
                        if (publisherDupli[i].toString().toLowerCase(Locale.getDefault()).contains(charText)) {
                            newTot = newTot + 1;

                            books[newTot - 1] = booksDupli[i];
                            author[newTot - 1] = authorDupli[i];
                            publisher[newTot - 1] = publisherDupli[i];

                            // Log.e("this is log", " equal value=  " + booksDupli[i]);


                        }

                        Log.e("this is log", " inside 3");


                    }


                }
                totalCount = newTot;
                notifyDataSetChanged();



        }
    }

}