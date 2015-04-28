package com.example.silverwindz.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class justfood extends ActionBarActivity {
    CalorieDBHelper helper;
    public String username="";
    public String uid="";
    public double cal1;
    public String call2="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_justfood);
        Intent i = this.getIntent();
        username = i.getStringExtra("username");
        uid = i.getStringExtra("uid");



    }

    public void buttonFOClicked (View v)
    {

        cal1 = 0;
        EditText Qual = (EditText)findViewById(R.id.qualityblank);
        String t = Qual.getText().toString();
        Double Qual1 = Double.parseDouble(t);

        RadioGroup foodlists = (RadioGroup)findViewById(R.id.foodlists);
        RadioButton fool = (RadioButton) findViewById(foodlists.getCheckedRadioButtonId());
        int selex = foodlists.getCheckedRadioButtonId();
        if (selex == R.id.scr) { //name
            cal1 = 585 * Qual1;

        }
        else if (selex == R.id.nd)//
        {
            cal1 = 300 * Qual1;
        }
        else if (selex == R.id.spl)//
        {
            cal1 = 690 * Qual1;
        }
        else if (selex == R.id.wrnoodle)//
        {
            cal1 = 405 * Qual1;
        }
        else if (selex == R.id.fr)//
        {
            cal1 = 500 * Qual1;
        }
        else if (selex == R.id.brp)//
        {
            cal1 = 560 * Qual1;

        }
        else if (selex == R.id.rt)//
        {
            cal1 = 580 * Qual1;

        }
        else if (selex == R.id.mpo)//
        {
            cal1 = 300 * Qual1;

        }
        else if (selex == R.id.pt)//
        {
            cal1 = 545 * Qual1;

        }
        else if (selex == R.id.cg)//
        {
            cal1 = 230 * Qual1;

        }
        else {
            cal1 = 550 * Qual1;
        }

        int id = v.getId();
        Intent i;

        switch(id) {
            case R.id.confirm:
                i = new Intent(this, choice3.class);
                call2 = String.valueOf(cal1);
                i.putExtra("foodcal", call2);
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
