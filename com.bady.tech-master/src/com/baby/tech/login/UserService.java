package com.baby.tech.login;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.baby.tech.login.User;

public class UserService {
	private DatabaseHelper dbHelper;
	public UserService(Context context){
		dbHelper=new DatabaseHelper(context);
	}

	//��¼��
	public boolean login(String username,String password){
		SQLiteDatabase sdb=dbHelper.getReadableDatabase();
		String sql="select * from user where username=? and password=?";
		Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});		
		if(cursor.moveToFirst()==true){
			cursor.close();
			return true;
		}
		return false;
	}
	//
	public boolean register(User user){
		SQLiteDatabase sdb=dbHelper.getReadableDatabase();
		String sql="insert into user(username,password,age,sex) values(?,?,?,?)";
		Object obj[]={user.getUsername(),user.getPassword(),user.getAge(),user.getSex()};
		sdb.execSQL(sql, obj);	
		return true;
	}
	public boolean update(User user){
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql1 = "update user set username=?, password=?, age=?, sex=? where username=?";   //把where条件的id改成username
		Object obj[]={user.getUsername(),user.getPassword(),user.getAge(),user.getSex(),user.getUsername()};
		sdb.execSQL(sql1, obj);
		return true;
//		SQLiteDatabase sdb=dbHelper.getReadableDatabase();
//		String sql1="update user set username=? password=? age=? sex=? where id=?";
//		sdb.execSQL(sql1);
//		return true;
	}
}
