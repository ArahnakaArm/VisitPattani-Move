package com.example.arahnaka.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class CheckIn extends AppCompatActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final String TAG = "CheckIn";
    private  RatingBar mRatingBar;
    private TextView mRatingScale;
    private EditText mFeedback;
    private Button mSendFeedback;
    private TextView mName;
    private static String Comment="comment";
    private  int rate;
    private static String UserKey;
    private static String Placesname="placename";
    private  static String UserTest="TestUser";
    static int cDay;
    static int cMonth;
    static int cSum;
    static int cYear;
    static int cMonthh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkin);
        final String deviceId = Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setNumUpdates(1);
        mLocationRequest.setMaxWaitTime(10000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        UserKey=MainActivity.getKey();
        //Toast.makeText(CheckIn.this,UserKey,Toast.LENGTH_LONG).show();
        //mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        //mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
      //  mFeedback = (EditText) findViewById(R.id.etFeedback);
        mSendFeedback = (Button) findViewById(R.id.btnSubmit);
        mName = (TextView)findViewById(R.id.textView2);
        SmileRating smileRating = (SmileRating) findViewById(R.id.smile_rating);
        Placesname = getIntent().getStringExtra("placename");

        Placesname = Placesname.replaceAll("[^0-9 a-z A-Z ก-ฮ  ะ-์ (-)]"," ");
        Calendar calendar = Calendar.getInstance();
        final     int thisY =calendar.get(Calendar.YEAR);
        final int thisM =calendar.get(Calendar.MONTH);
        final int thisD =calendar.get(Calendar.DAY_OF_MONTH);

        final String DATE = String.valueOf(thisD)+String.valueOf(thisM+1)+String.valueOf(thisY);
        mName.setText(Placesname);
        //Toast.makeText(this,Placesname,Toast.LENGTH_SHORT).show();
        //date

        Calendar calander = Calendar.getInstance();
        cDay = calander.get(Calendar.DAY_OF_MONTH);
        final String Day = Integer.toString(cDay);
        cMonth = calander.get(Calendar.MONTH);
        final String Month = Integer.toString(cMonth);
        cYear = calander.get(Calendar.YEAR);
        final String Year = Integer.toString(cYear);
        cSum=cDay+cMonth+cYear;
        final String Sum = Integer.toString(cSum);

        //Toast.makeText(Start.this,Day+"/"+Monthh+"/"+Year,Toast.LENGTH_LONG).show();





        //

        /*mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("Very bad");
                        rate=1;
                        break;
                    case 2:
                        mRatingScale.setText("Need some improvement");
                        rate=2;
                        break;
                    case 3:
                        mRatingScale.setText("Good");
                        rate=3;
                        break;
                    case 4:
                        mRatingScale.setText("Great");
                        rate=4;
                        break;
                    case 5:
                        mRatingScale.setText("Awesome. I love it");
                        rate=5;
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });*/

        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                switch (smiley) {
                    case SmileRating.BAD:

                        break;
                    case SmileRating.GOOD:

                        break;
                    case SmileRating.GREAT:

                        break;
                    case SmileRating.OKAY:

                        break;
                    case SmileRating.TERRIBLE:

                        break;
                }
            }
        });
        smileRating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {
                rate=level;
            }
        });
        smileRating.setNameForSmile(BaseRating.TERRIBLE,"แย่มาก");
        smileRating.setNameForSmile(BaseRating.BAD,"ปรับปรุง");
        smileRating.setNameForSmile(BaseRating.OKAY,"พอใช้");
        smileRating.setNameForSmile(BaseRating.GOOD,"ดี");
        smileRating.setNameForSmile(BaseRating.GREAT,"ดีมาก");
        mSendFeedback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Comment = mFeedback.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference DeviceidRef = database.getReference("UserAccount").child(deviceId);
                 DatabaseReference myRef = database.getReference("Places").child(Placesname);
                 DatabaseReference myComRef = database.getReference("Places").child(Placesname).child("Comment");
                 DatabaseReference Userdiary = database.getReference("UserAccount")/*.child(UserKey)*/;
                 DatabaseReference Location = database.getReference("Location(users)");
                //if (mFeedback.getText().toString().isEmpty()) {
                    //Userdiary.push().setValue("Name",Placesname);

                Toast.makeText(getApplicationContext(),Placesname,Toast.LENGTH_SHORT);
                    String key = Userdiary.push().getKey();
                    HashMap<String, Object> postValues = new HashMap<>();

                    postValues.put("title", Placesname);
                    postValues.put("Day", Sum);
                    postValues.put("Month", Month);
                    postValues.put("Year", Year);



                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put("/PlacesName/" + key, postValues);

                   // childUpdates.put("/user-messages/Jirawatee/" + key, postValues);

                    Userdiary.updateChildren(childUpdates);


                  //  myRef.push().setValue(Placesname);

                    String key1 = myRef.push().getKey();
                    HashMap<String, Object> postValues1 = new HashMap<>();
                    postValues1.put("Rate", rate);
                    postValues1.put("Date",DATE);
                    Map<String, Object> childUpdates1 = new HashMap<>();
                    childUpdates1.put( key1,postValues1);
                    // childUpdates.put("/user-messages/Jirawatee/" + key, postValues);

                    myRef.updateChildren(childUpdates1);


                // childUpdates.put("/user-messages/Jirawatee/" + key, postValues);



                    //Toast.makeText(CheckIn.this,Placesname, Toast.LENGTH_LONG).show();
                   // myComRef.push().setValue(Comment);
              /*  } else {
                    Userdiary.push().setValue(Placesname);


                    //jn,jhk
                    String key = Userdiary.push().getKey();
                    HashMap<String, Object> postValues = new HashMap<>();
                    postValues.put("username", "Jirawatee");
                    postValues.put("text", "Hello World!");


                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put("/messages/" + key, postValues);
                    childUpdates.put("/user-messages/Jirawatee/" + key, postValues);

                    Userdiary.updateChildren(childUpdates);
                    //ghjgjh

                    mFeedback.setText("");
                    /////////////////////////
                    mRatingBar.setRating(0);
                    /////////////////
                   // Toast.makeText(CheckIn.this,Placesname, Toast.LENGTH_LONG).show();
                    myRef.push().setValue(rate);
                    myComRef.push().setValue(Comment);
                }*/
                Intent goMap =new Intent(CheckIn.this,MapActivity.class);
                startActivity(goMap);

            }
        });
    }
    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onLocationChanged(final Location location) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference myRef = database.getReference("Location(users)");
        if (location != null) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    String strLocation =
                            DateFormat.getTimeInstance().format(location.getTime()) + "\n" +
                                    "Latitude=" + location.getLatitude() + "\n" +
                                    "Longitude=" + location.getLongitude();

                    String key1 = myRef.push().getKey();
                    HashMap<String, Object> postValues1 = new HashMap<>();
                    postValues1.put("latt", location.getLatitude());
                    postValues1.put("long", location.getLongitude());

                    Map<String, Object> childUpdates1 = new HashMap<>();
                    childUpdates1.put( key1,postValues1);
                    // childUpdates.put("/user-messages/Jirawatee/" + key, postValues);

                    myRef.updateChildren(childUpdates1);
                }
            }, 10000);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(CheckIn.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);

    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(CheckIn.this,
                            "permission was granted, :)",
                            Toast.LENGTH_LONG).show();

                    try{
                        LocationServices.FusedLocationApi.requestLocationUpdates(
                                mGoogleApiClient, mLocationRequest, this);
                    }catch(SecurityException e){
                        Toast.makeText(CheckIn.this,
                                "SecurityException:\n" + e.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(CheckIn.this,
                            "permission denied, ...:(",
                            Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(CheckIn.this,
                "onConnectionFailed: \n" + connectionResult.toString(),
                Toast.LENGTH_LONG).show();
    }
}
