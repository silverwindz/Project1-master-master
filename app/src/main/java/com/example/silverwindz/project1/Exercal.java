package com.example.silverwindz.project1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class Exercal extends ActionBarActivity {
    CalorieDBHelper helper;
    public String username="";
    public String uid="";
    public String Exbmr="";
    public Double Exb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_cal);
        Intent i = this.getIntent();
        username = i.getStringExtra("username");
        uid = i.getStringExtra("uid");
        Log.e(uid,"");
        Exbmr=i.getStringExtra("mainbmr");

         Exb = Double.parseDouble(Exbmr);
        TextView bmr1 = (TextView)findViewById(R.id.bmrin);
        bmr1.setText(String.format("%.2f",Exb));

    }




    public void buttonCaloClicked (View v) throws JSONException
    {
        /*SQLiteDatabase dbr = helper.getReadableDatabase();
        Cursor cursor = dbr.rawQuery("SELECT _id,bmr FROM caloriess ORDER BY _id DESC;", null);
        cursor.moveToFirst();

        Double bmr = cursor.getDouble(1);*/

        Exb = Double.parseDouble(Exbmr);

        double cal1 = 0;
        double burn = 0;

        /*SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues r = new ContentValues();*/



        EditText dura = (EditText)findViewById(R.id.duration);
        String t = dura.getText().toString();
        Double time1 = Double.parseDouble(t);

        RadioGroup exerlist = (RadioGroup)findViewById(R.id.exerlists);
        RadioButton exl = (RadioButton) findViewById(exerlist.getCheckedRadioButtonId());
        int selex = exerlist.getCheckedRadioButtonId();

        String time = String.valueOf(time1);

        if (selex == R.id.rel) { //name
            cal1 = ((Exb / 24) * 1.54);
            burn = cal1 * (time1 / 60);

            String bur = String.valueOf(burn);
           /* r.put("exercise", "Relaxing");
            r.put("caloburn", burn);
            long new_id = db.insert("calories2", null, r);*/
            PostMessageTask p = new PostMessageTask();
            p.execute("Relaxing", time, bur,uid);
        } else if (selex == R.id.foot)//
        {
            cal1 = ((Exb / 24) * 8.00);
            burn = cal1 * (time1 / 60);

            String bur = String.valueOf(burn);
           /* r.put("exercise", "Playing Football");
            r.put("caloburn", burn);
            long new_id = db.insert("calories2", null, r);*/
            PostMessageTask p = new PostMessageTask();
            p.execute("Playing Football", time, bur,uid);
        } else if (selex == R.id.run)//
        {
            cal1 = ((Exb / 24) * 7.50);
            burn = cal1 * (time1 / 60);

            String bur = String.valueOf(burn);
            /*r.put("exercise", "Running");
            r.put("caloburn", burn);
            long new_id = db.insert("calories2", null, r);*/
            PostMessageTask p = new PostMessageTask();
            p.execute("Running", time, bur,uid);
        } else if (selex == R.id.walk)//
        {
            cal1 = ((Exb / 24) * 3.80);
            burn = cal1 * (time1 / 60);

            String bur = String.valueOf(burn);
            /*r.put("exercise", "Walking");
            r.put("caloburn", burn);
            long new_id = db.insert("calories2", null, r);*/
            PostMessageTask p = new PostMessageTask();
            p.execute("Walking", time, bur,uid);
        } else if (selex == R.id.lift)//
        {
            cal1 = ((Exb / 24) * 3.00);
            burn = cal1 * (time1 / 60);

            String bur = String.valueOf(burn);
           /* r.put("exercise", "Weight lifting");
            r.put("caloburn", burn);
            long new_id = db.insert("calories2", null, r);*/
            PostMessageTask p = new PostMessageTask();
            p.execute("Weight lifting", time, bur,uid);
        } else if (selex == R.id.mrt)//
        {
            cal1 = ((Exb / 24) * 10.00);
            burn = cal1 * (time1 / 60);

            String bur = String.valueOf(burn);
            /*r.put("exercise", "Martial Arts");
            r.put("caloburn", burn);
            long new_id = db.insert("calories2", null, r);*/
            PostMessageTask p = new PostMessageTask();
            p.execute("Martial Arts", time, bur,uid);
        } else {
            cal1 = ((Exb / 24) * 2.50);
            burn = cal1 * (time1 / 60);

            String bur = String.valueOf(burn);
           /* r.put("exercise", "Education");
            r.put("caloburn", burn);
            long new_id = db.insert("calories2", null, r);*/
            PostMessageTask p = new PostMessageTask();
            p.execute("Education", time, bur,uid);
        }


        TextView calburn = (TextView)findViewById(R.id.calout);
        calburn.setText(String.format("%.2f",burn));



    }

    public void buttonToListClicked(View v) {
        int id = v.getId();
        Intent i;

        switch(id) {
            case R.id.confirm:
                i = new Intent(this, MainActivity.class);
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

    class PostMessageTask extends AsyncTask<String, Void, Boolean> {
        String line;
        StringBuilder buffer = new StringBuilder();
        String errMsg = "";

        @Override
        protected Boolean doInBackground(String... params) {
            Log.d("DEBUG", "Start Task");
            String exname = params[0];
            String extime= params[1];
            String exburn = params[2];
            String userid= params[3];

            String resp;
            HttpClient h = new DefaultHttpClient();
            HttpPost p = new HttpPost("http://ict.siit.tu.ac.th/~u5522790500/postex.php");

            List<NameValuePair> values = new ArrayList<NameValuePair>();
            values.add(new BasicNameValuePair("ExName",exname));
            values.add(new BasicNameValuePair("exer_time",extime));
            values.add(new BasicNameValuePair("exer_burn",exburn));
            values.add(new BasicNameValuePair("user_ID",userid));

            try {
                p.setEntity(new UrlEncodedFormEntity(values));
                HttpResponse response = h.execute(p);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));
                Log.d("DEBUG", "HERE");
                while ((line = reader.readLine()) != null) {
                    Log.d("DEBUGL", line);
                    buffer.append(line);
                }

                //Log.e("LoadMessageTask", buffer.toString());
                //Parsing JSON and displaying messages

                //To append a new message:
                //Map<String, String> item = new HashMap<String, String>();
                //item.put("user", u);
                //item.put("message", m);
                //data.add(0, item);
                Log.d("DEBUG", buffer.toString());
                JSONObject json = new JSONObject(buffer.toString());

                boolean respo = json.getBoolean("response");
                errMsg = json.getString("errmsg");
                if(respo) {
                    return true;
                }


            }catch(JSONException e){
                Log.e("LoadMessageTask", "Invalid JSON");
            }catch(UnsupportedEncodingException e){
                Log.e("Error", "Invalid encoding");
            }catch(ClientProtocolException e){
                Log.e("Error", "Error in posting a message");
            }catch(IOException e){
                Log.e("Error", "I/O Exception");
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast t = Toast.makeText(Exercal.this.getApplicationContext(),
                        "Complete Calculation",
                        Toast.LENGTH_SHORT);
                t.show();
            }
            else {
                Toast t = Toast.makeText(Exercal.this.getApplicationContext(),
                        "Unable to Calculate: " + errMsg,
                        Toast.LENGTH_SHORT);
                t.show();
            }
        }



    }
}
