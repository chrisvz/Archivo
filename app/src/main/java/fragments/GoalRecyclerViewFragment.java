package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import achivo.com.achivo.GoalDetailViewActivity;
import achivo.com.achivo.GoalViewPagerActivity;
import achivo.com.achivo.R;
import database.GoalDatabase;
import model.Goal;

/**
 * Created by Chris on 2/29/2016.
 */
public class GoalRecyclerViewFragment extends Fragment {

    private RecyclerView recyclerView;
    private GoalAdapter goalAdapter;


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.goal_recycler_view_fragment,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_new_goal:
                GoalDatabase goalDatabase = GoalDatabase.newInstance(getActivity());
                Goal goal = new Goal();
                goalDatabase.addGoal(goal);
                Intent intent = GoalViewPagerActivity.getIntent(goal.getUuid(), getActivity());
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater,ViewGroup container,Bundle savedInstanceState) {


        View v = layoutInflater.inflate(R.layout.goal_recycler_view_fragment, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.goal_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;
    }

    private void updateUI() {
        GoalDatabase goalDatabase = GoalDatabase.newInstance(getActivity());
        ArrayList<Goal> goals = goalDatabase.getGoals();
        goalAdapter = new GoalAdapter(goals);
        recyclerView.setAdapter(goalAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();


        if(goalAdapter != null)
        goalAdapter.notifyDataSetChanged();

    }

    public static GoalRecyclerViewFragment newInstance() {
        return new GoalRecyclerViewFragment();
    };

    private class GoalHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener{

        private CheckBox successfulCheckBox;
        private TextView titleTextView;
        private Goal goal;


        public GoalHolder(View itemView) {
            super(itemView);

            successfulCheckBox = (CheckBox)itemView.findViewById(R.id.successful_checkbox);
            titleTextView = (TextView)itemView.findViewById(R.id.title_text_view);
            itemView.setOnClickListener(this);

            successfulCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    goal.setSuccessful(isChecked);
                }
            });

        }


        private void bind(Goal mGoal) {
            goal = mGoal;
            successfulCheckBox.setChecked(goal.isSuccessful());
            titleTextView.setText(goal.getTitle());
        }

        @Override
        public void onClick(View v) {
            Intent intent = GoalViewPagerActivity.getIntent(goal.getUuid(), getActivity());
            startActivity(intent);
        }
    }


    private class GoalAdapter extends RecyclerView.Adapter<GoalHolder> {

        private ArrayList<Goal> goals;
        private GoalAdapter(ArrayList<Goal> goals) {
            this.goals = goals;
        }
        @Override
        public GoalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.goal_holder,parent,false);
            return new GoalHolder(view);
        }

        @Override
        public void onBindViewHolder(GoalHolder holder, int position) {
            Goal goal = goals.get(position);
            holder.bind(goal);

        }

        @Override
        public int getItemCount() {
            return goals.size();
        }
    }
}
