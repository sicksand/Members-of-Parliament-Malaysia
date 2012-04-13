package com.mpmalaysia.sicksand;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBAdapter {
	public static final String KEY_NAME = "Nama";
	public static final String KEY_ROWID = "key";
	public static final String KEY_ALAMAT = "Alamat_Surat-menyurat";
	public static final String KEY_NEGERI = "Negeri";
	public static final String KEY_IMG = "img";
	public static final String KEY_PARTI = "Parti";
	public static final String KEY_EMAIL = "E-Mail";
	public static final String KEY_TEL = "No_Telefon";
	public static final String KEY_PAR = "Parlimen";
	public static final String KEY_FAX = "No_Fax";
	public static final String KEY_KABINET = "Jawatan_dalam_Kabinet";
	public static final String KEY_KAWASAN = "Kawasan";
	public static final String KEY_JAWATAN = "Jawatan_dalam_Parlimen";
	

    static final String TAG = "MalaysianMP";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    /**
     * Database creation sql statement
     */
    private static final String DATABASE_CREATE =
            "CREATE TABLE `swdata` (`Nama` text, `Alamat_Surat-menyurat` text, `Negeri` text, `img` text, `Parti` text, `E-Mail` text, `No_Telefon` text, `Parlimen` integer, `key` integer, `No_Fax` text, `Jawatan_dalam_Kabinet` text, `Kawasan` text, `Jawatan_dalam_Parlimen` text);"
;

    private static final String DATABASE_NAME = "malaysian_mp_profile.sqlite";
    private static final String DATABASE_TABLE = "swdata";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS notes");
            onCreate(db);
        }
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     * 
     * @param ctx the Context within which to work
     */
    public DBAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the notes database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     * 
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public DBAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }



    /**
     * Return a Cursor positioned at the note that matches the given rowId
     * 
     * @param rowId id of note to retrieve
     * @return Cursor positioned to matching note, if found
     * @throws SQLException if note could not be found/retrieved
     */
    public Cursor fetchMP() {

    	//Cursor cursor = 
    	//	mDb.query(DATABASE_TABLE, new String[] 
    	//	          {KEY_ROWID + " as _id", KEY_NAME, KEY_PARTI, KEY_PAR}, 
    	//	          null, null, null, null, null);
    	Cursor cursor = 
        		mDb.rawQuery("SELECT `Nama`, `Parti`, `Parlimen`, `key` as _id FROM swdata GROUP BY `Nama`", 
        		          null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Log.v("Count ",""+cursor.getCount());
        return cursor;
    }

    
    public Cursor fetchNote(String strname) throws SQLException {

        //Cursor mCursor = mDb.rawQuery("SELECT `key` as _id, `Nama` FROM swdata WHERE `Nama` LIKE "  + "'%" +strname + "%'", null);
        
        Cursor mCursor = mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID + " as _id" ,KEY_NAME}, KEY_NAME + " LIKE "  + "'%" +strname + "%'", null,  null, null, null, null);
        Log.d("SENT", "query sent");
        //mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID ,KEY_NAME}, KEY_NAME + " LIKE "  + "'%" +strname + "%'", null,  null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
            Log.d("SENT", "move next");
        }
    
        return mCursor;
        
    }
}


    

