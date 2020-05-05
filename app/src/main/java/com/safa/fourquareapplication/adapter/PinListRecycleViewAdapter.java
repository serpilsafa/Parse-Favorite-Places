package com.safa.fourquareapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.safa.fourquareapplication.R;
import com.safa.fourquareapplication.databinding.PinlistRowBinding;
import com.safa.fourquareapplication.model.PinArea;

import java.util.ArrayList;
import java.util.List;

public class PinListRecycleViewAdapter extends RecyclerView.Adapter<PinListRecycleViewAdapter.MyViewHolder> implements OnRecyclerViewClickListener{
    private ArrayList<PinArea> pinList;
    private Context context;

    public PinListRecycleViewAdapter(ArrayList<PinArea> pinList, Context context){
        this.pinList = pinList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PinlistRowBinding view = DataBindingUtil.inflate(inflater, R.layout.pinlist_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(pinList.get(position));
        System.out.println("adapter "+position+": "+pinList.get(position).getAreaName()+ " size: "+pinList.size());
        holder.pinlistRowBinding.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return pinList.size();
    }

    @Override
    public void onclick(PinArea pin) {
       Toast.makeText(context, pin.getAreaName(), Toast.LENGTH_LONG).show();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        PinlistRowBinding pinlistRowBinding;
        public MyViewHolder(PinlistRowBinding pinlistRowBinding){
            super(pinlistRowBinding.getRoot());
            this.pinlistRowBinding = pinlistRowBinding;

        }

        public void bind(Object obj){
            pinlistRowBinding.setVariable(BR.pin, obj);
            pinlistRowBinding.executePendingBindings();
        }

    }

    public void updateView(List<PinArea> newPinList){
        pinList.clear();
        pinList.addAll(newPinList);
        notifyDataSetChanged();
    }


}
