package com.mycompany.manajementoko;

public class ProdukPremium extends Produk {
    private String garansi;

    public ProdukPremium(String kodeProduk, String namaProduk, int stok, String garansi) throws Exception {
        super(kodeProduk, namaProduk, stok);
        this.garansi = garansi;
    }

    public String getGaransi() {
        return garansi;
    }

    public void setGaransi(String garansi) {
        this.garansi = garansi;
    }

    @Override
    public String hitungKetentuanProduk() {
        return "Premium: " + garansi;
    }

    @Override
    public void tampilkanInfoProduk() {
        super.tampilkanInfoProduk();
        System.out.println("-> " + hitungKetentuanProduk());
    }
}