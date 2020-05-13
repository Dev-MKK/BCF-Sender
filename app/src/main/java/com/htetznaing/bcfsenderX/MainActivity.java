package com.htetznaing.bcfsenderX;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.htetznaing.bcfsender.BCFSender;

public class MainActivity extends AppCompatActivity {
    BCFSender bcfSender;
    TextView textResult;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textResult = findViewById(R.id.textResult);
        progressDialog = new ProgressDialog(this);

        bcfSender = new BCFSender(this, "https://zfonts.blogspot.com/", new BCFSender.OnSendListener() {
            @Override
            public void onResult(boolean success, String message) {
                progressDialog.dismiss();
                textResult.setText("Status => " + (success ? "ok" : "error")+ "\nMessage => "+message);
                System.out.println("=> "+success+" | "+message);
            }
        });
    }

    public void sendNow(View view) {
        progressDialog.show();
        textResult.setText("");
        EditText contant_name = findViewById(R.id.contant_name);
        EditText contant_email = findViewById(R.id.contant_email);
        EditText contant_message = findViewById(R.id.contant_message);

        String name = contant_name.getText().toString();
        String email = contant_email.getText().toString();
        String message = contant_message.getText().toString();
        bcfSender.send(name,email,message);
    }
}
