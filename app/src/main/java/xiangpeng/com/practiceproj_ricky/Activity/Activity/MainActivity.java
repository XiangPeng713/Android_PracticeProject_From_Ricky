package xiangpeng.com.practiceproj_ricky.Activity.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import xiangpeng.com.practiceproj_ricky.Activity.Fragment.ControlGameFragment;
import xiangpeng.com.practiceproj_ricky.Activity.Fragment.MainGameFragment;
import xiangpeng.com.practiceproj_ricky.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction()
                .replace(
                        R.id.activity_main_top_main_game_fragment_container,
                        MainGameFragment.newInstance(),
                        MainGameFragment.TAG
                ).commit();

        getFragmentManager().beginTransaction()
                .replace(
                        R.id.activity_main_bottom_control_fragment_container,
                        ControlGameFragment.newInstance(),
                        ControlGameFragment.TAG
                ).commit();

    }

}

