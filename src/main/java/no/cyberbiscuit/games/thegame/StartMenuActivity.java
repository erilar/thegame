package no.cyberbiscuit.games.thegame;

import no.simjan.games.thegame.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class StartMenuActivity extends Activity {

    private static String TAG = "StartMenuActivity";

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.startmenu);
    }

}

