package achivo.com.achivo;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fragments.GoalRecyclerViewFragment;
import single.fragment.activity.SingleFragmentActivity;

public class GoalRecyclerViewActivity extends SingleFragmentActivity {


    @Override
    public Fragment getFragment() {
        return GoalRecyclerViewFragment.newInstance();
    }
}
