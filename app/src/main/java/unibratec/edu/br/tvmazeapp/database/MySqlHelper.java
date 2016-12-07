package unibratec.edu.br.tvmazeapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static okhttp3.internal.Internal.instance;

public class MySqlHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "tvapp.db";
    public static final int DB_VERSION = 2;

    public MySqlHelper(Context ctx){
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbConstants.CREATE_TBSHOW.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}