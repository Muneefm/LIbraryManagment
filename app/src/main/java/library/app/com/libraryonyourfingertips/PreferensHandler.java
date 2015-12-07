package library.app.com.libraryonyourfingertips;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Muneef on 01/04/15.
 */
public class PreferensHandler {
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context c;
    int PRIVATE_MODE = 0;


    private static final String PREF_NAME = "settings_pref";
    final String searchPref= "count";
   /* final String countActive= "choose";
    final String countWinners= "choose";
    final String which= "choose";*/

    @SuppressLint("CommitPrefEdits")
    public PreferensHandler(Context context) {
        this.c = context;
        pref = c.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }



    public void setOption(int count){
        editor.putInt(searchPref, count);
        editor.commit();
    }
    public int getOption(){
        return pref.getInt(searchPref, 1);
    }


}
