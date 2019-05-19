package com.example.barvolume;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String STATE_RESULT = "state_result";

    EditText editWidth, editHeight, editLength;
    Button btnCalculate, btnReset;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editWidth = (EditText) findViewById(R.id.editWidth);
        editHeight = (EditText) findViewById(R.id.editHeight);
        editLength = (EditText) findViewById(R.id.editLength);

        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnReset = (Button) findViewById(R.id.btnReset);
        textViewResult = (TextView) findViewById(R.id.textViewResult);

        btnCalculate.setOnClickListener(this);
        btnReset.setOnClickListener(this);

        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            textViewResult.setText(result);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, textViewResult.getText().toString());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCalculate) {
            String inputLength = editLength.getText().toString().trim();
            String inputWidth = editWidth.getText().toString().trim();
            String inputHeight = editHeight.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputLength)) {
                isEmptyFields = true;
                editLength.setError("Field tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyFields = true;
                editWidth.setError("Field tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true;
                editHeight.setError("Field tidak boleh kosong");
            }

            Double length = toDouble(inputLength);
            Double width = toDouble(inputWidth);
            Double height = toDouble(inputHeight);

            if (length == null) {
                isInvalidDouble = true;
                editLength.setError("Field ini harus berupa angka yang valid");
            }

            if (width == null) {
                isInvalidDouble = true;
                editWidth.setError("Field ini harus berupa angka yang valid");
            }

            if (height == null) {
                isInvalidDouble = true;
                editHeight.setError("Field ini harus berupa angka yang valid");
            }

            if (!isEmptyFields && !isInvalidDouble) {
                double volume = length * width * height;
                textViewResult.setText(String.valueOf(volume));
            }
        }

        if (v.getId() == R.id.btnReset) {
            reset();
        }
    }

    private Double toDouble(String string) {
        try {
            return Double.valueOf(string);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void reset() {
        editWidth.setText("");
        editHeight.setText("");
        editLength.setText("");
        textViewResult.setText("Hasil");
    }
}
