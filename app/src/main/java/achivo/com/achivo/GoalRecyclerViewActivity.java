package achivo.com.achivo;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import fragments.GoalDetailViewFragment;
import fragments.GoalRecyclerViewFragment;
import model.Goal;
import single.fragment.activity.SingleFragmentActivity;

public class GoalRecyclerViewActivity extends SingleFragmentActivity implements GoalRecyclerViewFragment.Callbacks , GoalDetailViewFragment.Callbacks {


    @Override
    protected int getLayoutId() {
        return R.layout.master_xml;
    }

    @Override
    public Fragment getFragment() {
        return GoalRecyclerViewFragment.newInstance();
    }

    @Override
    public void onGoalSelected(Goal goal) {
        if( findViewById(R.id.fragment_detail_view) == null) {
            Intent intent  = GoalViewPagerActivity.getIntent(goal.getUuid(),this);
            startActivity(intent);
        }
        else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            GoalDetailViewFragment goalDetailViewFragment = GoalDetailViewFragment.newInstance(goal.getUuid());
            fragmentManager.beginTransaction().replace(R.id.fragment_detail_view, goalDetailViewFragment).commit();
        }
    }

    @Override
    public void onGoalUpdate(Goal goal) {
        GoalRecyclerViewFragment goalRecyclerViewFragment = ( GoalRecyclerViewFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        goalRecyclerViewFragment.updateUI();
    }
}
