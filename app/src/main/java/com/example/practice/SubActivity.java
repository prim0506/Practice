package com.example.practice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practice.databinding.ActivityCalculatorBinding;

public class SubActivity extends AppCompatActivity {

    boolean isFirstInput = true;
    boolean isOperatorClick = false;
    double resultNumber = 0;
    double inputNumber = 0;
    int intResultNumber= 0;
    String strResultNumber = "";
    String operator = "=";
    String lastOperator = "＋";
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
            isFirstInput = false;
            if(operator.equals("=")){
                activityCalculatorBinding.mathTextView.setText(null);
                isOperatorClick = false;
            }
        }else{
            if(activityCalculatorBinding.resultTextView.getText().toString().equals("0")){
                Toast.makeText(this, "0으로 시작되는 숫자는 없습니다.", Toast.LENGTH_SHORT).show();
                isFirstInput = true;
            }else {
                activityCalculatorBinding.resultTextView.append(getButtonText);
            }
        }
    }

    public void allClearButtonClick (View view){
        activityCalculatorBinding.resultTextView.setText("0");
        activityCalculatorBinding.mathTextView.setText("");
        resultNumber = 0;
        operator = "＋";
        isFirstInput = true;
        isOperatorClick = false;
    }

    public void pointButtonClick (View view){
        if(isFirstInput){
            activityCalculatorBinding.resultTextView.setText("0" + view.getTag().toString());
            isFirstInput = false;
        }else {
            if(activityCalculatorBinding.resultTextView.getText().toString().contains(".")){
                Toast.makeText(this, "이미 소수점이 존재합니다.", Toast.LENGTH_SHORT).show();
            } else {
                activityCalculatorBinding.resultTextView.append(view.getTag().toString());
            }
        }
    }

    public void operatorClick (View view){
        isOperatorClick = true;
        lastOperator = view.getTag().toString();
        if(isFirstInput){
            if(operator.equals("=")){
                operator = view.getTag().toString();
                resultNumber = Double.parseDouble(activityCalculatorBinding.resultTextView.getText().toString());
                activityCalculatorBinding.mathTextView.setText(resultNumber + " " + operator + " ");
            }else {
                operator = view.getTag().toString();
                String getMathText = activityCalculatorBinding.mathTextView.getText().toString();
                String subString = getMathText.substring(0,getMathText.length() - 2);
                activityCalculatorBinding.mathTextView.setText(subString);
                activityCalculatorBinding.mathTextView.append(operator + " ");
            }

        } else {
            inputNumber = Double.parseDouble(activityCalculatorBinding.resultTextView.getText().toString());

            resultNumber = calculator(resultNumber, inputNumber, operator);

            replaceNum(resultNumber);
            activityCalculatorBinding.resultTextView.setText(strResultNumber);
            isFirstInput = true;
            operator = view.getTag().toString();
            activityCalculatorBinding.mathTextView.append(inputNumber + " " + operator + " ");
        }
    }

    private double calculator(double resultNumber, double inputNumber, String operator) {

        switch (operator){
            case "=":
                resultNumber = inputNumber;
                break;
            case "＋":
                resultNumber += inputNumber;
                break;
            case "－":
                resultNumber -= inputNumber;
                break;
            case "×":
                resultNumber *= inputNumber;
                break;
            case "÷":
                resultNumber /= inputNumber;
                break;
        }
        return resultNumber;
    }

    public void equalsButtonClick (View view){
        if(isFirstInput){
            if(isOperatorClick){
                activityCalculatorBinding.mathTextView.setText(resultNumber + " " + lastOperator + " " + inputNumber + " =");
                resultNumber = calculator(resultNumber, inputNumber, lastOperator);
                replaceNum(resultNumber);
                activityCalculatorBinding.resultTextView.setText(strResultNumber);
            }
        } else {
            inputNumber = Double.parseDouble(activityCalculatorBinding.resultTextView.getText().toString());

            resultNumber = calculator(resultNumber, inputNumber, operator);
            replaceNum(resultNumber);
            activityCalculatorBinding.resultTextView.setText(strResultNumber);
            isFirstInput = true;
            operator = view.getTag().toString();
            activityCalculatorBinding.mathTextView.append(inputNumber + " " + operator + " ");
        }
    }

    public void backspaceButtonClick(View view){
        if(!isFirstInput){
            String getResultText = activityCalculatorBinding.resultTextView.getText().toString();
            if(getResultText.length() > 1){
                String subString = getResultText.substring(0, getResultText.length() -1);
                activityCalculatorBinding.resultTextView.setText(subString);
            } else {
                activityCalculatorBinding.resultTextView.setText("0");
                isFirstInput = true;
            }
        }
    }

    public void replaceNum(double result){
        if(isNumeric(result)){
            strResultNumber = String.valueOf((int)result);
        }else {
            strResultNumber = String.valueOf((Math.round(result*10000)/10000.0));
        }
    }

    public boolean isNumeric(double result){
        return Math.ceil(result) == Math.floor(result);
    }
}
