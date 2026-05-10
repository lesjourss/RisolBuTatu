package com.example.risolbutatu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Ambil semua view dari fragment
        TextView tvNama     = view.findViewById(R.id.tvNama);
        TextView tvPhone    = view.findViewById(R.id.tvPhone);
        TextView tvAlamat   = view.findViewById(R.id.tvAlamat);
        TextView tvRasa     = view.findViewById(R.id.tvRasa);
        TextView tvUkuran   = view.findViewById(R.id.tvUkuran);
        TextView tvTambahan = view.findViewById(R.id.tvTambahan);
        TextView tvTotal    = view.findViewById(R.id.tvTotal);

        // Ambil data dari SharedPreferences
        SharedPreferences prefs = requireActivity()
                .getSharedPreferences("PESANAN_DATA", Context.MODE_PRIVATE);

        String nama     = prefs.getString("nama", "-");
        String phone    = prefs.getString("phone", "-");
        String alamat   = prefs.getString("alamat", "-");
        String rasa     = prefs.getString("rasa", "-");
        String ukuran   = prefs.getString("ukuran", "-");
        String tambahan = prefs.getString("tambahan", "Tidak ada");
        String total    = prefs.getString("total", "Rp 0");

        // Tampilkan ke TextView
        tvNama.setText("Nama      : " + nama);
        tvPhone.setText("No. HP    : " + phone);
        tvAlamat.setText("Alamat    : " + alamat);
        tvRasa.setText("Rasa      : " + rasa);
        tvUkuran.setText("Paket     : " + ukuran);
        tvTambahan.setText("Tambahan  : " + tambahan);
        tvTotal.setText("Total     : " + total);
    }
}

