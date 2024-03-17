package com.example.number_guess;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView tvLeft;
    private TextView tvRight;
    private TextView tvOperand;
    private EditText edAnswer;
    private Button btnCheck;
    private TextView tvAnswer;
    private Button btnSetQuiz;

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

        edAnswer = findViewById(R.id.edit_answer);

        btnCheck = findViewById(R.id.button);
        tvAnswer = findViewById(R.id.tv_right_answer);

        btnSetQuiz = findViewById(R.id.set_quiz);

        initializeButton();
        setQuiz();
    }

    public void initializeButton(){
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

                try{
                    int nPredicted = Integer.parseInt(edAnswer.getText().toString());
                    if(nPredicted == nAnswer) {
                        new SimpleConfirmDialog(MainActivity.this, "정답",
                                "정답입니다").createAndShow();
                    } else {
                        new SimpleConfirmDialog(MainActivity.this, "오답",
                                "다음 문제에 다시 도전해 보세요").createAndShow();
                    }
                } catch (NumberFormatException e){
                    new SimpleConfirmDialog(MainActivity.this, "Error",
                            "입력하신 내용이 숫자가 아닙니다").createAndShow();
                }
            }
        });

        btnSetQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuiz();
            }
        });

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
        edAnswer.setText("");
        tvAnswer.setText("");
    }

}