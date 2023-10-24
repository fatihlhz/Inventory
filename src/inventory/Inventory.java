/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import view.*;
import view.form.*;

/**
 *
 * @author Acer
 */
public class Inventory {
    private static Login login = new Login();
    public static Dashboard dashboard = new Dashboard();
    public static DataBarang dataBarang = new DataBarang();
    public static JenisBarang jenisBarang = new JenisBarang();
    public static SatuanBarang satuanBarang = new SatuanBarang();
    public static BarangMasuk barangMasuk = new BarangMasuk();
    public static BarangKeluar barangKeluar = new BarangKeluar();
    
    public static FormBarang formBarang = new FormBarang();
    public static FormBrgMasuk formBrgMasuk = new FormBrgMasuk();
    public static FormBrgKeluar formBrgKeluar = new FormBrgKeluar();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        login.setVisible(true);
    }
    
}
