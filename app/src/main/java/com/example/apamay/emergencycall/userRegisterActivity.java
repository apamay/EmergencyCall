package com.example.apamay.emergencycall;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class userRegisterActivity extends AppCompatActivity {

    EditText edtRegName;
    EditText edtRegPwd;
    EditText edtRegRePwd;
    Button btnRegSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//เกี่ยวกับการเปลี่ยนหน้าแอพ
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);


        //if the user is already logged in we will directly start the profile activity
        //ตรวจสอบว่า login อยู่รึเปล่า
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, userLoginActivity.class));
            return;
        }

        //กลับหน้า userLoginActivity เมื่อกดปุ่ม Home
        Button btn = (Button) findViewById(R.id.btnRegHome);
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
        initView();
    }


    private void initView() {//เกี่ยวกับการดึงข้อมูลต่างๆในแอพ
        edtRegName = (EditText) findViewById(R.id.edtRegName);
        edtRegPwd = (EditText) findViewById(R.id.edtRegPwd);
        edtRegRePwd = (EditText) findViewById(R.id.edtRegRePwd);
        btnRegSubmit = (Button) findViewById(R.id.btnRegSubmit);

        btnRegSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edtRegName.getText().toString().trim();
                String pwd = edtRegPwd.getText().toString().trim();
                String rePwd = edtRegRePwd.getText().toString().trim();

                //เช็คว่ากรอกข้อมูลครบทุกช่องไหม
                if (TextUtils.isEmpty(userName)) {
                    edtRegName.setError("Please complete in Block");
                    return;
                }

                if (TextUtils.isEmpty(pwd)) {
                    edtRegPwd.setError("Please complete in Block");
                    return;
                }

                if (TextUtils.isEmpty(rePwd)) {
                    edtRegRePwd.setError("Please complete in Block");
                    return;
                }

                //เช็คว่า pwd กับ repwd ตรงกันไหม
                if (pwd.equals(rePwd)) {
                    registerUser(userName, pwd);
                } else {
                    edtRegRePwd.setError("Wrong Password");
                    return;
                }
            }
        });

    }


    private void registerUser(final String userName, final String pwd) {
        Context alert = getApplicationContext();
        //แสดง username , password
        //Toast.makeText(alert,userName + pwd,Toast.LENGTH_SHORT).show();

        //บันทึกข้อมูลลงฐานข้อมูล
        class RegisterUser extends AsyncTask<Void, Void, String> {

            //private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("username", userName);
                params.put("password", pwd);


                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_USER_REGISTER, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                //progressBar = (ProgressBar) findViewById(R.id.progressBar);
                //progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                //progressBar.setVisibility(View.GONE);

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");

                        //creating a new user object
                        User user = new User(
                                userJson.getInt("userid"),
                                userJson.getString("username")

                        );

                        //storing the user in shared preferences
                        //SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), userLoginActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }
        //executing the async task
        RegisterUser ru = new RegisterUser();
        ru.execute();
    }
}