package com.Algis_Asmaradhana_JW_F52124032.aplikasi_uts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter implements Filterable {

    private Context context;
    private List<String> originalNames;
    private List<String> filteredNames;
    private List<String> originalPhones;
    private List<String> originalDescs;
    private List<Integer> originalImages;
    private ItemFilter filter;

    public CustomAdapter(Context context, String[] names, String[] phones, String[] descs, int[] images) {
        this.context = context;
        this.originalNames = new ArrayList<>();
        this.filteredNames = new ArrayList<>();
        this.originalPhones = new ArrayList<>();
        this.originalDescs = new ArrayList<>();
        this.originalImages = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            originalNames.add(names[i]);
            filteredNames.add(names[i]);
            originalPhones.add(phones[i]);
            originalDescs.add(descs[i]);
            originalImages.add(images[i]);
        }
    }

    @Override
    public int getCount() {
        return filteredNames.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        }

        ImageView img = convertView.findViewById(R.id.imgPhoto);
        TextView name = convertView.findViewById(R.id.txtName);
        TextView phone = convertView.findViewById(R.id.txtPhone);
        TextView desc = convertView.findViewById(R.id.txtDesc);

        String currentName = filteredNames.get(position);
        int originalIndex = originalNames.indexOf(currentName);

        img.setImageResource(originalImages.get(originalIndex));
        name.setText(currentName);
        phone.setText(originalPhones.get(originalIndex));
        desc.setText(originalDescs.get(originalIndex));

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new ItemFilter();
        }
        return filter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<String> tempList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                tempList.addAll(originalNames);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (String name : originalNames) {
                    if (name.toLowerCase().contains(filterPattern)) {
                        tempList.add(name);
                    }
                }
            }

            results.values = tempList;
            results.count = tempList.size();
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredNames = (List<String>) results.values;
            notifyDataSetChanged();
        }
    }
}