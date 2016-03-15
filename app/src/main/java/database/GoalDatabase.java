package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.UUID;

import model.Goal;

import static database.DbSchema.*;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.Goal;

import static database.DbSchema.*;

/**
 * Created by Chris on 2/29/2016.
 */
public class GoalDatabase {

    private static GoalDatabase goalDatabase;


    private SQLiteDatabase sqLiteDatabase;
    private Context mContext;

    public static GoalDatabase newInstance(Context context) {
        if(goalDatabase == null) {
            goalDatabase = new GoalDatabase(context);
        }
        return goalDatabase;
    }


    private GoalDatabase(Context context) {
        mContext = context.getApplicationContext();
        sqLiteDatabase = new GoalDbHelper(context).getWritableDatabase();

    }

    private static ContentValues getContentValues(Goal goal){
        ContentValues contentValues = new ContentValues();
        contentValues.put(GoalTable.Cols.UUID, goal.getUuid().toString());
        contentValues.put(GoalTable.Cols.TITLE, goal.getTitle());
        contentValues.put(GoalTable.Cols.DESCRIPTION, goal.getDescription());
        contentValues.put(GoalTable.Cols.SUCCESSFUL, goal.isSuccessful() ? 1 : 0);
        return contentValues;
    }

    public void addGoal(Goal goal) {
        ContentValues contentValues = getContentValues(goal);
        sqLiteDatabase.insert(GoalTable.NAME, null, contentValues);
    }

    public void deleteGoal(Goal goal) {

        String uuid = goal.getUuid().toString();
        ContentValues contentValues = getContentValues(goal);
        sqLiteDatabase.delete( GoalTable.NAME, GoalTable.Cols.UUID + " = ?", new String[]{uuid});
    }

    public void updateGoal(Goal goal) {
        String uuid = goal.getUuid().toString();
        ContentValues contentValues = getContentValues(goal);
        sqLiteDatabase.update(GoalTable.NAME, contentValues, GoalTable.Cols.UUID + " = ?", new String[]{uuid});
    }




    private GoalCursorWrapper queryGoal(String whereClause, String[] whereArgs) {
        Cursor cursor = sqLiteDatabase.query(
                GoalTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new GoalCursorWrapper(cursor);
    }





    public Goal getGoal(UUID id) {
        GoalCursorWrapper cursorWrapper = queryGoal(
                GoalTable.Cols.UUID + " = ?", new String[]{id.toString()}
        );

        try {
            if (cursorWrapper.getCount() == 0) {
                return null;
            }
            cursorWrapper.moveToFirst();
            return cursorWrapper.getGoal();
        }
        finally {
            cursorWrapper.close();
        }

    }



    public ArrayList<Goal> getGoals() {

        ArrayList<Goal> goals = new ArrayList<>();
        GoalCursorWrapper cursorWrapper = queryGoal(null,null);

        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                goals.add(cursorWrapper.getGoal());
                cursorWrapper.moveToNext();
            }
        }
        finally {
            cursorWrapper.close();

        }

        return goals;


    }


}
