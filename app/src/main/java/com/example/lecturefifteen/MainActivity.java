package com.example.lecturefifteen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        textView = findViewById(R.id.text_view);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Callable callable = getDataFromCallable();
                FutureTask futureTask = new FutureTask(callable);
                new Thread(futureTask).start();
                try {
                    textView.setText(futureTask.get().toString());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Callable <Integer> getDataFromCallable () {
        Callable<Integer> callable = new Callable<Integer> () {
            @Override
            public Integer call() throws Exception {
                int result = 0;
                for (int i = 2; i <= Integer.parseInt(editText.getText().toString()); i++) {
                    result = result + i;
                }
                return result;
            }
        };
        return callable;
    }
}


