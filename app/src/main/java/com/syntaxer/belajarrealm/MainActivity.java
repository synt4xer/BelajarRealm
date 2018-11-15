package com.syntaxer.belajarrealm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSave, btnView;
    EditText nim, name;
    String sName;
    Integer sNim;
    Realm realm;
    RealmHelper realmHelper;
    MahasiswaModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: inisialisasi
        btnSave = findViewById(R.id.btnSimpan);
        btnView = findViewById(R.id.btnTampil);
        nim = findViewById(R.id.nim);
        name = findViewById(R.id.nama);

        //TODO: Setup Realm
        Realm.init(this);
        RealmConfiguration configuration = Realm.getDefaultConfiguration();
        realm = Realm.getInstance(configuration);

        btnSave.setOnClickListener(this);
        btnView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnSave) {
            sNim = Integer.parseInt(nim.getText().toString());
            sName = name.getText().toString();

            if (!sNim.equals("") && !sName.isEmpty()) {
                model = new MahasiswaModel(sNim, sName);
                realmHelper = new RealmHelper(realm);
                realmHelper.save(model);

                Toast.makeText(MainActivity.this, "Berhasil Disimpan!", Toast.LENGTH_SHORT).show();

                nim.setText("");
                name.setText("");
            } else {
                Toast.makeText(MainActivity.this, "Terdapat inputan yang kosong", Toast.LENGTH_SHORT).show();
            }
        } else if (v == btnView) {
            startActivity(new Intent(MainActivity.this, MahasiswaActivity.class));
        }
    }
}
