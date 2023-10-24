/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.table;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Barang;
import model.Transaksi;

/**
 *
 * @author T450
 */
public class TransaksiModel extends AbstractTableModel {
    private List<Transaksi> data;
    private String[] columnNames = {"Id","Id Barang", "Nama Barang", "Jumlah", "Tanggal"};
    
    public TransaksiModel (List<Transaksi> data) {
        this.data = data;
    }
    
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Transaksi item = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return item.id;
            case 1:
                return item.idBarang;
            case 2:
                return item.namaBarang;
             case 3:
                return item.jumlah;
             case 4:
                return item.tanggal;
            default:
                return null;
        }
    }
}
