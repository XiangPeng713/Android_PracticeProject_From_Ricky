package xiangpeng.com.practiceproj_ricky.Activity.Class_Data_Misc;

import java.io.Serializable;

/**
 * Created by xiangpeng on 3/18/17.
 */

public class Dice implements Serializable {
    private int value;
    private String color;

    public Dice(int value, String color) {
        this.value = value;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
