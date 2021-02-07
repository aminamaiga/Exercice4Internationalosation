package fr.umontpellier.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//Exercice 7 : Intent implicite
public class Activity3 extends AppCompatActivity {
    private Button call, back;
    private EditText numberPhone;
    private final static int RESULT = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        call = (Button)findViewById(R.id.idCall);
        back = (Button)findViewById(R.id.idPrevious);
        numberPhone = (EditText)findViewById(R.id.ed_number_phone);

        call.setOnClickListener(new OnButtoNclick());
        back.setOnClickListener(new OnButtoNclick());
    }

    class OnButtoNclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.idCall:
                    callPhoneNumber();
                    break;
                case R.id.idPrevious:
                    Intent intent2 = new Intent(getApplicationContext(), Activity2.class);
                    startActivity(intent2);
                    break;
            }
        }
    }

    public void callPhoneNumber()
    {
        try
        {
            if(Build.VERSION.SDK_INT > 22)
        {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Activity3.this, new String[]{Manifest.permission.CALL_PHONE}, RESULT);
               Toast.makeText(getApplicationContext(), "Permission refusée", Toast.LENGTH_LONG).show();
                return;
            }
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + numberPhone.getText().toString()));
            startActivity(callIntent);

        }
        else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + numberPhone.getText().toString()));
            startActivity(callIntent);
        }
        }
        catch (Exception ex)
        { }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    { if(requestCode == RESULT)
        { if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            { callPhoneNumber();
            }
        }
    }
}