package de.chucknorrisfacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {

    private TextView factsText;
    private Button nextFact;
    private String[] facts = new String[]{};
    private static final Random RAND = new Random();
    private String currentFact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        facts = getResources().getStringArray(R.array.chuck_norris_facts);
        factsText = (TextView) findViewById(R.id.factsText);
        nextFact = (Button) findViewById(R.id.buttonNextFact);
        startService(new Intent(this, SendFact.class));

        setFactText();

        new Timer() {
            @Override
            protected void onProgressUpdate(Integer... values) {
                Log.d("myCounterLog", "onProgressUpdate: " + currentFact);
                setFactText();
            }
        }.execute();
    }



    public void setFactText()
    {
        int rndIdx = -1;
        do{
            rndIdx = RAND.nextInt(facts.length); // [0,WEATHERS.length[
        } while (facts[rndIdx].equals(this.currentFact));
        this.currentFact = facts[rndIdx];
        factsText.setText(currentFact);
    }

    public void setNextFact(View view)
    {
        Log.d("myCounterLog", "setNextFact: " +currentFact);
        setFactText();
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
