package xiangpeng.com.practiceproj_ricky.Activity.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xiangpeng.com.practiceproj_ricky.R;

/**
 * Created by xiangpeng on 3/16/17.
 */

public class ControlGameFragment extends Fragment {
    public static final String TAG = "xiangpeng.com.practiceproj_ricky.Activity.Fragment.ControlGameFragment.TAG";

    public static ControlGameFragment newInstance() {
        Bundle args = new Bundle();

        ControlGameFragment fragment = new ControlGameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_bottom_control_section, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
