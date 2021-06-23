package com.prantomondal.todoandroidapp.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.prantomondal.todoandroidapp.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String NAME = "toDoListDatabase";
    private static final String TODO_TABLE = "todo";
    private static final String ID = "id";
    private static final String TASK = "task";
    private static final String STATUS = "status";
    private static final String CREATE_TODO_TABLE = "CREATE TABLE" + TODO_TABLE+"(" + ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                                                        +TASK +"TEXT ," + STATUS+ "INTEGER)";
    private SQLiteDatabase db;
    private DatabaseHandler(Context context){
        super(context,NAME,null,VERSION);
    }
    @Override
    public void onnCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TODO_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        //drop the older table
        db.execSQL("DROP TABLE IF EXISTS" +TODO_TABLE);
        //create tables again
        onCreate(db);
    }
    public void openDatabase(){
        db = this.getWritableDatabase();
    }
    public void insertTask(ToDoModel task){
        ContentValues cv = new ContentValues();
        cv.put(TASK, task.getTask());
        cv.put(STATUS,0);
        db.insert(TODO_TABLE, null, cv);
    }
    public List<ToDoModel> getAllTasks(){
        List<ToDoModel> taskList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try {
            cur = db.query(TODO_TABLE,null,null,null,null,null,null,null);
            if (cur!=null){
                if (cur.moveToFirst()){
                    do{
                        ToDoModel task = new ToDoModel();
                        task.setId(cur.getInt(cur.));
                    }
                }
            }
        }
        db.endTransaction();

    }





}
