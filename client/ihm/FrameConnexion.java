package client.ihm;

import client.Client;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.*;
import java.awt.Color;


import java.awt.Component;


public class FrameConnexion extends JFrame implements ActionListener {

    private JTextField txtPseudo;

    private Client      client;
    
    public FrameConnexion( Client c) {
        this.client = c; 

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width  = (int)dimension.getWidth ();

        this.setSize    ( 500,300 );
        this.setLocation( width/2 - this.getWidth()/2, height/2 - this.getHeight()/2 );
        
        
        //création
        JPanel panelSaisie = new JPanel( null );
        panelSaisie.setBackground( new Color(  2895667 ) );

        this.txtPseudo   = new JTextField( "Tapez votre Pseudo", 10);
        this.txtPseudo.setBorder    ( null );
        this.txtPseudo.setForeground( new Color( 16777215 ) );
        this.txtPseudo.setBackground( new Color(  5332841 ) );
        
        JLabel lblPseudo = new JLabel( "Pseudo", JLabel.CENTER );
        lblPseudo.setForeground( new Color( 16777215 ) );

        FrameConnexion.positionner(lblPseudo     , this.getWidth() / 2 - 50 , this.getHeight() / 2 - 100 , 100, 50 );
        FrameConnexion.positionner(this.txtPseudo, this.getWidth() / 2 - 88 , this.getHeight() / 2 - 50  , 175, 30);


        //positionnement
        panelSaisie.add(      lblPseudo );
        panelSaisie.add( this.txtPseudo );

        this.add(panelSaisie);
        

        //activation
        this.txtPseudo.addActionListener( this );

        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        this.setVisible( true );
    }

    public void actionPerformed( ActionEvent e ) {
        this.client.setPseudo( ((JTextField) e.getSource()).getText() );
        this.client.mettreVisible();
        this.dispose();

    }

    public static void positionner( Component c, int x, int y, int w, int h) {
        c.setBounds(x,y,w,h);
    }

}
