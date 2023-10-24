/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.table;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Barang;

/**
 *
 * @author T450
 */
public class BarangModel extends AbstractTableModel {
    private List<Barang> data;
    private String[] columnNames = {"Id","Nama", "Jenis", "Stok", "Satuan"};
    
    public BarangModel (List<Barang> data) {
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
        Barang item = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return item.id;
            case 1:
                return item.nama;
            case 2:
                return item.jenis;
             case 3:
                return item.stok;
             case 4:
                return item.satuan;
            default:
                return null;
        }
    }
}
