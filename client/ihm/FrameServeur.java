package client.ihm;

import client.Client;

import javax.swing.*;

import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.BorderLayout;

public class FrameServeur extends JFrame {

    private PanelMessages panelMessages;
    private PanelGauche   panelGauche;

    private Client       client;


    public FrameServeur(Client c) {
        this.client = c;

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width  = (int)dimension.getWidth ();

        
        this.setSize      ( 1500, 1000 );
        this.setLocation  ( width/2 - this.getWidth()/2, height/2 - this.getHeight()/2 );
        this.setLayout    ( new BorderLayout() );

        //création des éléments
        this.panelGauche   = new PanelGauche  ( this.client );
        this.panelMessages = new PanelMessages( this.client );

        //positionnement
        this.add(this.panelMessages, BorderLayout.CENTER );
        this.add(this.panelGauche  , BorderLayout.WEST   );
        
              
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 

        this.setVisible( false );
    }

    public void majPseudo() {
        this.panelGauche.majPseudo();
    }

    public void majMessages( ArrayList<JLabel> alLbl ) {
        this.panelMessages.maj( alLbl );
    }

    public void majUsers( ArrayList<JLabel> alLbl ) {
        this.panelGauche.majUsers( alLbl );
    }

    public void clear() {
        this.getContentPane().removeAll();

        this.panelGauche   = new PanelGauche  ( this.client );
        this.panelMessages = new PanelMessages( this.client );

        this.add(this.panelMessages, BorderLayout.CENTER );
        this.add(this.panelGauche  , BorderLayout.WEST   );

        validate();
        this.client.repaint();
    }
}