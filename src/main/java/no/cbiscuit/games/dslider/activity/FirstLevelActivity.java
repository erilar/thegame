package no.cbiscuit.games.dslider.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class FirstLevelActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onBackPressed() {
		  startActivity( new Intent(
				"no.cbiscuit.games.dslider.activity.STARTMENU"));
	}

}
