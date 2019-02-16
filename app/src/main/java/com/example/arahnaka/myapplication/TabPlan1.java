package com.example.arahnaka.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TabPlan1 extends Fragment{
    Spinner sp;
    Spinner sp2;
    Spinner sp3;
    LinearLayout Li;
    String time[]={"กรุณาเลือกช่วงเวลา","ครึ่งวันเช้า","ครึ่งวันบ่าย","ทั้งวัน"};
    String style[]={"กรุณาเลือกสไตล์การท่องเที่ยว","ที่นิยม","ที่ใกล้เคียง","ที่ประหยัด"};
    String distance[]={"กรุณาเลือกกระยะทาง","ภายใน 5 กิโลเมตร","ภายใน 10 กิโลเมตร","ภานใน 20 กิโลเมตร"};
    ImageView downview;
    ArrayAdapter<String> adapter;
    ArrayAdapter <String> adapter2;
    ArrayAdapter <String> adapter3;
    String timeRecord="";
    String styleRecord="";
    String vechicleRecord="";
    String partyRecord="คนเดียว";
    String distanceRecord="";
    boolean stylecheck=false;
    TextView data;
    TextView data2;
    TextView data3;
    String testt = "asdasd°";
    // RadioGroup radioGroup;
    //RadioButton radioButton;
    Button btt;
    int timeState = 0;
    DateFormat dfHour = new SimpleDateFormat("HH");
    DateFormat dfMin = new SimpleDateFormat("mm");
    //  DateFormat dfHM = new SimpleDateFormat("HH:mm");


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.spinerlay, container, false);
        // radioGroup = rootView.findViewById(R.id.radioGroup);


        /// Spinner 1
        sp = (Spinner)rootView.findViewById(R.id.spinner);
        List<String> timelist=new ArrayList<>(Arrays.asList(time));
        adapter=new ArrayAdapter<String >(getContext(),R.layout.spinner_item,timelist){
            @Override
            public boolean isEnabled(int position) {
                if(position==0){
                    return false;
                }
                else{
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position,convertView,parent);
                TextView tv= (TextView)view;
                if(position==0){
                    tv.setTextColor(Color.GRAY);
                }
                else{
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        adapter.setDropDownViewResource(R.layout.spinner_item);
        sp.setAdapter(adapter);



        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 1:
                        timeRecord="halfday";
                        break;
                    case 2:
                        timeRecord="halfafter";
                        break;
                    case 3:
                        timeRecord="full";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                timeRecord="";

            }
        });
        ///

        /// Spinner 2

        sp2 = (Spinner)rootView.findViewById(R.id.spinner2);
        List<String> stylelist=new ArrayList<>(Arrays.asList(style));
        adapter2=new ArrayAdapter<String >(getContext(),R.layout.spinner_item,stylelist){
            @Override
            public boolean isEnabled(int position) {
                if(position==0){
                    return false;
                }
                else{
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position,convertView,parent);
                TextView tv= (TextView)view;

                if(position==0){
                    tv.setTextColor(Color.GRAY);
                }
                else{
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        adapter2.setDropDownViewResource(R.layout.spinner_item);
        sp2.setAdapter(adapter2);
        //data2 =(TextView)findViewById(R.id.datatext2);


        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1:
                        styleRecord="pop";
                        Li.setVisibility(View.INVISIBLE);
                        downview.setVisibility(View.INVISIBLE);
                        distanceRecord="";
                        stylecheck=false;
                        break;
                    case 2:
                        styleRecord="distance";
                        Li.setVisibility(View.VISIBLE);
                        downview.setVisibility(View.VISIBLE);
                        stylecheck=true;
                        break;
                    case 3:
                        styleRecord="cheap";
                        Li.setVisibility(View.INVISIBLE);
                        downview.setVisibility(View.INVISIBLE);
                        stylecheck=false;
                        distanceRecord="";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                timeRecord="";

            }
        });


        ////


        /// Spinner 1
        downview=(ImageView) rootView.findViewById(R.id.down2);
        Li=(LinearLayout)rootView.findViewById(R.id.li3) ;
        sp3 = (Spinner)rootView.findViewById(R.id.spinner3);
        final List<String> distancelist=new ArrayList<>(Arrays.asList(distance));
        adapter3=new ArrayAdapter<String >(getContext(),R.layout.spinner_item,distancelist){
            @Override
            public boolean isEnabled(int position) {
                if(position==0){
                    return false;
                }
                else{
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position,convertView,parent);
                TextView tv= (TextView)view;
                if(position==0){
                    tv.setTextColor(Color.GRAY);
                }
                else{
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        adapter3.setDropDownViewResource(R.layout.spinner_item);
        sp3.setAdapter(adapter3);
        //data3 =(TextView)findViewById(R.id.datatext3);


        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1:
                        distanceRecord="5";
                        break;
                    case 2:
                        distanceRecord="10";
                        break;
                    case 3:
                        distanceRecord="20";

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                timeRecord="";

            }
        });
