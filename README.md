# T4-Mobile

## Nama : Fadila Rahmania
## NIM : F1D02310048

## Deskripsi
Aplikasi ini merupakan sistem pengelolaan data mahasiswa berbasis Android yang dikembangkan menggunakan bahasa Kotlin. Pembangunannya dilakukan sebagai bagian dari Tugas Akhir pada mata kuliah Mobile Programming. Fitur utama yang disediakan mencakup autentikasi pengguna sederhana, pengolahan data mahasiswa dengan operasi CRUD (Create, Read, Update, Delete), pencarian data secara real-time, serta fitur kustomisasi profil pengguna.

## Dokumentasi Aplikasi 
**1. Halaman Login** Memiliki fitur "Remember Me" agar user tidak perlu login berulang kali. 
<img width="419" height="919" alt="Screenshot 2026-05-05 105701" src="https://github.com/user-attachments/assets/4d670598-75c9-478e-8b58-38ef1b9f451b" />


**2. Halaman Utama**
<img width="421" height="922" alt="Screenshot 2026-05-05 105711" src="https://github.com/user-attachments/assets/029524d4-7a42-42ea-b976-e411a957ee3c" />


**3. Halaman Profile** Berisi pengaturan aplikasi (Dark Mode, Font Size, Notifikasi) dan tombol Logout untuk menghapus sesi login. 
<img width="419" height="916" alt="Screenshot 2026-05-05 105726" src="https://github.com/user-attachments/assets/d0f35ae1-da1f-4fe0-ae2a-df8cd4cf1cbd" />

## Metode Penyimpanan Data ##
Aplikasi ini menggunakan tiga cara penyimpanan data di Android:

**1. Room Database (SQLite)**

Digunakan untuk menyimpan data mahasiswa (Nama, NIM, Prodi, Email, Semester). Cocok untuk data yang sering di-update dan ditampilkan secara real-time.

**2. SharedPreferences**

Digunakan untuk menyimpan data sederhana seperti status login, username, dan pengaturan (misalnya Dark Mode).

**3. Internal Storage**

Digunakan untuk menyimpan catatan pada halaman detail mahasiswa dalam bentuk file teks yang bersifat aman (private).

## Kendala dan Solusi
**1. Aplikasi Tidak Bisa Dijalankan**

Masalah: Aplikasi sering gagal dijalankan karena error dari Gradle.



