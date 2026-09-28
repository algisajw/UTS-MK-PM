package com.Algis_Asmaradhana_JW_F52124032.aplikasi_uts;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CustomAdapter adapter;
    private String[] names = {
            "Algis Asmaradhana JW",
            "Mega Saputri",
            "Adhit Juliyono"
    };
    private String[] phones = {
            "085150948757",
            "081241494696",
            "081245672958"
    };
    private String[] descs = {
            "Teknik Elektro",
            "Sistem Informasi",
            "Pendidikan Guru PAUD"
    };
    private int[] images = {
            R.drawable.foto_1,
            R.drawable.foto_2,
            R.drawable.foto_3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        EditText editSearch = findViewById(R.id.editSearch);

        adapter = new CustomAdapter(this, names, phones, descs, images);
        listView.setAdapter(adapter);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}