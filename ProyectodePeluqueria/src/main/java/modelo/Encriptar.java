/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

/**
 *
 * @author Jonathan Flores
 */
public class Encriptar {

    private static final String ALGORITMO = "AES";
    private static final String CLAVE_SECRETA = "murcielago";

    // Método para obtener la clave secreta AES a partir de un texto
    private static SecretKeySpec obtenerClaveSecreta(String clave) throws Exception {
        byte[] claveBytes = clave.getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        claveBytes = sha.digest(claveBytes);
        claveBytes = Arrays.copyOf(claveBytes, 16); // Solo utilizamos los primeros 128 bits
        return new SecretKeySpec(claveBytes, ALGORITMO);
    }

    // Método para encriptar un texto
    public static String encriptar(String texto) {
        try {
            SecretKeySpec claveSecreta = obtenerClaveSecreta(CLAVE_SECRETA);
            Cipher cifrador = Cipher.getInstance(ALGORITMO);
            cifrador.init(Cipher.ENCRYPT_MODE, claveSecreta);
            byte[] textoEncriptadoBytes = cifrador.doFinal(texto.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(textoEncriptadoBytes);
        } catch (Exception e) {
            System.out.println("Error al encriptar: " + e.toString());
        }
        return null;
    }

    // Método para desencriptar un texto encriptado
    public static String desencriptar(String textoEncriptado) {
        try {
            SecretKeySpec claveSecreta = obtenerClaveSecreta(CLAVE_SECRETA);
            Cipher cifrador = Cipher.getInstance(ALGORITMO);
            cifrador.init(Cipher.DECRYPT_MODE, claveSecreta);
            byte[] textoEncriptadoBytes = Base64.getDecoder().decode(textoEncriptado);
            byte[] textoDesencriptadoBytes = cifrador.doFinal(textoEncriptadoBytes);
            return new String(textoDesencriptadoBytes, "UTF-8");
        } catch (Exception e) {
            System.out.println("Error al desencriptar: " + e.toString());
        }
        return null;
    }

 
}