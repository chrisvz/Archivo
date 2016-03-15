package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.UUID;

import model.Goal;

import static database.DbSchema.*;

/**
 * Created by Chris on 2/29/2016.
 */
public class GoalDatabase {

    private static GoalDatabase goalDatabase;
    private ArrayList<Goal> goals;

    private Context mContext;
    private SQLiteDatabase mDatabase;


    public Goal findGoal(UUID uuid) {
        for(Goal g: goals) {
            if(g.getUuid().equals(uuid)){
                return g;
            }
        }
        return null;
    }

    public static GoalDatabase newInstance(Context context) {
        if(goalDatabase == null) {
            goalDatabase = new GoalDatabase(context);
        }
        return goalDatabase;
    }



    private GoalDatabase(Context context) {
        mContext = context;
        mDatabase = new GoalDbHelper(mContext).getWritableDatabase();
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

    public void delete(Goal goal) {
        goals.remove(goal);
    }



    private static ContentValues contentValues(Goal goal) {

        ContentValues values = new ContentValues();
        values.put(GoalBase.COLS.UUID,goal.getUuid().toString());
        values.put(GoalBase.COLS.TITLE,goal.getTitle());
        values.put(GoalBase.COLS.DESCRIPTION,goal.getDescription());
        values.put(GoalBase.COLS.SUCCESSFUL, goal.isSuccessful() ? 1 : 0);
        return values;
    }

    public void addGoal(Goal g){
        ContentValues contentValues = contentValues(g);
        mDatabase.insert(GoalBase.NAME, null, contentValues);
        goals.add(g);
    }

    public void updateGoal(Goal g){
        String uuid = g.getUuid().toString();
        ContentValues contentValues = contentValues(g);
        mDatabase.update(GoalBase.NAME, contentValues , GoalBase.COLS.UUID+" = ?",new String[]{ uuid});
    }


}
