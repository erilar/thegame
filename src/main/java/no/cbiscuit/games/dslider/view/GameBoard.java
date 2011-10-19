package no.cbiscuit.games.dslider.view;

import java.util.ArrayList;
import java.util.List;

import no.cbiscuit.games.dslider.listener.BoardGestureListener;
import no.cbiscuit.games.dslider.listener.BoardOnTouchListener;
import no.cbiscuit.games.dslider.model.Mobile;
import no.cbiscuit.games.dslider.model.Player;
import no.cbiscuit.games.dslider.thread.BoardReDrawThread;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameBoard extends SurfaceView implements SurfaceHolder.Callback {

	public static float panelWidth;
	public static float panelHeigth;
	private BoardReDrawThread reDrawThread;
	private View.OnTouchListener playerTouchListener;
	private final Bitmap background;
	
	private Player player;
	
	protected List<Mobile> enemies = new ArrayList<Mobile>();

	public GameBoard(Context context, Bitmap background) {
		super(context);
		this.background = background;
		createResources(context);
	}

	private void createResources(Context context) {
		getHolder().addCallback(this);
		playerTouchListener = new BoardOnTouchListener(this);
		setOnTouchListener(playerTouchListener);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		if(reDrawThread == null || !reDrawThread.isAlive()){
			reDrawThread = new BoardReDrawThread(this);
			reDrawThread.setRunning(true);
			reDrawThread.start();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		
	}

	public void addElement(MotionEvent e1, float velocityX, float velocityY) {
		
	}

	public void doDraw(long mElapsed, Canvas canvas) {
		canvas.drawBitmap(background, 0, 0, null);
	}

	public void purgeDestroyed() {
		
	}

	public void addElement(MotionEvent e) {
		
	}
}
