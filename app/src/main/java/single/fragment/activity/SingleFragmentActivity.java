package single.fragment.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import achivo.com.achivo.R;

/**
 * Created by Chris on 2/29/2016.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    public abstract Fragment getFragment();

    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if(fragment == null) {
            fragment = getFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }
    }

    protected int getLayoutId() {
        return R.layout.single_fragment_activity;
    }
}
