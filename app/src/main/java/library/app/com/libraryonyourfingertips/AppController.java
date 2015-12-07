package library.app.com.libraryonyourfingertips;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by Muneef on 22/03/15.
 */
public class AppController extends Application {





    @Override
    public void onCreate() {
        super.onCreate();


        // Initialize the Parse SDK.
        // Parse.initialize(this, "YOUR_APP_ID", "YOUR_CLIENT_KEY");

        // Specify an Activity to handle all pushes by default.
        // PushService.setDefaultPushCallback(this, MainActivity.class);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "LWsAMdRMq1baTLwMXewDgl7Dp5vkc1qg4213gkjH", "x2xcb2rkwL1F3qxjFY65kWz6CiOLkNWCKj6Q1Djr");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }
}





