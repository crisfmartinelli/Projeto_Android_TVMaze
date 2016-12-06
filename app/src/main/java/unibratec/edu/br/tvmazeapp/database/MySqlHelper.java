package unibratec.edu.br.tvmazeapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqlHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "tvappdatabase";
    public static final int DB_VERSION = 1;

    private static MySqlHelper instance;
    private Context ctx;

    public MySqlHelper(Context ctx){
        super(ctx, DB_NAME, null, DB_VERSION);
        this.ctx = ctx;
    }

    public static synchronized MySqlHelper getInstance(Context ctx){
        if(instance == null){
            instance = new MySqlHelper(ctx);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbConstants.CREATE_TBSHOW.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}