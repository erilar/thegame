package no.cbiscuit.games.dslider.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class StartMenuActivity extends Activity {

    private static String TAG = "StartMenuActivity";

    /**
     * This is where the game process starts when the game is launched. Because it is declared as the Main intent in the
     * android manifest file.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.startmenu);
        
        
        
        Button startBtn = (Button) findViewById(R.id.startBtn);
		startBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent("no.cbiscuit.games.dslider.activity.FIRSTLEVEL"));
			}
		});
    }

}

