package de.chucknorrisfacts;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sebi on 05.07.15.
 */
public class SendFact extends Service {

    public static final String NEW_CHUCK_NORRIS_FACT= "newFact";
    public static final String NEW_CHUCK_NORRIS_INTENT= "newIntent";
    private ScheduledExecutorService executorService;
    private String[] facts = new String[]{};
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        facts = getResources().getStringArray(R.array.chuck_norris_facts);
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                newChuckNorrisFact(getRandomFact());
                Log.d("myServiceLog", "Service send");
            }
        }, 0, 10, TimeUnit.SECONDS);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    private void newChuckNorrisFact(String fact)
    {
        final Intent stickyFactIntent = new Intent(NEW_CHUCK_NORRIS_INTENT);
        stickyFactIntent.putExtra(NEW_CHUCK_NORRIS_FACT, fact);
        this.sendStickyBroadcast(stickyFactIntent);
    }


    public String getRandomFact()
    {
        Random rand = new Random();
        return facts[rand.nextInt(facts.length)];
    }
}
