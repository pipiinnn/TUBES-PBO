/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telu.dekstop.model;

/**
 *
 * @author maula
 */
public enum OrderStatus {
    
    Lunas,
    Belum_Lunas;
    
    public String getValue() {
        if(this == Lunas){
            return "Lunas";
        }else if(this == Belum_Lunas){
            return "Belum Lunas";
        }else {
            return "";
        }
    }

    public static OrderStatus fromString(String value) {
        if (value == null || value.isEmpty()) return Belum_Lunas;

        if (value.equalsIgnoreCase("lunas")) return Lunas;      
        else return Belum_Lunas;
    }
}
