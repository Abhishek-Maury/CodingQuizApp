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

public class QuestionActivity extends AppCompatActivity {
    int flag=0;
    int marks=0;
   public static int correct=0;
    int wrong =0;
    String[] questions={  "What is the size of an int variable in Java?",
                          "Which of the following is not a Java keyword?",
                          "Which of the following correctly declares a constructor in Java?",
                          "Which of these is not a feature of Java?",
                          "What is the superclass of every class in Java?"

    };
    String[] options={
              "8 bits","16 bits","32 bits","64 bits",
              "static","try","Integer","new",
              "public void MyClass() {}","public MyClass(void) {}","public MyClass() {}","void MyClass() {}",
              "Object-oriented","Platform independent","Pointer support","Robust",
              "Object","String","Class","System"
    };
    String[] answers={
            "32 bits",
            "Integer",
            "public MyClass() {}",
            "Pointer support",
            "Object"
    };
    TextView quitBtn,dispNo,score,question;
    Button next;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question);
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
                   Toast.makeText(QuestionActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
               }
               RadioButton uanswer=findViewById(radio_g.getCheckedRadioButtonId());
               String ansText=uanswer.getText().toString();
               if (ansText.equals(answers[flag])){
                   correct++;
                   Toast.makeText(QuestionActivity.this, "Hurray!! it was correct", Toast.LENGTH_SHORT).show();
               }else{
                   wrong++;
                   Toast.makeText(QuestionActivity.this, "Ohh!! it was incorrect", Toast.LENGTH_SHORT).show();
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
                       Intent intent=new Intent(QuestionActivity.this,ResultActivity.class);
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
                Intent intent=new Intent(QuestionActivity.this,ResultActivity.class);
                intent.putExtra("attempted",flag);
                intent.putExtra("correct",correct);
                intent.putExtra("wrong",wrong);
                startActivity(intent);
                finish();
            }
        });
    }
}