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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;


public class ViewActivity extends ActionBarActivity {

    ImageView write;
    Intent intent;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ListView listView;
    ArrayList<ViewData> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        setDefault();
    }

    void setDefault(){
        intent = getIntent();
        final int asdf  = intent.getIntExtra("position", 0);
        sharedPreferences = getSharedPreferences("View" + asdf, 0);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffca28")));
        actionBar.setTitle(Html.fromHtml("<font color=\"#bd4300\"><b>내용</b> </p>"));
        actionBar.setElevation(0);
        write = (ImageView)findViewById(R.id.view_float);
        editor = sharedPreferences.edit();
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), WriteActivity.class).putExtra("position", asdf));
            }
        });
    }

    void setArray() {
        listView=  (ListView)findViewById(R.id.View_ListView);
        int count = sharedPreferences.getInt("count", 0)-1;
        arrayList = new ArrayList<>();
        for(int i=0;i<=count;i++){
            arrayList.add(new ViewData(getApplicationContext(),
                    sharedPreferences.getString("title"+i, ""),
                    sharedPreferences.getString("description"+i, ""),
                    sharedPreferences.getString("writedate"+i,"")));
        }
        ViewAdapter adapter = new ViewAdapter(getApplicationContext(), arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MaterialDialog materialDialog = new MaterialDialog.Builder(ViewActivity.this)
                        .title(sharedPreferences.getString("title"+position, ""))
                        .content(sharedPreferences.getString("description"+position, ""))
                        .positiveText("확인")
                        .negativeText("취소")
                        .show();
            }
        });
    }
    public void onResume(){
        super.onResume();
        setArray();
    }
}
