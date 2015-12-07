package library.app.com.libraryonyourfingertips;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by Muneef on 01/04/15.
 */
public class CustomOnItemSelectedListener  implements AdapterView.OnItemSelectedListener {
PreferensHandler pref;
    public void onItemSelected(AdapterView<?> parent, View view, int pos,
                               long id) {

        pref = new PreferensHandler(parent.getContext());

        Toast.makeText(parent.getContext(),
                " Selected : \n" + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_LONG).show();

        if(parent.getItemAtPosition(pos).toString().equals("Book Name")){
            pref.setOption(1);
        }else if(parent.getItemAtPosition(pos).toString().equals("Author")){
            pref.setOption(2);
        }else if(parent.getItemAtPosition(pos).toString().equals("Publisher")){
            pref.setOption(3);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
pref.setOption(1);
    }

}

