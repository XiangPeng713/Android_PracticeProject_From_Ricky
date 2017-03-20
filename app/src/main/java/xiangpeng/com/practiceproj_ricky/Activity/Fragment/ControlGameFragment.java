package xiangpeng.com.practiceproj_ricky.Activity.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import xiangpeng.com.practiceproj_ricky.Activity.Class_Data_Misc.ControlGameInterface;
import xiangpeng.com.practiceproj_ricky.R;

/**
 * Created by xiangpeng on 3/16/17.
 */

public class ControlGameFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "xiangpeng.com.practiceproj_ricky.Activity.Fragment.ControlGameFragment.TAG";

    private ControlGameInterface mListener;

    public static ControlGameFragment newInstance() {
        Bundle args = new Bundle();

        ControlGameFragment fragment = new ControlGameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    Button roll;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ControlGameInterface) {
            mListener = (ControlGameInterface) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_bottom_control_section, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        roll = (Button) getActivity().findViewById(R.id.fragment_control_game_button_roll);
        roll.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // Log.e(" - Progress Track - ", " ControlGameFragment.onClick - Roll -");
        // tell other fragment start rolling
        mListener.startRolling();
    }

}
