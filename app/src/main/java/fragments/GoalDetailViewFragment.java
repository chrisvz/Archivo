package fragments;

import android.content.Context;
import android.text.Editable;

        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.text.Editable;
        import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.EditText;

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

    private EditText titleEditText;
    private EditText descriptionEditText;
    private CheckBox successfulCheckBox;

    private Callbacks mCallbacks;
    private CompoundButton.OnCheckedChangeListener checkBoxListener ;


    public static GoalDetailViewFragment newInstance(UUID uuid) {

        Bundle bundle = new Bundle();
        bundle.putSerializable(UUID, uuid);

        GoalDetailViewFragment goalDetailViewFragment = new GoalDetailViewFragment();
        goalDetailViewFragment.setArguments(bundle);
        return goalDetailViewFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.menu_item_delete_goal:
                GoalDatabase goalDatabase = GoalDatabase.newInstance(getContext());
                goalDatabase.deleteGoal(goal);
                mCallbacks.finishActivityIfNotTablet();
                resetAllViews();
                update();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.goal_detail_view_fragment,menu);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        GoalDatabase goalDatabase = GoalDatabase.newInstance(getActivity());
        uuid = (UUID)getArguments().getSerializable(UUID);
        goal = goalDatabase.getGoal(uuid);

    }

    public void update() {
        // updates the database
        GoalDatabase.newInstance(getActivity()).updateGoal(goal);
        // updates the recycler view
        mCallbacks.onGoalUpdate(goal);
    }

    public void onPause() {
        super.onPause();
        update();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.goal_detail_view_fragment,container,false);

        titleEditText = (EditText)v.findViewById(R.id.title_edit_text_detail);
        descriptionEditText = (EditText)v.findViewById(R.id.description_edit_text_detail);
        successfulCheckBox = (CheckBox)v.findViewById(R.id.successful_checkBox_detail);



        titleEditText.setText(goal.getTitle());

        descriptionEditText.setText(goal.getDescription());

        successfulCheckBox.setChecked(goal.isSuccessful());



        titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                goal.setTitle(titleEditText.getText().toString());
                update();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        descriptionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                goal.setDescription(descriptionEditText.getText().toString());
                update();
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        checkBoxListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                goal.setSuccessful(isChecked);
                update();
            }
        };

        successfulCheckBox.setOnCheckedChangeListener(checkBoxListener);

        return v;
    }

    private void resetAllViews() {
            titleEditText.setText(null);
            descriptionEditText.setText(null);
            successfulCheckBox.setChecked(false);
    }

    private void setAllViews(boolean success,String title,String description) {
        successfulCheckBox.setChecked(goal.isSuccessful());
        titleEditText.setText(goal.getTitle());
        descriptionEditText.setText(goal.getDescription());
    }

    public void updateFromDatabase(Goal goal) {
        //  the goal received from the recyclerView
        this.goal = goal;

        // Remove the listener because we do not want to update the RecyclerView
        if(successfulCheckBox != null) {
            successfulCheckBox.setOnCheckedChangeListener(null);

            // Change the values of the views
            setAllViews(goal.isSuccessful(),goal.getTitle(),goal.getDescription());

            // set listener back
            successfulCheckBox.setOnCheckedChangeListener(checkBoxListener);

        }
    }


    public UUID getGoalUuid() {
        return goal.getUuid();
    }


    public interface Callbacks {
        public void onGoalUpdate(Goal goal);

        public void  finishActivityIfNotTablet() ;
    }
}
