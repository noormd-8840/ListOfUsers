package com.example.listofusers.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listofusers.Model.Users;
import com.example.listofusers.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    Context mContext;
    ArrayList<Users> usersList = new ArrayList<>();

    public UsersAdapter(Context mContext, ArrayList<Users> usersList) {
        this.mContext = mContext;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.users_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Users users = usersList.get(position);
        Picasso.get().load(users.getAvatar()).placeholder(R.drawable.ic_person).into(holder.avatar);
        holder.id.setText(String.valueOf(users.getId()));
        holder.email.setText(users.getEmail());
        holder.firstName.setText(users.getFirst_name());
        holder.lastName.setText(users.getLast_name());

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView avatar;
        private TextView id,email,firstName,lastName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            id = itemView.findViewById(R.id.id);
            email = itemView.findViewById(R.id.email);
            firstName = itemView.findViewById(R.id.first_name);
            lastName = itemView.findViewById(R.id.last_name);

        }
    }

}
