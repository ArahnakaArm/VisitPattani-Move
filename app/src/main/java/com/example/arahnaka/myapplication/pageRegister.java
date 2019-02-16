package com.example.arahnaka.myapplication;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class pageRegister extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST=1;
    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private Button commit;
    private EditText editTextName;
    private EditText editTextTime;
    private EditText editTextDes;
    private EditText editTextLocal;
    private EditText editTextTel;
    private ImageView mImageView;
    private ProgressBar mProgressbar;
    private Uri mImageURI;
    private String Name="";
    private StorageTask storageTask;
    private ProgressDialog progressDialog;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;




    String TEXT;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pageregis);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("เพิ่มข้อมูลร้านค้า");
        toolbar.setTitleTextColor(Color.WHITE);

        editTextName=(EditText)findViewById(R.id.textName);
        editTextLocal=(EditText)findViewById(R.id.textLocal);
        editTextDes=(EditText)findViewById(R.id.textDes);
        editTextTime=(EditText)findViewById(R.id.textTime);
        editTextTel=(EditText) findViewById(R.id.textTel);


        commit = (Button)findViewById(R.id.next);

        storageReference =FirebaseStorage.getInstance().getReference("uploads");
        databaseReference=FirebaseDatabase.getInstance().getReference("uploads");

        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editTextName.getText().toString().equals("")||editTextLocal.getText().toString().equals("")
                        ||editTextDes.getText().toString().equals("")||editTextTime.getText().toString().equals("")
                        ||editTextTel.getText().toString().equals("")||mImageURI == null){
                    Toast.makeText(pageRegister.this,"กรุณากรอกข้อมูลให้ครบถ้วน",Toast.LENGTH_SHORT).show();

                }




                else {
                    if (storageTask != null && storageTask.isInProgress()) {
                        Toast.makeText(pageRegister.this, "กำลังอยู่ในกระบวนการ", Toast.LENGTH_SHORT).show();

                    } else {
                        uploadFile();
                        Intent goHome = new Intent(pageRegister.this, MainActivity.class);
                        startActivity(goHome);
                    }
                }
            }
        });

        mButtonChooseImage=findViewById(R.id.button_choose);
        mButtonUpload=findViewById(R.id.uploadbtn);
        mImageView=findViewById(R.id.imageshow);
        mProgressbar=findViewById(R.id.processbar);

        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });




/*



*/




    }
    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageURI = data.getData();
            Picasso.get().
                    load(mImageURI)
                    .into(mImageView);

        }
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR=getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile(){
        if(mImageURI != null){
             StorageReference fileRef = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(mImageURI));

             storageTask=fileRef.putFile(mImageURI)
                     .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                         @Override
                         public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                             Handler handler = new Handler();
                             handler.postDelayed(new Runnable() {
                                 @Override
                                 public void run() {
                                     mProgressbar.setProgress(0);
                                 }
                             },10);

                             Toast.makeText(pageRegister.this,"เพิ่มข้อมูลสำเร็จ",Toast.LENGTH_SHORT).show();
                             Upload upload= new Upload(editTextName.getText().toString(),taskSnapshot.getDownloadUrl().toString(),editTextDes.getText().toString()
                             ,editTextLocal.getText().toString(),editTextTime.getText().toString(),editTextTel.getText().toString());

                             String uploadID = databaseReference.push().getKey();
                             databaseReference.child(uploadID).setValue(upload);
                         }
                     })
                     .addOnFailureListener(new OnFailureListener() {
                         @Override
                         public void onFailure(@NonNull Exception e) {
                            Toast.makeText(pageRegister.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                         }
                     })
                     .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                         @Override
                         public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double process = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            mProgressbar.setProgress((int)process);
                         }
                     });
        }else {
            Toast.makeText(pageRegister.this,"No File Selected",Toast.LENGTH_SHORT).show();
        }

    }
}

