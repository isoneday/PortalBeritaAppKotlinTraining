package com.imastudio.portalberitaapp.model;

public class Mahasiswa {
String nama;
String nim;
String alamat;
String nohp;

    public Mahasiswa() {
    }

    public Mahasiswa(String nama, String nim, String alamat, String nohp) {
        this.nama = nama;
        this.nim = nim;
        this.alamat = alamat;
        this.nohp = nohp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }
}
