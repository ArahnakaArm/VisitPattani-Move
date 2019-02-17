package com.example.arahnaka.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class spiner extends AppCompatActivity {
    Spinner sp;
    Spinner sp2;
    Spinner sp3;
    String time[]={"กรุณาเลือกระยะเวลา","3 ชั่วโมง","ครึ่งวัน","ทั้งวัน"};
    String budget[]={"กรุณาเลือกงบประมาณ","500","1500","2500+"};
    String vechicle[]={"กรุณาเลือกยานพาหนะ","รถจักยานยนต์","รถยนต์"};
    ArrayAdapter <String> adapter;
    ArrayAdapter <String> adapter2;
    ArrayAdapter <String> adapter3;
    String timeRecord="";
    String budgetRecord="";
    String vechicleRecord="";
    String partyRecord="";
    TextView data;
    TextView data2;
    TextView data3;
    ImageView iconn;
    RadioGroup radioGroup;
    RadioButton radioButton;
    int timeState = 0;
    DateFormat dfHour = new SimpleDateFormat("HH");
    DateFormat dfMin = new SimpleDateFormat("mm");
  //  DateFormat dfHM = new SimpleDateFormat("HH:mm");



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinerlay);
        //radioGroup = findViewById(R.id.radiog);

        iconn = (ImageView)findViewById(R.id.pointicon);
        /// Spinner 1
        sp = (Spinner)findViewById(R.id.spinner);
        List<String> timelist=new ArrayList<>(Arrays.asList(time));
        adapter=new ArrayAdapter<String >(this,R.layout.spinner_item,timelist){
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
        //data =(TextView)findViewById(R.id.datatext);


        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 1:
                        timeRecord="3 ชั่วโมง";
                        break;
                    case 2:
                        timeRecord="ครึ่งวัน";
                        break;
                    case 3:
                        timeRecord="ทั้งวัน";
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

        sp2 = (Spinner)findViewById(R.id.spinner2);
        List<String> budgetlist=new ArrayList<>(Arrays.asList(budget));
        adapter2=new ArrayAdapter<String >(this,R.layout.spinner_item,budgetlist){
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
                        budgetRecord="500";
                        break;
                    case 2:
                        budgetRecord="1500";
                        break;
                    case 3:
                        budgetRecord="2500+";
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
      /*  sp3 = (Spinner)findViewById(R.id.spinner3);
        List<String> vechiclelist=new ArrayList<>(Arrays.asList(vechicle));
        adapter3=new ArrayAdapter<String >(this,R.layout.spinner_item,vechiclelist){
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
                        vechicleRecord="รถยนจักยานยนต์";
                        break;
                    case 2:
                        vechicleRecord="รถยนต์";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                timeRecord="";

            }
        });
        ///
*/

    }

    public void display(View view){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
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

    }
    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);



    }
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
