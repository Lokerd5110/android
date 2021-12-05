package com.example.wear;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        EditText pass = findViewById(R.id.Password);
        EditText login = findViewById(R.id.Email);
        String[] data = new String[2];
        data[0] = login.getText().toString();
        data[1] = pass.getText().toString();

        String[] dataFromRes = getResources().getStringArray(R.array.passAndLogin);
        boolean flag = false;
        for(int i = 0; i<dataFromRes.length / 2-1; i++) {
            if(dataFromRes[i * 2].equals(data[0]) & dataFromRes[i * 2 + 1].equals(data[1])) {
                flag = true;
                break;
            }
        }
        if(flag){
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
        } else {
            if (!data[0].isEmpty() & !data[1].isEmpty()) {
                pass.setTextColor(Color.RED);
                login.setTextColor(Color.RED);
            }
        }
    }
}
