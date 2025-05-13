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

public class QuestionActivity6 extends AppCompatActivity {


    int flag=0;
    int marks=0;
    public static int correct=0;
    int wrong =0;
    String[] questions={  " Which SQL statement is used to retrieve data from a database?",
            "What does the WHERE clause do in a SQL query?",
            "Which SQL function is used to count the number of rows?",
            "Which keyword is used to sort the result-set in SQL?",
            " What is the default order of sorting using ORDER BY in SQL?"

    };
    String[] options={
            "GET","EXTRACT","SELECT","RETRIEVE",
            "It groups the data","It joins two tables","It filters records","It sorts the result",
            "SUM()","COUNT()","TOTAL()","ROWCOUNT()",
            "ORDER BY","SORT","GROUP BY","ARRANGE",
            " Descending","Random","Ascending","None"
    };
    String[] answers={
            "SELECT",
            "It filters records",
            "COUNT() ",
            "ORDER BY",
            "Ascending "
    };
    TextView quitBtn,dispNo,score,question;
    Button next;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question6);
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
                    Toast.makeText(QuestionActivity6.this, "Please select an option", Toast.LENGTH_SHORT).show();
                }
                RadioButton uanswer=findViewById(radio_g.getCheckedRadioButtonId());
                String ansText=uanswer.getText().toString();
                if (ansText.equals(answers[flag])){
                    correct++;
                    Toast.makeText(QuestionActivity6.this, "Hurray!! it was correct", Toast.LENGTH_SHORT).show();
                }else{
                    wrong++;
                    Toast.makeText(QuestionActivity6.this, "Ohh!! it was incorrect", Toast.LENGTH_SHORT).show();
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
                        Intent intent=new Intent(QuestionActivity6.this,ResultActivity.class);
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
                Intent intent=new Intent(QuestionActivity6.this,ResultActivity.class);
                intent.putExtra("attempted",flag);
                intent.putExtra("correct",correct);
                intent.putExtra("wrong",wrong);
                startActivity(intent);
                finish();
            }
        });
    }
}