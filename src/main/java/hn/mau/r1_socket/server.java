/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.mau.r1_socket;

import java.net.*;
import java.io.*;

/**
 *
 * @author maureen
 */
public class server {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1768);
        Socket s = ss.accept();

        System.out.println("cliente conectado");

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String str = bf.readLine();
        System.out.println("cliente :" + str);

        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println("hola");
        pr.flush();
    }

}
