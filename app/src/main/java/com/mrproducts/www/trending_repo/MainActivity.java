package com.mrproducts.www.trending_repo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    final private static String EXTRA_SPINNER_OPTION = "SPINNER_OPTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] spinnerOptionList = {"Today", "This Week", "This Month"};
        final Spinner spinner = findViewById(R.id.timeSortSpinner);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.item_spinner_options, spinnerOptionList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        Button btnSearchRepository = findViewById(R.id.searchReposButton);
        btnSearchRepository.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent repository = new Intent(MainActivity.this, RepositoryActivity.class);
                String strTime = spinner.getSelectedItem().toString().trim();
                if (strTime.equals(spinnerOptionList[0]))
                    repository.putExtra(EXTRA_SPINNER_OPTION, "today");
                else if (strTime.equals(spinnerOptionList[1]))
                    repository.putExtra(EXTRA_SPINNER_OPTION, "weekly");
                else
                    repository.putExtra(EXTRA_SPINNER_OPTION, "monthly");
                startActivity(repository);
            }
        });


    }
}
