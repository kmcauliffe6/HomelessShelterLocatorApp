package edu.gatech.cs2340.thisteamnameapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.Filter;
import android.content.Context;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;


/**
 * Shelter adapter class
 * Created by Paige McAuliffe on 2/20/18.
 */
public class ShelterAdapter extends RecyclerView.Adapter<ShelterAdapter.MyViewHolder> implements Filterable {
    private final List<Shelter> shelterList;
    private List<Shelter> shelterListFiltered;
    private final ShelterAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView gender;
        /**
         * @param view the current view
         *
         */
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
    /**
     * @param context the current context
     * @param shelterList the list of shelters to be filtered
     * @param listener the listener
     */
    public ShelterAdapter(Context context, List<Shelter> shelterList, ShelterAdapterListener listener) {
        Context context1 = context;
        this.listener = listener;
        this.shelterList = shelterList;
        this.shelterListFiltered = shelterList;
    }

    /**
     * created view holder
     * @param parent parent viewGroup
     * @param viewType the viewType
     * @return MyViewHolder
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shelter_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    /**
     * changes name of viewholder to name of shelter at position
     * @param holder viewholder
     * @param position position of where shelter is
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Shelter shelter = shelterListFiltered.get(position);
        holder.name.setText(shelter.getName());
    }

    /**
     * gets size of filtered list
     * @return size of filtered list
     */
    @Override
    public int getItemCount() {
        return shelterListFiltered.size();
    }

    /**
     * returns filter results
     * @return filter trying to get
     */
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                boolean filterby = false;
                String[] arr = {"Anyone", " Male", "Female", "Children", "Family/Newborn", "Young Adults"};
                for (String p: arr) {
                    if (charString.equals(p)) {
                        List<Shelter> filteredList = new ArrayList<>();
                        filterby = true;
                        //System.out.println("charString:" + charString);
                        if ("Anyone".equals(charString)) {
                            shelterListFiltered = shelterList;
                        } else {
                            for (Shelter s: shelterList) {
                                //System.out.println(s.getGender());
                                if (s.getGender().toLowerCase().contains(charString.toLowerCase())) {
                                    filteredList.add(s);
                                }
                            }
                            shelterListFiltered = filteredList;
                        }

                    }
                }
                if (charString.isEmpty() && !filterby) {
                    shelterListFiltered = shelterList;
                } else if (!filterby) {
                    List<Shelter> filteredList = new ArrayList<>();
                    for (Shelter row : shelterList) {
                        //System.out.println("ugggghhhhhh");
                        // shelter name match condition
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
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
    /**
     * interface for ShelterAdapter
     */
    public interface ShelterAdapterListener {
        /**
         * required interface method
         * @param shelter the shelter being selected
         */
        void onShelterSelected(Shelter shelter);
    }


}
