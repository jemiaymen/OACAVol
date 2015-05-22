package com.jemix.model;


import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper {

	public DataBaseHelper(Context context) {
		super(context, "OACA", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("CREATE TABLE user( id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT NOT NULL,pw TEXT NOT NULL)");
		db.execSQL("CREATE TABLE ads( id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT NOT NULL,pw TEXT NOT NULL,type INTEGER NOT NULL,dt datatime DEFAULT current_timestamp)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS user");
		db.execSQL("DROP TABLE IF EXIST ads");
		onCreate(db);
		
	}
	
	public void addUser(String email,String pw){
		email = DatabaseUtils.sqlEscapeString(email);
		pw = DatabaseUtils.sqlEscapeString(pw);
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("INSERT INTO user VALUES(null,"+email+","+pw+")");
	}
	
	public boolean login(String email,String pw){
		email = DatabaseUtils.sqlEscapeString(email);
		pw = DatabaseUtils.sqlEscapeString(pw);
		SQLiteDatabase db = this.getReadableDatabase();
		String qry = "SELECT * FROM user WHERE email ="+email+" and pw =" + pw;
		
		try{
			Cursor c = db.rawQuery(qry,null);
			if(c != null){
				c.moveToFirst();
				return (c.getInt(c.getColumnIndex("id")) != 0) ;
			}
		}catch(Exception ex){
			
		}
		
		return false;
	}
	
	public void addWindow(String lbl){
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("INSERT INTO window VALUES(null,'"+lbl+"',0)");
	}

	public void addDoor(String lbl){
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("INSERT INTO door VALUES(null,'"+lbl+"',0)");
	}
	
	public void addOther(String lbl){
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("INSERT INTO other VALUES(null,'"+lbl+"',0)");
	}
	
	public boolean isOpenWindow(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		String qry = "SELECT * FROM window WHERE id =" + id;
		
		try{
			Cursor c = db.rawQuery(qry,null);
			if(c != null){
				c.moveToFirst();
				return (c.getInt(c.getColumnIndex("st")) == 1) ;
			}
		}catch(Exception ex){
			
		}
		
		return false;
	}
	
	public boolean isOpenDoor(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		String qry = "SELECT * FROM door WHERE id =" + id;
		
		try{
			Cursor c = db.rawQuery(qry,null);
			if(c != null){
				c.moveToFirst();
				return (c.getInt(c.getColumnIndex("st")) == 1) ;
			}
		}catch(Exception ex){
			
		}
		
		return false;
	}
	
	public boolean isOpenOther(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		String qry = "SELECT * FROM other WHERE id =" + id;
		
		try{
			Cursor c = db.rawQuery(qry,null);
			if(c != null){
				c.moveToFirst();
				return (c.getInt(c.getColumnIndex("st")) == 1) ;
			}
		}catch(Exception ex){
			
		}
		
		return false;
	}
	
	public void OpenWindow(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("UPDATE window SET st = 1 WHERE id=" + id);
	}
	
	public void CloseWindow(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("UPDATE window SET st = 0 WHERE id=" + id);
	}
	
	public void OpenDoor(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("UPDATE door SET st = 1 WHERE id=" + id);
	}
	
	public void CloseDoor(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("UPDATE door SET st = 0 WHERE id=" + id);
	}
	
	
	public void OpenOther(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("UPDATE other SET st = 1 WHERE id=" + id);
	}
	
	public void CloseOther(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("UPDATE other SET st = 0 WHERE id=" + id);
	}
	
	

	
	
}

