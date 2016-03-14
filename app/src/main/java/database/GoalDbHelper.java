package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static database.DbSchema.*;

/**
 * Created by Chris on 3/14/2016.
 */
public class GoalDbHelper extends SQLiteOpenHelper {

    public static final String NAME ="goalBase.db";
    public static final int VERSION = 1;

    public GoalDbHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ GoalBase.NAME +" ("+
                        "_id integer primary key autoincrement, "+

                        GoalBase.COLS.UUID           +", "+
                        GoalBase.COLS.TITLE          +", "+
                        GoalBase.COLS.DESCRIPTION    +", "+
                        GoalBase.COLS.SUCCESSFUL     +" )"

        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
