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

	public float[] crash(float xCo1,float yCo1, float xSpd1, float ySpd1,float xCo2,float yCo2,float xSpd2, float ySpd2 ){
		float[] crash=new float[4];
		float dx;
		float dy;
		float mass1=1;
		float mass2=1;
		float collisionA;
		float magnitude1;
		float magnitude2;
		float direction1;
		float direction2;
		float XnewSpeed1;
		float YnewSpeed1;
		float XnewSpeed2;
		float YnewSpeed2;
		float finalXspeed1;
		float finalYspeed1;
		float finalXspeed2;
		float finalYspeed2;
		dx = xCo1-xCo2;
		dy = yCo1-yCo2;
		collisionA= (float) Math.atan2(dy, dx);
		magnitude1 = (float) Math.sqrt(xSpd1*xSpd1+ySpd1*ySpd1);
		magnitude2 = (float) Math.sqrt(xSpd2*xSpd2+ySpd2*ySpd2);
		direction1 = (float) Math.atan2(ySpd1, xSpd1);
		direction2 = (float) Math.atan2(ySpd2, xSpd2);
		XnewSpeed1 = (float) (magnitude1*Math.cos(direction1-collisionA));
		YnewSpeed1 = (float) (magnitude1*Math.sin(direction1-collisionA));
		XnewSpeed2 = (float) (magnitude2*Math.cos(direction2-collisionA));
		YnewSpeed2 = (float) (magnitude2*Math.sin(direction2-collisionA));
		finalXspeed1 =((mass1-mass2)*XnewSpeed1+(mass1+mass2) *XnewSpeed2)/(mass1+mass2);//the balls have a mass of one
		finalXspeed2 = ((mass1+mass2)*XnewSpeed1+(mass2-mass1)*XnewSpeed2)/(mass1+mass2);//the balls have a mass of one
		finalYspeed1 = YnewSpeed1;
		finalYspeed2 = YnewSpeed2;
		crash[0]= (float) (Math.cos(collisionA)*finalXspeed1+Math.cos(collisionA*Math.PI/2)*finalYspeed1);
		crash[1] = (float) (Math.sin(collisionA)*finalXspeed1+Math.sin(collisionA*Math.PI/2)*finalYspeed1);
		crash[2]= (float) (Math.cos(collisionA)*finalXspeed2+Math.cos(collisionA*Math.PI/2)*finalYspeed2);
		crash[3]= (float) (Math.sin(collisionA)*finalXspeed2+Math.sin(collisionA*Math.PI/2)*finalYspeed2);
        
		return crash;
		}
}
