/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.table;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Satuan;

/**
 *
 * @author T450
 */
public class SatuanModel extends AbstractTableModel {
    private List<Satuan> data;
    private String[] columnNames = {"Id","Nama"};
    
    public SatuanModel (List<Satuan> data) {
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
        Satuan item = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return item.id;
            case 1:
                return item.nama;
            default:
                return null;
        }
    }
}
