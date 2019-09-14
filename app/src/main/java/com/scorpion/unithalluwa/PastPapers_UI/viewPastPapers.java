package com.scorpion.unithalluwa.PastPapers_UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.scorpion.unithalluwa.R;

import java.util.ArrayList;

public class viewPastPapers extends AppCompatActivity {

    SearchView mySearchView;
    ListView myList;
    Button addbtn, updatebtn;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_past_papers);

        mySearchView = (SearchView)findViewById(R.id.searchView);
        myList = (ListView)findViewById(R.id.myList);
        addbtn = findViewById(R.id.addBtn);
        updatebtn = findViewById(R.id.updateBtn);

        list = new ArrayList<String>();

        list.add("SPM");
        list.add("IP");
        list.add("SE");
        list.add("IWT");
        list.add("MAD");
        list.add("OOP");
        list.add("CN");
        list.add("CS");
        list.add("MC");
        list.add("ICS");
        list.add("DMS");
        list.add("DSA");
        list.add("PS");
        list.add("OOC");

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);

        myList.setAdapter(adapter);

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                adapter.getFilter().filter(s);

                return false;
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),selectPastPapers.class);
                startActivity(i);
            }
        });

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent i = new Intent(getApplicationContext(),#.class);
               // startActivity(i);
            }
        });



    }
}
