package com.example.silverwindz.project1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {
    String username="";
    String uid="";
    String mainbmr="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = this.getIntent();
        username = i.getStringExtra("username");
        uid = i.getStringExtra("uid");
        mainbmr = i.getStringExtra("bmrLog");
    }


    public void buttonClicked(View v) {
        int id = v.getId();
        Intent i;

        switch(id) {
            case R.id.exer:
                i = new Intent(this, Exercal.class);
                i.putExtra("username", username);
                i.putExtra("uid", uid);
                i.putExtra("mainbmr",mainbmr);
                startActivity(i);
                break;

            case R.id.fod:
                i = new Intent(this, FoodAct.class);
                i.putExtra("username", username);
                i.putExtra("uid", uid);
                i.putExtra("mainbmr",mainbmr);
                startActivity(i);
                break;

            case R.id.info:
                i = new Intent(this, information.class);
                startActivity(i);

                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
