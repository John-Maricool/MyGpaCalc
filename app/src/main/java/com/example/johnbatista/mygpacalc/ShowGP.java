package com.example.johnbatista.mygpacalc;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ShowGP extends AppCompatActivity {

    String message;
    MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_gp);
        myDatabase = new MyDatabase(this);
        Intent intent = getIntent();
         message = intent.getStringExtra("GPA");
        TextView textView = (TextView)findViewById(R.id.tV);
        textView.setText(message);
       }


    public void save(View view){
        final EditText name = new EditText(this);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Save with ...");
        builder.setView(name);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (name.getText() != null) {
                    String savedName = name.getText().toString();
                   boolean ifSaved =  myDatabase.AddToDatabase(savedName, message);
                   if (ifSaved) {
                       Toast.makeText(ShowGP.this, "Saved", Toast.LENGTH_LONG).show();
                   }
                   else{
                       Toast.makeText(ShowGP.this, " Not Saved", Toast.LENGTH_LONG).show();
                   }
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }


    public void gotoGp(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void list(View view) {
        Intent intent = new Intent(this, ListOfGps.class);
        startActivity(intent);
    }
}
