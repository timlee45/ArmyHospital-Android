package com.system.data.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

public class SQLiteProvider extends ContentProvider{

	private final String TAG = "SQLiteProvider";
	private final String SQLITE_FILE_NAME = "SQLprovider.db";
	private final String SQLITE_NAME = "provider";
	private final int SQLITE_VERSION = 1;
	
	private SQLiteOpenHelper sqlHelper = null;
	
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		if(uri.getPath().contains("/presence_record")){
			logCat("delete chat record for where: " + selection);
			try {
				StringBuilder whereClause = new StringBuilder();
				whereClause.append("name = '" + selection + "'");
				sqlHelper.getWritableDatabase().delete(SQLITE_NAME, whereClause.toString(), null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		if(uri.getPath().contains("/presence_record")){
			insertRecord(values);
		}
		return null;
	}

	@Override
	public boolean onCreate() {
		initSQLiteHelper();
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		if(uri.getPath().contains("/presence_record")){
			try{
				StringBuilder where = new StringBuilder();
				if(selectionArgs.length > 0){
					if(selectionArgs[0] != null){
						where.append("id = '" + selectionArgs[0] + "'");
						where.append(" AND " + "name = '" + selectionArgs[1] + "'");
					}
					logCat("query chat_record: " + where.toString());
				}
				Cursor cursor = sqlHelper.getReadableDatabase().query(SQLITE_NAME, null, where.toString(), null, null, null, sortOrder);
				return cursor;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void logCat(String info){
		Log.i(TAG, info);
	}

	private void initSQLiteHelper(){
		sqlHelper = new SQLiteOpenHelper(getContext(), SQLITE_FILE_NAME, null, SQLITE_VERSION) {
			
			@Override
			public void onCreate(SQLiteDatabase db) {
				db.execSQL("CREATE TABLE " + SQLITE_NAME + " ("
		                   + "name" + " TEXT,"
		                   + "birthday" + " TEXT,"
		                   + "id" + " TEXT,"
		                   + "mobile" + " TEXT,"
		                   + "address" + " TEXT,"
		                   + "armyTime" + " TEXT,"
		                   + "armyNum" + " TEXT,"
		                   + "sex" + " TEXT"
		                   + ");");
				db.setVersion(SQLITE_VERSION);
			}
			
			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//				if(oldVersion == 1){
//					try{
//						db.execSQL("ALTER TABLE " + "chat_record" + " ADD COLUMN "+"history"+" VARCHAR(255)");
//						db.setVersion(newVersion);
//						return;
//					}catch(Exception e){
//						e.printStackTrace();
//					}
//				}
			}
		};
	}
	
	private boolean insertRecord(ContentValues values){
		try{	
			String id = values.getAsString("id");
			String name = values.getAsString("name");
			StringBuilder where = new StringBuilder();
			where.append("id = '" + id + "'");
			where.append(" AND " + "name = '" + name + "'");
			Cursor cursor = sqlHelper.getReadableDatabase().query(SQLITE_NAME, null, where.toString(), null, null, null, null);
			logCat("try to insert to database,where = " + where);
			if(cursor == null || cursor.getCount() == 0){
				long row1 = sqlHelper.getWritableDatabase().insert(SQLITE_NAME, null, values);
				logCat("insert chat record.  "+row1);
				cursor.close();
				return true;
			}else{
				int row = sqlHelper.getWritableDatabase().update(SQLITE_NAME, values, where.toString(), null);
				logCat("update chat record.   "+row);
				cursor.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
