package com.safa.fourquareapplication.adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.safa.fourquareapplication.R;
import com.safa.fourquareapplication.databinding.PinlistRowBinding;
import com.safa.fourquareapplication.model.PinArea;

import java.util.ArrayList;
import java.util.List;

public class PinListRecycleViewAdapter extends RecyclerView.Adapter<PinListRecycleViewAdapter.MyViewHolder> {
    private ArrayList<PinArea> pinList;
    private TextView pinName;
    private ImageView pinImage;
    public PinlistRowBinding pinlistRowBinding;

    public PinListRecycleViewAdapter(ArrayList<PinArea> pinList){
        this.pinList = pinList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.pinlist_row, parent, false);

        //ViewDataBinding view = DataBindingUtil.inflate(inflater, R.layout.pinlist_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        pinName.setText(pinList.get(position).getAreaName());
        pinImage.setImageBitmap(pinList.get(position).getAreaImage());

    }

    @Override
    public int getItemCount() {
        return pinList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
               super(itemView);
            pinImage = itemView.findViewById(R.id.rowPinImage);
            pinName = itemView.findViewById(R.id.rowPinNameTextView);

        }


    }

    public void updateView(List<PinArea> newPinList){
        pinList.clear();
        pinList.addAll(newPinList);
        notifyDataSetChanged();
    }
}
