package com.example.practice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practice.databinding.ActivityCalculatorBinding;

public class SubActivity extends AppCompatActivity {

    boolean isFirstInput = true;
    ActivityCalculatorBinding activityCalculatorBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCalculatorBinding = ActivityCalculatorBinding.inflate(getLayoutInflater());
        setContentView(activityCalculatorBinding.getRoot());
    }

    public void numButtonClick(View view){
        String getButtonText = view.getTag().toString();
        if(isFirstInput) {
            activityCalculatorBinding.resultTextView.setText(getButtonText);
            activityCalculatorBinding.mathTextView.setText(getButtonText);
            isFirstInput = false;
        }else{
            activityCalculatorBinding.resultTextView.append(getButtonText);
            activityCalculatorBinding.mathTextView.append(getButtonText);
        }
    }



}
