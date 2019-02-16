package com.example.arahnaka.myapplication;

import android.Manifest;
import android.app.Dialog;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private FirebaseAuth firebaseAuth;

    private TextView textViewUserEmail;

    private DatabaseReference databaseReference;
    private EditText editTextName, editTextLName, editTextAge,
            editTextAddress, editTextHosteller,  editTextCostter, editTextTTR;
    Spinner spinnerVehicle, spinnerNation;
    private Button buttonSave;
    private RadioGroup rgroup;
    private RadioButton rbutton,radiobuttonMale;
    DatabaseReference myRef;
    DatabaseReference myRef2;
    FirebaseDatabase mFirebaseDatabase;


    private ProgressDialog progressDialog;
    static String key2;
    Button buttonHelp,btnAccept;
    Dialog dialog;
    TextView titleHelp, titleHelp2;
    ImageView closePop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("VisitPattani");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitle("");
        toolbar.setSubtitleTextColor(Color.MAGENTA);

        toolbar.setBackgroundColor(0xff509CEC);
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextLName = (EditText) findViewById(R.id.editTextLName);

        rgroup = (RadioGroup)findViewById(R.id.rgroup);
        radiobuttonMale = (RadioButton) findViewById(R.id.radiobuttonMale);
        rbutton = (RadioButton) findViewById(radiobuttonMale.getId());

        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextHosteller = (EditText) findViewById(R.id.editTextHosteller);
        editTextCostter = (EditText) findViewById(R.id.editTextCostter);
        editTextTTR = (EditText) findViewById(R.id.editTextTTR);

        spinnerVehicle = (Spinner) findViewById(R.id.spinnerVehicle);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Vehicle, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVehicle.setAdapter(adapter);
        spinnerVehicle.setOnItemSelectedListener(this);

        spinnerNation = (Spinner) findViewById(R.id.spinnerNation);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Nation, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNation.setAdapter(adapter2);
        spinnerNation.setOnItemSelectedListener(this);

        buttonSave = (Button) findViewById(R.id.buttonSave);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("ยินดีต้อนรับคุณ "+user.getEmail());

        buttonSave.setOnClickListener(this);
    }

    public void rbclick(View view)
    {
        int selectid = rgroup.getCheckedRadioButtonId();
        rbutton = (RadioButton) findViewById(selectid);
    }
    private  void saveUser(){

        String Firstname = editTextName.getText().toString().trim();
        String Lastname = editTextLName.getText().toString().trim();
        String Gender = rbutton.getText().toString().trim();
        String Age = editTextAge.getText().toString().trim();
        String Nation = spinnerNation.getSelectedItem().toString().trim();
        String Address = editTextAddress.getText().toString().trim();
        String Hostel = editTextHosteller.getText().toString().trim();
        String CostTravel = editTextCostter.getText().toString().trim();
        String TimeTravel = editTextTTR.getText().toString().trim();
        String Vehicle = spinnerVehicle.getSelectedItem().toString().trim();
        String Email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        UserInformation userInformation = new UserInformation(Firstname, Lastname, Gender, Age, Nation, Address, Hostel, CostTravel, TimeTravel, Vehicle, Email);

        if (userInformation.Firstname.isEmpty()){
            Toast.makeText(this, "กรุณากรอก ชื่อ",Toast.LENGTH_SHORT).show();
        }
        else if (userInformation.Lastname.isEmpty()){
            Toast.makeText(this, "กรุณากรอก นามสกุล",Toast.LENGTH_SHORT).show();
        }
        else if (userInformation.Age.isEmpty()){
            Toast.makeText(this, "กรุณากรอก อายุ",Toast.LENGTH_SHORT).show();
        }
        else if (userInformation.Address.isEmpty()){
            Toast.makeText(this, "กรุณากรอก ที่อยู่",Toast.LENGTH_SHORT).show();
        }
        else if (userInformation.Hostel.isEmpty()){
            Toast.makeText(this, "กรุณากรอก สถานที่พัก",Toast.LENGTH_SHORT).show();
        }
        else if (userInformation.CostTravel.isEmpty()){
            Toast.makeText(this, "กรุณากรอก งบประมาณท่องเที่ยว",Toast.LENGTH_SHORT).show();
        }
        else if (userInformation.TimeTravel.isEmpty()){
            Toast.makeText(this, "กรุณากรอก ระยะเวลาการท่องเที่ยว",Toast.LENGTH_SHORT).show();
        }
        else {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            databaseReference.child("UserAccount").child(user.getUid()).setValue(userInformation);

            Intent goMain =new Intent(ProfileActivity.this,MainActivity.class);
            goMain.putExtra("Email",Email);
            startActivity(goMain);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef2 = database.getReference("UserAccount");

            String key =myRef2.push().getKey();
            HashMap<String, Object> postValues = new HashMap<>();
            postValues.put("username",Email);


            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put(key, postValues);


            myRef2.updateChildren(childUpdates);
           // Toast.makeText(this, key, Toast.LENGTH_SHORT).show();
            mFirebaseDatabase = FirebaseDatabase.getInstance();

            myRef = mFirebaseDatabase.getReference("UserAccount");


            myRef.orderByChild("Email").equalTo(Email).addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot child: dataSnapshot.getChildren()) {
                        Log.d("User key", child.getKey());
                        key2 = child.getKey();
                        Log.d("User ref", child.getRef().toString());
                        Log.d("User val", child.getValue().toString());
                    }
                    //Toast.makeText(ProfileActivity.this,key2,Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                }
            });
        }
    }

    public void  onClick(View view){
        if (view == buttonSave){
            saveUser();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
            Intent goSet = new Intent(ProfileActivity.this,Setting.class);
            startActivity(goSet);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}


