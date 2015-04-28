package com.example.silverwindz.project1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class FoodAct2 extends ActionBarActivity {
    public String allcal="";

    public Double allcal1;
    public double allcalplus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_act2);
        Intent i = this.getIntent();
        allcal=i.getStringExtra("allcal");

        allcal1 = Double.parseDouble(allcal);
        allcalplus = allcalplus + allcal1;

        TextView CAL = (TextView)findViewById(R.id.OC);
        CAL.setText(String.format("%.2f",allcalplus));
    }

    public void buttonClicked(View v){
        int id = v.getId();
        Intent i;
        switch(id) {
            case R.id.breakfast:
                i = new Intent(this, choice2.class);
                startActivity(i);
                break;

            case R.id.lunch:
                i = new Intent(this, choice2.class);
                startActivity(i);
                break;

            case R.id.din:
                i = new Intent(this, choice2.class);
                startActivity(i);
                break;

            case R.id.clear:
                allcalplus = 0;
                break;

            case R.id.confirm:
                i = new Intent(this, MainActivity.class);
                i.putExtra("calculatepls", allcalplus);
                startActivity(i);
                break;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_food, menu);
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
