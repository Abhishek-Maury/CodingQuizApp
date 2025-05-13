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

public class QuestionActivity2 extends AppCompatActivity {

    int flag=0;
    int marks=0;
    public static int correct=0;
    int wrong =0;
    String[] questions={  "What is the default visibility modifier for top-level functions in Kotlin?",
            "Which keyword is used in Kotlin for inheritance?",
            "What does the Elvis operator (?:) do in Kotlin?",
            "Which of the following is used to define a function in Kotlin?",
            "Which of the following is NOT a valid collection in Kotlin?"

    };
    String[] options={
            "private","internal","public","protected",
            "extends","inherits","derive",":(colon)",
            "Throws an exception","Returns a value if the variable is null","Assigns a default value if the expression is null"," Compares two values",
            "function","func","def","fun",
            "listof()","setof()","mapof()","arrayList()"
    };
    String[] answers={
            "public",
            ":(colon)",
            "Assigns a default value if the expression is null",
            "fun",
            "arrayList()"
    };
    TextView quitBtn,dispNo,score,question;
    Button next;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question2);
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
                    Toast.makeText(QuestionActivity2.this, "Please select an option", Toast.LENGTH_SHORT).show();
                }
                RadioButton uanswer=findViewById(radio_g.getCheckedRadioButtonId());
                String ansText=uanswer.getText().toString();
                if (ansText.equals(answers[flag])){
                    correct++;
                    Toast.makeText(QuestionActivity2.this, "Hurray!! it was correct", Toast.LENGTH_SHORT).show();
                }else{
                    wrong++;
                    Toast.makeText(QuestionActivity2.this, "Ohh!! it was incorrect", Toast.LENGTH_SHORT).show();
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
                        Intent intent=new Intent(QuestionActivity2.this,ResultActivity.class);
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
                Intent intent=new Intent(QuestionActivity2.this,ResultActivity.class);
                intent.putExtra("attempted",flag);
                intent.putExtra("correct",correct);
                intent.putExtra("wrong",wrong);
                startActivity(intent);
                finish();
            }
        });
    }
}