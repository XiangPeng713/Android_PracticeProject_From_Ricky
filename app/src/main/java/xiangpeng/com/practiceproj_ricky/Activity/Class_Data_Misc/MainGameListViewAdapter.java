package xiangpeng.com.practiceproj_ricky.Activity.Class_Data_Misc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import xiangpeng.com.practiceproj_ricky.R;

/**
 * Created by xiangpeng on 3/18/17.
 */

public class MainGameListViewAdapter extends BaseAdapter {
    static final int ID_CONSTANT = 0x0001001;
    Context context;
    ArrayList<GameListItem> list_items;

    public MainGameListViewAdapter(Context context, ArrayList<GameListItem> list_items) {
        this.context = context;
        this.list_items = list_items;
    }

    @Override
    public int getCount() {
        return (list_items != null) ? list_items.size() : 0;
    }

    @Override
    public GameListItem getItem(int i) {
        return (list_items != null && i < list_items.size() && i > 0) ? list_items.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return ID_CONSTANT + i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {

            view = LayoutInflater.from(context)
                    .inflate(
                            R.layout.game_list_item,
                            viewGroup,
                            false
                    );
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (list_items.get(i).isCheck()) {
            Picasso.with(context)
                    .load(R.drawable.checkbox_checked)
                    .into(holder.checkbox);
        } else {
            Picasso.with(context)
                    .load(R.drawable.checkbox_uncheck)
                    .into(holder.checkbox);
        }
        holder.description.setText(list_items.get(i).getText());

        return view;
    }

    static class ViewHolder {
        ImageView checkbox;
        TextView description;

        public ViewHolder(View v) {
            checkbox = (ImageView) v.findViewById(R.id.game_list_item_image_view_checkbox);
            description = (TextView) v.findViewById(R.id.game_list_item_text_view_description);
        }
    }

}
