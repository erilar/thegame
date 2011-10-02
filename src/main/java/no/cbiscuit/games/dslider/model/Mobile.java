package no.cbiscuit.games.dslider.model;

import java.util.Random;

import no.cbiscuit.games.dslider.activity.R;
import no.cbiscuit.games.dslider.view.Panel;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Mobile {

	private static int speedDivider = 100;
	private float coordinateX;
	private float coordinateY;
	private double speedX;
	private double speedY;

	private static Bitmap aliveImage;
	private static Bitmap destroyedImage;

	private float destroyedX;
	private float destroyedY;

	private float yPosOffset;
	private float yNegOffset;

	private float xPosOffset;
	private float xNegOffset;

	private boolean destroyed = false;

	public boolean isDestroyed() {
		return destroyed;
	}

	public Mobile(Resources res, int x, int y) {
		Random rand = new Random();
		setGraphics(res);
		coordinateX = x - aliveImage.getWidth() / 2;
		coordinateY = y - aliveImage.getHeight() / 2;

		// offsets based on height (y axis) and width (x axis)
		xPosOffset = aliveImage.getWidth() / 2;
		xNegOffset = aliveImage.getWidth() / 2;
		yPosOffset = aliveImage.getHeight() / 2;
		yNegOffset = aliveImage.getHeight() / 2;

		speedX = rand.nextInt(7) - 3;
		speedY = rand.nextInt(7) - 3;
	}
	
	public Mobile(Resources res, int x, int y, int speedX, int speedY) {
		Random rand = new Random();
		setGraphics(res);
		coordinateX = x - aliveImage.getWidth() / 2;
		coordinateY = y - aliveImage.getHeight() / 2;

		// offsets based on height (y axis) and width (x axis)
		xPosOffset = aliveImage.getWidth() / 2;
		xNegOffset = aliveImage.getWidth() / 2;
		yPosOffset = aliveImage.getHeight() / 2;
		yNegOffset = aliveImage.getHeight() / 2;

		speedX = speedX/speedDivider;
		speedY = speedY/speedDivider;
		while(speedX ==0 && speedY == 0 ){
			speedX = rand.nextInt(7) - 3;
			speedY = rand.nextInt(7) - 3;
		}
	}

	public static void setGraphics(Resources res) {
		if(aliveImage == null) aliveImage = BitmapFactory.decodeResource(res, R.drawable.goodguy);
		if(destroyedImage == null) destroyedImage = BitmapFactory.decodeResource(res, R.drawable.evil);
	}


	public void animate(Long elapsedTime) {
		if (!isDestroyed()) {
			coordinateX += speedX * (elapsedTime / 20f);
			coordinateY += speedY * (elapsedTime / 20f);
			checkBorders();
		}
	}

	public void setDestroyed() {
		destroyedX = coordinateX;
		destroyedY = coordinateY;
		destroyed = true;
	}

	private void checkBorders() {
		if (coordinateX <= 0) {
			speedX = -speedX;
			coordinateX = 0;
		} else if (coordinateX + aliveImage.getWidth() >= Panel.panelWidth) {
			speedX = -speedX;
			coordinateX = Panel.panelWidth - aliveImage.getWidth();
		}
		if (coordinateY <= 0) {
			coordinateY = 0;
			speedY = -speedY;
		}
		if (coordinateY + aliveImage.getHeight() >= Panel.panelHeigth) {
			speedY = -speedY;
			coordinateY = Panel.panelHeigth - aliveImage.getHeight();
		}
	}

	public void doDraw(Canvas canvas) {
		if (!destroyed)
			canvas.drawBitmap(aliveImage, coordinateX, coordinateY, null);
		else
			canvas.drawBitmap(destroyedImage, destroyedX, destroyedY, null);
	}

	public boolean isOccupyingSameSpace(Mobile other) {
		int oX = (int) other.coordinateX;
		int oY = (int) other.coordinateY;
		int tX = (int) coordinateX;
		int tY = (int) coordinateY;
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
		Mobile.speedDivider = speedDivider;
	}

	public double getmSpeedX() {
		return speedX;
	}

	public void setmSpeedX(double mSpeedX) {
		this.speedX = mSpeedX;
	}

	public double getmSpeedY() {
		return speedY;
	}

	public void setmSpeedY(double mSpeedY) {
		this.speedY = mSpeedY;
	}

	

}
