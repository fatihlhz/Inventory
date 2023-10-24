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
public class ComboItem {
    private String displayText;
    private int value;
    
    public ComboItem(String displayText, int value) {
        this.displayText = displayText;
        this.value = value;
    }
    
    public String getDisplayText() {
        return displayText;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return displayText;
    }
}
