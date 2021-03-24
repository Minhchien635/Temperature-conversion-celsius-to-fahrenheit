package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> arrA;
    ListView listView;
    LinkedList<String> arr = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listproduct);
        arrA = new ArrayAdapter<>(this, R.layout.activity_listview, R.id.textView, arr);
        listView.setAdapter(arrA);
    }

    public void onChangeRadio(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radio_pirates:
                if (checked) {
                    setHintIpOp("F", "C");
                    break;
                }
            case R.id.radio_ninjas:
                if (checked) {
                    setHintIpOp("C", "F");
                    break;
                }
        }
    }

    public int onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        if (checked) {
            return 1;
        }
        return 0;
    }

    public void convertButton(View view) {
        String input = ((EditText) findViewById(R.id.plain_text_input)).getText().toString();
        if (input.length() != 0 && input.matches("[0-9]+[\\\\.]?[0-9]*")) {
            View radio = findViewById(R.id.radio_ninjas);
            float x = Float.parseFloat(((EditText) findViewById(R.id.plain_text_input)).getText().toString());
            setTextResult(radio, x);
            arrA.setNotifyOnChange(true);
            arrA.notifyDataSetChanged();
        }
    }

    public void setTextResult(View radio, float valIp) {
        if (onRadioButtonClicked(radio) == 1) {
            show(valIp, "C", "F");
        } else {
            show(valIp, "F", "C");
        }
    }

    public void setHintIpOp(String ip, String op) {
        TextView textViewip = findViewById(R.id.plain_text_input);
        TextView textViewop = findViewById(R.id.plain_text_output);
        textViewip.setHint(ip);
        textViewop.setHint(op);
    }

    public void show(float valIp, String typeIp, String typeOp) {
        TextView textView = findViewById(R.id.plain_text_output);
        textView.setText(convertType(typeIp, valIp) + "⁰" + typeOp);
        arr.addFirst(valIp + "⁰" + typeIp + " => " + convertType(typeIp, valIp) + "⁰" + typeOp);
    }

    public float convertType(String type, float valIp) {
        if (type.equals("C")) {
            return convertC(valIp);
        } else return convertF(valIp);
    }

    public float convertF(float F) {
        float C = (float) ((5.0 / 9.0) * (F - 32));
        return (float) (Math.round(C * 100.0) / 100.0);
    }

    public float convertC(float C) {
        float F = (float) ((C * 9.0) / 5.0 + 32);
        return (float) (Math.round(F * 100.0) / 100.0);
    }
}