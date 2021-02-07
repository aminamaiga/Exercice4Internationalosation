package fr.umontpellier.android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
Locale locale;
String Fr = "fr";
String En = "en";
//switch langue
ImageView frBouton, enBouton;
//extras intent
  public EditText edNom, edPrenom, edAge, edPhone, edDomaine;
Button valider;
AlertDialog.Builder builder;
final String NOM = "NOM";
final String PRENOM = "PRENOM";
final String AGE = "AGE";
final String DOMAINE = "DOMAINE";
final String TELEPHONE = "PHONE";

//Exercice 4 : Internationalisation des interfaces
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
        valider = (Button)findViewById(R.id.valider);
        builder = new AlertDialog.Builder(this);
        edNom = (EditText)findViewById(R.id.ed_nom);
        edPrenom= (EditText)findViewById(R.id.ed_prenom);
        edAge = (EditText)findViewById(R.id.ed_age);
        edDomaine = (EditText)findViewById(R.id.ed_domaine);
        edPhone = (EditText)findViewById(R.id.ed_phone);

        frBouton.setOnClickListener(new OnChangLanguage());
        enBouton.setOnClickListener(new OnChangLanguage());
        valider.setOnClickListener(new OnButonValidationClick());
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

    //Exercice 5 : Événements associés aux objets graphiques d’une vue
    class OnButonValidationClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
           builder.setMessage("") .setTitle("");

            builder.setMessage("Etes-vous sûr vouloir confimer la validation?")
                    .setCancelable(false)
                    .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startActivity2();
                          }
                    })
                    .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            Toast.makeText(getApplicationContext(),"Annulation",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.setTitle("Confirmation");
            alert.show();
        }
    }

    //Exercice 6 : Intent explicite
    private void startActivity2() {
        Intent intent = new Intent(getApplicationContext(), Activity2.class);
        intent.putExtra(PRENOM, edPrenom.getText().toString());
        intent.putExtra(NOM, edNom.getText().toString());
        intent.putExtra(AGE, edAge.getText().toString());
        intent.putExtra(DOMAINE, edDomaine.getText().toString());
        intent.putExtra(TELEPHONE, edPhone.getText().toString());
        startActivity(intent);
    }
}