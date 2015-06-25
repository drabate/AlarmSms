package com.example.etienne.alarmsms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Ajout extends ActionBarActivity {

    EditText num;
    int place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);
        num= (EditText)findViewById(R.id.textNum);

        place = getIntent().getIntExtra("place",0);
    }


    public void Spam (View v)
    {
        Log.i("mon debug", "longueur num " + num.getText().length());
        if(num.getText().length() == 10 && num.getText().charAt(0) == '0' && (num.getText().charAt(1) == '6' || num.getText().charAt(1) == '7'))
        {
            SharedPreferences numPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor edit = numPref.edit();
            edit.putString(String.valueOf(place), num.getText().toString());
            Log.i("mon debug","place du nouveau numéro "+String.valueOf(place));
            Intent intent = new Intent(this,Main.class);
            intent.putExtra("numero",num.getText().toString());
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Merci de rentrer un numéro valide",Toast.LENGTH_LONG).show();
        }

    }
}
