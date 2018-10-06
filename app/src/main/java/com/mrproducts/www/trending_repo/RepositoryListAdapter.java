package com.mrproducts.www.trending_repo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RepositoryListAdapter extends RecyclerView.Adapter<RepositoryListAdapter.RepositoryViewHolder> {

    private List<Repository> repositoryList;

    public RepositoryListAdapter(List<Repository> repositoryList) {
        this.repositoryList = repositoryList;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repository, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        holder.bind(repositoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    class RepositoryViewHolder extends RecyclerView.ViewHolder {

        TextView itemTitle, itemDescription;

        RepositoryViewHolder(View view) {
            super(view);
        }

        void bind(Repository repository) {
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemDescription = itemView.findViewById(R.id.itemDescription);

            itemTitle.setText(repository.getTitle());
            itemDescription.setText(repository.getDescription());
        }
    }
}