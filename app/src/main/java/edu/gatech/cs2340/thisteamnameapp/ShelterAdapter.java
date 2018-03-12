package edu.gatech.cs2340.thisteamnameapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.Filter;
import android.content.Context;
import android.widget.TextView;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
/**
 * Created by paigemca on 3/7/18.
 */

public class ShelterAdapter extends RecyclerView.Adapter<ShelterAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<Shelter> shelterList;
    private List<Shelter> shelterListFiltered;
    private ShelterAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView gender;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            gender = view.findViewById(R.id.gender);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onShelterSelected(shelterListFiltered.get(getAdapterPosition()));
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ShelterDetailActivity.class);
                    intent.putExtra(ShelterDetailFragment.ARG_ITEM_ID, shelterListFiltered.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });
        }
    }

    public ShelterAdapter(Context context, List<Shelter> shelterList, ShelterAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.shelterList = shelterList;
        this.shelterListFiltered = shelterList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shelter_row_item, parent, false);

        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Shelter shelter = shelterListFiltered.get(position);
        holder.name.setText(shelter.getName());
    }

    @Override
    public int getItemCount() {
        return shelterListFiltered.size();
    }


    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    shelterListFiltered = shelterList;
                } else {
                    List<Shelter> filteredList = new ArrayList<>();
                    for (Shelter row : shelterList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getPhone().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    shelterListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = shelterListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                shelterListFiltered = (ArrayList<Shelter>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface ShelterAdapterListener {
        void onShelterSelected(Shelter shelter);
    }


}
