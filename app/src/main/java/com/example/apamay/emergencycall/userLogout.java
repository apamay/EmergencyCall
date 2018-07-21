package com.example.apamay.emergencycall;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class userLogout extends AppCompatActivity {

    Button btnAm,btnFav,btnLogout;
    TextView txtName,txtLocation;
    //GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_logout);

        btnAm = (Button)findViewById(R.id.btnCallAmbLogout);
        btnFav = (Button)findViewById(R.id.btnFavorite);
        btnLogout = (Button)findViewById(R.id.btnLogout);
        txtName = (TextView)findViewById(R.id.txtName);
        txtLocation = (TextView)findViewById(R.id.txtLocationLogout);


        User user = SharedPrefManager.getInstance(this).getUser();
        txtName.setText(user.getUsername());


        btnAm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Context alert = getApplicationContext();
//                Toast.makeText(alert,"Hello ",Toast.LENGTH_SHORT).show();
//                txtLocation.setText("cccc");
//                gps = new GPSTracker(userLogout.this);
//
//                if(gps.canGetLocation()){
//
//                    double latitude = gps.getLatitude();
//                    double longitude = gps.getLongitude();
//
//                    txtLocation.setText("ตำแหน่งของคุณคือ - \nLat: " + latitude + "\nLong: " + longitude);
//
//                }else{
//                    txtLocation.setText("อุปกรณ์์ของคุณ ปิด GPS");
//                }

                Intent intent = new Intent(getApplicationContext(), hospBookActivity.class);
                startActivity(intent);
            }
        });



        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), showFavActivity.class);
                startActivity(intent);
            }
        });



        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
                //logout แล้วกลับไปหน้าเดิม
                startActivity(new Intent(getApplicationContext(), userLoginActivity.class));
            }
        });

    }
}
