package com.mrproducts.www.trending_repo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;

import static com.mrproducts.www.trending_repo.MainActivity.nk;
import static com.mrproducts.www.trending_repo.MainActivity.ra;

public class RepositoryActivity extends AppCompatActivity {
    final private static String EXTRA_SPINNER_OPTION = "SPINNER_OPTION";
    private RecyclerView recyclerViewRepositories;
    private RepositoryListAdapter adapter;
    private ArrayList<Repository> repositories = new ArrayList<Repository>();
    private ProgressBar progress;
    String web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        recyclerViewRepositories = findViewById(R.id.repositoriesRecyclerView);
        progress = findViewById(R.id.progress);
        adapter = new RepositoryListAdapter();

        showProgress();
        setupRecycleView();
        loadData();
    }

    private void setupRecycleView(){
        recyclerViewRepositories.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewRepositories.setAdapter(adapter);
    }

    private void loadData(){
        Bundle bundle = getIntent().getExtras();
        String selectedTimeOption = "";

        if (bundle != null)
            selectedTimeOption = bundle.getString(EXTRA_SPINNER_OPTION);
        TextView tt = findViewById(R.id.timeSelected);
        String t = "";
        if (selectedTimeOption.equals("today"))
            t = "Today";
        else if (selectedTimeOption.equals("weekly"))
            t = "This Week";
        else
            t = "This Month";

        tt.setText(t);
        web = "https://github.com/trending?since=" + selectedTimeOption;
        Thread th = new Thread(){
            @Override
            public void run() {
                try {
                    Document doc =  Jsoup
                            .connect(web)
                            .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
                            .get();
                    Element content = doc.getElementsByClass("repo-list").first();
                    Elements links = content.getElementsByTag("li");
                    nk = new String[links.size()];
                    ra = new String[links.size()];
                    int i=0;
                    for (Element link : links) {
                        nk[i]= link.getElementsByTag("a").first().attr("href");
                        ra[i]= link.getElementsByTag("p").first().text();
                        i++;
                    }

                }

                catch (Exception e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<nk.length;i++) {
                            Repository ld = new Repository(nk[i], "https://github.com" + nk[i] ,ra[i]);
                            repositories.add(ld);
                        }
                        hideProgress();
                        adapter.update(repositories);
                    }
                });
            }
        };
        th.start();
    }


    private void hideProgress(){
        progress.setVisibility(View.GONE);
    }
    private void showProgress(){
        progress.setVisibility(View.VISIBLE);
    }

}

