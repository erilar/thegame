package no.cbiscuit.games.dslider.model;

public class Settings {
	
	private static boolean sound = true;
	private static boolean music = true;
	private static boolean vibration = true;
	private static boolean devmode = false;
	
	public static boolean isSound() {
		return sound;
	}
	public static void setSound(boolean sound) {
		Settings.sound = sound;
	}
	public static boolean isMusic() {
		return music;
	}
	public static void setMusic(boolean music) {
		Settings.music = music;
	}
	public static boolean isVibration() {
		return vibration;
	}
	public static void setVibration(boolean vibration) {
		Settings.vibration = vibration;
	}
	public static boolean isDevmode() {
		return devmode;
	}
	public static void setDevmode(boolean devmode) {
		Settings.devmode = devmode;
	}

}
