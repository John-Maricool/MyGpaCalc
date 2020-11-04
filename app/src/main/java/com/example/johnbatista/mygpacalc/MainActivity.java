package com.example.johnbatista.mygpacalc;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    LinearLayout spinner_layout, course_layout, ul_layout;
    Button calc;
    static int viewCount = 0;
    double GPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    spinner_layout = (LinearLayout)findViewById(R.id.spinner_layout);
    course_layout = (LinearLayout)findViewById(R.id.course_layout);
    ul_layout = (LinearLayout)findViewById(R.id.ul_layout);
    calc = (Button)findViewById(R.id.calc);
        AddViews();

    }


    public void Add(View view) {
        AddViews();
        viewCount++;
    }

    public void Remove(View view) {
        if(spinner_layout.getChildCount() > 1) {
            course_layout.removeViewAt(viewCount);
            ul_layout.removeViewAt(viewCount);
            spinner_layout.removeViewAt(viewCount);
            viewCount--;
        }else {
            Toast.makeText(MainActivity.this, "You cant go beyond this", Toast.LENGTH_LONG).show();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public boolean AddViews(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 30, 0, 0);

        EditText editText = new EditText(this);
        editText.setLayoutParams(params);
        editText.setEms(5);
        editText.setBackground(getResources().getDrawable(R.drawable.rounded_corners));
        editText.setTextSize(13);
        editText.setHint("course");
        course_layout.addView(editText);

        EditText unitLoads = new EditText(this);
        unitLoads.setLayoutParams(params);
        unitLoads.setEms(3);
        unitLoads.setBackground(getResources().getDrawable(R.drawable.rounded_corners));
        unitLoads.setInputType(InputType.TYPE_CLASS_NUMBER);
        unitLoads.setHint("ul");
        unitLoads.setTextSize(13);
       // unitLoads.setPadding(6, 0, 6, 0);
        ul_layout.addView(unitLoads);

        Spinner spinner = new Spinner(this);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        param.setMargins(0, 0, 0, 10);
        spinner.setLayoutParams(param);
        spinner.setBackground(getResources().getDrawable(R.drawable.rounded_corners));
        spinner.setPadding(-10, -10, -10 , -10);
        spinner.setPopupBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corners));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.grades, android.R.layout.simple_spinner_dropdown_item);
       // adapter.setDropDownViewResource(R.layout.each_view);
        spinner.setAdapter(adapter);
        spinner_layout.addView(spinner);

        return true;
    }

    public void calc(View view) {
        int[] unitLoadsArray = unitLoads();
        int[] gradesArray = Grades();

        double TotUL = 0;
        double Mult = 0;

        for (int i = 0; i < unitLoadsArray.length; i++) {
        TotUL += unitLoadsArray[i];
        Mult += (unitLoadsArray[i] * gradesArray[i]);
    }

    GPA = Mult / TotUL;
    String GPa = String.format("%.4f", GPA);
    Intent intent = new Intent(this, ShowGP.class);
    intent.putExtra("GPA", GPa);
    startActivity(intent);

    }


    public int[] unitLoads(){


        int num = ul_layout.getChildCount();
        int[] unitLoads = new int[num];
        int child;

        try {
            for (int i = 0; i < num; i++) {
                EditText currentChild = (EditText) ul_layout.getChildAt(i);
                if (currentChild.getText() != null) {
                    child = Integer.parseInt(String.valueOf(currentChild.getText()));
                    unitLoads[i] = child;
                }

            }
        }
        catch(Exception e){
                Toast.makeText(this, "There is a problem with your entries", Toast.LENGTH_LONG).show();

        }
        return unitLoads;
    }



    public int[] Grades() {


        int num = spinner_layout.getChildCount();
        int[] grades = new int[num];

        try{
        for (int i = 0; i < num; i++) {
            Spinner currentChild = (Spinner) spinner_layout.getChildAt(i);
            if (currentChild.getSelectedItem() != null) {
                String Item = String.valueOf(currentChild.getSelectedItem());
                switch (Item) {
                    case "A":
                        grades[i] = 5;
                        break;

                    case "B":
                        grades[i] = 4;
                        break;

                    case "C":
                        grades[i] = 3;
                        break;


                    case "D":
                        grades[i] = 2;
                        break;


                    case "E":
                        grades[i] = 1;
                        break;


                    case "F":
                        grades[i] = 0;
                        break;


                    default:
                        Toast.makeText(this, "Enter a grade", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
           }

        } catch(Exception e){
            Toast.makeText(this, "There is a problem with your entries", Toast.LENGTH_LONG).show();
        }
        return grades;
    }
}
