package fr.umontpellier.android;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//Exercice 6 : Intent explicite
public class Activity2 extends AppCompatActivity {
Intent intent;
    //extras intent
    public TextView tvNom, tvPrenom, tvAge, tvPhone, tvDomaine;

    final String NOM = "NOM";
    final String PRENOM = "PRENOM";
    final String AGE = "AGE";
    final String DOMAINE = "DOMAINE";
    final String TELEPHONE = "PHONE";
    Button next, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        intent = getIntent();

        tvNom = (TextView)findViewById(R.id.id_tv_nom);
        tvPrenom = (TextView)findViewById(R.id.id_tv_prenom);
        tvAge = (TextView)findViewById(R.id.id_tv_age);
        tvDomaine = (TextView)findViewById(R.id.id_tv_domain);
        tvPhone = (TextView)findViewById(R.id.id_tv_telephone);
        next = (Button)findViewById(R.id.idPrevious);
        back = (Button)findViewById(R.id.idNext);

        tvNom.setText(intent.getStringExtra(NOM));
        tvPrenom.setText(intent.getStringExtra(PRENOM));
        tvAge.setText(intent.getStringExtra(AGE));
        tvDomaine.setText(intent.getStringExtra(DOMAINE));
        tvPhone.setText(intent.getStringExtra(TELEPHONE));

        next.setOnClickListener(new OnButtoNclick());
        back.setOnClickListener(new OnButtoNclick());
    }

    class OnButtoNclick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.idNext:
                    Intent intent = new Intent(getApplicationContext(), Activity3.class);
                    startActivity(intent);
                    break;
                case R.id.idPrevious:
                    Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent2);
                    break;
            }
        }
    }
}