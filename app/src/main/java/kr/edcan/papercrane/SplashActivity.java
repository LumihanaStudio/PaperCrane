package kr.edcan.papercrane;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class SplashActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                finish();    // 액티비티 종료
            }
        };
        handler.sendEmptyMessageDelayed(0, 1500);
    }
}
