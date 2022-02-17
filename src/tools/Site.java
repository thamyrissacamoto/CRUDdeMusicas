/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.net.URL;
import java.awt.Desktop;
import java.net.URI;

/**
 *
 * @author karen
 */
public class Site {

    URL site = Site.class.getResource("https://www.vaticannews.va/pt/santo-do-dia.html");

    public class AbrirLink {

        {
             try {
                URI link = new URI("https://www.vaticannews.va/pt/santo-do-dia.html");
                Desktop.getDesktop().browse(link);
            } catch (Exception erro) {
                System.out.println(erro);
            }
        }

    }


    public void parar() {
        System.out.println("\"https://www.vaticannews.va/pt/santo-do-dia.html\"");
    }

}