package com.example.silverwindz.project1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LoginAct extends ActionBarActivity {
    ArrayList<Map<String, String>> data;
    SimpleAdapter adapter;
    String name;
    String username;
    String password;
    String test;
    long lastUpdate = 0;
    Handler handler;
    public String r ="";
    public String uid="";
    public String bmrLog="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void buttonClicked(View v) throws JSONException {
        EditText etUser = (EditText)findViewById(R.id.etUser);
        EditText etPass = (EditText)findViewById(R.id.etPass);
        username = etUser.getText().toString().trim();
        password = etPass.getText().toString().trim();
        int id = v.getId();
        Intent i;

        LoadMessageTask task = new LoadMessageTask();
        task.execute();

        switch(id) {
            case R.id.sign_in:



                if (username.length() == 0) {
                    Toast t = Toast.makeText(this.getApplicationContext(),
                            "Please input the user name",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
                else if (password.length() == 0) {
                    Toast tt = Toast.makeText(this.getApplicationContext(),
                            "Please input the password",
                            Toast.LENGTH_SHORT);
                    tt.show();
                }

                else {

                    Log.d(username,password);
                    Log.d(r,"dddd");
                    if(r.equals("true")) {
                        i = new Intent(this, MainActivity.class);
                        i.putExtra("username", username);
                        i.putExtra("uid", uid);
                        i.putExtra("bmrLog", bmrLog);
                        startActivity(i);


                    }
                }
                break;

            case R.id.register:
                i = new Intent(this, RegisterAct.class);
                startActivity(i);
                break;
        }

    }
    class LoadMessageTask extends AsyncTask<String, Void, Boolean> {
        String phpResponse = "";
        String phpMsg = "";
        String phpUserId = "";
        String phpbmr ="";
        @Override
        protected Boolean doInBackground(String... params) {
            BufferedReader reader;
            StringBuilder buffer = new StringBuilder();
            String line;
            try {
                Log.e("LoadMessageTask", "");
                URL u = new URL("http://ict.siit.tu.ac.th/~u5522790500/fetch.php?username="+username+"&"+"pass="+password);
                HttpURLConnection h = (HttpURLConnection)u.openConnection();
                h.setRequestMethod("GET");
                h.setDoInput(true);
                h.connect();

                int response = h.getResponseCode();
                if (response == 200) {
                    reader = new BufferedReader(new InputStreamReader(h.getInputStream()));
                    while((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    Log.e("LoadMessageTask", buffer.toString());
                    //Parsing JSON and displaying messages

                    //To append a new message:
                     Map<String, String> item = new HashMap<String, String>();
                    JSONObject json = new JSONObject(buffer.toString());

                    phpResponse =  json.getString("response");
                    phpbmr = json.getString("phpbmr");
                    bmrLog = phpbmr;
                    uid=json.getString("userId");
                    r=phpResponse;
                    if(phpResponse.equals("true"))
                    {

                        phpMsg = "Logged In";
                        phpUserId = json.getString("userId");
                    }
                    else if(phpResponse.equals("false")) {

                        phpMsg = "Invalid username or password";
                        phpUserId = "Invalid user id ";
                    }
                }
                return true;
            } catch (MalformedURLException e) {
                Log.e("LoadMessageTask", "Invalid URL");
            } catch (IOException e) {
                Log.e("LoadMessageTask", "I/O Exception");
            } catch (JSONException e) {
                Log.e("LoadMessageTask", "Invalid JSON");
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result == true) {

                if(phpResponse.equals("true"))
                {
                    //r="true";

                    Toast t = Toast.makeText(LoginAct.this.getApplicationContext(),
                            phpMsg + " with user id:" + phpUserId,
                            Toast.LENGTH_SHORT);
                    t.show();
                }
                else
                {
                   // r="false";
                    Toast t = Toast.makeText(LoginAct.this.getApplicationContext(),
                            phpMsg + " wrong username or password " + phpUserId,
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
            else if(result == false)
            {
                Toast t = Toast.makeText(LoginAct.this.getApplicationContext(),
                        "Cannot connect to server",
                        Toast.LENGTH_SHORT);
                t.show();
            }
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
