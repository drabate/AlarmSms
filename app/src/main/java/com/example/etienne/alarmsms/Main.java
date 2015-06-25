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
        liste = (ListView)findViewById(R.id.listView);
        //On ajoute un écouteur à la liste manuellement
        liste.setOnItemClickListener(new EcouteurListe());

        //On fait un arrayadapter pour ajouter dans la listView, en se basant sur le XML textViewAdapter
        adapter = new ArrayAdapter<String>(this, R.layout.textviewadapter);
        liste.setAdapter(adapter);
    }

    private void remplirListe() {

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        tp = new ThreadP(num);
        tp.start();
        listeThread.add(tp);
        //On ajoute un texte à l'arrayAdapter puis on informe la listview du changement
        adapter.add(num);
        adapter.notifyDataSetChanged();

       /* Log.i("mon debug", "la liste est "+listeNumPref.toString());

        for (Object s: listeNumPref.keySet())
        {
            Log.i("mon debug", "je suis dans la liste avec le num = "+s);
            tp = new ThreadP((String)s);
            //tp.start();
            listeThread.add(tp);
            //On ajoute un texte à l'arrayAdapter puis on informe la listview du changement
            adapter.add((String)s);
            adapter.notifyDataSetChanged();
        }*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //TODO charger Thread dans un fichier de préférence???? qu'il agissent quand l'appli se ferme
        //mais que quand l'appli s'ouvre, elle les récupères et les intègrent à la liste
        for(ThreadP tp: listeThread)
        {
            tp.arret();
        }
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

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onRestart();
        String num = getIntent().getStringExtra("numero");
        Log.i("mon debug", "dans le Restart je récupère le num " + num);

            remplirListe();
    }

    public void ajoutNum (View v)
    {
        Intent intent = new Intent(this,Ajout.class);
        intent.putExtra("place", liste.getCount());
        startActivity(intent);
    }
}
