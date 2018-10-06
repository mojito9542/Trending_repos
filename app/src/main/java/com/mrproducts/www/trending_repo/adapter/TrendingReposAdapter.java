package com.mrproducts.www.trending_repo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mrproducts.www.trending_repo.R;
import com.mrproducts.www.trending_repo.model.TrendingRepo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrendingReposAdapter extends RecyclerView.Adapter<TrendingReposAdapter.TrendingReposViewHolder> {

    private Context context;
    private List<TrendingRepo> repos;

    public TrendingReposAdapter(Context context, List<TrendingRepo> repos) {
        this.context = context;
        this.repos = repos;
    }

    @Override
    public TrendingReposViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TrendingReposViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_repository, parent, false));
    }

    @Override
    public void onBindViewHolder(TrendingReposViewHolder holder, int position) {
        TrendingRepo repo = repos.get(position);
        holder.name.setText(repo.getName());
        holder.desc.setText(repo.getDesc());
        holder.link.setText(repo.getLink());
    }

    @Override
    public int getItemCount() {
        return repos == null ? 0 : repos.size();
    }

    public class TrendingReposViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.desc)
        TextView desc;
        @BindView(R.id.go)
        TextView link;

        public TrendingReposViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
