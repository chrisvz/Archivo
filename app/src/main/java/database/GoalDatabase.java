package database;

import java.util.ArrayList;

import model.Goal;

/**
 * Created by Chris on 2/29/2016.
 */
public class GoalDatabase {

    private static GoalDatabase goalDatabase;
    private ArrayList<Goal> goals;


    public static GoalDatabase newInstance() {
        if(goalDatabase == null) {
            goalDatabase = new GoalDatabase();
        }
        return goalDatabase;
    }

    private GoalDatabase() {
        goals = new ArrayList<>();
        goals.add(new Goal("Test","test",false));
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

}
