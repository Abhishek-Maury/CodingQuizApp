package com.abhishekmaurya.codingquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuestionActivity8 extends AppCompatActivity {

    int flag=0;
    int marks=0;
    public static int correct=0;
    int wrong =0;
    String[] questions={  "  Which of the following is the correct syntax to declare a variable in C?",
            "Which function is used to print output in C?",
            "What is the correct file extension for C programs?",
            "Which data type is used to store a single character in C?",
            " What is the size of an int data type (on most 32-bit systems)?"

    };
    String[] options={
            "int x;","x int;"," declare int x;","var x",
            "print()","output()","echo()","printf()",
            ".cpp",".java",".c",".cs",
            "int","char","float","string",
            "2 bytes","4 bytes","8 bytes","1 bytes"
    };
    String[] answers={
            "int x;",
            "printf()",
            ".c",
            "char",
            "4 bytes"
    };
    TextView quitBtn,dispNo,score,question;
    Button next;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question8);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        quitBtn=findViewById(R.id.quitBtn);
        question=findViewById(R.id.question);
        score=findViewById(R.id.score);
        dispNo=findViewById(R.id.disNo);
        next=findViewById(R.id.nextBtn);
        radio_g=findViewById(R.id.answerGroup);
        rb1=findViewById(R.id.radioBtn1);
        rb2=findViewById(R.id.radioBtn2);
        rb3=findViewById(R.id.radioBtn3);
        rb4=findViewById(R.id.radioBtn4);

        question.setText(questions[flag]);
        rb1.setText(options[0]);
        rb2.setText(options[1]);
        rb3.setText(options[2]);
        rb4.setText(options[3]);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radio_g.getCheckedRadioButtonId()==-1){
                    Toast.makeText(QuestionActivity8.this, "Please select an option", Toast.LENGTH_SHORT).show();
                }
                RadioButton uanswer=findViewById(radio_g.getCheckedRadioButtonId());
                String ansText=uanswer.getText().toString();
                if (ansText.equals(answers[flag])){
                    correct++;
                    Toast.makeText(QuestionActivity8.this, "Hurray!! it was correct", Toast.LENGTH_SHORT).show();
                }else{
                    wrong++;
                    Toast.makeText(QuestionActivity8.this, "Ohh!! it was incorrect", Toast.LENGTH_SHORT).show();
                }
                flag++;
                if (score!=null){
                    score.setText(""+correct);
                    if (flag<questions.length){
                        question.setText(questions[flag]);
                        rb1.setText(options[flag*4]);
                        rb2.setText(options[flag*4 + 1]);
                        rb3.setText(options[flag*4 + 2]);
                        rb4.setText(options[flag*4 + 3]);

                        dispNo.setText((flag+ 1)+ "/"+questions.length);
                    }else{
                        marks=correct;
                        Intent intent=new Intent(QuestionActivity8.this,ResultActivity.class);
                        intent.putExtra("attempted",flag);
                        intent.putExtra("correct",correct);
                        intent.putExtra("wrong",wrong);
                        startActivity(intent);
                        finish();
                    }
                    radio_g.clearCheck();
                }


            }
        });

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QuestionActivity8.this,ResultActivity.class);
                intent.putExtra("attempted",flag);
                intent.putExtra("correct",correct);
                intent.putExtra("wrong",wrong);
                startActivity(intent);
                finish();
            }
        });

    }
}