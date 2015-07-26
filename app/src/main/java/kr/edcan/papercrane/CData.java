package kr.edcan.papercrane;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by Junseok on 2015-04-16.
 */
public class CData {
    private String name, senddate;

    public CData(Context context,String name_, String senddate_) {
        this.name = name_;
        this.senddate = senddate_;
    }

    public String getName(){
        return name;
    }
    public String getSenddate(){
        return senddate;
    }
}
