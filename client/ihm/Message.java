package client.ihm;

import javax.swing.*;

import client.Client;

import java.awt.BorderLayout;

import java.awt.Color;



public class Message extends JPanel{
    
    public Message( JLabel lbl) {
        this.setLayout( new BorderLayout());

        JLabel nom = new JLabel(lbl.getName());
        nom.setForeground( new Color ( 255, 255, 255 ));

        if (lbl.getText().equals("a rejoint")) {
            Client.style(lbl, 'V');
        } else if (lbl.getText().equals("a quitté")) {
            Client.style(lbl, 'R');
        } else {
            Client.style(lbl, 'M');
        }

        Client.style(nom, 'T');
        

        this.add( lbl, BorderLayout.CENTER );
        this.add( nom, BorderLayout.NORTH  );
    
        this.setOpaque ( false );
        this.setVisible( true );
    }
}
