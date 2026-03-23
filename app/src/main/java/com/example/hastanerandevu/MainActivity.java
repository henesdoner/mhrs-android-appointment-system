package com.example.hastanerandevu;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ekran elemanlarını koda bağlıyoruz
        EditText etSikayet = findViewById(R.id.etSikayet);
        Button btnSorgula = findViewById(R.id.btnSorgula);
        TextView tvSonuc = findViewById(R.id.tvSonuc);

        // Yapay Zeka sınıfımızı tanımlıyoruz
        GeminiYardimci aiYardimci = new GeminiYardimci();

        btnSorgula.setOnClickListener(v -> {
            String sikayet = etSikayet.getText().toString();

            if (sikayet.isEmpty()) {
                tvSonuc.setText("Lütfen önce bir şikayet yazın!");
                return;
            }

            tvSonuc.setText("Yapay Zeka analiz ediyor...");

            // Yapay zekaya soruyoruz
            aiYardimci.bolumTavsiyeEt(sikayet, new GeminiYardimci.AIInterface() {
                @Override
                public void sonucGeldiginde(String bolum) {
                    // Sonucu ekranda gösteriyoruz
                    runOnUiThread(() -> tvSonuc.setText("Önerilen Bölüm: " + bolum));
                }

                @Override
                public void hataOldugunda(String hata) {
                    runOnUiThread(() -> tvSonuc.setText("Hata: " + hata));
                }
            });
        });
    }
}