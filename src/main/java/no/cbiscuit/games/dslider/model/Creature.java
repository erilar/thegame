package no.cbiscuit.games.dslider.model;

import android.content.res.Resources;

public class Creature extends Mobile {

	private int currentHitpoints;
	private int totalHitpoints;
	private int damage;
	
	public Creature(Resources res, int x, int y) {
		super(res, x, y);
	}

	public int getCurrentHitpoints() {
		return currentHitpoints;
	}

	public int getTotalHitpoints() {
		return totalHitpoints;
	}

	public int getDamage() {
		return damage;
	}

	public void setCurrentHitpoints(int currentHitpoints) {
		this.currentHitpoints = currentHitpoints;
	}

	public void setTotalHitpoints(int totalHitpoints) {
		this.totalHitpoints = totalHitpoints;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	

}
