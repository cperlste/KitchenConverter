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
        mSpinnerFraction = findViewById(R.id.spinner_fraction);
        String[] fractions = new String[]{"none", "3/4", "2/3", "1/2", "1/3", "1/4", "1/8", "1/16"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, fractions);
        mSpinnerFraction.setAdapter(adapter);
        mSpinnerFraction.setOnItemSelectedListener(this);

    }

    private void setUpSpinner2() {
        mSpinnerMeasurement = findViewById(R.id.spinner_measurement);
        String[] measurements = new String[]{"cup", "teaspoons", "tablespoons", "ounces"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, measurements);
        mSpinnerMeasurement.setAdapter(adapter);
    }

    private void setUpSpinner3() {
        mSpinnerMeasurement2 = findViewById(R.id.spinner_measurement2);
        String[] measurements = new String[]{"cup", "teaspoons", "tablespoons", "ounces"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, measurements);
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
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putDouble("AMT_FROM",mFrom);
        outState.putDouble("AMT_TO", mTo);
        outState.putString("TYPE_FROM", typeFrom);
        outState.putString("TYPE_TO", typeTo);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        mFrom=savedInstanceState.getDouble("AMT_FROM");
        mTo=savedInstanceState.getDouble("AMT_TO");
        typeFrom=savedInstanceState.getString("TYPE_FROM");
        typeTo=savedInstanceState.getString("TYPE_TO");

    }

}
