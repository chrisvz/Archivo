package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.UUID;

import model.Goal;

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

    public void addGoal(Goal g){
        goals.add(g);
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
}
