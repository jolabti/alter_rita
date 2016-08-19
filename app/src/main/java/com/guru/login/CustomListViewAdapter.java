package com.guru.login;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by johan on 16/08/16.
 */
public class CustomListViewAdapter extends ArrayAdapter<Banku> {

    private Activity activity;

    public CustomListViewAdapter(Activity activity, int resource, List<Banku> books) {
        super(activity, resource, books);
        this.activity = activity;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        Banku book = getItem(position);

        holder.name.setText(book.getNamaBan());
        holder.authorName.setText(book.getKodeBan());
        Picasso.with(activity).load(book.getImgUrl()).into(holder.image);

        return convertView;
    }

    private static class ViewHolder {
        private TextView name;
        private TextView authorName;
        private ImageView image;

        public ViewHolder(View v) {
            name = (TextView) v.findViewById(R.id.title);
            image = (ImageView) v.findViewById(R.id.thumbnail);
            authorName = (TextView) v.findViewById(R.id.author);
        }
    }

}