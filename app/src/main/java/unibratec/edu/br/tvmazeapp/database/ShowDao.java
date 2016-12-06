package unibratec.edu.br.tvmazeapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import unibratec.edu.br.tvmazeapp.model.Result;
import unibratec.edu.br.tvmazeapp.model.Show;


public class ShowDao {

    private static ShowDao instance;
    private Context ctx;

    private ShowDao(Context ctx){
        this.ctx = ctx;
    }

    public static synchronized ShowDao getInstance(Context ctx){
        if(instance == null){
            instance = new ShowDao(ctx);
        }
        return instance;
    }

    public long insertShow(Show show){
        long result = -1;

        if(show != null) {
            MySqlHelper mySqlHelper = new MySqlHelper(ctx);
            SQLiteDatabase db = mySqlHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbConstants.IDSHOW, show.id);
            values.put(DbConstants.NAME, show.name);
            values.put(DbConstants.LANGUAGE, show.language);
            values.put(DbConstants.PREMIERED, show.premiered);
            values.put(DbConstants.STATUS, show.status);
            values.put(DbConstants.SUMMARY, show.summary);
            values.put(DbConstants.IMAGE, show.images.medium);

            try {
                result = db.insert(DbConstants.TBSHOW, null, values);
            }finally {
                db.close();
            }
        }
        return result;
    }

    public long insertResult(Result results){
        long result = -1;

        if(results != null) {
            MySqlHelper mySqlHelper = new MySqlHelper(ctx);
            SQLiteDatabase db = mySqlHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DbConstants.IDSHOW, results.show.id);
            values.put(DbConstants.NAME, results.show.name);
            values.put(DbConstants.LANGUAGE, results.show.language);
            values.put(DbConstants.PREMIERED, results.show.premiered);
            values.put(DbConstants.STATUS, results.show.status);
            values.put(DbConstants.SUMMARY, results.show.summary);
            values.put(DbConstants.IMAGE, results.show.images.medium);

            try {
                result = db.insert(DbConstants.TBSHOW, null, values);
            }finally {
                db.close();
            }
        }
        return result;
    }

    public long deleteShow(Show show) {
        long result = -1;

        if (show != null) {
            MySqlHelper mySqlHelper = new MySqlHelper(ctx);
            SQLiteDatabase db = mySqlHelper.getWritableDatabase();

            try{
                result = db.delete(DbConstants.TBSHOW, DbConstants.IDSHOW
                        + " = ?", new String[]{String.valueOf(show.id)});
            }finally {
                db.close();
            }
        }
        return result;
    }

    public long deleteResult(Result results) {
        long result = -1;

        if (results != null) {
            MySqlHelper mySqlHelper = new MySqlHelper(ctx);
            SQLiteDatabase db = mySqlHelper.getWritableDatabase();

            try{
                result = db.delete(DbConstants.TBSHOW, DbConstants.IDSHOW
                        + " = ?", new String[]{String.valueOf(results.show.id)});
            }finally {
                db.close();
            }
        }
        return result;
    }

    public Show getShow(Show show){
        Show result = null;
        if(show != null){
            MySqlHelper mySqlHelper = new MySqlHelper(ctx);
            SQLiteDatabase db = mySqlHelper.getWritableDatabase();

            try{
                Cursor c = db.rawQuery("select * from " + DbConstants.TBSHOW +
                                " where " + DbConstants.IDSHOW + " = ?",
                        new String[]{String.valueOf(show.id)});

                if(c.moveToFirst()){
                    result = getResultFromCursor(c);
                }
            }finally {
                db.close();
            }
        }
        return result;
    }

    public Show getResult(Result results){
        Show result = null;
        if(results != null){
            MySqlHelper mySqlHelper = new MySqlHelper(ctx);
            SQLiteDatabase db = mySqlHelper.getWritableDatabase();

            try{
                Cursor c = db.rawQuery("select * from " + DbConstants.TBSHOW +
                                " where " + DbConstants.IDSHOW + " = ?",
                        new String[]{String.valueOf(results.show.id)});

                if(c.moveToFirst()){
                    result = getResultFromCursor(c);
                }
            }finally {
                db.close();
            }
        }
        return result;
    }

    public List<Show> getFavoriteShows(){
        List<Show> resultList = new ArrayList<Show>();

        MySqlHelper mySqlHelper = new MySqlHelper(ctx);
        SQLiteDatabase db = mySqlHelper.getWritableDatabase();

        try{
            Cursor c = db.rawQuery("select * from " + DbConstants.TBSHOW, null);

            while(c.moveToNext()){
                resultList.add(getResultFromCursor(c));
            }
        }finally {
            db.close();
        }
        return resultList;
    }

    public List<Result> getFavoriteResults(){
        List<Result> resultList = new ArrayList<Result>();

        MySqlHelper mySqlHelper = new MySqlHelper(ctx);
        SQLiteDatabase db = mySqlHelper.getWritableDatabase();

        try{
            Cursor c = db.rawQuery("select * from " + DbConstants.TBSHOW, null);

            while(c.moveToNext()){
                resultList.add(getResultResultFromCursor(c));
            }
        }finally {
            db.close();
        }
        return resultList;
    }

    public Show getResultFromCursor(Cursor c){
        Show show = new Show();
        if(c != null){
            show.id = c.getColumnIndex(DbConstants.IDSHOW);
            show.name = c.getString(c.getColumnIndex(DbConstants.NAME));
            show.language = c.getString(c.getColumnIndex(DbConstants.LANGUAGE));
            show.premiered = c.getString(c.getColumnIndex(DbConstants.PREMIERED));
            show.status = c.getString(c.getColumnIndex(DbConstants.STATUS));
            show.summary = c.getString(c.getColumnIndex(DbConstants.SUMMARY));
            show.images.medium = c.getString(c.getColumnIndex(DbConstants.IMAGE));
        }
        return show;
    }

    public Result getResultResultFromCursor(Cursor c){
        Result result = new Result();
        if(c != null){
            result.show.id = c.getColumnIndex(DbConstants.IDSHOW);
            result.show.name = c.getString(c.getColumnIndex(DbConstants.NAME));
            result.show.language = c.getString(c.getColumnIndex(DbConstants.LANGUAGE));
            result.show.premiered = c.getString(c.getColumnIndex(DbConstants.PREMIERED));
            result.show.status = c.getString(c.getColumnIndex(DbConstants.STATUS));
            result.show.summary = c.getString(c.getColumnIndex(DbConstants.SUMMARY));
            result.show.images.medium = c.getString(c.getColumnIndex(DbConstants.IMAGE));
        }
        return result;
    }
}
