package com.example.relativelayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterContact extends RecyclerView.Adapter<AdapterContact.ViewHolder> {
    Context context;
    ArrayList<ContactModel> list = new ArrayList<>();
    AdapterContact(Context context,ArrayList<ContactModel> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.userImage.setImageResource(list.get(position).image);
        holder.number.setText(list.get(position).number);
        holder.name.setText(list.get(position).name);
        setAnimation(holder.itemView, position);

        holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = holder.getAdapterPosition(); // Get the latest position
                if (currentPosition == RecyclerView.NO_POSITION) return;

                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_update_layout);
                EditText et1 = dialog.findViewById(R.id.name_et);
                EditText et2 = dialog.findViewById(R.id.number_et);
                TextView text = dialog.findViewById(R.id.title_text);
                Button btn = dialog.findViewById(R.id.add_btn);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = "";
                        String number = "";

                        if (!et1.getText().toString().equals("")) {
                            name = et1.getText().toString();
                        } else {
                            Toast.makeText(context, "Please enter name", Toast.LENGTH_SHORT).show();
                        }
                        if (!et2.getText().toString().equals("")) {
                            number = et2.getText().toString();
                        } else {
                            Toast.makeText(context, "Please enter number", Toast.LENGTH_SHORT).show();
                        }
                        list.set(currentPosition, new ContactModel(number, name));
                        notifyItemChanged(currentPosition);
                        dialog.dismiss(); // Close dialog after update
                    }
                });

                dialog.show();
                btn.setText("Update Number");
                text.setText("Update My Contact");
                et1.setText(list.get(currentPosition).name);
                et2.setText(list.get(currentPosition).number);
            }
        });

        holder.rowLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int currentPosition = holder.getAdapterPosition(); // Get the latest position
                if (currentPosition == RecyclerView.NO_POSITION) return false;

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete this contact?")
                        .setIcon(R.drawable.akanksha)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                list.remove(currentPosition);
                                notifyItemRemoved(currentPosition);
                            }
                        })
                        .setNegativeButton("No", null);

                builder.show();
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public  class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView userImage;
        TextView name,number;
        LinearLayout rowLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.user_image);
            name = itemView.findViewById(R.id.user_name);
            number = itemView.findViewById(R.id.user_number);
            rowLayout = itemView.findViewById(R.id.contact_row);
        }
    }
    private void setAnimation(View view, int position){
        Animation slideIn = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        view.startAnimation(slideIn);
    }
}
