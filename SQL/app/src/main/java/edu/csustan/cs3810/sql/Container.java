package edu.csustan.cs3810.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Container extends SQLiteOpenHelper {
    private static final String DB_NAME = "myDB";
    private static final int DB_VERSION = 1;

    public Container(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private String createTable(SQLiteDatabase db, String query) {
        db.execSQL(query);
        return "created table";
    }

    private String deleteTable(SQLiteDatabase db, String query) {
        db.execSQL(query);
        return "deleted table";
    }

    private String insert(SQLiteDatabase db, String query) {
        db.execSQL(query);
        return "inserted";
    }

    private String update(SQLiteDatabase db, String query) {
        db.execSQL(query);
        return "updated";
    }

    private String delete(SQLiteDatabase db, String query) {
        db.execSQL(query);
        return "deleted";
    }

    private String select(SQLiteDatabase db, String query) {
        String result = "";
        Cursor cursor = db.rawQuery(query, null);
        Log.i("Container", "query: " + query);
        Log.i("Container", "column count: " + cursor.getColumnCount());

        while(cursor.moveToNext()) {
            for (int i =0; i < cursor.getColumnCount(); i++) {
                if (cursor.getType(i) == Cursor.FIELD_TYPE_INTEGER) {
                    result += cursor.getInt(i) + " ";
                }
                else if (cursor.getType(i) == Cursor.FIELD_TYPE_FLOAT) {
                    result += cursor.getFloat(i) + " ";
                }
                else if(cursor.getType(i) == Cursor.FIELD_TYPE_STRING) {
                    result += cursor.getString(i) + " ";
                }
                else {
                    result += cursor.getString(i) + " ";
                }
            }

            result += "\n";
        }

        return result;
    }

    public String runQuery(String query) {
        SQLiteDatabase db = this.getWritableDatabase();
        String result = "";
        if (query.contains("create")) {
            result = createTable(db, query);
        }
        else if (query.contains("drop")) {
            result = deleteTable(db, query);
        }
        else if (query.contains("insert")) {
            result = insert(db, query);
        }
        else if (query.contains("update")) {
            result = update(db, query);
        }
        else if (query.contains("delete")) {
            result = delete(db, query);
        }
        else if (query.contains("select")) {
            result = select(db, query);
        }
        else {
            result = "no create/drop/insert/update/delete/select";
        }
        return result;
    }
}
