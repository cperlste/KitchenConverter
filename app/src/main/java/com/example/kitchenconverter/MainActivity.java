package com.example.kitchenconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {
    private Spinner mSpinnerFraction, mSpinnerMeasurement, mSpinnerMeasurement2;
    private double mFrom, mTo;
    private EditText wholeNumber;
    private Measurement mMeasurement;
    private String typeFrom, typeTo;
    private DecimalFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("SETUP", "setting up fields");
        initializeFields();
        Log.d("SETUP", "setting up first spinner");
        setUpSpinner1();
        Log.d("SETUP", "setting up second spinner");
        setUpSpinner2();
        Log.d("SETUP", "setting up third spinner");
        setUpSpinner3();
    }

    private void initializeFields() {
        Log.d("SETUP", "creating measurement object");
        mMeasurement = new Measurement();
        formatter = new DecimalFormat("00.00");
    }

    private void setUpSpinner1() {
        mSpinnerFraction = findViewById(R.id.spinner_fraction);
        String[] fractions = new String[]{"none", "3/4", "2/3", "1/2", "1/3", "1/4", "1/8", "1/16"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, fractions);
        mSpinnerFraction.setAdapter(adapter);
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
    }


    private double convertToDecimal(String strFraction) {
        int numerator = strFraction.charAt(0);
        int denominator = strFraction.charAt(2);
        return (double) numerator / denominator;
    }

    public void calcResults(View view) {
        Log.d("CALC", "getting typeFrom from Spinner");
        String typeFrom = mSpinnerMeasurement.getSelectedItem().toString();
        Log.d("CALC", "type From is now "+typeFrom);
        Log.d("CALC", "getting typeTo from Spinner");
        String typeTo = mSpinnerMeasurement2.getSelectedItem().toString();
        Log.d("CALC", "typeTo is now "+typeTo);
        Log.d("CALC", "getting string fraction from spinner");
        String strFraction = mSpinnerFraction.getSelectedItem().toString();
        Log.d("CALC", "strFraction is now "+strFraction);
        Log.d("CALC", "converting string fraction to decimal. Calling convertToDecimal method");
        double decimal = convertToDecimal(strFraction);
        Log.d("CALC", "fraction decimal value "+decimal);
        Log.d("CALC", "reading the whole number as a string from wholeNumber edit text");
        String strWholeNum = wholeNumber.getText().toString();
        Log.d("CALC", "converting the string to a double");
        double numDouble = Double.parseDouble(strWholeNum);
        Log.d("CALC", "numDouble is now "+numDouble);
        Log.d("CALC", "adding numDouble and decimal");
        double mFrom = numDouble + decimal;
        Log.d("CALC", "mFrom is now"+mFrom);
        Log.d("CALC", "type to: " + typeTo + " typeFrom " + typeFrom + " mFrom " + mFrom + " mTo: " + mTo);
        if (mFrom == 0.0) {
            Snackbar.make(view, "The measurement cannot be empty.", Snackbar.LENGTH_LONG).show();
        } else {
            ifAndElse(mFrom);
            String msg = mFrom + " " + typeFrom + " is " + mTo + " " + typeTo + ".";
            //Intent intent;
            Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
            //.setAction("More",{
            //intent = new Intent(getApplicationContext(), ResultsActivity.class);
            //intent.putExtra("NUM_FROM", mFrom);
            //intent.putExtra("NUM_TO", mTo);
            //}).show();
        }
    }

    private void ifAndElse(double from) {
        //cup to oz
        if (typeFrom.equalsIgnoreCase("cup") && typeTo.equalsIgnoreCase("oz")) {
            mMeasurement.setCup(from);
            mTo = mMeasurement.cupToOz(from);
            mMeasurement.setOz(mTo);
        }
        //oz to cup
        else if (typeFrom.equalsIgnoreCase("oz") && typeTo.equalsIgnoreCase("cup")) {
            mTo = mMeasurement.ozToCup(from);
        }
        //cup to tbsp
        else if (typeFrom.equalsIgnoreCase("cup") && typeTo.equalsIgnoreCase("tbsp")) {
            mTo = mMeasurement.cupToTbsp(from);
        }
        //cup to tsp
        else if (typeFrom.equalsIgnoreCase("cup") && typeTo.equalsIgnoreCase("tsp")) {
            mTo = mMeasurement.cupToTsp(from);
        }
        //oz to tbsp
        else if (typeFrom.equalsIgnoreCase("oz") && typeTo.equalsIgnoreCase("tbsp")) {
            mTo = mMeasurement.ozToTbsp(from);
        }
        //oz to tsp
        else if (typeFrom.equalsIgnoreCase("oz") && typeTo.equalsIgnoreCase("tsp")) {
            mTo = mMeasurement.ozToTsp(from);
        }
        //tbsp to cup
        else if (typeFrom.equalsIgnoreCase("tbsp") && typeTo.equalsIgnoreCase("cup")) {
            mTo = mMeasurement.tbspToCup(from);
        }
        //tsp to cup
        else if (typeFrom.equalsIgnoreCase("tsp") && typeTo.equalsIgnoreCase("cup")) {
            mTo = mMeasurement.tspToCup(from);
        }
        //tbsp to oz
        else if (typeFrom.equalsIgnoreCase("tbsp") && typeTo.equalsIgnoreCase("oz")) {
            mTo = mMeasurement.tbspToOz(from);
        }
        //tsp to oz
        else if (typeFrom.equalsIgnoreCase("tsp") && typeTo.equalsIgnoreCase("oz")) {
            mTo = mMeasurement.tspToOz(from);
        }
        //tsp to tbsp
        else if (typeFrom.equalsIgnoreCase("tsp") && typeTo.equalsIgnoreCase("tbsp")) {
            mTo = mMeasurement.tspToTbsp(from);

        }
        //tbsp to tsp
        else if (typeFrom.equalsIgnoreCase("tbsp") && typeTo.equalsIgnoreCase("tsp")) {
            mTo = mMeasurement.tbspToTsp(from);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("AMT_FROM", mFrom);
        outState.putDouble("AMT_TO", mTo);
        outState.putString("TYPE_FROM", typeFrom);
        outState.putString("TYPE_TO", typeTo);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mFrom = savedInstanceState.getDouble("AMT_FROM");
        mTo = savedInstanceState.getDouble("AMT_TO");
        typeFrom = savedInstanceState.getString("TYPE_FROM");
        typeTo = savedInstanceState.getString("TYPE_TO");

    }

}
