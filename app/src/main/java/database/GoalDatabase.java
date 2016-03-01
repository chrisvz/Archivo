package database;

import java.util.ArrayList;
import java.util.UUID;

import model.Goal;

/**
 * Created by Chris on 2/29/2016.
 */
public class GoalDatabase {

    private static GoalDatabase goalDatabase;
    private ArrayList<Goal> goals;


    public Goal findGoal(UUID uuid) {
        for(Goal g: goals) {
            if(g.getUuid().equals(uuid)){
                return g;
            }
        }
        return null;
    }

    public static GoalDatabase newInstance() {
        if(goalDatabase == null) {
            goalDatabase = new GoalDatabase();
        }
        return goalDatabase;
    }

    public void addGoal(Goal g){
        goals.add(g);
    }

    private GoalDatabase() {
        goals = new ArrayList<>();
        for(int i= 0; i < 22; i++)
        goals.add(new Goal("Goal "+i,"My goal for today is to be a very nice person and get a really hot girl",false));
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

    public void delete(Goal goal) {
        goals.remove(goal);
    }
}
