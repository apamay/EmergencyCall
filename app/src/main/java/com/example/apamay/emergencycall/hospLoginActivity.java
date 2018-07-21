package com.example.apamay.emergencycall;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class hospLoginActivity extends AppCompatActivity {

    Spinner spnHosLogName;
    EditText edtHosLogId;
    EditText edtHosLogPwd;
    Button btnHosLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosp_login);

        Button btn = (Button)findViewById(R.id.btnHosHome);
        Button btn1 = (Button)findViewById(R.id.btnHosLogReg);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), userLoginActivity.class);
                startActivity(intent);

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), hospRegisterActivity.class);
                startActivity(intent);
            }
        });

        inItView();
    }


    private void inItView(){
        edtHosLogId = (EditText)findViewById(R.id.edtHosLogId);
        edtHosLogPwd = (EditText)findViewById(R.id.edtHosLogPwd);
        btnHosLog = (Button)findViewById(R.id.btnHosLog);

        btnHosLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtHosLogId.getText().toString();
                String pwd = edtHosLogPwd.getText().toString();

                Context alert1 = getApplicationContext();
                Toast.makeText(alert1,"Hello Ambulance...",Toast.LENGTH_LONG).show();

                HosLogin(id,pwd);
            }
        });
    }


    private void HosLogin(String id,String pwd){

    }
}
