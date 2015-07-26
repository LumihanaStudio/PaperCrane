package kr.edcan.papercrane;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class Dialog extends Activity{

    ImageView cancel, confirm;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText name, phone, email, year, month, date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        setDefault();

    }

    void setDefault(){
        sharedPreferences = getSharedPreferences("PaperCrane", 0);
        editor = sharedPreferences.edit();
        cancel = (ImageView)findViewById(R.id.create_cancel);
        confirm = (ImageView)findViewById(R.id.create_confirm);
        name = (EditText)findViewById(R.id.create_name);
        phone = (EditText)findViewById(R.id.create_phonenum);
        email = (EditText)findViewById(R.id.create_email);
        year = (EditText)findViewById(R.id.create_year);
        month = (EditText)findViewById(R.id.create_month);
        date = (EditText)findViewById(R.id.create_day);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yearString = year.getText().toString();
                String monthString = month.getText().toString();
                String dateString = date.getText().toString();
                if(name.getText().toString().trim().equals("")||
                        phone.getText().toString().trim().equals("")||
                        email.getText().toString().trim().equals("")) Toast.makeText(getApplicationContext(), "공백 없이 입력해주세요!", Toast.LENGTH_SHORT).show();
                else if (Integer.parseInt(yearString) <= 1970 || Integer.parseInt(yearString) >= 4000)
                    year.setError("올바르게 입력해주세요");
                else if (Integer.parseInt(monthString) < 1 || Integer.parseInt(monthString) > 12)
                    month.setError("올바르게 입력해주세요");
                else if (Integer.parseInt(dateString) < 1 || Integer.parseInt(dateString) > 31)
                    date.setError("올바르게 입력해주세요");
                else {
                    int count = sharedPreferences.getInt("count", 0);
                    editor.putString("name" + count, name.getText().toString());
                    editor.putString("number" + count, phone.getText().toString());
                    editor.putString("email" + count, email.getText().toString());
                    editor.putString("sendDate" + count, yearString+monthString+dateString);
                    editor.putInt("count", count+1);
                    editor.commit();
                    finish();
                }
            }
        });
    }
}
