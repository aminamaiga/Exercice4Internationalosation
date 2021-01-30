package fr.umontpellier.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
Locale locale;
String Fr = "fr";
String En = "en";

ImageView frBouton, enBouton;
    public void setLocale(String lang) {
        locale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
        recreate();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frBouton = (ImageView)findViewById(R.id.fr_bouton);
        enBouton = (ImageView)findViewById(R.id.en_bouton);

        frBouton.setOnClickListener(new OnChangLanguage());
        enBouton.setOnClickListener(new OnChangLanguage());
    }

    class OnChangLanguage implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.fr_bouton:
                    setLocale(Fr);
                    Toast.makeText(getApplicationContext(), "Français", Toast.LENGTH_LONG).show();
                    break;
                case R.id.en_bouton:
                    setLocale(En);
                    Toast.makeText(getApplicationContext(), "Anglais", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
}