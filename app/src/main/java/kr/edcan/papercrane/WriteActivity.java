package kr.edcan.papercrane;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


public class WriteActivity extends ActionBarActivity {

    Intent intent;
    int asdf;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText title, message;
    ImageView cancel, confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        setDefault();
    }

    void setDefault(){
        intent = getIntent();
        asdf  = intent.getIntExtra("position", 0);
        sharedPreferences = getSharedPreferences("View" + asdf, 0);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffca28")));
        actionBar.setTitle(Html.fromHtml("<font color=\"#bd4300\"><b>글쓰기</b> </p>"));
        actionBar.setElevation(0);
        editor = sharedPreferences.edit();
        title = (EditText)findViewById(R.id.write_title);
        message = (EditText)findViewById(R.id.write_message);
        cancel = (ImageView)findViewById(R.id.write_cancel);
        confirm = (ImageView)findViewById(R.id.write_confirm);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = sharedPreferences.getInt("count", 0);
                String titleString = title.getText().toString().trim();
                String messageString = message.getText().toString().trim();
                if(!titleString.equals("")&&!messageString.equals("")){
                    editor.putString("title"+count, titleString);
                    editor.putString("description"+count, messageString);
                    editor.putString("writedate" + count, "20150726");
                    editor.putInt("count", count+1);
                    editor.commit();
                    finish();
                }
            }
        });
    }
}
