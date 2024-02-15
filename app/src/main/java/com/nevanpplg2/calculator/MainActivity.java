package com.nevanpplg2.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText inputValue1;
    EditText inputValue2;
    Spinner inputMethod;
    Button listenSubmit;
    TextView resultOutput;

    double oldValue1;
    double oldValue2;
    double lastResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue1 = findViewById(R.id.input_one);
        inputValue2 = findViewById(R.id.input_two);
        inputMethod = findViewById(R.id.calc_method);
        listenSubmit = findViewById(R.id.submit_button);
        resultOutput = findViewById(R.id.text_result);

        listenSubmit.setOnClickListener(this::calculate);
    }

    private void calculate(View view) {
        boolean oldValue = false;

        if (inputValue1.getText().toString().length() == 0) {
            showSnack(view, "Input value 1 cannot be empty!");
            return;
        }

        if (inputValue2.getText().toString().length() == 0) {
            showSnack(view, "Input value 2 cannot be empty!");
            return;
        }

        double value1 = Double.parseDouble(inputValue1.getText().toString());
        double value2 = Double.parseDouble(inputValue2.getText().toString());
        String method = inputMethod.getSelectedItem().toString().toLowerCase().trim();

        if (value1 == oldValue1 && value2 == oldValue2) {
            oldValue = true;
            value1 = lastResult;
        }

        double result = 0F;
        switch (method) {
            case "addition":
                result = value1 + value2;
                break;
            case "subtraction":
                result = value1 - value2;
                break;
            case "multiplication":
                result = value1 * value2;
                break;
            case "division":
                result = value1 / value2;
                break;
        }

        String resString = Double.valueOf(result).toString().replaceAll("\\.?0*$", "");

        if (resString.length() > 7) {
            resultOutput.setTextSize(48);
        } else {
            resultOutput.setTextSize(56);
        }
        resultOutput.setText(resString);

        if (!oldValue) {
            oldValue1 = value1;
            oldValue2 = value2;
        }
        lastResult = result;
    }

    private void showSnack(View view, String message) {
        Snackbar.make(
                view,
                message, Snackbar.LENGTH_SHORT
        ).show();
    }


}