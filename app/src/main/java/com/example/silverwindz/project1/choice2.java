package com.example.silverwindz.project1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class choice2 extends ActionBarActivity {

    //public String foodcal="";
    //public String fndcal="";
    //public double allcal;
    //public Double fdccal;
    public double allcall;
    public String allcal ="";
    public Double all;
    public String al="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice2);
        Intent i = this.getIntent();
        //foodcal = i.getStringExtra("foodcal");
       // fndcal = i.getStringExtra("fdcal");

        //Double focal = Double.parseDouble(foodcal);
        //fdccal = Double.parseDouble(fndcal);
        allcal = i.getStringExtra("calculatepls");
        all = Double.parseDouble(allcal);

        allcall = all;

    }

    public void buttonClicked(View v){
        int id = v.getId();
        Intent i;
        switch(id) {
            case R.id.food:
                i = new Intent(this, justfood2.class);
                al = String.valueOf(allcall);
                i.putExtra("allcal", allcal);
                startActivity(i);
                break;

            case R.id.fnd:
                i = new Intent(this, fruitdrink.class);
                startActivity(i);
                break;

            case R.id.confirm:
                i = new Intent(this, FoodAct2.class);
                al = String.valueOf(allcall);
                i.putExtra("allcal", allcal);
                startActivity(i);
                break;
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choice, menu);
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
