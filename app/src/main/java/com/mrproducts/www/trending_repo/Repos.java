package com.mrproducts.www.trending_repo;

import android.app.ActionBar;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static com.mrproducts.www.trending_repo.MainActivity.nk;
import static com.mrproducts.www.trending_repo.MainActivity.ra;

public class Repos extends AppCompatActivity {
    public ListView rl;
    public ArrayList<ListData> myList = new ArrayList<ListData>();
    MyBaseAdapter adapter;
   String web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);
        Bundle bundle = getIntent().getExtras();
        String l = "";
        rl = (ListView) findViewById(R.id.rl);
        adapter = new MyBaseAdapter(getApplicationContext(), myList);
        rl.setAdapter(adapter);
        if (bundle != null)
            l = bundle.getString("go");
        TextView tt = findViewById(R.id.tt);
        String t = "";
        if (l.equals("today"))
            t = "Today";
        else if (l.equals("weekly"))
            t = "This Week";
        else
            t = "This Month";

        tt.setText(t);
        web = "https://github.com/trending?since=" + l;
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
                        int l=nk.length;
                        System.out.print(l);
                        for(int i=0;i<nk.length;i++)
                        {
                            ListData ld = new ListData();
                            ld.setLink("https://github.com" + nk[i]);
                            ld.setName(nk[i]);
                            ld.setDesc(ra[i]);
                            myList.add(ld);
                        }
                        ((BaseAdapter)rl.getAdapter()).notifyDataSetChanged();
                    }
                });
            }
        };
        th.start();
            }


    }

