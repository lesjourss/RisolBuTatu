package com.example.risolbutatu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Deklarasi semua widget
    EditText etNama, etPhone, etAlamat;
    Spinner spinnerRasa;
    RadioGroup rgUkuran;
    CheckBox cbSausSambal, cbSausMayo, cbBubbleWrap, cbSetuju;
    Button btnPesan, btnHubungi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi semua widget
        etNama        = findViewById(R.id.etNama);
        etPhone       = findViewById(R.id.etPhone);
        etAlamat      = findViewById(R.id.etAlamat);
        spinnerRasa   = findViewById(R.id.spinnerRasa);
        rgUkuran      = findViewById(R.id.rgUkuran);
        cbSausSambal  = findViewById(R.id.cbSausSambal);
        cbSausMayo    = findViewById(R.id.cbSausMayo);
        cbBubbleWrap  = findViewById(R.id.cbBubbleWrap);
        cbSetuju      = findViewById(R.id.cbSetuju);
        btnPesan      = findViewById(R.id.btnPesan);
        btnHubungi    = findViewById(R.id.btnHubungi);

        // Setup Spinner varian rasa
        String[] rasaList = {
                "-- Pilih Rasa --",
                "Coklat Lumer",
                "Matcha Keju",
                "Smoked Beef Mayo",
                "Ayam Pedas Spicy",
                "Ragout Sayur Ayam",
                "Crab Mentai",
                "Pisang Coklat",
                "Kornet Keju"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                rasaList
        );
        spinnerRasa.setAdapter(adapter);

        // =============================================
        // TOMBOL PESAN — Explicit Intent
        // =============================================
        btnPesan.setOnClickListener(v -> {
            if (validasiInput()) {
                simpanKeSharedPreferences();
                // Explicit Intent: pindah ke ResultActivity
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });

        // =============================================
        // TOMBOL HUBUNGI — Implicit Intent (WhatsApp/Telepon)
        // =============================================
        btnHubungi.setOnClickListener(v -> {
            String nomorWA = "6282113118380"; // ganti dengan nomor Bu Tatu
            String pesan   = "Halo Bu Tatu, saya ingin memesan risol!";
            try {
                Intent waIntent = new Intent(Intent.ACTION_VIEW);
                waIntent.setData(Uri.parse(
                        "https://wa.me/" + nomorWA + "?text=" +
                                Uri.encode(pesan)
                ));
                startActivity(waIntent);
            } catch (Exception e) {
                // Fallback ke dial telepon biasa
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:+6281234567890"));
                startActivity(dialIntent);
            }
        });

        // =============================================
        // TOMBOL LIHAT MENU — Implicit Intent (Browser)
        // =============================================

    }

    // =============================================
    // VALIDASI SEMUA INPUT
    // =============================================
    private boolean validasiInput() {

        String nama   = etNama.getText().toString().trim();
        String phone  = etPhone.getText().toString().trim();
        String alamat = etAlamat.getText().toString().trim();

        // Cek nama tidak kosong
        if (nama.isEmpty()) {
            etNama.setError("Nama tidak boleh kosong!");
            etNama.requestFocus();
            return false;
        }

        // Cek nama minimal 3 karakter
        if (nama.length() < 3) {
            etNama.setError("Nama minimal 3 karakter!");
            etNama.requestFocus();
            return false;
        }

        // Cek nomor HP tidak kosong
        if (phone.isEmpty()) {
            etPhone.setError("Nomor HP tidak boleh kosong!");
            etPhone.requestFocus();
            return false;
        }

        // Cek nomor HP hanya angka dan minimal 10 digit
        if (!phone.matches("[0-9]+") || phone.length() < 10) {
            etPhone.setError("Nomor HP harus angka minimal 10 digit!");
            etPhone.requestFocus();
            return false;
        }

        // Cek alamat tidak kosong
        if (alamat.isEmpty()) {
            etAlamat.setError("Alamat tidak boleh kosong!");
            etAlamat.requestFocus();
            return false;
        }

        // Cek Spinner rasa dipilih (index 0 = "-- Pilih Rasa --")
        if (spinnerRasa.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Silakan pilih rasa risol!", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Cek RadioButton ukuran dipilih
        if (rgUkuran.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Silakan pilih ukuran paket!", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Cek CheckBox persetujuan dicentang
        if (!cbSetuju.isChecked()) {
            Toast.makeText(this,
                    "Anda harus menyetujui syarat dan ketentuan!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        return true; // semua valid
    }

    // =============================================
    // SIMPAN DATA KE SHAREDPREFERENCES
    // =============================================
    private void simpanKeSharedPreferences() {

        String nama   = etNama.getText().toString().trim();
        String phone  = etPhone.getText().toString().trim();
        String alamat = etAlamat.getText().toString().trim();
        String rasa   = spinnerRasa.getSelectedItem().toString();

        // Ambil ukuran dari RadioButton
        int selectedId = rgUkuran.getCheckedRadioButtonId();
        RadioButton rbSelected = findViewById(selectedId);
        String ukuran = rbSelected.getText().toString();

        // Hitung total harga
        int hargaPokok = 0;
        if (selectedId == R.id.rbKecil)  hargaPokok = 15000;
        if (selectedId == R.id.rbSedang) hargaPokok = 28000;
        if (selectedId == R.id.rbBesar)  hargaPokok = 50000;

        int hargaTambahan = 0;
        StringBuilder tambahanStr = new StringBuilder();

        if (cbSausSambal.isChecked()) {
            hargaTambahan += 2000;
            tambahanStr.append("Saus Sambal, ");
        }
        if (cbSausMayo.isChecked()) {
            hargaTambahan += 3000;
            tambahanStr.append("Saus Mayo, ");
        }
        if (cbBubbleWrap.isChecked()) {
            hargaTambahan += 2000;
            tambahanStr.append("Bubble Wrap, ");
        }

        String tambahan = tambahanStr.length() > 0
                ? tambahanStr.toString().replaceAll(", $", "")
                : "Tidak ada";

        int totalHarga = hargaPokok + hargaTambahan;
        String totalStr = "Rp " + String.format("%,d", totalHarga)
                .replace(',', '.');

        // Simpan ke SharedPreferences
        SharedPreferences prefs = getSharedPreferences("PESANAN_DATA", MODE_PRIVATE);
        prefs.edit()
                .putString("nama", nama)
                .putString("phone", phone)
                .putString("alamat", alamat)
                .putString("rasa", rasa)
                .putString("ukuran", ukuran)
                .putString("tambahan", tambahan)
                .putString("total", totalStr)
                .apply();
    }
}
