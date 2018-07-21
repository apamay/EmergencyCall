package com.example.apamay.emergencycall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class showFavActivity extends AppCompatActivity {

    RecyclerView lvwFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_fav);

        inItView();
    }



    private void inItView(){
        lvwFav = (RecyclerView)findViewById(R.id.lvwFav);
        //ไปดูเรื่องการดึง list
    }
}
