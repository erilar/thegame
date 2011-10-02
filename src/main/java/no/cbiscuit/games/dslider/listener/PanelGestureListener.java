package no.cbiscuit.games.dslider.listener;

import no.cbiscuit.games.dslider.view.Panel;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

public class PanelGestureListener extends SimpleOnGestureListener {
	private View view;

	public PanelGestureListener(View view) {
		this.view = view;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		((Panel) view).addElement(e1, velocityX, velocityY);
		return false;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		((Panel) view).addElement(e);
		return super.onSingleTapConfirmed(e);
	}


}