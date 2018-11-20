package com.example.user.dialogshak;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    AlertDialog.Builder adb;
    AlertDialog ad;
    LinearLayout mydialog;
    EditText et1, et2;
    Switch s1;
    double num1, d, sum1, temp;
    int i = 0;
    Double[] array1;
    ListView l1;
    String str1, str2;
    ArrayAdapter<Double> adp;
    TextView t1, t2, t3, t4;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l1 = (ListView) findViewById(R.id.ListView1);
        t1 = (TextView) findViewById(R.id.textView1);
        t2 = (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView3);
        t4 = (TextView) findViewById(R.id.textView4);
        array1 = new Double[20];
        l1.setOnItemClickListener(this);
        l1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);

    }

    public void data(View view) {
        mydialog = (LinearLayout) getLayoutInflater().inflate(R.layout.mydialog, null);
        s1 = (Switch) mydialog.findViewById(R.id.switch1);
        et1 = (EditText) mydialog.findViewById(R.id.firstNum);
        et2 = (EditText) mydialog.findViewById(R.id.MultOrDif);
        adb = new AlertDialog.Builder(this);
        adb.setView(mydialog);
        adb.setTitle("enter the data");
        adb.setPositiveButton("enter", myclick);
        adb.setNegativeButton("reset", myclick);
        adb.setNeutralButton("cancel", myclick);
        ad = adb.create();
        ad.show();


    }

    DialogInterface.OnClickListener myclick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            str1 = et1.getText().toString();
            str2 = et2.getText().toString();


            if (which == DialogInterface.BUTTON_POSITIVE) {
                if ((str1.isEmpty()) || (str1.equals(".")) || (str1.equals("-")) || (str1.equals("-.") || str2.equals(".")) || (str2.equals("-.")) || (str2.isEmpty()) || (str2.equals("-")))
                    Toast.makeText(MainActivity.this, "you must enter an appropriate input", Toast.LENGTH_SHORT).show();
                else {
                    if (s1.isChecked()) {
                        num1 = Double.parseDouble(str1);
                        d = Double.parseDouble(str2);
                        temp=num1;
                        array1[0] = num1;
                        for (i = 1; i < 20; i++) {
                            array1[i] = temp * d;
                            temp = temp * d;
                        }


                    } else {
                        num1 = Double.parseDouble(str1);
                        d = Double.parseDouble(str2);
                        array1[0] = num1;
                        temp=num1;
                        for (i = 1; i < 20; i++) {
                            array1[i] = temp + d;
                            temp = temp + d;
                        }

                    }
                    setArray();

                }
            }
            if (which == DialogInterface.BUTTON_NEGATIVE) {
                num1= 0;
                d=0;
                for (i = 0; i < 20; i++) {
                    array1[i] =0.0;
                }
                setArray();

            }
            if (which == DialogInterface.BUTTON_NEUTRAL) {
                ad.cancel();
            }

        }

    };

    private void setArray() {
        adp = new ArrayAdapter<Double>(this, R.layout.support_simple_spinner_dropdown_item, array1);
        l1.setAdapter(adp);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        t1.setText("X1= " + num1);
        t2.setText("d= " + d);
        t3.setText("n= " + (position+1));
        sum1 = 0;
        for (i = 0; i <= position; i++) {
            sum1 = array1[i] + sum1;
        }
        t4.setText("Sn= " + sum1);

    }

}

