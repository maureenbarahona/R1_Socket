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
public class Client {
    public static void main(String[] args) throws IOException{
        Socket s = new Socket("localhost", 1768);
        
        PrintWriter pr = new PrintWriter (s.getOutputStream());
        pr.println("hello");
        pr.flush();
        
    }
}
