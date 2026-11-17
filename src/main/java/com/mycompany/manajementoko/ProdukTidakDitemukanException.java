package com.mycompany.manajementoko;

public class ProdukTidakDitemukanException extends Exception {
    public ProdukTidakDitemukanException(String pesan) {
        super(pesan);
    }
}