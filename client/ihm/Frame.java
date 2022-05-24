package client.ihm;

import client.Client;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.BorderLayout;

public class Frame extends JFrame {
    private PanelServeurs panelServeurs;

    private Client       client;


    public Frame(Client c) {
        this.client = c;
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width  = (int)dimension.getWidth ();

        
        this.setSize      ( 1500, 1000 );
        this.setLocation  ( width/2 - this.getWidth()/2, height/2 - this.getHeight()/2 );
        this.setLayout    ( new BorderLayout() );

        //création des éléments
        this.panelServeurs = new PanelServeurs( this.client );    
        

        //positionnement      
        this.add(this.panelServeurs, BorderLayout.CENTER );
              
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        if ( Client.nbCo() ) {
            this.setVisible( false );
            new FrameConnexion( this.client );      
        } else {
            this.setVisible( true );
        }
        
        
    }

    public void majPseudo() {
        this.panelServeurs.majPseudo();
        System.out.println("a");
    }
    
}
