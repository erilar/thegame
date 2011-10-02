package no.cbiscuit.games.dslider.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class KeyValueRepository {
	
	// Used for persisting K/V pairs
		private SharedPreferences preferences;
		private SharedPreferences.Editor editor;
	
	public KeyValueRepository(Context context){
		 preferences = PreferenceManager.getDefaultSharedPreferences(context);
		 editor = preferences.edit();
	}
	
	
	public int getIntValue(String key){
		return preferences.getInt(key, 0);
	}
	
	public void saveInt(String key, int value){
		editor.putInt(key, value);
		editor.commit();
	}
	
	

}
