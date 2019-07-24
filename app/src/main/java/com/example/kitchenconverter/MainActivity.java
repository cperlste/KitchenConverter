package com.example.kitchenconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
    private TextView mTVresults;

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
        wholeNumber=findViewById(R.id.enter_measure);
        mTVresults=findViewById(R.id.tv_results);
    }

    private void setUpSpinner1() {
        mSpinnerFraction = findViewById(R.id.spinner_fraction);
        String[] fractions = new String[]{"Select Fraction", "none", "3/4", "2/3", "1/2", "1/3", "1/4", "1/8", "1/16"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, fractions);
        mSpinnerFraction.setAdapter(adapter);
    }

    private void setUpSpinner2() {
        mSpinnerMeasurement = findViewById(R.id.spinner_measurement);
        String[] measurements = new String[]{"Select Measurement", "cups", "teaspoons", "tablespoons", "ounces"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, measurements);
        mSpinnerMeasurement.setAdapter(adapter);
    }

    private void setUpSpinner3() {
        mSpinnerMeasurement2 = findViewById(R.id.spinner_measurement2);
        String[] measurements = new String[]{"Select Measurement", "cups", "teaspoons", "tablespoons", "ounces"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, measurements);
        mSpinnerMeasurement2.setAdapter(adapter);
    }


    private double convertToDecimal(String strFraction) {
        int numerator = Character.getNumericValue(strFraction.charAt(0));
        int denominator = Character.getNumericValue(strFraction.charAt(2));
        return (double) numerator / denominator;
    }

    public void calcResults(View view) {
        double decimal;
        String typeFrom = mSpinnerMeasurement.getSelectedItem().toString();
        String typeTo = mSpinnerMeasurement2.getSelectedItem().toString();
        String strFraction = mSpinnerFraction.getSelectedItem().toString();
        if(strFraction.equalsIgnoreCase("none")){
            decimal=0.0;
        }
        else {
            decimal = convertToDecimal(strFraction);
        }
        String strWholeNum = wholeNumber.getText().toString();
        double numDouble = Double.parseDouble(strWholeNum);
        double mFrom = numDouble + decimal;
        if (mFrom == 0.0) {
            Snackbar.make(view, "The measurement cannot be empty.", Snackbar.LENGTH_LONG).show();
        } else {
            //cup to oz
            if (typeFrom.equalsIgnoreCase("cups") && typeTo.equalsIgnoreCase("ounces")) {
                mTo = mMeasurement.cupToOz(mFrom);
            }
            //oz to cup
            else if (typeFrom.equalsIgnoreCase("ounces") && typeTo.equalsIgnoreCase("cups")) {
                mTo = mMeasurement.ozToCup(mFrom);
            }
            //cup to tbsp
            else if (typeFrom.equalsIgnoreCase("cups") && typeTo.equalsIgnoreCase("tablespoons")) {
                mTo = mMeasurement.cupToTbsp(mFrom);
            }
            //cup to tsp
            else if (typeFrom.equalsIgnoreCase("cups") && typeTo.equalsIgnoreCase("teaspoons")) {
                mTo = mMeasurement.cupToTsp(mFrom);
            }
            //oz to tbsp
            else if (typeFrom.equalsIgnoreCase("ounces") && typeTo.equalsIgnoreCase("tablespoons")) {
                mTo = mMeasurement.ozToTbsp(mFrom);
            }
            //oz to tsp
            else if (typeFrom.equalsIgnoreCase("ounces") && typeTo.equalsIgnoreCase("teaspoons")) {
                mTo = mMeasurement.ozToTsp(mFrom);
            }
            //tbsp to cup
            else if (typeFrom.equalsIgnoreCase("tablespoons") && typeTo.equalsIgnoreCase("cups")) {
                mTo = mMeasurement.tbspToCup(mFrom);
            }
            //tsp to cup
            else if (typeFrom.equalsIgnoreCase("teaspoons") && typeTo.equalsIgnoreCase("cups")) {
                mTo = mMeasurement.tspToCup(mFrom);
            }
            //tbsp to oz
            else if (typeFrom.equalsIgnoreCase("tablespoons") && typeTo.equalsIgnoreCase("ounces")) {
                mTo = mMeasurement.tbspToOz(mFrom);
            }
            //tsp to oz
            else if (typeFrom.equalsIgnoreCase("teaspoons") && typeTo.equalsIgnoreCase("ounces")) {
                mTo = mMeasurement.tspToOz(mFrom);
            }
            //tsp to tbsp
            else if (typeFrom.equalsIgnoreCase("teaspoons") && typeTo.equalsIgnoreCase("tablespoons")) {
                mTo = mMeasurement.tspToTbsp(mFrom);

            }
            //tbsp to tsp
            else if (typeFrom.equalsIgnoreCase("tablespoons") && typeTo.equalsIgnoreCase("teaspoons")) {
                mTo = mMeasurement.tbspToTsp(mFrom);
            }
            String msg = mFrom + " " + typeFrom + " is " + mTo + " " + typeTo + ".";
            mTVresults.setText(msg);
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
    public void resetAll(View view) {
        mTVresults.setText(" ");
        mFrom=0.0;
        mTo=0.0;
        wholeNumber.setText(" ");
        setUpSpinner1();
        setUpSpinner2();
        setUpSpinner3();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_about){
            Utils.showInfoDialog(getApplicationContext(),R.string.about, R.string.about_body);
        }
        return super.onOptionsItemSelected(item);

    }
}
