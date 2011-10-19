package no.cbiscuit.games.dslider.listener;

import no.cbiscuit.games.dslider.view.GameBoard;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

public class BoardGestureListener extends SimpleOnGestureListener {
	private View view;

	public BoardGestureListener(View view) {
		this.view = view;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		((GameBoard) view).addElement(e1, velocityX, velocityY);
		return false;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		((GameBoard) view).addElement(e);
		return super.onSingleTapConfirmed(e);
	}


}