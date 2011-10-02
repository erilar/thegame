package no.cbiscuit.games.dslider.thread;

import no.cbiscuit.games.dslider.view.Panel;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class ViewThread extends Thread {

	private Panel mPanel;
	private SurfaceHolder mHolder;
	private boolean mRun = false;
	private long mStartTime;
	private long mElapsed;
	
	public ViewThread(Panel panel){
		mPanel = panel;
		mHolder = mPanel.getHolder();
	}
	
	public void setRunning(boolean run){
		mRun = run;
	}
	
	
	@Override
	public void run(){
		Canvas canvas = null;
		mStartTime = System.currentTimeMillis();
		while(mRun){ 
			canvas = mHolder.lockCanvas();
			if(canvas != null){
				
				mPanel.animate(mElapsed);
				mPanel.doDraw(mElapsed, canvas);
				mElapsed = System.currentTimeMillis() - mStartTime;
				mPanel.purgeDestroyed();
				mHolder.unlockCanvasAndPost(canvas);
				
			}
			  mStartTime = System.currentTimeMillis();
		}
	}
	
}
