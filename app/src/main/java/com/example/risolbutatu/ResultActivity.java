package com.example.risolbutatu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    Button btnShare, btnEmail, btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnShare   = findViewById(R.id.btnShare);
        btnEmail   = findViewById(R.id.btnEmail);
        btnKembali = findViewById(R.id.btnKembali);

        // Tampilkan Fragment detail pesanan
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, new ProfileFragment())
                .commit();

        // =============================================
        // TOMBOL SHARE — Implicit Intent (Share Text)
        // =============================================
        btnShare.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("PESANAN_DATA", MODE_PRIVATE);
            String nama   = prefs.getString("nama", "-");
            String rasa   = prefs.getString("rasa", "-");
            String ukuran = prefs.getString("ukuran", "-");
            String total  = prefs.getString("total", "-");

            String pesanShare =
                    "*Pesanan Risol Bu Tatu*\n" +
                            "Nama  : " + nama + "\n" +
                            "Rasa  : " + rasa + "\n" +
                            "Paket : " + ukuran + "\n" +
                            "Total : " + total + "\n\n" +
                            "Pesan sekarang di Risol Bu Tatu!";

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, pesanShare);
            startActivity(Intent.createChooser(shareIntent, "Bagikan Pesanan via..."));
        });

        // =============================================
        // TOMBOL EMAIL — Implicit Intent (Email)
        // =============================================
        btnEmail.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("PESANAN_DATA", MODE_PRIVATE);
            String nama   = prefs.getString("nama", "-");
            String phone  = prefs.getString("phone", "-");
            String alamat = prefs.getString("alamat", "-");
            String rasa   = prefs.getString("rasa", "-");
            String ukuran = prefs.getString("ukuran", "-");
            String total  = prefs.getString("total", "-");

            String bodyEmail =
                    "Halo Bu Tatu,\n\n" +
                            "Saya ingin mengkonfirmasi pesanan berikut:\n\n" +
                            "Nama     : " + nama   + "\n" +
                            "No. HP   : " + phone  + "\n" +
                            "Alamat   : " + alamat + "\n" +
                            "Rasa     : " + rasa   + "\n" +
                            "Paket    : " + ukuran + "\n" +
                            "Total    : " + total  + "\n\n" +
                            "Mohon konfirmasi pesanan saya. Terima kasih!";

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(android.net.Uri.parse("mailto:"));
            emailIntent.putExtra(Intent.EXTRA_EMAIL,
                    new String[]{"ezrarahmadityaa@gmail.com"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT,
                    "Konfirmasi Pesanan Risol Bu Tatu - " + nama);
            emailIntent.putExtra(Intent.EXTRA_TEXT, bodyEmail);

            startActivity(emailIntent);
        });

        // =============================================
        // TOMBOL KEMBALI — Kembali ke MainActivity
        // =============================================
        btnKembali.setOnClickListener(v -> {
            finish(); // tutup ResultActivity, kembali ke MainActivity
        });
    }
}
