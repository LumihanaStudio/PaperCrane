package kr.edcan.papercrane;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Build;
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


public class MainActivity extends ActionBarActivity {

    ArrayList<CData> arrayList;
    ListView listView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ImageView floatButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(getApplicationContext(), CustomDialog.class));
        finish();
        startActivity(new Intent(getApplicationContext(), SplashActivity.class));
        setDefault();
        setData();
    }

    public void setDefault() {
        sharedPreferences = getSharedPreferences("PaperCrane", 0);
        editor = sharedPreferences.edit();
        listView = (ListView)findViewById(R.id.ListView);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffca28")));
        actionBar.setTitle(Html.fromHtml("<font color=\"#bd4300\"><b>연락처</b> </p>"));
        actionBar.setElevation(0);
        floatButton = (ImageView)findViewById(R.id.float_button);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreatePerson.class));
            }
        });
    }

    private void setData() {
        listView = (ListView)findViewById(R.id.ListView);
        int count =sharedPreferences.getInt("count", 0)-1;
        arrayList = new ArrayList<>();
        for(int i=0;i<=count;i++){
            arrayList.add(new CData(getApplicationContext(), sharedPreferences.getString("name"+i, ""),sharedPreferences.getString("sendDate"+i, "")));
        }
        DataAdapter adapter = new DataAdapter(getApplicationContext(), arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), ViewActivity.class).putExtra("position", position));
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                return true;
            }
        });
    }

    public void onResume(){
        super.onResume();
        setData();
    }
}
