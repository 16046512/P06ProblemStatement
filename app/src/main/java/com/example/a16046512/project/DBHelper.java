package com.example.a16046512.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "task.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TASK = "task";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TASK_NAME= "name";
    private static final String COLUMN_TASK_DESCRIPTION= "description";
    private static final String COLUMN_TASK_TIME= "time";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TASK_NAME + " TEXT,"
                + COLUMN_TASK_DESCRIPTION +" TEXT,"
                + COLUMN_TASK_TIME +" INTEGER)";
        db.execSQL(createNoteTableSql);

        //Dummy records, to be inserted when the database is created
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK_NAME,  "Buy milk");
        values.put(COLUMN_TASK_DESCRIPTION,   "Low fat");
        values.put(COLUMN_TASK_TIME,   5);
        db.insert(TABLE_TASK, null, values);

        values.put(COLUMN_TASK_NAME,  "Post letters");
        values.put(COLUMN_TASK_DESCRIPTION,   "Get stamps from car");
        values.put(COLUMN_TASK_TIME,   5);
        db.insert(TABLE_TASK, null, values);


        Log.i("tttinfo", "dummy records inserted");

    }

    //Delete
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(db);
    }

    //Create table
    public long insertSong(String name,String description,int time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK_NAME, name);
        values.put(COLUMN_TASK_DESCRIPTION, description);
        values.put(COLUMN_TASK_TIME, time);
        long result = db.insert(TABLE_TASK, null, values);
        if (result == -1){
            Log.d("tttDBHelper", "Insert failed”");
        }
        Log.d("tttSQL Insert ",""+ result); //id returned, shouldn’t be -1


        db.close();
        return result;
    }



    //read
    public ArrayList<Task> getAllTask() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        // "SELECT id, note_content, stars FROM note"
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_TASK_NAME+","
                + COLUMN_TASK_DESCRIPTION+","
                + COLUMN_TASK_TIME
                + " FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                int time = cursor.getInt(3);
                Task song = new Task(id, name, description,time);
                tasks.add(song);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return tasks;
    }



    public ArrayList<Task> getTask(int getid) {
        ArrayList<Task> songs = new ArrayList<Task>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TASK_NAME,COLUMN_TASK_DESCRIPTION,COLUMN_TASK_TIME};
        String condition = COLUMN_ID + " =?";
        String[] args = {String.valueOf(getid)};
        Cursor cursor = db.query(TABLE_TASK, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                int time = cursor.getInt(3);
                Task task = new Task(id,name,description,time);
                songs.add(task);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }
}
