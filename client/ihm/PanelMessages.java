package client.ihm;

import client.Client;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.*;

public class PanelMessages extends JPanel implements ActionListener {

    private Client            client;

    private JTextField        txtMessages;
    
    private JPanel            panelLbl;

    private ArrayList<JLabel> alLbl;

    public PanelMessages( Client c) {
        this.client = c;

        this.setLayout    ( new BorderLayout() );
        this.setBackground( new Color( 2895667 ) );

        
        //création
        this.panelLbl = new JPanel( new GridLayout(12, 1) );
        panelLbl.setOpaque( false );

        this.txtMessages = new JTextField(50);
        txtMessages.setOpaque( false );
        txtMessages.setForeground( new Color ( 255,255,255 ) );
        Client.style(txtMessages, 'T');


        JTextField txtTmp = new JTextField(5);
        txtTmp.setEditable( false );
        txtTmp.setOpaque  ( false );
        txtTmp.setBorder  ( null );

        //positionnement        
        this.add( panelLbl        , BorderLayout.CENTER );
        this.add( txtTmp          , BorderLayout.WEST   );
        this.add( this.txtMessages, BorderLayout.SOUTH  );
        
        //activation
        this.txtMessages.addActionListener( this );
        
        
        this.setVisible( true );
    }

    public void maj( ArrayList<JLabel> alLbl ) {

        this.panelLbl.removeAll();

        int size = alLbl.size();

        for ( int cpt = 0; cpt < size - 12; cpt++ )
            alLbl.remove(0);

        System.out.println(alLbl.size());

        for ( JLabel lbl : alLbl ) {
            lbl.setForeground( new Color ( 255, 255, 255 ));
            panelLbl.add     ( new Message( lbl ));
        }

        this.add( panelLbl, BorderLayout.CENTER );

        this.client.repaint();
    }

    public void actionPerformed( ActionEvent e ) {
        
        JTextField tmp = (JTextField) e.getSource();

        if ( tmp.getText().equals("") ) return;

        this.client.envoyerMessage( tmp.getText() );
        tmp.setText("");
    }
    
}
