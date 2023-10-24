/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author T450
 */
public class Barang {
    public int id;
    public String nama;
    public String jenis;
    public int stok;
    public String satuan;
    public int idJenis;
    public int idSatuan;
    
    public Barang() { }
    
    public Barang(int id, String nama, String jenis, int stok, String satuan, int idJenis, int idSatuan) {
        this.id = id;
        this.nama = nama;
        this.jenis = jenis;
        this.stok = stok;
        this.satuan = satuan;
        this.idJenis = idJenis;
        this.idSatuan = idSatuan;
    }
}
