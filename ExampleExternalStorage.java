package com.example.yawe;

import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class ExampleExternalStorage extends AppCompatActivity {

    EditText editTextMessage;
    Button buttonSave, buttonDisplay;
    TextView textView;
    String myFile = "MyFiles";
    String fileName ="message.txt";
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_external_storage);

        editTextMessage = findViewById(R.id.edit_message);
        buttonSave = findViewById(R.id.button_save);
        buttonDisplay = findViewById(R.id.button_display);
        textView = findViewById(R.id.message_display);

        if(isExternalStorageWritable()){
            buttonSave.setEnabled(false);
        }else {
            ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
            file = new File(contextWrapper.getExternalFilesDir(myFile),fileName);
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editTextMessage.getText().toString();
                try {
                    FileOutputStream outputStream = new FileOutputStream(file);
                    outputStream.write(message.getBytes());
                    outputStream.flush();
                    outputStream.close();
                    editTextMessage.setText("");
                    Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        buttonDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    FileInputStream inputStream = new FileInputStream(file);
                    InputStreamReader streamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(streamReader);
                    String message;
                    StringBuilder stringBuilder = new StringBuilder();

                    while ((message = bufferedReader.readLine())!=null){
                        stringBuilder.append(message);
                    }
                    textView.setText("Message: "+stringBuilder.toString());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        return !Environment.MEDIA_MOUNTED.equals(state);
    }
}


