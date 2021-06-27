package com.prantomondal.todoandroidapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.prantomondal.todoandroidapp.Adapter.ToDoAdapter;

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private ToDoAdapter adapter;

    public RecyclerItemTouchHelper(ToDoAdapter adapter){
        super(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }
    @Override
    public boolean onMove(RecyclerView recyclerView,RecyclerView.ViewHolder viewHolder,RecyclerView.ViewHolder target){
        return false;
    }
    @Override
    public void onSwiped(final RecyclerView.ViewHolder viewHolder,int direction){
        final int position = viewHolder.getAbsoluteAdapterPosition();
        if (direction == ItemTouchHelper.LEFT){
            AlertDialog.Builder builder = new AlertDialog.Builder(adapter.getContext());
            builder.setTitle("Delete Task");
            builder.setMessage("Are you sure you want to delete the task?");
            builder.setPositiveButton("Confirm",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.deleteItem(position);
                        }
                    });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.notifyItemChanged(viewHolder.getAbsoluteAdapterPosition());
                }
            });
            AlertDialog dialog = builder.create();

        }
        else {
            adapter.editItem(position);
        }
    }
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dx,float dy, int actionState, boolean isCurrentlyActive)


}
