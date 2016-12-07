package unibratec.edu.br.tvmazeapp.database;


import android.provider.BaseColumns;

public interface DbConstants extends BaseColumns{

    String TBSHOW = "tbshow";
    String  IDSHOW = "id";
    String  NAME = "name";
    String  LANGUAGE = "language";
    String  PREMIERED ="premiered";
    String  STATUS ="status";
    String  SUMMARY="summary";
    String IMAGE = "image";

    StringBuilder CREATE_TBSHOW =
            new StringBuilder("create table " + TBSHOW)
                    .append(" ( " + _ID + " integer primary key autoincrement, ")
                    .append(IDSHOW + " integer, ")
                    .append(NAME + " text, ")
                    .append(LANGUAGE + " text, ")
                    .append(PREMIERED + " text, ")
                    .append(STATUS + " text, ")
                    .append(IMAGE + " text, ")
                    .append(SUMMARY + " text);");


}
