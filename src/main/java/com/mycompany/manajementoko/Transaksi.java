package com.mycompany.manajementoko;

import java.io.Serializable;

public class Transaksi implements Serializable {
    private Pelanggan pelanggan;
    private Produk produk;
    private int jumlah;

    public Transaksi(Pelanggan pelanggan, Produk produk, int jumlah) {
        this.pelanggan = pelanggan;
        this.produk = produk;
        this.jumlah = jumlah;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}