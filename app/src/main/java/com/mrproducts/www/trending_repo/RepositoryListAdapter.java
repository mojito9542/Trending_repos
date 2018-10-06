package com.mrproducts.www.trending_repo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RepositoryListAdapter extends RecyclerView.Adapter<RepositoryListAdapter.RepositoryViewHolder> {

    private List<Repository> repositoryList = new ArrayList<>();

    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repository, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position) {
        holder.bind(repositoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    void update(List<Repository> data){
        repositoryList.clear();
        repositoryList.addAll(data);
        notifyDataSetChanged();
    }

    class RepositoryViewHolder extends RecyclerView.ViewHolder{

        RepositoryViewHolder(View view){
            super(view);
        }

        void bind(Repository repository){
            TextView itemTitle = itemView.findViewById(R.id.itemTitle);
            TextView itemDescription = itemView.findViewById(R.id.itemDescription);
            itemTitle.setText(repository.getTitle());
            itemDescription.setText(repository.getDescription());
        }
    }
}