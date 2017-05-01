package csee4190.columbiaa;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by cckec on 4/30/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper{

    Context context;

    private static final int DB_VER = 1;
    private static final String DB_Name = "quoteManager";
    private static final String TABLE_QUOTE = "quote";

    private static final String KEY_ID = "id";
    private static final String KEY_TYPE = "type";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_QUOTE = "quote";

    public DatabaseHandler(Context context){
        super(context, DB_Name, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Testing db with", "log");
        String CREATE_TABLE = "CREATE TABLE " + TABLE_QUOTE + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TYPE + " TEXT," + KEY_AUTHOR + " TEXT," + KEY_QUOTE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);

        // open and prepare to get info from csv
        String mCSVfile = "quote.csv";
        AssetManager manager = context.getAssets();
        InputStream inStream = null;
        try{
            inStream = manager.open(mCSVfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // start to insert
        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line = "";
        db.beginTransaction();
            try{
                while((line = buffer.readLine()) != null){
                    String[] colums = line.split(",");
                    ContentValues cv = new ContentValues();
                    cv.put(KEY_TYPE, colums[1]);
                    cv.put(KEY_AUTHOR, colums[2]);
                    cv.put(KEY_QUOTE, colums[3]);
                    db.insert(TABLE_QUOTE, null, cv);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Quote getQuote(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_QUOTE, new String[]{KEY_ID,KEY_TYPE,KEY_AUTHOR,KEY_QUOTE},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Quote quote = new Quote(cursor.getString(1),cursor.getString(2),cursor.getString(3));

        return quote;
    }

    public String getIt(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_QUOTE, new String[]{KEY_ID,KEY_TYPE,KEY_AUTHOR,KEY_QUOTE},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        String ans = cursor.getString(3);

        return ans;
    }

}
