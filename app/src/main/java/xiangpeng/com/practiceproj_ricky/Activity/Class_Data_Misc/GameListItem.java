package xiangpeng.com.practiceproj_ricky.Activity.Class_Data_Misc;

import java.io.Serializable;

/**
 * Created by xiangpeng on 3/18/17.
 */

public class GameListItem implements Serializable {
    private String text;
    private String bonus;
    private String tag;
    private boolean check;

    public GameListItem(String text, String bonuns, String tag, boolean check) {
        this.text = text;
        this.bonus = bonuns;
        this.tag = tag;
        this.check = check;
    }

    public String getText() {
        return text;
    }

    public String getBonuns() {
        return bonus;
    }

    public String getTag() {
        return tag;
    }

    public boolean isCheck() {
        return check;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setBonuns(String bonuns) {
        this.bonus = bonuns;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
