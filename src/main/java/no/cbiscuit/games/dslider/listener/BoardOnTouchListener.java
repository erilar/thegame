package no.cbiscuit.games.dslider.listener;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class BoardOnTouchListener implements View.OnTouchListener {
	
	private GestureDetector gestureDetector;
	
	public BoardOnTouchListener(View view){
		this.gestureDetector = new GestureDetector(new BoardGestureListener(view));;
	}
	
	
	public boolean onTouch(View v, MotionEvent event) {
		if (gestureDetector.onTouchEvent(event)) {
			return true;
		}
		return false;
	}
}
