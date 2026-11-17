package com.mycompany.manajementoko;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SistemToko implements Serializable {
    private String namaToko;
    private HashMap<String, Pelanggan> daftarPelanggan;
    private HashMap<String, Produk> mapProduk;
    private ArrayList<Produk> daftarProduk;
    private ArrayList<Transaksi> daftarTransaksi;

    public SistemToko(String namaToko) {
        this.namaToko = namaToko;
        this.daftarPelanggan = new HashMap<>();
        this.mapProduk = new HashMap<>();
        this.daftarProduk = new ArrayList<>();
        this.daftarTransaksi = new ArrayList<>();
    }

    public void tambahPelanggan(String id, String nama) {
        daftarPelanggan.put(id, new Pelanggan(id, nama));
    }

    public void tambahProduk(String kode, String nama, int stok) {
        try {
            Produk p;
            if (kode.startsWith("OBJ-A")) {
                p = new ProdukPremium(kode, nama, stok, "Garansi Resmi 2 Tahun");
            } else if (kode.startsWith("OBJ-B")) {
                p = new ProdukReguler(kode, nama, stok, "All Size");
            } else {
                p = new ProdukEkonomis(kode, nama, stok, "Kemasan Praktis");
            }
            mapProduk.put(kode, p);
            daftarProduk.add(p);
        } catch (Exception e) {
            System.out.println("[ERROR PRODUK] " + e.getMessage());
        }
    }

    public void jalankanTransaksiDariFile(String namaFile) throws Exception {
        String dataTransaksi = "ID-ACT-01/KD-OBJ-A/JML-3/|ID-ACT-02/KD-OBJ-C/JML-2/|ID-ACT-01/KD-OBJ-B/JML-5/|ID-ACT-99/KD-OBJ-A/JML-1/|ID-ACT-02/KD-OBJ-A/JML-1/";

        String[] daftarBaris = dataTransaksi.split("\\|");

        for (String baris : daftarBaris) {
            baris = baris.trim();
            if (baris.isEmpty()) continue;

            try {
                String[] bagian = baris.split("/");
                String idPelanggan = "";
                String kodeProduk = "";
                int jumlah = 0;

                for (String part : bagian) {
                    if (part.isEmpty()) continue;
                    int idxStrip = part.indexOf("-");
                    if (idxStrip != -1) {
                        String key = part.substring(0, idxStrip).trim();
                        String val = part.substring(idxStrip + 1).trim();

                        if (key.equalsIgnoreCase("ID")) {
                            idPelanggan = val;
                        } else if (key.equalsIgnoreCase("KD")) {
                            kodeProduk = val;
                        } else if (key.equalsIgnoreCase("JML")) {
                            jumlah = Integer.parseInt(val);
                        }
                    }
                }

                if (!daftarPelanggan.containsKey(idPelanggan)) {
                    throw new ProdukTidakDitemukanException("ID Pelanggan '" + idPelanggan + "' tidak terdaftar!");
                }
                if (!mapProduk.containsKey(kodeProduk)) {
                    throw new ProdukTidakDitemukanException("Kode Produk '" + kodeProduk + "' tidak valid!");
                }

                Pelanggan p = daftarPelanggan.get(idPelanggan);
                Produk prod = mapProduk.get(kodeProduk);

                if (prod.getStok() < jumlah) {
                    throw new Exception("Stok produk " + prod.getNamaProduk() + " tidak mencukupi!");
                }

                prod.setStok(prod.getStok() - jumlah);
                Transaksi trx = new Transaksi(p, prod, jumlah);
                daftarTransaksi.add(trx);
                System.out.println("[SUKSES] " + p.getNamaPelanggan() + " membeli " + jumlah + "x " + prod.getNamaProduk());

            } catch (Exception e) {
                System.out.println("[TRANSAKSI ERROR] Baris gagal diproses (" + baris + ") -> " + e.getMessage());
            }
        }
    }

    public void simpanLaporanAkhir() {
        try {
            FileOutputStream fos = new FileOutputStream("archive_mem.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            System.out.println("[SERIALIZATION] Berhasil menyimpan status ke 'archive_mem.bin'.");
        } catch (IOException e) {
            System.out.println("[SERIALIZATION ERROR] " + e.getMessage());
        }

        try {
            FileWriter writer = new FileWriter("laporan.txt");
            writer.write("=== LAPORAN SISA STOK PRODUK (" + namaToko + ") ===\n");
            for (Produk prod : daftarProduk) {
                writer.write("Kode: " + prod.getKodeProduk() + " | Nama: " + prod.getNamaProduk() + " | Sisa Stok: " + prod.getStok() + "\n");
            }
            writer.close();
            System.out.println("[FILE I/O] Sisa kuota objek berhasil diekspor ke 'laporan.txt'.");
        } catch (IOException e) {
            System.out.println("[FILE ERROR] " + e.getMessage());
        }
    }
}