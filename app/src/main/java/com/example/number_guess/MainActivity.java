package com.example.number_guess;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tvLeft;
    TextView tvRight;
    TextView tvOperand;
    Button btnCheck;
    TextView tvAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvLeft = findViewById(R.id.tv_left_number);
        tvRight = findViewById(R.id.tv_right_number);
        tvOperand = findViewById(R.id.tv_operand);

        btnCheck = findViewById(R.id.button);
        tvAnswer = findViewById(R.id.tv_right_answer);


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strAnswer = "";
                int left = Integer.parseInt((String) tvLeft.getText());
                int right = Integer.parseInt((String) tvRight.getText());
                int nAnswer = 0;

                if(tvOperand.getText().equals("+")){
                    nAnswer = left + right;
                    strAnswer = tvLeft.getText() + " + " + tvRight.getText() + " = " + nAnswer;
                    tvAnswer.setText(strAnswer);
                } else {
                    nAnswer = left - right;
                    strAnswer = tvLeft.getText() + " - " + tvRight.getText() + " = " + nAnswer;
                    tvAnswer.setText(strAnswer);
                }
            }
        });

        setQuiz();
    }

    private void setQuiz() {
        Random random = new Random();

        String newNumber = "" + random.nextInt(999);
        tvLeft.setText(newNumber);
        newNumber = "" + random.nextInt(999);
        tvRight.setText(newNumber);
        int operand = random.nextInt(2);
        switch(operand){
            case 0 : tvOperand.setText("+"); break;
            case 1 : tvOperand.setText("-"); break;
        }
    }
}