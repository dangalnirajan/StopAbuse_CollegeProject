package com.darkness.sparkwomen;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.lang.reflect.Array;

public class UploadActivity extends AppCompatActivity {

    ImageView uploadImage;
    Button saveButton;
    CheckBox postAnonymous;
    EditText uploadName,uploadAddress,uploadContact,uploadDescription;
    String imageURL;
    Uri uri;

     Spinner uploadCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        uploadImage = findViewById(R.id.uploadImage);
        saveButton = findViewById(R.id.upload_save);
        postAnonymous=findViewById(R.id.anonymous_post);
        uploadName = findViewById(R.id.upload_name);
        uploadAddress=findViewById(R.id.upload_address);
        uploadContact=findViewById(R.id.upload_contact);
        uploadDescription=findViewById(R.id.upload_description);
//        uploadCategory=findViewById(R.id.spinner_category);
//        String[] categoryItems={"Physical Abuse" , "Emotional/Verbal Abuse" , "Psychological Abuse" ,
//                "Sexual Abuse" , "Financial Abuse" , "Digital/Online Abuse" , "Stalking" ,
//                "Spiritual Abuse" , "Cultural/Identity Abuse"};
//
//        ArrayAdapter<String> spinnerAdapter= new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_item,categoryItems);
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        uploadCategory.setAdapter(spinnerAdapter);

//        uploadCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
//                String selectedCategory=categoryItems[position];
//                Toast.makeText(UploadActivity.this, "Selected Category: " + selectedCategory, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
        postAnonymous.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    uploadName.setVisibility(View.GONE);
                    uploadContact.setVisibility(View.GONE);
                }else {
                    uploadName.setVisibility(View.VISIBLE);
                    uploadContact.setVisibility(View.VISIBLE);
                }
            }
        });

        ActivityResultLauncher<Intent>  activityResultLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()== Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri=data.getData();
                            uploadImage.setImageURI(uri);
                        }else {
                            Toast.makeText(UploadActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent intent = new Intent(UploadActivity.this, ReportActivity.class);
                intent.putExtra("postAnonymous", postAnonymous.isChecked());
                intent.putExtra("Name", uploadName.getText().toString());
                intent.putExtra("Contact", uploadContact.getText().toString());
                intent.putExtra("Address", uploadAddress.getText().toString());
                intent.putExtra("Description", uploadDescription.getText().toString());
//                intent.putExtra("Category", uploadCategory.getSelectedItemPosition());
                startActivity(intent);
            }
        });
    }

    public void saveData(){

        String names ="";
        String contacts ="";
        if (!postAnonymous.isChecked()){
            names=uploadName.getText().toString();
            contacts=uploadContact.getText().toString();
        }
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Android Image")
                .child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(UploadActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri uriImage = uriTask.getResult();
                imageURL=uriImage.toString();
                uploadData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }
     public void uploadData(){

         String names ="";
         String contacts ="";

         if(!postAnonymous.isChecked()){
             names = uploadName.getText().toString();
             contacts = uploadContact.getText().toString();
         }

        String name = uploadName.getText().toString();
        String address = uploadAddress.getText().toString();
        String contact = uploadContact.getText().toString();
        String description = uploadDescription.getText().toString();
//        int category = uploadCategory.getSelectedItemPosition();

        DataClass dataClass= new DataClass(name,address,contact,description,imageURL);

         FirebaseDatabase.getInstance().getReference("Android App by Dangal").child(name)
                 .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                         if(task.isSuccessful()){
                             Toast.makeText(UploadActivity.this, "Saved Success", Toast.LENGTH_SHORT).show();
                             finish();
                         }
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         Toast.makeText(UploadActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                     }
                 });
     }
}