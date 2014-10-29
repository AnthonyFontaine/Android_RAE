package com.rae.placetobe.util;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPreferencesUtil
{
	static private final String TAG = SharedPreferencesUtil.class.getSimpleName();
	
	private final static String PREFERENCE_FILE_NAME_BACKUP  = "backup" ;
	private final static String PREFERENCE_FILE_NAME_IMAGE   = "ImageData" ;
	private final static String PREFERENCE_FILE_NAME_ACCOUNT = "AccountData" ;
	
	final static private String BACKUP_BLACK_AND_WHITE = "BACKUP_BLACK_AND_WHITE" ;
	final static private String BACKUP_FILE_PATH       = "BACKUP_FILE_PATH" ;
	
	
	/**
	 * Retrieves the Shared Preferences where the image data are saved
	 */
	static public SharedPreferences getImageDataPreferences(Context context)  {
		return context.getSharedPreferences(PREFERENCE_FILE_NAME_IMAGE, Context.MODE_PRIVATE)  ;
	}
	
	/**
	 * Retrieves the Shared Preferences for user Account
	 */
	static public SharedPreferences getAccountDataPreferences(Context context)  {
		return context.getSharedPreferences(PREFERENCE_FILE_NAME_ACCOUNT, Context.MODE_PRIVATE)  ;
	}
	
	/**
	 * Retrieves the Shared Preferences for user Account
	 */
	static private SharedPreferences getBackupPreferences(Context context)  {
		return context.getSharedPreferences(PREFERENCE_FILE_NAME_BACKUP, Context.MODE_PRIVATE)  ;
	}

	static public void backupFilePath(Context context, String filePath)  {
    	SharedPreferences.Editor editor = getBackupPreferences(context).edit();
    	if(filePath==null || filePath.isEmpty()) editor.remove   (BACKUP_FILE_PATH) ;
    	else									 editor.putString(BACKUP_FILE_PATH, filePath);
	    editor.commit() ;
	}
	
	static public void backupBlackAndWhite(Context context, Boolean blackAndWhite)  {
    	SharedPreferences.Editor editor = getBackupPreferences(context).edit();
    	if(blackAndWhite==null) editor.remove    (BACKUP_BLACK_AND_WHITE);
    	else				    editor.putBoolean(BACKUP_BLACK_AND_WHITE, blackAndWhite);
	    editor.commit() ;
	}

	static public String restoreFilePath(Context context)  {
    	SharedPreferences backup = getBackupPreferences(context);
    	return backup.getString(BACKUP_FILE_PATH, "") ;
	}

	static public Boolean restoreBlackAndWhite(Context context)  {
    	SharedPreferences backup = getBackupPreferences(context);
    	return backup.getBoolean(BACKUP_BLACK_AND_WHITE, Boolean.FALSE) ;
	}
	
	

	/**
	 * Utility method to see the content of shared preferences
	 */
	static public void dump(SharedPreferences sharedPreferences)
	{
        Log.d(TAG, "<< START DUMP >>");            
		Map<String,?> keys = sharedPreferences.getAll();
		for(Map.Entry<String,?> entry : keys.entrySet()){
            Log.d(TAG, entry.getKey() + " : " + String.valueOf(entry.getValue()));            
		}
        Log.d(TAG, "<< END DUMP >>");            
	}
}
