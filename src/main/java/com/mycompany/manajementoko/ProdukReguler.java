package com.mycompany.manajementoko;

public class ProdukReguler extends Produk {
    private String ukuran;

    public ProdukReguler(String kodeProduk, String namaProduk, int stok, String ukuran) throws Exception {
        super(kodeProduk, namaProduk, stok);
        this.ukuran = ukuran;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    @Override
    public String hitungKetentuanProduk() {
        return "Reguler: " + ukuran;
    }

    @Override
    public void tampilkanInfoProduk() {
        super.tampilkanInfoProduk();
        System.out.println("-> " + hitungKetentuanProduk());
    }
}