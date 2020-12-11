package com.example.yawe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingSuperCall")
    protected  void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button list=(Button)findViewById(R.id.button2);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 =new Intent(MainActivity.this,ListViewActivity.class);
                startActivity(intent2);

            }
        });


    }

    public  void sendMesaage (View view){
        EditText message = (EditText)findViewById(R.id.edit_message);
        Toast.makeText(this, "sending Message" +message.getText().toString(),Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(this,DisplayMessageActivity.class);
        intent.putExtra("Message",message.getText().toString());
        startActivity(intent);

        message.setText("");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.games:
                Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.music:
                Toast.makeText(getApplicationContext(),"Item 2 Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.call:
                Intent intent2 = new Intent(Intent.ACTION_DIAL);
                intent2.setData( Uri.parse("tel:0702329920"));

                startActivity(intent2);
                return true;
            case R.id.email:

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("emailto:"));
                String to[] = {"nalumagamaria001@gmail.com", "kyorigarurab@gmail.com", "annetkansiime76@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, to);
                intent.putExtra(Intent.EXTRA_SUBJECT, "hey girls");
                intent.putExtra(Intent.EXTRA_TEXT, "am around please");
                intent.setType("message/rfc822");
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
