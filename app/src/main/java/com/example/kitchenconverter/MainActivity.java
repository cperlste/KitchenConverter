package com.example.kitchenconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toolbar;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {
    private Spinner mSpinnerFraction, mSpinnerMeasurement, mSpinnerMeasurement2;
    private Double mFrom, mTo;
    private EditText wholeNumber;
    private Measurement mMeasurement;
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

    private void setUpSpinner3() {
        //get the spinner from the xml.
        mSpinnerMeasurement2 = findViewById(R.id.spinnerMeasurement2);
//create a list of items for the spinner.
        String[] measurements = new String[]{"cup", "teaspoons", "tablespoons", "ounces"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, measurements);
//set the spinners adapter to the previously created one.
        mSpinnerMeasurement2.setAdapter(adapter);
        mSpinnerMeasurement2.setOnItemSelectedListener(this);
    }

    private void calcResults(View view) {

    }


    private void initializeFields() {
        mMeasurement = new Measurement();
        formatter = new DecimalFormat("00.00")
    }

    private void setUpSpinner1() {
        //get the spinner from the xml.
        mSpinnerFraction = findViewById(R.id.spinnerFraction);

//create a list of items for the spinner.
        String[] fractions = new String[]{"3/4", "2/3", "1/2", "1/3", "1/4", "1/8", "1/16"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, fractions);
//set the spinners adapter to the previously created one.
        mSpinnerFraction.setAdapter(adapter);
        mSpinnerFraction.setOnItemSelectedListener(this);

    }

    private void setUpSpinner2() {
        //get the spinner from the xml.
        mSpinnerMeasurement = findViewById(R.id.spinnerMeasurement);
//create a list of items for the spinner.
        String[] measurements = new String[]{"cup", "teaspoons", "tablespoons", "ounces"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, measurements);
//set the spinners adapter to the previously created one.
        mSpinnerMeasurement.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (view) {
            case mSpinnerFraction:
                String strFraction = (String) adapterView.getItemAtPosition(i);
                double decimal = convertToDecimal(strFraction);
                mFrom= Integer.parseInt(wholeNumber.getText().toString())+decimal;
                break;
            case mSpinnerMeasurement:
                String measurement
        }
    }

    private double convertToDecimal(String strFraction) {
        double quotient = strFraction.charAt(0) / strFraction.charAt(2);
        return quotient;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
