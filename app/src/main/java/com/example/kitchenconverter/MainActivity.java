package com.example.kitchenconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {
    private Spinner mSpinnerFraction, mSpinnerMeasurement, mSpinnerMeasurement2;
    private Double mFrom, mTo;
    private EditText wholeNumber;
    private Measurement mMeasurement;
    private String typeFrom, typeTo;
    private DecimalFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeFields();
        setUpSpinner1();
        setUpSpinner2();
        setUpSpinner3();
    }




    private void initializeFields() {
        mMeasurement = new Measurement();
        formatter = new DecimalFormat("00.00");
    }

    private void setUpSpinner1() {
        //get the spinner from the xml.
        mSpinnerFraction = findViewById(R.id.spinner_fraction);

//create a list of items for the spinner.
        String[] fractions = new String[]{"none", "3/4", "2/3", "1/2", "1/3", "1/4", "1/8", "1/16"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, fractions);
//set the spinners adapter to the previously created one.
        mSpinnerFraction.setAdapter(adapter);
        mSpinnerFraction.setOnItemSelectedListener(this);

    }

    private void setUpSpinner2() {
        //get the spinner from the xml.
        mSpinnerMeasurement = findViewById(R.id.spinner_measurement);
//create a list of items for the spinner.
        String[] measurements = new String[]{"cup", "teaspoons", "tablespoons", "ounces"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, measurements);
//set the spinners adapter to the previously created one.
        mSpinnerMeasurement.setAdapter(adapter);
    }

    private void setUpSpinner3() {
        //get the spinner from the xml.
        mSpinnerMeasurement2 = findViewById(R.id.spinner_measurement2);
//create a list of items for the spinner.
        String[] measurements = new String[]{"cup", "teaspoons", "tablespoons", "ounces"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, measurements);
//set the spinners adapter to the previously created one.
        mSpinnerMeasurement2.setAdapter(adapter);
        mSpinnerMeasurement2.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (view.equals(mSpinnerFraction)){
            if (i==0){
                mFrom=Double.parseDouble(wholeNumber.getText().toString());
            }
            else {
                String strFraction = (String) adapterView.getItemAtPosition(i);
                double decimal = convertToDecimal(strFraction);
                mFrom = Integer.parseInt(wholeNumber.getText().toString()) + decimal;
            }
        }
        else if(view.equals(mSpinnerMeasurement)){
            typeFrom= (String) adapterView.getItemAtPosition(i);
        }
        else if(view.equals(mSpinnerMeasurement2)){
            typeTo= (String) adapterView.getItemAtPosition(i);
        }
    }

    private double convertToDecimal(String strFraction) {
        double quotient = strFraction.charAt(0) / strFraction.charAt(2);
        return quotient;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        typeFrom="cup";
        typeTo="tbsp";

    }
    public void calcResults(View view){
        if (mFrom==0.0){
            Snackbar.make(view, "The measurement cannot be empty.", Snackbar.LENGTH_LONG).show();
        }
        else{
            ifAndElse();
            String msg= mFrom+ " "+typeFrom+ " is "+ mTo+" "+ typeTo+".";
            Intent intent;
            Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
                    //.setAction("More",{
                //intent = new Intent(getApplicationContext(), ResultsActivity.class);
                //intent.putExtra("NUM_FROM", mFrom);
                //intent.putExtra("NUM_TO", mTo);
            //}).show();
        }
    }

    private void ifAndElse() {
        //cup to oz
        if(typeFrom.equalsIgnoreCase("cup")&& typeTo.equalsIgnoreCase("oz")){
            mTo=mMeasurement.cupToOz(mFrom);
        }
        //oz to cup
        else if(typeFrom.equalsIgnoreCase("oz")&& typeTo.equalsIgnoreCase("cup")){
            mTo=mMeasurement.ozToCup(mFrom);
        }
        //cup to tbsp
        else if(typeFrom.equalsIgnoreCase("cup")&& typeTo.equalsIgnoreCase("tbsp")){
            mTo=mMeasurement.cupToTbsp(mFrom);
        }
        //cup to tsp
        else if(typeFrom.equalsIgnoreCase("cup")&& typeTo.equalsIgnoreCase("tsp")){
            mTo=mMeasurement.cupToTsp(mFrom);
        }
        //oz to tbsp
        else if(typeFrom.equalsIgnoreCase("oz")&& typeTo.equalsIgnoreCase("tbsp")){
            mTo=mMeasurement.ozToTbsp(mFrom);
        }
        //oz to tsp
        else if(typeFrom.equalsIgnoreCase("oz")&& typeTo.equalsIgnoreCase("tsp")){
            mTo=mMeasurement.ozToTsp(mFrom);
        }
        //tbsp to cup
        else if(typeFrom.equalsIgnoreCase("tbsp")&& typeTo.equalsIgnoreCase("cup")){
            mTo=mMeasurement.tbspToCup(mFrom);
        }
        //tsp to cup
        else if(typeFrom.equalsIgnoreCase("tsp")&& typeTo.equalsIgnoreCase("cup")){
            mTo=mMeasurement.tspToCup(mFrom);
        }
        //tbsp to oz
        else if(typeFrom.equalsIgnoreCase("tbsp")&& typeTo.equalsIgnoreCase("oz")){
            mTo=mMeasurement.tbspToOz(mFrom);
        }
        //tsp to oz
        else if(typeFrom.equalsIgnoreCase("tsp")&& typeTo.equalsIgnoreCase("oz")){
            mTo=mMeasurement.tspToOz(mFrom);
        }
        //tsp to tbsp
        else if (typeFrom.equalsIgnoreCase("tsp")&& typeTo.equalsIgnoreCase("tbsp")){
            mTo=mMeasurement.tspToTbsp(mFrom);

        }
        //tbsp to tsp
        else if(typeFrom.equalsIgnoreCase("tbsp")&& typeTo.equalsIgnoreCase("tsp")){
            mTo=mMeasurement.tbspToTsp(mFrom);
        }
    }
}
