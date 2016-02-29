package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.UUID;

import achivo.com.achivo.R;
import database.GoalDatabase;
import model.Goal;

/**
 * Created by Chris on 2/29/2016.
 */
public class GoalDetailViewFragment extends Fragment {

    public static final String UUID = "uuid";
    private Goal goal;
    private UUID uuid;

    private TextView titleTextView;
    private TextView descriptionTextView;
    private CheckBox successfulCheckBox;

    public static GoalDetailViewFragment newInstance(UUID uuid) {

        Bundle bundle = new Bundle();
        bundle.putSerializable(UUID, uuid);

        GoalDetailViewFragment goalDetailViewFragment = new GoalDetailViewFragment();
        goalDetailViewFragment.setArguments(bundle);
        return goalDetailViewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoalDatabase goalDatabase = GoalDatabase.newInstance();
        uuid = (UUID)getArguments().getSerializable(UUID);
        goal = goalDatabase.findGoal(uuid);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.goal_detail_view_fragment,container,false);

        titleTextView = (TextView)v.findViewById(R.id.title_text_view_detail);
        descriptionTextView = (TextView)v.findViewById(R.id.description_text_view_detail);
        successfulCheckBox = (CheckBox)v.findViewById(R.id.successful_checkBox_detail);

        titleTextView.setText(goal.getTitle());
        descriptionTextView.setText(goal.getDescription());
        successfulCheckBox.setChecked(goal.isSuccessful());
        return v;
    }
}
