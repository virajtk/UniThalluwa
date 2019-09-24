package com.scorpion.unithalluwa.Assingment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scorpion.unithalluwa.R;

public class FirebaseViewHolderAssignment extends RecyclerView.ViewHolder {

    public TextView assTitle,year,sem,module;

    public FirebaseViewHolderAssignment(@NonNull View itemView) {
        super(itemView);

        assTitle = itemView.findViewById(R.id.listAssTitle);
        year = itemView.findViewById(R.id.listAssYear);
        sem = itemView.findViewById(R.id.listAssSem);
        module = itemView.findViewById(R.id.listAssModule);

    }

}
