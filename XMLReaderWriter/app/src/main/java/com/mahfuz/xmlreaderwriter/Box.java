package com.mahfuz.xmlreaderwriter;

/**
 * Created by IslamMha on 9/2/2014.
 */
public class Box {
    int box_length ;
    int box_width ;
    String box_name ;

    public Box(){

    }
    public int getBox_length() {
        return box_length;
    }

    public void setBox_length(int box_length) {
        this.box_length = box_length;
    }

    public int getBox_width() {
        return box_width;
    }

    public void setBox_width(int box_width) {
        this.box_width = box_width;
    }

    public String getBox_name() {
        return box_name;
    }

    public void setBox_name(String box_name) {
        this.box_name = box_name;
    }

    public String getBox_color() {
        return box_color;
    }

    public void setBox_color(String box_color) {
        this.box_color = box_color;
    }

    String box_color ;
}
