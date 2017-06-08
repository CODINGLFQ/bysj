package com.baby.tech.db;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        // helper = new DBHelper(context);
        // // 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
        // // mFactory);
        // // 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        // db = helper.getWritableDatabase();
        // helper.onCreate(db);

        helper = new DBHelper(context);

        try {
            helper.createDataBase();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        db = helper.getWritableDatabase();
    }

    /**
     * query all persons, return list
     * 
     * @return List<Person>
     */
    public ArrayList<TechdbInfo> getEvent() {
        ArrayList<TechdbInfo> persons = new ArrayList<TechdbInfo>();
        // Cursor c = queryTheCursor();
        Cursor c = db.rawQuery("select * from t002cikudb where _id > ?",
                new String[] { "0" });
        while (c.moveToNext()) {
            TechdbInfo person = new TechdbInfo();
            person.mId = c.getInt(c.getColumnIndex("_id"));
            person.mZi = c.getString(c.getColumnIndex("zi"));
            person.mCi = c.getString(c.getColumnIndex("ci"));
            person.mJu = c.getString(c.getColumnIndex("ju"));

            persons.add(person);
        }
        c.close();
        return persons;
    }

    /**
     * truncateTable
     * 
     * @param person
     */
    public void truncateTable() {
        db.execSQL("DROP table EventListInfo");
    }

    public String getFriendTel(int nFid) {
        Cursor c = db.rawQuery("select * from FriendInfo where fid = ?",
                new String[] { String.valueOf(nFid) });
        if (c.moveToNext()) {
            return c.getString(c.getColumnIndex("hpone"));
        } else {
            return "";
        }
    }

    public String getLastTime() {
        Cursor c = db.rawQuery(
                "select * from EventListInfo ORDER BY _id DESC LIMIT ?",
                new String[] { "1" });
        if (c.moveToNext()) {
            return c.getString(c.getColumnIndex("time"));
        } else {
            return "";
        }

    }

    /**
     * query all persons, return cursor
     * 
     * @return Cursor
     */
    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM person", null);
        return c;
    }

    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }
}