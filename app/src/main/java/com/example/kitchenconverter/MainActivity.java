package com.example.kitchenconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {
    private Spinner mSpinnerFraction, mSpinnerMeasurement, mSpinnerMeasurement2;
    private double mFrom, mTo;
    private EditText wholeNumber;
    private Measurement mMeasurement;
    private String typeFrom, typeTo;
    private TextView mTVresults;
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
        wholeNumber = findViewById(R.id.enter_measure);
        mTVresults = findViewById(R.id.converted_output);
        formatter = new DecimalFormat("00.00");
    }

    private void setUpSpinner1() {
        mSpinnerFraction = findViewById(R.id.spinner_fraction);
        String[] fractions = new String[]{"Select", "none", "3/4", "2/3", "1/2", "1/3", "1/4", "1/8", "1/16"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, fractions);
        mSpinnerFraction.setAdapter(adapter);
    }

    private void setUpSpinner2() {
        mSpinnerMeasurement = findViewById(R.id.spinner_measurement);
        String[] measurements = new String[]{"Select", "cups", "tsp", "tbsp", "oz"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, measurements);
        mSpinnerMeasurement.setAdapter(adapter);
    }

    private void setUpSpinner3() {
        mSpinnerMeasurement2 = findViewById(R.id.spinner_measurement2);
        String[] measurements = new String[]{"Select", "cups", "tsp", "tbsp", "oz"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, measurements);
        mSpinnerMeasurement2.setAdapter(adapter);
    }

    public void calcResults(View view) {
        double decimal;
        double numDouble;
        String typeFrom = mSpinnerMeasurement.getSelectedItem().toString();
        String typeTo = mSpinnerMeasurement2.getSelectedItem().toString();
        String strFraction = mSpinnerFraction.getSelectedItem().toString();
        if (strFraction.equalsIgnoreCase("none")) {
            decimal = 0.0;
        } else {
            decimal = convertToDecimal(strFraction);
        }
        String strWholeNum = wholeNumber.getText().toString();
        if (strWholeNum.matches("")) {
            numDouble=0.0;
        } else {
            numDouble = Double.parseDouble(strWholeNum);
        }
        double mFrom = numDouble + decimal;
        if (mFrom == 0.0) {
            Snackbar.make(view, "The measurement cannot be empty.", Snackbar.LENGTH_LONG).show();
        } else {
            //cup to oz
            if (typeFrom.equalsIgnoreCase("cups") && typeTo.equalsIgnoreCase("oz")) {
                mTo = mMeasurement.cupToOz(mFrom);
            }
            //oz to cup
            else if (typeFrom.equalsIgnoreCase("oz") && typeTo.equalsIgnoreCase("cups")) {
                mTo = mMeasurement.ozToCup(mFrom);
            }
            //cup to tbsp
            else if (typeFrom.equalsIgnoreCase("cups") && typeTo.equalsIgnoreCase("tbsp")) {
                mTo = mMeasurement.cupToTbsp(mFrom);
            }
            //cup to tsp
            else if (typeFrom.equalsIgnoreCase("cups") && typeTo.equalsIgnoreCase("tsp")) {
                mTo = mMeasurement.cupToTsp(mFrom);
            }
            //oz to tbsp
            else if (typeFrom.equalsIgnoreCase("oz") && typeTo.equalsIgnoreCase("tbsp")) {
                mTo = mMeasurement.ozToTbsp(mFrom);
            }
            //oz to tsp
            else if (typeFrom.equalsIgnoreCase("oz") && typeTo.equalsIgnoreCase("tsp")) {
                mTo = mMeasurement.ozToTsp(mFrom);
            }
            //tbsp to cup
            else if (typeFrom.equalsIgnoreCase("tbsp") && typeTo.equalsIgnoreCase("cups")) {
                mTo = mMeasurement.tbspToCup(mFrom);
            }
            //tsp to cup
            else if (typeFrom.equalsIgnoreCase("tsp") && typeTo.equalsIgnoreCase("cups")) {
                mTo = mMeasurement.tspToCup(mFrom);
            }
            //tbsp to oz
            else if (typeFrom.equalsIgnoreCase("tbsp") && typeTo.equalsIgnoreCase("oz")) {
                mTo = mMeasurement.tbspToOz(mFrom);
            }
            //tsp to oz
            else if (typeFrom.equalsIgnoreCase("tsp") && typeTo.equalsIgnoreCase("oz")) {
                mTo = mMeasurement.tspToOz(mFrom);
            }
            //tsp to tbsp
            else if (typeFrom.equalsIgnoreCase("tsp") && typeTo.equalsIgnoreCase("tbsp")) {
                mTo = mMeasurement.tspToTbsp(mFrom);

            }
            //tbsp to tsp
            else if (typeFrom.equalsIgnoreCase("tbsp") && typeTo.equalsIgnoreCase("tsp")) {
                mTo = mMeasurement.tbspToTsp(mFrom);
            }
            String msg = formatter.format(mFrom) + " " + typeFrom + " is " + formatter.format(mTo) + " " + typeTo + ".";
            mTVresults.setText(msg);
        }
    }
    private double convertToDecimal(String strFraction) {
        int numerator = Character.getNumericValue(strFraction.charAt(0));
        int denominator = Character.getNumericValue(strFraction.charAt(2));
        return (double) numerator / denominator;
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
        mTVresults.setText("");
        mFrom = 0.0;
        mTo = 0.0;
        wholeNumber.setText("");
        setUpSpinner1();
        setUpSpinner2();
        setUpSpinner3();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            Utils.showInfoDialog(this, R.string.about, R.string.about_body);
        } else if (item.getItemId() == R.id.action_chart) {
            Intent intent = new Intent(this, ChartActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }
}
