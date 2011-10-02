package no.cbiscuit.games.dslider.activity;

import no.cbiscuit.games.dslider.thread.TransitionThread;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class StartUpActivity extends Activity{

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	    	requestWindowFeature(Window.FEATURE_NO_TITLE);
	    	setContentView(R.layout.splash);
	    	
	    	TransitionThread logoSplash = new TransitionThread(this,new Intent("no.cbiscuit.games.dslider.activity.STARTMENU"),2000);
	    	logoSplash.start();
	    	
	    }
	
	

}
