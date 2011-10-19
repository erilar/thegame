package no.cbiscuit.games.dslider.activity;

import no.cbiscuit.games.dslider.view.GameBoard;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;

public class FirstLevelActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameBoard gameBoard = new GameBoard(this, BitmapFactory.decodeResource(getResources(), R.drawable.backgroundlvl1));
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(gameBoard);
	}

	@Override
	public void onBackPressed() {
		startActivity(new Intent("no.cbiscuit.games.dslider.activity.STARTMENU"));
	}

}
