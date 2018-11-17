package cn.vobile.com.xposeddemo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tvShow = findViewById(R.id.tv_show);
        final TextView tvTime = findViewById(R.id.tv_time);
        Button button = findViewById(R.id.button);
        View button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                String time = DateFormat.getDateInstance().format(date);
                tvTime.setText("当前时间是："+ time);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = getString();
                tvShow.setText(str);
            }
        });
    }

    public String getString(){
        return "Hello World!!!";
    }
}
