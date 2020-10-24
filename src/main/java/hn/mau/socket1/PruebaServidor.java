/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.mau.socket1;

import javax.swing.JFrame;

/**
 *
 * @author maureen
 */
public class PruebaServidor {

    public static void main(String[] args) {
        Servidor aplicacion = new Servidor();
        aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aplicacion.ejecutarServidor();
    }
}
