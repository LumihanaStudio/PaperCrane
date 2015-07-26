package kr.edcan.papercrane;

import android.content.Context;

/**
 * Created by Junseok on 2015-04-16.
 */
public class ViewData {
    private String name, description, writedate;

    public ViewData(Context context, String name_, String description_, String writedate_) {
        this.name = name_;
        this.description = description_;
        this.writedate = writedate_;
    }

    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public String getWritedate(){
        return writedate;
    }
}
