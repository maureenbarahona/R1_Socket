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
public class PruebaCliente {

    public static void main(String[] args) {
        Cliente aplicacion; // declara la aplicación cliente
// si no hay argumentos de línea de comandos
        if (args.length == 0) {
            aplicacion = new Cliente("127.0.0.1"); // se conecta a localhost
        } else {
            aplicacion = new Cliente(args[0]); // usa args para conectarse
        }
        aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aplicacion.ejecutarCliente(); // ejecuta la aplicación cliente
    }
}
