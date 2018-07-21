package com.example.apamay.emergencycall;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class userLoginActivity extends AppCompatActivity {

    EditText edtUserName;
    EditText edtPwd;
    TextView txtNameLogin;
    Button btnLogin, btnLogout;

    //public static final String PREFS_NAME = "LoginPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);



        Button btn = (Button)findViewById(R.id.btnCallAmb);
        Button btn1 = (Button)findViewById(R.id.btnRegUser);
        Button btn2 = (Button)findViewById(R.id.btnHospZone);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), hospBookActivity.class);
//                startActivity(intent);
            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), userRegisterActivity.class);
                startActivity(intent);
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), hospLoginActivity.class);
                startActivity(intent);
            }
        });


        inItView();
    }


    private void inItView(){


        edtUserName = (EditText)findViewById(R.id.edtUserLoginName);
        edtPwd = (EditText)findViewById(R.id.edtUserLoginPwd);


        btnLogin = (Button)findViewById(R.id.btnUserLogin);


        //ตรวจสอบว่ามีการ login ค้างไว้หรือไม่ ถ้าล็อคอินไว้ให้ไปหน้า userLogout
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(getApplicationContext(), userLogout.class));
        }


        //เมื่อมีการคลิกปุ่ม login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edtUserName.getText().toString();
                String pwd = edtPwd.getText().toString();


                if (TextUtils.isEmpty(userName)) {
                    edtUserName.setError("Please enter your username");
                    return;
                }

                if (TextUtils.isEmpty(pwd)) {
                    edtPwd.setError("Please enter your password");
                    return;
                }


                userLogin(userName,pwd);
            }
        });



    }




    private void userLogin(final String username, final String pwd){
        //คำสั่งใช้ตอน get ข้อมูลจาก Database โดยตรง
        //User user = SharedPrefManager.getInstance(this).getUser();

        class UserLogin extends AsyncTask<Void, Void, String> {

            //ทำงานครั้งเดียว
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            //ทำเมื่อสิ้นสุดการทำงาน
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);


                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);


                    //ตรวจสอบว่าเชื่อมต่อกับ web service ได้รึยัง (เมย์อย้่าลืมเปิด XAMPP)
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
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);


                        edtUserName.setText("");
                        edtPwd.setText("");

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), userLogout.class));

                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Context alert = getApplicationContext();
                    Toast.makeText(alert,"Open Xampp!!!!!!",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }



            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", pwd);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_USER_LOGIN, params);
            }
        }

        UserLogin ul = new UserLogin();
        ul.execute();

//        Context alert = getApplicationContext();
//        Toast.makeText(alert,"Hello "+user.getUsername(),Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(getApplicationContext(), userLogout.class));


        /*Intent intent = new Intent(getApplicationContext(), userLogout.class);
        intent.putExtra("username",username);
        startActivity(intent);*/
    }
}
