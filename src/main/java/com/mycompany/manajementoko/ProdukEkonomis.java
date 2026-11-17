package com.mycompany.manajementoko;

public class ProdukEkonomis extends Produk {
    private String kemasan;

    public ProdukEkonomis(String kodeProduk, String namaProduk, int stok, String kemasan) throws Exception {
        super(kodeProduk, namaProduk, stok);
        this.kemasan = kemasan;
    }

    public String getKemasan() {
        return kemasan;
    }

    public void setKemasan(String kemasan) {
        this.kemasan = kemasan;
    }

    @Override
    public String hitungKetentuanProduk() {
        return "Ekonomis: " + kemasan;
    }

    @Override
    public void tampilkanInfoProduk() {
        super.tampilkanInfoProduk();
        System.out.println("-> " + hitungKetentuanProduk());
    }
}