/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.mau.socket1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author maureen
 */
public class Cliente extends JFrame {

    private JTextField campoIntroducir;
    private JTextArea areaPantalla;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private String mensaje = "";
    private String servidorChat;
    private Socket cliente;

    // inicializa el objeto servidorChat y establece la GUI
    public Cliente(String host) {
        super("Cliente");
        servidorChat = host; // establece el servidor al que se conecta este cliente
        campoIntroducir = new JTextField(); // crea objeto campoIntroducir
        campoIntroducir.setEditable(false);
        campoIntroducir.addActionListener((ActionEvent evento) -> {
            enviarDatos(evento.getActionCommand());
            campoIntroducir.setText("");
        } // fin del método actionPerformed
        // envía el mensaje al servidor
        // fin de la clase interna anónima
        ); // fin de la llamada a addActionListener
        add(campoIntroducir, BorderLayout.NORTH);
        areaPantalla = new JTextArea(); // crea objeto areaPantalla
        add(new JScrollPane(areaPantalla), BorderLayout.CENTER);
        setSize(300, 150); // establece el tamaño de la ventana
        setVisible(true); // muestra la ventana
    } // fin del constructor de Cliente

    public void ejecutarCliente() {
        try {
            conectarAlServidor();
            obtenerFlujos();
            procesarConexion();

        } catch (IOException e) {
            mostrarMensaje("\nCliente termino la conexion");
        } finally {
            cerrarConexion(); // cierra la conexión
        }
    }

    private void conectarAlServidor() throws IOException {
        mostrarMensaje("Intentando realizar conexion\n");
// crea objeto Socket para hacer conexión con el servidor
        cliente = new Socket(InetAddress.getByName(servidorChat), 12345);
// muestra la información de la conexión
        mostrarMensaje("Conectado a: "
                + cliente.getInetAddress().getHostName());
    } // fin del método conectarAlServidor

    private void obtenerFlujos() throws IOException {
        salida = new ObjectOutputStream(cliente.getOutputStream());
        salida.flush();

        entrada = new ObjectInputStream(cliente.getInputStream());

        mostrarMensaje("\nSe obtuvieron los flujos de E/S\n");
    }

    private void procesarConexion() throws IOException {
        establecerCampoEditable(true);
        do {
            try {
                mensaje = (String) entrada.readObject();
                mostrarMensaje("\n" + mensaje);

            } catch (IOException | ClassNotFoundException e) {
                mostrarMensaje("nSe recibio un tipo de objeto desconocido");
            }
        } while (!mensaje.equals("SERVIDOR>>> TERMINAR"));

    }

    private void cerrarConexion() {
        mostrarMensaje("\nCerrando conexion");
        establecerCampoEditable(false); // deshabilita campoIntroducir
        try {
            salida.close(); // cierra el flujo de salida
            entrada.close(); // cierra el flujo de entrada
            cliente.close(); // cierra el socket
        } // fin de try
        catch (IOException excepcionES) {
            excepcionES.printStackTrace();
        } // fin de catch
    } // fin del método cerrarConexion

    private void enviarDatos(String mensaje) {
        try // envía un objeto al servidor
        {
            salida.writeObject("CLIENTE>>> " + mensaje);
            salida.flush(); // envía todos los datos a la salida
            mostrarMensaje("\nCLIENTE>>> " + mensaje);
        } // fin de try
        catch (IOException excepcionES) {
            areaPantalla.append("\nError al escribir objeto");
        } // fin de catch
    } // fin del método enviarDatos

    private void mostrarMensaje(final String mensajeAMostrar) {
        SwingUtilities.invokeLater(() -> {
            areaPantalla.append(mensajeAMostrar);
        });

    }

    private void establecerCampoEditable(final boolean editable) {
        SwingUtilities.invokeLater(() -> {
            campoIntroducir.setEditable(editable);
        });
    }

}
