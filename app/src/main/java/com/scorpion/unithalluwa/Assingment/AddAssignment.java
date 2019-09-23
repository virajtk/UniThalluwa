package com.scorpion.unithalluwa.Assingment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.scorpion.unithalluwa.R;
import com.scorpion.unithalluwa.data.model.Assignment;

public class AddAssignment extends AppCompatActivity {

    DatabaseReference dbref;
    Spinner spinner1, spinner2, spinner3;
    EditText etTopic;
    private Button btnAdd;
    Assignment assignment;
    FirebaseDatabase database;
    FirebaseStorage storage;
    Button addBtn, importAssbtn;
    Uri pdfUri;
    ProgressDialog progressDialog;

    private void clearContrals(){

        spinner1.setSelection(0);
        spinner2.setSelection(0);
        spinner3.setSelection(0);
        etTopic.setText("");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        assignment = new Assignment();
        spinner1 = findViewById(R.id.yearSpinner);

        etTopic = findViewById(R.id.topic);

        btnAdd = findViewById(R.id.addBtn);
        importAssbtn =findViewById(R.id.importAssbtn);

        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        importAssbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(AddAssignment.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                   selectPdf();
                }
                else {
                    ActivityCompat.requestPermissions(AddAssignment.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                spinner1 = findViewById(R.id.yearSpinner);
                spinner2 = findViewById(R.id.SemSpinner);
                spinner3 = findViewById(R.id.ModuleSpinner);
                etTopic = findViewById(R.id.topic);
                btnAdd = findViewById(R.id.addBtn);

                dbref = FirebaseDatabase.getInstance().getReference().child("Assignment");

                if (pdfUri != null){
                    uploadFile(pdfUri);
                }
                else {
                    Toast.makeText(AddAssignment.this,"Select a file", Toast.LENGTH_SHORT).show();
                }

                try {
                    if (TextUtils.isEmpty(spinner1.getSelectedItem().toString()))
                        Toast.makeText(getApplicationContext(),"  Select Year... ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(spinner2.getSelectedItem().toString()))
                        Toast.makeText(getApplicationContext(), " Select Semester...  ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(spinner3.getSelectedItem().toString()))
                        Toast.makeText(getApplicationContext(), "   Select Module...  ",Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(etTopic.getText().toString() ))
                        Toast.makeText(getApplicationContext(), "  Insert a topic  ",Toast.LENGTH_SHORT).show();

                    else{
                        assignment.setYear(spinner1.getSelectedItem().toString().trim());
                        assignment.setSem(spinner2.getSelectedItem().toString().trim());
                        assignment.setModule(spinner3.getSelectedItem().toString().trim());
                        assignment.setAssTitle(etTopic.getText().toString().trim());


                        dbref.push().setValue(assignment);

                        Toast.makeText(getApplicationContext(), " New Assignment Add successfully    ",Toast.LENGTH_SHORT).show();
                        clearContrals();
                    }

                }
                catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"   ", Toast.LENGTH_SHORT).show();

}

            }
        });

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddAssignment.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.year));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myAdapter);

        spinner2= findViewById(R.id.SemSpinner);

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(AddAssignment.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.semester));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myAdapter1);

        spinner3 = findViewById(R.id.ModuleSpinner);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(AddAssignment.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.module));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(myAdapter2);

    }

    private void uploadFile(Uri pdfUri) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File...");
        progressDialog.setProgressStyle(0);
        progressDialog.show();



        final String fileName = System.currentTimeMillis() + "";
        StorageReference storageReference = storage.getReference();

        storageReference.child("Uploads").child(fileName).putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String url = taskSnapshot.getUploadSessionUri().toString();

                DatabaseReference reference = database.getReference();
                reference.child(fileName).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task .isSuccessful()){
                            Toast.makeText(AddAssignment.this,"Assignment Upload successfully!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(AddAssignment.this,"Assignment not upload Successfully!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(AddAssignment.this,"Assignment not upload Successfully!",Toast.LENGTH_SHORT).show();


            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                int currentProgress = (int) (100* taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 9 && grantResults [0] == PackageManager.PERMISSION_GRANTED){
            selectPdf();

        }
        else {
            Toast.makeText(AddAssignment.this,"PLease Provide permission!",Toast.LENGTH_SHORT).show();
        }
    }

    private void selectPdf(){
        Intent i  = new Intent();
        i.setType("application/pdf");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,86);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 86 && requestCode == RESULT_OK && data!=null){
            pdfUri = data.getData();
            etTopic.setText("A file is selected : "  + data.getData().getLastPathSegment());
        }
        else {
            Toast.makeText(AddAssignment.this,"Please Select a file",Toast.LENGTH_SHORT).show();
        }
    }
}
