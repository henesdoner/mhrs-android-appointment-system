package com.example.hastanerandevu;

import android.os.Handler;
import android.os.Looper;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class GeminiYardimci {
    // BURAYA ANAHTARINI YAPIŞTIR
    private final String API_KEY = "AIzaSyDUF9W7z_2VmgJYIG1gP7nSjzO0TT7K9RE";

    public void bolumTavsiyeEt(String sikayet, AIInterface callback) {
        Executors.newSingleThreadExecutor().execute(() -> {
            HttpURLConnection conn = null;
            try {
                // 404 HATASINI ÇÖZEN EN TEMEL URL YAPISI
                URL url = new URL("https://generativelanguage.googleapis.com/v1alpha/models/gemini-3-flash-preview:generateContent?key=" + API_KEY);

                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                conn.setDoOutput(true);

                // Gönderilen veriyi en basit hale getirdik
                String jsonInput = "{\"contents\":[{\"parts\":[{\"text\":\"Sen bir hastane asistanısın. Kullanıcı şikayeti: " + sikayet + ". Hangi tıbbi bölüme gidilmesi gerektiğini söyle ve nedenini tek bir kısa cümleyle açıkla. Örn: Ortopedi - Kemik ve eklem ağrılarıyla bu bölüm ilgilenir.\"}]}]}";

                try (OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8)) {
                    writer.write(jsonInput);
                    writer.flush();
                }

                int responseCode = conn.getResponseCode();
                if (responseCode != 200) {
                    // Hata kodunu göstererek sorunu anlamamızı sağlar
                    throw new Exception("Sunucu Hatası: " + responseCode);
                }

                Scanner scanner = new Scanner(conn.getInputStream(), "UTF-8");
                StringBuilder response = new StringBuilder();
                while (scanner.hasNextLine()) response.append(scanner.nextLine());

                JSONObject jsonResponse = new JSONObject(response.toString());
                String result = jsonResponse.getJSONArray("candidates")
                        .getJSONObject(0)
                        .getJSONObject("content")
                        .getJSONArray("parts")
                        .getJSONObject(0)
                        .getString("text");

                new Handler(Looper.getMainLooper()).post(() -> callback.sonucGeldiginde(result.trim()));

            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> callback.hataOldugunda("Hata: " + e.getMessage()));
            } finally {
                if (conn != null) conn.disconnect();
            }
        });
    }

    public interface AIInterface {
        void sonucGeldiginde(String bolum);
        void hataOldugunda(String hata);
    }
}