package com.tistory.dayglo.sqliteexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "WordText.db", null, 1);

        final TextView result = findViewById(R.id.txt_result);

        final EditText successEdt = findViewById(R.id.edt_success);
        final EditText wrongEdt = findViewById(R.id.edt_wrong);

        Button insert = findViewById(R.id.btn_insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wrongWord = wrongEdt.getText().toString();
                String successWord = successEdt.getText().toString();

                dbHelper.insert(successWord, wrongWord);
                result.setText(dbHelper.getData());
            }
        });
    }
}
