package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InstructorScreenList extends RecyclerView.Adapter<InstructorScreenList.InstructorViewHolder> {
    List<InstructorScreen.Instructor> instructors;
    private int selectedItemPosition = RecyclerView.NO_POSITION;
    public InstructorScreenList(List<InstructorScreen.Instructor> instructors) {
        this.instructors = instructors;
    }

    @NonNull
    @Override
    public InstructorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_instructor, parent, false);
        return new InstructorViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull InstructorViewHolder holder, int position) {
        InstructorScreen.Instructor instructor = instructors.get(position);
        holder.instructorNameTextView.setText(instructor.getName() != null ? instructor.getName() : "Name not available");
        holder.itemView.setActivated(selectedItemPosition == position); //Highlight selected item

        holder.itemView.setOnCreateContextMenuListener((menu, v, menuInfo) -> {
            selectedItemPosition = holder.getAdapterPosition();
        });
    }

    public void setSelectedItemPosition(int position) {
        selectedItemPosition = position;
        notifyDataSetChanged();
    }

    public int getSelectedItemPosition() {
        return selectedItemPosition;
    }

    @Override
    public int getItemCount() {
        return instructors.size();
    }

    static class InstructorViewHolder extends RecyclerView.ViewHolder {
        TextView instructorNameTextView;

        InstructorViewHolder(View itemView) {
            super(itemView);
            instructorNameTextView = itemView.findViewById(R.id.instructorNameTextView);
        }
    }
}
