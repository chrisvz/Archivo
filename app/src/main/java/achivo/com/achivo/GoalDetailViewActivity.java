package achivo.com.achivo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.UUID;

import fragments.GoalDetailViewFragment;
import single.fragment.activity.SingleFragmentActivity;

/**
 * Created by Chris on 2/29/2016.
 */
public class GoalDetailViewActivity  extends SingleFragmentActivity {

    private static final String UUID = "uuid";
    private UUID uuid;



    @Override
    public Fragment getFragment() {
        uuid = (UUID)getIntent().getSerializableExtra(UUID);
        return GoalDetailViewFragment.newInstance(uuid);
    }

    public static Intent getIntent(UUID uuid,Context context) {
        Intent intent = new Intent(context,GoalDetailViewActivity.class);
        intent.putExtra(UUID,uuid);
        return intent;
    }
}
