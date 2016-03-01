package achivo.com.achivo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.UUID;

import database.GoalDatabase;
import fragments.GoalDetailViewFragment;
import model.Goal;

/**
 * Created by Chris on 3/1/2016.
 */
public class GoalViewPagerActivity extends FragmentActivity {

    private static final String UUID = "UUID";
    private UUID uuid;
    private ViewPager viewPager;
    private ArrayList<Goal> goals;


    public static Intent getIntent(UUID uuid,Context context) {
        Intent intent = new Intent(context,GoalViewPagerActivity.class);
        intent.putExtra(UUID,uuid);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goal_view_pager);

        uuid = (UUID)getIntent().getSerializableExtra(UUID);

        GoalDatabase goalDatabase = GoalDatabase.newInstance();
        goals = goalDatabase.getGoals();

        viewPager = (ViewPager)findViewById(R.id.goal_view_pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Goal goal = goals.get(position);
                return GoalDetailViewFragment.newInstance(goal.getUuid());
            }

            @Override
            public int getCount() {
                return goals.size();
            }
        });

        // start the goal which was clicked
        for(int i = 0; i < goals.size(); i++) {
            if(goals.get(i).getUuid().equals(uuid)){
                viewPager.setCurrentItem(i);
            }
        }
    }
}
