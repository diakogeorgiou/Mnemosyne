/*
 * Copyright (c) 2015. Diakogeorgiou Kostas
 * All rights reserved.
 */

package project.mobile.mnemosyne;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DeckListAdapter extends BaseAdapter {
    private ArrayList<Deck> decks;
    Context context;

    private LayoutInflater mInflater;

    public DeckListAdapter(Context context, ArrayList<Deck> decks) {
        this.decks = decks;
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public int getCount() {
        return decks.size();
    }

    public Object getItem(int position) {
        return decks.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.decks_row_view, null);
            holder = new ViewHolder();

            holder.txtDeckTitle = convertView.findViewById(R.id.txtDeckTitle);
            holder.txtDeckDesc = convertView.findViewById(R.id.txtDeckDesc);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //Label values
        holder.txtDeckTitle.setText(decks.get(position).getTitle());
        holder.txtDeckDesc.setText(decks.get(position).getDesc());

        return convertView;
    }

    static class ViewHolder {
        TextView txtDeckTitle;
        TextView txtDeckDesc;
    }
}
