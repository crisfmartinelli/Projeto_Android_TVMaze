package unibratec.edu.br.tvmazeapp.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import unibratec.edu.br.tvmazeapp.R;
import unibratec.edu.br.tvmazeapp.model.Result;
import unibratec.edu.br.tvmazeapp.model.Show;
import unibratec.edu.br.tvmazeapp.model.Image;

import static android.R.attr.data;
import static android.R.attr.resource;
import static java.lang.System.load;

public class ShowAdapter extends ArrayAdapter{
    public ShowAdapter(Context context, List<Result> results) {
        super(context, 0, results);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Result result = (Result) getItem(position);

        ViewHolder vh;
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_show, parent, false);
            vh = new ViewHolder();
            vh.textViewName = (TextView)convertView.findViewById(R.id.item_show_name);
            vh.textViewLanguage = (TextView)convertView.findViewById(R.id.item_show_language);
            vh.textViewStatus = (TextView)convertView.findViewById(R.id.item_show_status);
            vh.imageView = (ImageView)convertView.findViewById(R.id.item_show_image);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder)convertView.getTag();
        }

        vh.textViewName.setText(result.show.name);
        vh.textViewLanguage.setText(result.show.language);
        vh.textViewStatus.setText(result.show.status);

        if (result.show.images != null && result.show.images.medium != null) {
            Glide
                    .with(getContext())
                    .load(result.show.images.medium)
                    .into(vh.imageView);
        }else{
            vh.imageView.setImageResource(R.drawable.nophoto);
        }
        return convertView;
    }

    static class ViewHolder {
        TextView textViewName;
        TextView textViewLanguage;
        TextView textViewStatus;
        ImageView imageView;
    }
}
