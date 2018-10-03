package com.mrproducts.www.trending_repo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
static String nk[],ra[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] s= {"Today","This Week","This Month"};
        final Spinner spinner=findViewById(R.id.sp);
        ArrayAdapter a= new ArrayAdapter(this,R.layout.view, s);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(a);

        Button btn= (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Repos.class);
                String st= spinner.getSelectedItem().toString().trim();
                if(st==(s[0]))
                    intent.putExtra("go","today");
               else if(st==(s[1]))
                    intent.putExtra("go","weekly");
                else
                    intent.putExtra("go","monthly");
                startActivity(intent);
            }
        });


    }
}
