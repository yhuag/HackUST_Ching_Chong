package whatever.whatever;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by artie on 7/10/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String  DATABASE_NAME = "restaurant2.db";
    public static final String  TABLE_NAME = "allRestaurant_table";   //all
    //public static final String COL_1 = "ID";
    public static final String COL_2 = "ID";
    public static final String COL_3 = "NAME";
    public static final String COL_4 = "PRICERANGE_LOW";
    public static final String COL_5 = "PRICERANGE_HIGH";
    public static final String COL_6 = "STYLE";
    public static final String COL_7 = "LOCATION_X";
    public static final String COL_8 = "LOCATION_Y";
    public static final String COL_9 = "ADDRESS";
    public static final String COL_10 = "LINK";
    public static final String COL_11 = "IMAGE";
    public static final String COL_12 = "MOMENT";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER," +
                "NAME TEXT," +
                "PRICERANGE_LOW INTEGER," +
                "PRICERANGE_HIGH INTEGER," +
                "STYLE TEXT," +
                "LOCATION_X DOUBLE," +
                "LOCATION_Y DOUBLE," +
                "ADDRESS TEXT," +
                "LINK TEXT," +
                "IMAGE TEXT," +
                "MOMENT DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData (String id, String name, String priceRange_low, String priceRange_high, String style, String location_x, String location_y, String address, String link, String image)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, id);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, priceRange_low);
        contentValues.put(COL_5, priceRange_high);
        contentValues.put(COL_6, style);
        contentValues.put(COL_7, location_x);
        contentValues.put(COL_8, location_y);
        contentValues.put(COL_9, address);
        contentValues.put(COL_10, link);
        contentValues.put(COL_11, image);
        contentValues.put(COL_12, getDateTime());
        long result = db.insert(TABLE_NAME,null,contentValues);

        return !(result == -1);

    }

    public Cursor getAllData ()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String id, String name, String priceRange_low, String priceRange_high, String style, String location_x, String location_y, String address, String link, String image)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(COL_1,id);
        contentValues.put(COL_2, id);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, priceRange_low);
        contentValues.put(COL_5, priceRange_high);
        contentValues.put(COL_6, style);
        contentValues.put(COL_7, location_x);
        contentValues.put(COL_8, location_y);
        contentValues.put(COL_9, address);
        contentValues.put(COL_10, link);
        contentValues.put(COL_11, image);
        contentValues.put(COL_12, getDateTime());
        db.update(TABLE_NAME,contentValues,"ID = ?", new String[] {id});
        return true;
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }




}
