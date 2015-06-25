package com.example.etienne.alarmsms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class Main extends ActionBarActivity {


    private ThreadP tp;
    private ListView liste;
    private List<ThreadP> listeThread = new LinkedList<>();
    private ArrayAdapter<String> adapter;
    String num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("mon debug", "je suis dans le onCreate");
        setContentView(R.layout.activity_main);

        //liste = (ListView)findViewById(R.id.listView);
        //On ajoute un écouteur à la liste manuellement
        /*liste.setOnItemClickListener(new EcouteurListe());

        //On fait un arrayadapter pour ajouter dans la listView, en se basant sur le XML textViewAdapter
        adapter = new ArrayAdapter<String>(this, R.layout.textviewadapter);
        liste.setAdapter(adapter);*/
        Set<Thread> listeThread = ThreadP.getAllStackTraces().keySet();
        Thread[] tabThread = listeThread.toArray(new Thread[listeThread.size()]);
        Log.i("MONDEBUG", "nombre de thread actuellement : "+tabThread.length);

        int nbTp = 0;
        for(int i = 0;i<tabThread.length;i++)
        {
            try {

                ThreadP tp = (ThreadP)tabThread[i];
                nbTp++;

            }catch(Exception ex)
            {

            }
        }

        Log.i("MONDEBUG", "nombre de threadP : "+nbTp);
    }

    public void ajouterAlarme(View v)
    {
        Intent intent = new Intent(this, Ajout.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        Log.i("mondebug","je suis dans le onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.i("mondebug", "je suis dans le onResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i("mondebug", "je suis dans le onRestart");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.i("mondebug", "je suis dans le onStart");
        super.onStart();
    }

    @Override
    protected void onPostResume() {
        Log.i("mondebug", "je suis dans le onPostResume");
        super.onPostResume();
    }

    private void actualiser() {

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //On ajoute le thread nouvellement crée
        //TODO: il faut regarder si la date est celle du jour et si oui on lance le thread directement
        /*tp = new ThreadP(num);
        tp.start();
        listeThread.add(tp);*/


        //On ajoute un texte à l'arrayAdapter puis on informe la listview du changement
        adapter.add(num);
        adapter.notifyDataSetChanged();
    }

    public void stopSpam(ThreadP thread)
    {
        thread.arret();
        Log.i("Arret", "arret effectif");
        Toast.makeText(getApplicationContext(),"Spam arrêté",Toast.LENGTH_LONG).show();
    }

    class EcouteurListe implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int i = 0;
            Log.i("MON DEBUG", "dans l'écouteur");
            for(ThreadP top : listeThread)
            {
                Log.i("MON DEBUG", "element selectionné n°" + liste.getSelectedItemPosition()+" i = "+i);
                //la listeView commence à -1
                if(liste.getSelectedItemPosition()+1 == i)
                {
                    //On utilise le parametre View qui correspond à la vue selectionnée
                    Log.i("MON DEBUG", "element selectionné n°" + ((TextView) view).getText());

                    stopSpam(top);
                }
                i++;
            }
        }
    }
}
