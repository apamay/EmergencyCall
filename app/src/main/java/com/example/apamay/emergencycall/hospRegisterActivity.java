package com.example.apamay.emergencycall;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class hospRegisterActivity extends AppCompatActivity {

    Spinner spnHosRegName;
    EditText edtHosRegId, edtHosRegPwd, edtHosRegRePwd;
    Button btnHosRegSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosp_register);

        Button btn = (Button)findViewById(R.id.btnHosLogHome);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), userLoginActivity.class);
                startActivity(intent);

            }
        });
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }


    private void inItView(){
        spnHosRegName = (Spinner)findViewById(R.id.spnHosRegName);
        edtHosRegId = (EditText)findViewById(R.id.edtHosRegId);
        edtHosRegPwd = (EditText)findViewById(R.id.edtHosRegPwd);
        edtHosRegRePwd = (EditText)findViewById(R.id.edtHosRegRePwd);
        btnHosRegSubmit = (Button)findViewById(R.id.btnHosRegSubmit);

        btnHosRegSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edtHosRegId .getText().toString().trim();
                String pwd = edtHosRegPwd.getText().toString().trim();
                String rePwd = edtHosRegRePwd.getText().toString().trim();

                //เช็คว่ากรอกข้อมูลครบทุกช่องไหม
                if(TextUtils.isEmpty(userName)){
                    edtHosRegId .setError("Please complete in Block");
                    return;
                }

                if (TextUtils.isEmpty(pwd)){
                    edtHosRegPwd.setError("Please complete in Block");
                    return;
                }

                if (TextUtils.isEmpty(rePwd)){
                    edtHosRegRePwd.setError("Please complete in Block");
                    return;
                }

                //เช็คว่า pwd กับ repwd ตรงกันไหม
                if(pwd.equals(rePwd)){
                    registerHosAmp(userName,pwd);
                }
                else{
                    edtHosRegRePwd.setError("Wrong Password");
                }
            }
        });
    }


    private void registerHosAmp(String userName ,String pwd){
        //บันทึกข้อมูลลงฐานข้อมูล

    }




}
