package com.mrproducts.www.trending_repo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    static String nk[],ra[];
    final private static String EXTRA_SPINNER_OPTION = "SPINNER_OPTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] spinnerOptionList = {"Today","This Week","This Month"};
        final Spinner spinner =findViewById(R.id.timeSortSpinner);
        ArrayAdapter a = new ArrayAdapter(this,R.layout.item_spinner_options, spinnerOptionList);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(a);

        Button btn= (Button) findViewById(R.id.searchReposButton);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RepositoryActivity.class);
                String st = spinner.getSelectedItem().toString().trim();
                if(st.equals(spinnerOptionList[0]))
                    intent.putExtra(EXTRA_SPINNER_OPTION,"today");
               else if(st.equals(spinnerOptionList[1]))
                    intent.putExtra(EXTRA_SPINNER_OPTION,"weekly");
                else
                    intent.putExtra(EXTRA_SPINNER_OPTION,"monthly");
                startActivity(intent);
            }
        });


    }
}
