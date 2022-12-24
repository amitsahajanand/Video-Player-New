package com.videoplayer.fastplayer.gdvideoplayer.Activity.VideoCall.ExtraPiicker;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.R;


public class Age_number_Adapter extends RecyclerView.Adapter<Age_number_Adapter.MyviewHolder> {

    Activity activity;
    ArrayList<numberModel> List_age_data;

    public Age_number_Adapter(Activity context, ArrayList<numberModel> arrayList) {

        this.activity = context;
        this.List_age_data = arrayList;

    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);

        View view = inflater.inflate(R.layout.row_age_item_adapter, parent, false);
        MyviewHolder myviewHolder = new MyviewHolder(view);
        return myviewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {


        numberModel data = List_age_data.get(position % List_age_data.size());
        holder.txt_age_number.setText(data.getNumber());
        //if (List_age_data.get(position % List_age_data.size()).isSelected) {
        if (Utils.age_selected_pos == position && Utils.age_selected_pos != 0) {
            //Log.d("Number_age"," select number = " + List_age_data.get(position));
//            holder.txt_age_number.setTextSize(16);
            holder.txt_age_number.setAlpha(1f);
            holder.txt_age_number.setTextColor(activity.getResources().getColor(R.color.black));
            holder.txt_age_number.setTypeface(Typeface.DEFAULT_BOLD);
//            holder.txt_age_number.setBackgroundResource(R.drawable.number_selector);

            holder.ll_line1.setVisibility(View.VISIBLE);
            holder.ll_line2.setVisibility(View.VISIBLE);

        } else {
            holder.txt_age_number.setAlpha(0.7f);
            holder.txt_age_number.setTextColor(activity.getResources().getColor(R.color.txt_discription));
//            holder.txt_age_number.setBackgroundResource(R.drawable.bg_button_uri);
            holder.ll_line1.setVisibility(View.GONE);
            holder.ll_line2.setVisibility(View.GONE);
           // holder.txt_age_number.setBackgroundResource(R.color.black);
        }
        holder.txt_age_number.setText(List_age_data.get(position % List_age_data.size()).getNumber());
        holder.txt_age_number.setTag(position);

    }


    /*@Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }*/
    @Override
    public int getItemCount() {
        return List_age_data.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        TextView txt_age_number;
        LinearLayout ll_line1, ll_line2;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            txt_age_number = (TextView) itemView.findViewById(R.id.txt_age_number);
            ll_line1 = itemView.findViewById(R.id.ll_line1);
            ll_line2 = itemView.findViewById(R.id.ll_line2);

        }
    }
}
