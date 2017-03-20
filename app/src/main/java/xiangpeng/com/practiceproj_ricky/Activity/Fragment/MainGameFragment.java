package xiangpeng.com.practiceproj_ricky.Activity.Fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

import xiangpeng.com.practiceproj_ricky.Activity.Class_Data_Misc.ControlGameInterface;
import xiangpeng.com.practiceproj_ricky.Activity.Class_Data_Misc.Dice;
import xiangpeng.com.practiceproj_ricky.Activity.Class_Data_Misc.GameListItem;
import xiangpeng.com.practiceproj_ricky.Activity.Class_Data_Misc.MainGameListViewAdapter;
import xiangpeng.com.practiceproj_ricky.R;

/**
 * Created by xiangpeng on 3/16/17.
 */

public class MainGameFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    public static final String TAG = "xiangpeng.com.practiceproj_ricky.Activity.Fragment.MainGameFragment.TAG";

    public static MainGameFragment newInstance() {
        Bundle args = new Bundle();

        MainGameFragment fragment = new MainGameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private ImageView dice_1, dice_2, dice_3, dice_4, dice_5;
    private boolean d1lock, d2lock, d3lock, d4lock, d5lock;
    private ListView list_view;
    private ArrayList<GameListItem> list_items;
    private MainGameListViewAdapter list_adapter;
    private Button restart;
    private GameListItem selected_list_item;
    private ArrayList<Dice> dice_collection;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_top_game_section, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // init the game
        initGame();

    }

    // handles click on each list item
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // Log.e(" - Progress Track - ", "" + list_items.get(i).getText());
        for (GameListItem item : list_items) {
            item.setCheck(false);
        }
        list_items.get(i).setCheck(true);
        list_adapter.notifyDataSetChanged();
        selected_list_item = list_items.get(i);
    }

    // this just handles the dice click
    @Override
    public void onClick(View view) {
        // here using a switch to check which dice we clicking
        switch (view.getId()) {
            case R.id.fragment_main_game_dice_1:
                // Log.e(" - Progress Track - ", " MainGameFragment.onClick - Dice 1 -");
                d1lock = lockDice(dice_1, d1lock);
                break;
            case R.id.fragment_main_game_dice_2:
                // Log.e(" - Progress Track - ", " MainGameFragment.onClick - Dice 2 -");
                d2lock = lockDice(dice_2, d2lock);
                break;
            case R.id.fragment_main_game_dice_3:
                // Log.e(" - Progress Track - ", " MainGameFragment.onClick - Dice 3 -");
                d3lock = lockDice(dice_3, d3lock);
                break;
            case R.id.fragment_main_game_dice_4:
                // Log.e(" - Progress Track - ", " MainGameFragment.onClick - Dice 4 -");
                d4lock = lockDice(dice_4, d4lock);
                break;
            case R.id.fragment_main_game_dice_5:
                // Log.e(" - Progress Track - ", " MainGameFragment.onClick - Dice 5 -");
                d5lock = lockDice(dice_5, d5lock);
                break;
        } // _end switch (view.getId()) check
    } // _end public void onClick(View view)

    // change the appears depends on each dice's lock status
    private boolean lockDice(ImageView _v, boolean _l) {
        if (!_l) {
            // if not locked, just change their view to show user it's different and locked
            _v.setScaleX(1.2f);
            _v.setScaleY(1.2f);
            _v.setBackgroundColor(Color.BLACK);
            // change stat to locked
            return true;
        } else {
            // if locked, change it back to normal appear
            _v.setScaleX(1.0f);
            _v.setScaleY(1.0f);
            _v.setBackgroundColor(Color.TRANSPARENT);
            // change stat to unlocked
            return false;
        }
    }

    // just to roll each dice in order
    public void rollDice() {
        // Log.e(" - Progress Track - ", " MainGameFragment.rollDice - got it, roll here - ");
        // get action "Roll" from control frag, start rolling action here
        // should perform new roll on each dice by its order
        // check if a dice is locked to decide should we roll or not
        boolean[] dlocks = new boolean[]{d1lock, d2lock, d3lock, d4lock, d5lock};
        ImageView[] dices = new ImageView[]{dice_1, dice_2, dice_3, dice_4, dice_5};
        int counter = 0;
        for (boolean _l : dlocks) {
            if (!_l) {
                //if not locked, just roll the dice again
                int r_value;
                String r_color;
                r_value = randDiceValue();
                r_color = randDiceColor();
                diceStatChange(dices[counter], r_value, r_color);
                dice_collection.get(counter).setValue(r_value);
                dice_collection.get(counter).setColor(r_color);
            } else {
                // just do nothing here maybe? keep the dice status
            }
            counter++;
        }
//        Log.e(" - Progress Track - ", "MainGameFragment.rollDice - return all info we need here - ");
//        Log.e(" - Progress Track - ", "MainGameFragment.rollDice - we need: dice_value, dice_color, bonus");
//        Log.e(" - Progress Track - ", "MainGameFragment.rollDice " + selected_list_item.getBonuns());
        int total_dice_value = 0;
        for (int i = 0; i < dice_collection.size(); i++) {
            total_dice_value += dice_collection.get(i).getValue();
//            Log.e(" - Progress Track - ", "" + dice_collection.get(i).getColor());
        }
//        Log.e(" - Progress Track - ", "MainGameFragment.rollDice " + total_dice_value);
    }

    // change dice value, color based on each dice
    private void diceStatChange(ImageView _d, int _v, String _c) {
        // this function just to change the dice face
        switch (_v) {
            case 1:
                switch (_c) {
                    case "yellow":
                        _d.setImageResource(R.drawable.d1y);
                        break;
                    case "green":
                        _d.setImageResource(R.drawable.d1g);
                        break;
                    case "purple":
                        _d.setImageResource(R.drawable.d1p);
                        break;
                    default:
                        Log.e("Error.MainGameFragment", "DiceRolling");
                        break;
                }
                break;
            case 2:
                switch (_c) {
                    case "yellow":
                        _d.setImageResource(R.drawable.d2y);
                        break;
                    case "green":
                        _d.setImageResource(R.drawable.d2g);
                        break;
                    case "purple":
                        _d.setImageResource(R.drawable.d2p);
                        break;
                    default:
                        Log.e("Error.MainGameFragment", "DiceRolling");
                        break;
                }
                break;
            case 3:
                switch (_c) {
                    case "yellow":
                        _d.setImageResource(R.drawable.d3y);
                        break;
                    case "green":
                        _d.setImageResource(R.drawable.d3g);
                        break;
                    case "purple":
                        _d.setImageResource(R.drawable.d3p);
                        break;
                    default:
                        Log.e("Error.MainGameFragment", "DiceRolling");
                        break;
                }
                break;
            case 4:
                switch (_c) {
                    case "yellow":
                        _d.setImageResource(R.drawable.d4y);
                        break;
                    case "green":
                        _d.setImageResource(R.drawable.d4g);
                        break;
                    case "purple":
                        _d.setImageResource(R.drawable.d4p);
                        break;
                    default:
                        Log.e("Error.MainGameFragment", "DiceRolling");
                        break;
                }
                break;
            case 5:
                switch (_c) {
                    case "yellow":
                        _d.setImageResource(R.drawable.d5y);
                        break;
                    case "green":
                        _d.setImageResource(R.drawable.d5g);
                        break;
                    case "purple":
                        _d.setImageResource(R.drawable.d5p);
                        break;
                    default:
                        Log.e("Error.MainGameFragment", "DiceRolling");
                        break;
                }
                break;
            case 6:
                switch (_c) {
                    case "yellow":
                        _d.setImageResource(R.drawable.d6y);
                        break;
                    case "green":
                        _d.setImageResource(R.drawable.d6g);
                        break;
                    case "purple":
                        _d.setImageResource(R.drawable.d6p);
                        break;
                    default:
                        Log.e("Error.MainGameFragment", "DiceRolling");
                        break;
                }
                break;
        }
    }

    // random 1 ~ 6
    private int randDiceValue() {
        Random rand = new Random();
        // make sure it rand within range 1 ~ 6
        return rand.nextInt(6) + 1;
    }

    // random 0 ~ 2 and return color STRING
    @Nullable
    private String randDiceColor() {
        Random rand = new Random();
        // 0 - yellow
        // 1 - green
        // 2 - purple
        switch (rand.nextInt(3)) {
            case 0:
                return "yellow";
            case 1:
                return "green";
            case 2:
                return "purple";
            default:
                return null;
        }
    }

    // set all dice to ready
    private void initGame() {
        list_view = (ListView) getActivity().findViewById(R.id.fragment_main_game_list_view);
        restart = (Button) getActivity().findViewById(R.id.fragment_main_game_button_restart);
        // init dice imageView
        dice_1 = (ImageView) getActivity().findViewById(R.id.fragment_main_game_dice_1);
        dice_2 = (ImageView) getActivity().findViewById(R.id.fragment_main_game_dice_2);
        dice_3 = (ImageView) getActivity().findViewById(R.id.fragment_main_game_dice_3);
        dice_4 = (ImageView) getActivity().findViewById(R.id.fragment_main_game_dice_4);
        dice_5 = (ImageView) getActivity().findViewById(R.id.fragment_main_game_dice_5);
        // set up onclick
        dice_1.setOnClickListener(this);
        dice_2.setOnClickListener(this);
        dice_3.setOnClickListener(this);
        dice_4.setOnClickListener(this);
        dice_5.setOnClickListener(this);
        // set up dice image
        dice_1.setImageResource(R.drawable.d1y);
        dice_2.setImageResource(R.drawable.d1g);
        dice_3.setImageResource(R.drawable.d1p);
        dice_4.setImageResource(R.drawable.d1y);
        dice_5.setImageResource(R.drawable.d1g);
        // set up dice lock stat
        d1lock = false;
        d2lock = false;
        d3lock = false;
        d4lock = false;
        d5lock = false;
        // set up list item
        list_items = new ArrayList<>();
        list_items.add(new GameListItem("5 of a Kind (x20)", "20", "5_kind", true));  // 2-2-2-2-2
        list_items.add(new GameListItem("4 of a Kind (x10)", "10", "4_kind", false));  // 2-2-2-2-5
        list_items.add(new GameListItem("Full House (x5)", "5", "full_house", false)); // 2-2-2-4-4
        list_items.add(new GameListItem("Straight (x4)", "4", "straight", false));     // 1-2-3-4-5
        list_items.add(new GameListItem("Flush (x4)", "4", "flush", false));           // y-y-y-y-y (same color)
        list_items.add(new GameListItem("3 of a Kind (x3)", "3", "3_kind", false));    // 2-2-2-6-4
        list_items.add(new GameListItem("2 Pair (x2)", "2", "2pair", false));          // 2-2-5-5-3
        list_items.add(new GameListItem("Any 5 or higher (x1)", "1", "5_higher", false)); // >= 5 (5, 6)
        list_items.add(new GameListItem("Any 2 or lower (x1)", "1", "2_lower", false));   // <= 2 (2, 1)
        // set up list items
        list_adapter = new MainGameListViewAdapter(getActivity(),list_items);
        list_view.setAdapter(list_adapter);
        list_view.setOnItemClickListener(this);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }
        });

        // this is to make sure it's never null
        selected_list_item = list_items.get(0);
        // make sure it's not null
        dice_collection = new ArrayList<>();
        dice_collection.add(new Dice(1, "yellow"));
        dice_collection.add(new Dice(1, "green"));
        dice_collection.add(new Dice(1, "purple"));
        dice_collection.add(new Dice(1, "yellow"));
        dice_collection.add(new Dice(1, "green"));
    }

    // partially reset and restart the game
    private void restartGame(){
        // reset dice image
        dice_1.setImageResource(R.drawable.d1y);
        dice_2.setImageResource(R.drawable.d1g);
        dice_3.setImageResource(R.drawable.d1p);
        dice_4.setImageResource(R.drawable.d1y);
        dice_5.setImageResource(R.drawable.d1g);
        // reset dice lock stat
        d1lock = false;
        d2lock = false;
        d3lock = false;
        d4lock = false;
        d5lock = false;
        // reset dice appear
        ImageView[] dices = new ImageView[]{dice_1, dice_2, dice_3, dice_4, dice_5};
        for (ImageView dice : dices) {
            dice.setScaleX(1.0f);
            dice.setScaleY(1.0f);
            dice.setBackgroundColor(Color.TRANSPARENT);
        }
        // reset list item
        for (GameListItem item : list_items) {
            item.setCheck(false);
        }
        list_items.get(0).setCheck(true);
        // reset list view
        list_adapter.notifyDataSetChanged();

        // this is to make sure it's never null
        selected_list_item = list_items.get(0);
    }

} // _end main scope
