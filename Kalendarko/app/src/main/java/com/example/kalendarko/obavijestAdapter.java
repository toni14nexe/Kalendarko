package com.example.kalendarko;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class obavijestAdapter extends RecyclerView.Adapter<obavijestAdapter.ViewHolder> {
    private ArrayList<obavijest> obavijestiArrayList;
    private Context context;

    public obavijestAdapter(ArrayList<obavijest> courseModalArrayList, Context context) {
        this.obavijestiArrayList = obavijestiArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.podatak_lista, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        obavijest modal = obavijestiArrayList.get(position);
        holder.obavijestID.setText(modal.getId());
        holder.obavijestDate.setText(modal.getDate());
        holder.obavijestEvent.setText(modal.getEvent());
        holder.obavijestDescription.setText(modal.getDescription());
        holder.obavijestRepeat.setText(modal.getRepeat());
    }

    @Override
    public int getItemCount() {
        return obavijestiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView obavijestID, obavijestDate, obavijestEvent, obavijestDescription, obavijestRepeat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            obavijestID = itemView.findViewById(R.id.id);
            obavijestDate = itemView.findViewById(R.id.date);
            obavijestEvent = itemView.findViewById(R.id.event);
            obavijestDescription = itemView.findViewById(R.id.description);
            obavijestRepeat = itemView.findViewById(R.id.repeat);
        }
    }
}
