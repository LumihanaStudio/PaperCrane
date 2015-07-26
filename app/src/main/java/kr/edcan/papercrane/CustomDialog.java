package kr.edcan.papercrane;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rey.material.widget.Button;

import java.util.List;


/**
 * Created by kotohana5706 on 15. 7. 12.
 */
public class CustomDialog extends Activity {

    Button cancel, confirm;
    View.OnClickListener dialogConfirm;
    String apikey;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindow();
        setContentView(R.layout.custom_dialog);
        setDefault();
    }


    public void setDefault() {
        cancel = (Button) findViewById(R.id.cancel_button);
        confirm = (Button) findViewById(R.id.confirm_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS("010-7292-4291",
                        "오준석님으로부터 전송된 PaperCrane입니다\n" +
                                "[재현아 애플파이에서 수고가 많아, 20150726] 재현아! ~~~~~~~~\n" );

                String[] tos = {"ryan345@nate.com"};
                Intent it = new Intent(Intent.ACTION_SEND);
                it.setType("plain/text");
                it.putExtra(Intent.EXTRA_EMAIL, tos);
                it.putExtra(Intent.EXTRA_SUBJECT, "오준석님으로부터 전송된 PaperCrane입니다");
                it.putExtra(Intent.EXTRA_TEXT, "[재현아 애플파이에서 수고가 많아, 20150726] 재현아! ~~~~~~~~" );
                startActivity(it);

            }
        });
    }

    public void sendSMS(String smsNumber, String smsText) {
        PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_SENT_ACTION"), 0);
        PendingIntent deliveredIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_DELIVERED_ACTION"), 0);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        // 전송 성공
                        Toast.makeText(getApplicationContext(), "메세지 전송 완료", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        // 전송 실패
                        Toast.makeText(getApplicationContext(), "전송 실패", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        // 서비스 지역 아님
                        Toast.makeText(getApplicationContext(), "서비스 지역이 아닙니다", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        // 무선 꺼짐
                        Toast.makeText(getApplicationContext(), "무선(Radio)가 꺼져있습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        // PDU 실패
                        Toast.makeText(getApplicationContext(), "PDU Null", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter("SMS_SENT_ACTION"));

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        // 도착 완료
                        break;
                    case Activity.RESULT_CANCELED:
                        // 도착 안됨
                        break;
                }
            }
        }, new IntentFilter("SMS_DELIVERED_ACTION"));

        SmsManager mSmsManager = SmsManager.getDefault();
        mSmsManager.sendTextMessage(smsNumber, null, smsText, sentIntent, deliveredIntent);
        finish();
    }

    public void setWindow() {
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);
    }
}
