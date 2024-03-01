package com.example.thyme;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterPer extends RecyclerView.Adapter<AdapterPer.MyViewHolder> {

    private OnNoteListener mOnNoteListener;

    List<ModalPer> mList;
    Context context;


    public AdapterPer(List<ModalPer> mList, Context context, OnNoteListener onNoteListener) {
        this.mList = mList;
        this.context = context;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_per,parent,false);

        return new MyViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.imageviewPer.setImageResource(mList.get(position).getImage());
        holder.textViewPer.setText(mList.get(position).getText());

       /* holder.cardViewPer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,businessDet.class);
                context.startActivity(intent);
            }
        }); */

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnNoteListener onNoteListener;

        ImageView imageviewPer;
        TextView textViewPer;
        CardView cardViewPer;


        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            this.onNoteListener = onNoteListener;

            imageviewPer = itemView.findViewById(R.id.imagePer);
            textViewPer = itemView.findViewById(R.id.SourcePer);
            cardViewPer = itemView.findViewById(R.id.cardViewPer);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onNoteListener.OnNoteClick(getAdapterPosition());

        }
    }

    public interface OnNoteListener {
        void OnNoteClick(int position);
    }

}
