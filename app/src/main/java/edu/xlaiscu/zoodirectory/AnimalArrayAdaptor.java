package edu.xlaiscu.zoodirectory;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Lexie on 4/29/16.
 */
public class AnimalArrayAdaptor extends ArrayAdapter<Animal> {
    private final List<Animal> animals;
    private Context ctxt;

    public AnimalArrayAdaptor(Context context, int resource, List<Animal> animals) {
        super(context, resource, animals);
        this.animals = animals;
        ctxt = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ScrapViewHolder holder;

        View row = convertView;

        if (row == null) {
            Log.i("xlai", "Row is null; Need to be inflated.");

            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.custom_row, parent, false);

            holder = new ScrapViewHolder();
            holder.label = (TextView) row.findViewById(R.id.label);
            holder.icon = (ImageView) row.findViewById(R.id.icon);
            holder.favorite = (ToggleButton) row.findViewById(R.id.favorite);

            row.setTag(holder);

        } else {
            Log.i("xlai", "Row is NOT null; reusing it!");
            holder = (ScrapViewHolder) row.getTag();
        }

        holder.label.setText(animals.get(position).getName());

        try {
            String filename = animals.get(position).getFilename();
            InputStream inputStream = getContext().getAssets().open(filename);
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            holder.icon.setImageDrawable(drawable);
            holder.icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } catch (IOException e) {
            e.printStackTrace();
        }


        holder.favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String msg = "Favorite checked for " + animals.get(position).getName();
                    Toast.makeText(ctxt, msg, Toast.LENGTH_SHORT).show();
                    holder.favorite.setButtonDrawable(android.R.drawable.btn_star_big_on);
                }
                else {
                    String msg = "Favorite unchecked for " + animals.get(position).getName();
                    Toast.makeText(ctxt, msg, Toast.LENGTH_SHORT).show();
                    holder.favorite.setButtonDrawable(android.R.drawable.btn_star_big_off);
                }
            }
        });
        return row;
    }

    public class ScrapViewHolder {
        TextView label;
        ImageView icon;
        ToggleButton favorite;
    }
}
