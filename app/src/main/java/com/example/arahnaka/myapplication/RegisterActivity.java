package com.example.arahnaka.myapplication;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity
        implements View.OnClickListener {

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword, editTextPassword2;

    private TextView textViewSignin;
    private TextView textViewAgree;

    private CheckBox checkBoxAgree;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("VisitPattani");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitle("");
        toolbar.setSubtitleTextColor(Color.MAGENTA);
        //toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setBackgroundColor(0xff509CEC);
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();

        /*if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }*/

        progressDialog = new ProgressDialog(this);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPassword2 = (EditText) findViewById(R.id.editTextPassword2);

        textViewSignin = (TextView) findViewById(R.id.textViewSignin);
        textViewAgree = (TextView) findViewById(R.id.textViewAgree);

        checkBoxAgree = (CheckBox) findViewById(R.id.checkBoxAgree);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
        textViewAgree.setOnClickListener(this);
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String password2 = editTextPassword2.getText().toString().trim();
        boolean checkbox = checkBoxAgree.isChecked();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "กรุณากรอก อีเมล์ผู้ใช้", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "กรุณากรอก รหัสผ่าน", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 6){
            Toast.makeText(this, "กรุณากรอก รหัสผ่านมีความยาวอย่างน้อย 6 ตัวอักษรหรือมากกว่า", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password2)) {
            Toast.makeText(this, "กรุณากรอก รหัสผ่านอีกครั้ง", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(password2)) {
            Toast.makeText(this, "รหัสผ่านทั้ง 2 ครั้ง ไม่เหมือนกัน กรุณากรอกใหม่", Toast.LENGTH_LONG).show();
            return;
        }
        if (!checkbox) {
            Toast.makeText(this, "กรุณากดยอมรับ ข้อตกลงและเงื่อนไขการใช้งาน ", Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("กำลังสมัครสมาชิก...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "การสมัครสมาชิกเสร็จสมบูรณ์", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "ไม่สามารถสมัครสมาชิกได้ เนื่องจากมีการใช้งานอีเมล์นี่เเล้ว", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    public void onClick(View view) {
        if (view == buttonRegister) {
            registerUser();
        }
        if (view == textViewSignin) {
            startActivity(new Intent(this, LoginActivity.class));
        }
        if (view == textViewAgree) {
            startActivity(new Intent(this, AgreeActivity.class));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent goSet = new Intent(RegisterActivity.this,Setting.class);
            startActivity(goSet);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