/*
    RadioGroup radioGroup = (RadioGroup)rootView.findViewById(R.id.radiog);
    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
            switch (checkId){
                case R.id.radio_one:
                    partyRecord="คนเดียว";
                    break;
                case R.id.radio_two:
                    partyRecord="เป็นคู่";

                    break;
                case R.id.radio_three:
                    partyRecord="เป็นกลุ่ม";
                    break;
            }
        }
    });
*/

        btt =(Button)rootView.findViewById(R.id.btt);
        btt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           /* int radioId = radioGroup.getCheckedRadioButtonId();
            radioButton=view.findViewById(radioId);
            partyRecord=radioButton.getText().toString();*/

                //   Toast.makeText(getContext(),testt,Toast.LENGTH_SHORT);
                sp3.setVisibility(View.VISIBLE);
                int  currentHour = Integer.parseInt(dfHour.format(Calendar.getInstance().getTime()));
                int    currentMin = Integer.parseInt(dfMin.format(Calendar.getInstance().getTime()));

//    int currentTime=Integer.parseInt(dfHM.format(Calendar.getInstance().getTime()));
                int minRemain = timeCalculation(currentHour,currentMin)%100;
                int hourRemain = timeCalculation(currentHour,currentMin)/100;

                if(timeRecord==""||styleRecord==""||distanceRecord==""){
                    if(stylecheck==true){
                        //data.setText("กรุณาเลือก Option ให้ครบ");
                        Toast.makeText(getContext(), "กรุณาเลือก Option ให้ครบ", Toast.LENGTH_SHORT).show();
                    }
                    else if((timeRecord==""||styleRecord==""&&stylecheck == false)){
                        Toast.makeText(getContext(), "กรุณาเลือก Option ให้ครบ", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent goPlanningList = new Intent(getContext(),PlanningList.class);
                        goPlanningList.putExtra("information",timeRecord+styleRecord+distanceRecord);

                        startActivity(goPlanningList);
                    }
                }else {
                    //data.setText(timeRecord + "," + budgetRecord + "," + vechicleRecord+","+partyRecord+","+currentHour+":"+currentMin);
                    Intent goPlanningList = new Intent(getContext(),PlanningList.class);
                    goPlanningList.putExtra("information",timeRecord+styleRecord+distanceRecord);

                    startActivity(goPlanningList);
                }


            }
        });

/*
        public void checkButton(View view) {
            int radioId = radioGroup.getCheckedRadioButtonId();

            radioButton =rootView.findViewById(radioId);



        }
*/
        return rootView;
    }

   /* public void display(View view){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton=view.findViewById(radioId);
        partyRecord=radioButton.getText().toString();
        int  currentHour = Integer.parseInt(dfHour.format(Calendar.getInstance().getTime()));
        int    currentMin = Integer.parseInt(dfMin.format(Calendar.getInstance().getTime()));

//    int currentTime=Integer.parseInt(dfHM.format(Calendar.getInstance().getTime()));
        int minRemain = timeCalculation(currentHour,currentMin)%100;
        int hourRemain = timeCalculation(currentHour,currentMin)/100;

        if(timeRecord==""||budgetRecord==""||vechicleRecord==""){
            data.setText("กรุณาเลือก Option ให้ครบ");
        }else {
            data.setText(timeRecord + "," + budgetRecord + "," + vechicleRecord+","+partyRecord+","+currentHour+":"+currentMin);
        }

    }*/

    public int timeCalculation(int hour,int min){
        int hourBegin = 21;
        int minBegin = 0;
        int hourRemain=0;
        int minRemain=0;
        if(hour>7&&minBegin<min){
            minRemain=60-min;
            hourBegin--;
            hourRemain=hourBegin-hour;

        }
        return (hourRemain*100)+minRemain;

    }

}
