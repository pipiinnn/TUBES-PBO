/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telu.dekstop.model;

/**
 *
 * @author maula
 */
public enum PaymentMethod {
    BAYAR_DI_KASIR("Bayar di Kasir"),
    QRIS("QRIS");
    
    private final String value;

    PaymentMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PaymentMethod fromString(String text) {
        for (PaymentMethod method : PaymentMethod.values()) {
            if (method.getValue().equalsIgnoreCase(text)) {
                return method;
            }
        }
        return BAYAR_DI_KASIR; // default jika tidak dikenali
    }
}


