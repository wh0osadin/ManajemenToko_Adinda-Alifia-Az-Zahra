package com.mycompany.manajementoko;

import java.io.Serializable;

public abstract class Produk implements Serializable {
    private String kodeProduk;
    private String namaProduk;
    private int stok;
    protected static int totalProdukDibuat = 0;

    public Produk(String kodeProduk, String namaProduk, int stok) throws Exception {
        if (stok < 0) {
            throw new Exception("Stok produk tidak boleh negatif!");
        }
        this.kodeProduk = kodeProduk;
        this.namaProduk = namaProduk;
        this.stok = stok;
        totalProdukDibuat++;
    }

    public String getKodeProduk() {
        return kodeProduk;
    }

    public void setKodeProduk(String kodeProduk) {
        this.kodeProduk = kodeProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public abstract String hitungKetentuanProduk();

    public void tampilkanInfoProduk() {
        System.out.println("Kode: " + kodeProduk + " | Produk: " + namaProduk + " | Stok: " + stok);
    }
}