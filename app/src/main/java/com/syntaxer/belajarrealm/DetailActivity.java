package com.syntaxer.belajarrealm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etNim, etName;
    String nim, name;
    Integer id;
    Button btnChange, btnDelete, btnBack;
    RealmHelper realmHelper;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //TODO: Set up
        Realm.init(this);
        RealmConfiguration configuration = Realm.getDefaultConfiguration();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        //TODO: Inisialisasi
        etNim = findViewById(R.id.etNim);
        etName = findViewById(R.id.etNama);
        btnChange = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnHapus);
        btnBack = findViewById(R.id.btnCancel);

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        nim = getIntent().getStringExtra("nim");
        name = getIntent().getStringExtra("nama");

        etNim.setText(nim);
        etName.setText(name);

        btnBack.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnChange.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnChange){
            realmHelper.update(id, Integer.parseInt(etNim.getText().toString()),etName.getText().toString());
            Toast.makeText(DetailActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
            etNim.setText("");
            etName.setText("");
            finish();
        }else if (v == btnDelete) {
            realmHelper.delete(id);
            Toast.makeText(DetailActivity.this, "Delete Success", Toast.LENGTH_SHORT).show();
            finish();
        }else if (v == btnBack) {
            startActivity(new Intent(DetailActivity.this, MahasiswaActivity.class));
            finish();
        }
    }
}
