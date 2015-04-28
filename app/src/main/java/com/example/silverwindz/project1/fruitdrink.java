package com.example.silverwindz.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class fruitdrink extends ActionBarActivity {
    CalorieDBHelper helper;
    public String username="";
    public String uid="";

    public double cal3;
    public String cal4="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruitdrink);
        Intent i = this.getIntent();
        username = i.getStringExtra("username");
        uid = i.getStringExtra("uid");


    }


    public void buttonFDClicked (View v)
    {
        double cal1;
        double cal2;

        EditText Qualf = (EditText)findViewById(R.id.fruitqL);
        String fru = Qualf.getText().toString();
        Double Qual1 = Double.parseDouble(fru);

        RadioGroup fruitlist = (RadioGroup)findViewById(R.id.fruitlists);
        RadioButton fff = (RadioButton) findViewById(fruitlist.getCheckedRadioButtonId());
        int selex = fruitlist.getCheckedRadioButtonId();
        if (selex == R.id.Wm) { //name
            cal1 = 59 * Qual1;

        }
        else if (selex == R.id.km)//
        {
            cal1 = 96 * Qual1;
        }
        else if (selex == R.id.mg)//
        {
            cal1 = 117 * Qual1;
        }
        else if (selex == R.id.bnn)//
        {
            cal1 = 27.75 * Qual1;
        }
        else if (selex == R.id.gv)//
        {
            cal1 = 69 * Qual1;
        }
        else {
            cal1 = 53 * Qual1;

        }

        EditText Quald = (EditText)findViewById(R.id.drinkql);
        String dn = Quald.getText().toString();
        Double Qual2 = Double.parseDouble(dn);

        RadioGroup drinklist = (RadioGroup)findViewById(R.id.drinklists);
        RadioButton exl = (RadioButton) findViewById(drinklist.getCheckedRadioButtonId());
        int seld = drinklist.getCheckedRadioButtonId();
        if (seld == R.id.milo) { //name
            cal2 = 360 * Qual2;

        }
        else if(seld == R.id.coke) { //name
            cal2 = 140 * Qual2;

        }
        else if(seld == R.id.coco) { //name
            cal2 = 334 * Qual2;

        }
        else {
            cal2 = 150 * Qual2;

        }

        cal3 = cal1 + cal2;


        int id = v.getId();
        Intent i;

        switch(id) {
            case R.id.confirm:
                i = new Intent(this, choice2.class);
                cal4 = String.valueOf(cal3);
                Log.d(cal4,"1111111111111111111111111111111111111111");
                i.putExtra("fdcal", cal4);
                startActivity(i);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exercal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
