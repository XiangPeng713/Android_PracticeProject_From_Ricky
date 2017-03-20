package xiangpeng.com.practiceproj_ricky.Activity.Activity;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import xiangpeng.com.practiceproj_ricky.Activity.Class_Data_Misc.ControlGameInterface;
import xiangpeng.com.practiceproj_ricky.Activity.Fragment.ControlGameFragment;
import xiangpeng.com.practiceproj_ricky.Activity.Fragment.MainGameFragment;
import xiangpeng.com.practiceproj_ricky.R;

public class MainActivity extends AppCompatActivity implements ControlGameInterface {

    ControlGameFragment cg_frag;
    MainGameFragment mg_frag;

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

        // need this line to force enqueue so then i can use its instance
        getFragmentManager().executePendingTransactions();

        cg_frag = (ControlGameFragment) getFragmentManager().findFragmentByTag(ControlGameFragment.TAG);
        mg_frag = (MainGameFragment) getFragmentManager().findFragmentByTag(MainGameFragment.TAG);
    }

    @Override
    public void startRolling() {
        // Log.e(" - Progress Track - ", " MainActivity.startRolling - got it listener - ");
        // this is just a mid-point of fragment communication
        mg_frag.rollDice();
    }
}

