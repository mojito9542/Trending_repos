package com.mrproducts.www.trending_repo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mrproducts.www.trending_repo.adapter.TrendingReposAdapter;
import com.mrproducts.www.trending_repo.model.TrendingRepo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mrproducts.www.trending_repo.MainActivity.nk;
import static com.mrproducts.www.trending_repo.MainActivity.ra;

public class TrendingReposActivity extends AppCompatActivity {
    final private static String EXTRA_SPINNER_OPTION = "SPINNER_OPTION";

    @BindView(R.id.rl)
    RecyclerView recyclerView;
    @BindView(R.id.progress)
    ProgressBar progress;

    private TrendingReposAdapter adapter;
    private ArrayList<TrendingRepo> myList = new ArrayList<>();
    private String web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        ButterKnife.bind(this);

        adapter = new TrendingReposAdapter(getApplicationContext(), myList);

        //setup recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        showProgress();

        Bundle bundle = getIntent().getExtras();
        String l = "";
        if (bundle != null)
            l = bundle.getString(EXTRA_SPINNER_OPTION);
        TextView tt = findViewById(R.id.timeSelected);
        String t = "";
        if (l.equals("today"))
            t = "Today";
        else if (l.equals("weekly"))
            t = "This Week";
        else
            t = "This Month";

        tt.setText(t);
        web = "https://github.com/trending?since=" + l;
        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup
                            .connect(web)
                            .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
                            .get();
                    Element content = doc.getElementsByClass("repo-list").first();
                    Elements links = content.getElementsByTag("li");
                    nk = new String[links.size()];
                    ra = new String[links.size()];
                    int i = 0;
                    for (Element link : links) {
                        nk[i] = link.getElementsByTag("a").first().attr("href");
                        ra[i] = link.getElementsByTag("p").first().text();
                        i++;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int l = nk.length;
                        System.out.print(l);
                        for (int i = 0; i < nk.length; i++) {
                            TrendingRepo ld = new TrendingRepo();
                            ld.setLink("https://github.com" + nk[i]);
                            ld.setName(nk[i]);
                            ld.setDesc(ra[i]);
                            myList.add(ld);
                        }
                        hideProgress();
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                });
            }
        };
        th.start();
    }


    private void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    private void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

}

