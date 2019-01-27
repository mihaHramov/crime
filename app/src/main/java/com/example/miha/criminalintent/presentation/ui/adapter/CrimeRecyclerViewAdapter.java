package com.example.miha.criminalintent.presentation.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.miha.criminalintent.R;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.domain.model.ItemCrime;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CrimeRecyclerViewAdapter extends RecyclerView.Adapter<CrimeRecyclerViewAdapter.ViewHolder> {

    public interface OnItemClick {
        void click(Crime crime);
    }

    public interface OnItemLongClick {
        void click(Crime crime, View view);
    }

    private List<ItemCrime> crimes = new ArrayList<>();
    private OnItemClick itemClickListener;
    private OnItemLongClick itemLongClickListener;

    public CrimeRecyclerViewAdapter(OnItemClick itemClickListener, OnItemLongClick itemLongClickListener) {
        this.itemClickListener = itemClickListener;
        this.itemLongClickListener = itemLongClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_crime, viewGroup, false);
        return new ViewHolder(view);
    }

    public void setCrimes(List<ItemCrime> crimes) {
        this.crimes = crimes;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(crimes.get(i));
        viewHolder.itemView.setOnLongClickListener(view -> {
            itemLongClickListener.click(crimes.get(viewHolder.getAdapterPosition()).getCrime(), view);
            return false;
        });
        viewHolder.itemView.setOnClickListener(view -> itemClickListener.click(crimes.get(viewHolder.getAdapterPosition()).getCrime()));
    }

    @Override
    public int getItemCount() {
        return crimes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.crime_list_item_titleTextView)
        TextView titleTextView;
        @BindView(R.id.crime_list_item_dateTextView)
        TextView dateTextView;
        @BindView(R.id.crime_list_item_solvedCheckBox)
        CheckBox solvedCheckBox;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        void bind(ItemCrime itemCrime) {
            Crime crime = itemCrime.getCrime();
            titleTextView.setText(crime.getTitle());
            dateTextView.setText(crime.getDate());
            solvedCheckBox.setChecked(crime.getSolved());
        }
    }

    public void update(Crime crime) {
        Integer id = 0;
        for (ItemCrime cr : crimes) {
            if (cr.getCrime().getId().equals(crime.getId())) {
                ItemCrime itemCrime = new ItemCrime();
                itemCrime.setCrime(crime);
                itemCrime.setPosition(id);
                crimes.set(id, itemCrime);
                notifyItemChanged(id);
                break;
            }
            id++;
        }
    }
}
