# UTS MOBILE PROGRAMMING-AL
Repo ini berisi program untuk UTS mata kuliah MOBILE PROGRAMMING-AL

2411501865 Zahfandhika Fauzan Maldini<br><br>
2411501642 Raden Ezra Rahmaditya

```
RisolBuTatu/
├── app/src/main/
│   ├── java/com/example/risolbutatu/
│   │   ├── MainActivity.java
│   │   ├── ResultActivity.java
│   │   └── ProfileFragment.java
│   └── res/
│       ├── layout/
│       │   ├── activity_main.xml
│       │   ├── activity_result.xml
│       │   └── fragment_profile.xml
│       └── drawable/
│           └── risol_image.png
```

---
 
## Layout Files — `res/layout/`
 
- `activity_main.xml` : Halaman utama pemesanan — form input nama, nomor HP, alamat, pilihan rasa (Spinner), ukuran paket (RadioGroup), dan tambahan opsional (CheckBox).
- `activity_result.xml` : Halaman konfirmasi pesanan berhasil — menampilkan header sukses, container Fragment, dan tombol aksi (Share, Email, Kembali).
- `fragment_profile.xml` : Layout Fragment detail pesanan — menampilkan semua data pemesan beserta total harga yang dibaca dari SharedPreferences.


---

## Java Files — `java/com/example/risolbutatu/`
 
- `MainActivity.java` : Activity utama. Mengelola form input, validasi data, penyimpanan ke SharedPreferences, serta **Explicit Intent** ke `ResultActivity` dan **Implicit Intent** ke WhatsApp.
- `ResultActivity.java` : Activity hasil pesanan. Memuat `ProfileFragment` dan menangani **Implicit Intent** untuk Share teks dan kirim Email konfirmasi.
- `ProfileFragment.java` : Fragment yang membaca data pesanan dari SharedPreferences dan menampilkannya ke `TextView` di `fragment_profile.xml`.


---
 
## Drawable Files — `res/drawable/`
 
- `risol_image.png` : Gambar header risol yang ditampilkan di bagian atas halaman utama via `ImageView` (`android:src="@drawable/risol_image"`).
