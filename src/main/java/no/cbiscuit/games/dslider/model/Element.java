package no.cbiscuit.games.dslider.model;

import java.util.Random;

import no.cbiscuit.games.dslider.activity.R;
import no.cbiscuit.games.dslider.view.Panel;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Element {

	private static int speedDivider = 100;
	private float mX;
	private float mY;
	private double mSpeedX;
	private double mSpeedY;

	private static Bitmap mBitmap;
	private static Bitmap dBitmap;

	private float dX;
	private float dY;

	private float yPosOffset;
	private float yNegOffset;

	private float xPosOffset;
	private float xNegOffset;

	private boolean destroyed = false;

	public boolean isDestroyed() {
		return destroyed;
	}

	public Element(Resources res, int x, int y) {
		Random rand = new Random();
		setGraphics(res);
		mX = x - mBitmap.getWidth() / 2;
		mY = y - mBitmap.getHeight() / 2;

		// offsets based on height (y axis) and width (x axis)
		xPosOffset = mBitmap.getWidth() / 2;
		xNegOffset = mBitmap.getWidth() / 2;
		yPosOffset = mBitmap.getHeight() / 2;
		yNegOffset = mBitmap.getHeight() / 2;

		mSpeedX = rand.nextInt(7) - 3;
		mSpeedY = rand.nextInt(7) - 3;
	}
	
	public Element(Resources res, int x, int y, int speedX, int speedY) {
		Random rand = new Random();
		setGraphics(res);
		mX = x - mBitmap.getWidth() / 2;
		mY = y - mBitmap.getHeight() / 2;

		// offsets based on height (y axis) and width (x axis)
		xPosOffset = mBitmap.getWidth() / 2;
		xNegOffset = mBitmap.getWidth() / 2;
		yPosOffset = mBitmap.getHeight() / 2;
		yNegOffset = mBitmap.getHeight() / 2;

		mSpeedX = speedX/speedDivider;
		mSpeedY = speedY/speedDivider;
		while(mSpeedX ==0 && mSpeedY == 0 ){
			mSpeedX = rand.nextInt(7) - 3;
			mSpeedY = rand.nextInt(7) - 3;
		}
	}

	public static void setGraphics(Resources res) {
		if(mBitmap == null) mBitmap = BitmapFactory.decodeResource(res, R.drawable.goodguy);
		if(dBitmap == null) dBitmap = BitmapFactory.decodeResource(res, R.drawable.evil);
	}


	public void animate(Long elapsedTime) {
		if (!isDestroyed()) {
			mX += mSpeedX * (elapsedTime / 20f);
			mY += mSpeedY * (elapsedTime / 20f);
			checkBorders();
		}
	}

	public void setDestroyed() {
		dX = mX;
		dY = mY;
		destroyed = true;
	}

	private void checkBorders() {
		if (mX <= 0) {
			mSpeedX = -mSpeedX;
			mX = 0;
		} else if (mX + mBitmap.getWidth() >= Panel.mWidth) {
			mSpeedX = -mSpeedX;
			mX = Panel.mWidth - mBitmap.getWidth();
		}
		if (mY <= 0) {
			mY = 0;
			mSpeedY = -mSpeedY;
		}
		if (mY + mBitmap.getHeight() >= Panel.mHeight) {
			mSpeedY = -mSpeedY;
			mY = Panel.mHeight - mBitmap.getHeight();
		}
	}

	public void doDraw(Canvas canvas) {
		if (!destroyed)
			canvas.drawBitmap(mBitmap, mX, mY, null);
		else
			canvas.drawBitmap(dBitmap, dX, dY, null);
	}

	public boolean isOccupyingSameSpace(Element other) {
		int oX = (int) other.mX;
		int oY = (int) other.mY;
		int tX = (int) mX;
		int tY = (int) mY;
		if (checkForOverlap(oX, oY, tX, tY)) {
			return true;
		} else
			return false;
	}

	private boolean checkForOverlap(int oX, int oY, int tX, int tY) {
		if (checkForXAxisOverlap(oX, tX) && checkForYAxisOverlap(oY, tY))
			return true;
		else
			return false;

	}

	private boolean checkForYAxisOverlap(int oY, int tY) {
		return oY + yPosOffset >= tY && oY - yNegOffset <= tY;
	}

	private boolean checkForXAxisOverlap(int oX, int tX) {
		return oX + xPosOffset >= tX && oX - xNegOffset <= tX;
	}

	public static int getSpeedDivider() {
		return speedDivider;
	}

	public static void setSpeedDivider(int speedDivider) {
		Element.speedDivider = speedDivider;
	}

	public double getmSpeedX() {
		return mSpeedX;
	}

	public void setmSpeedX(double mSpeedX) {
		this.mSpeedX = mSpeedX;
	}

	public double getmSpeedY() {
		return mSpeedY;
	}

	public void setmSpeedY(double mSpeedY) {
		this.mSpeedY = mSpeedY;
	}

	

}
