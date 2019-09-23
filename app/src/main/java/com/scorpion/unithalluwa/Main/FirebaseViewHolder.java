package com.scorpion.unithalluwa.Main;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scorpion.unithalluwa.R;

public class FirebaseViewHolder extends RecyclerView.ViewHolder {

    public TextView title,message,sender;

    public FirebaseViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.listTitle);
        message = itemView.findViewById(R.id.listMessage);
        sender = itemView.findViewById(R.id.listSender);

    }
}
