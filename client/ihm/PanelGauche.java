package client.ihm;

import client.Client;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.*;

import java.util.ArrayList;

public class PanelGauche extends JPanel implements ActionListener{

    private JLabel lblNom;

    private JPanel panelUsers;

    private Client client;

    private JButton btnExit;

    public PanelGauche( Client c) {

        this.client = c;

        this.setLayout    ( new BorderLayout() );
        this.setBackground( new Color( 2303786 ) );


        //création des éléments
        JPanel panelProfil = new JPanel( new BorderLayout(2,2) );
        panelProfil.setOpaque( false );

        this.lblNom = new JLabel ( Client.getUsername(), JLabel.CENTER );
        this.lblNom.setForeground( new Color( 255,255,255 )      );

        this.btnExit = new JButton("X");
        this.btnExit.setOpaque    ( false );


        this.panelUsers = new JPanel();
        this.panelUsers.setOpaque( false );
        this.panelUsers.setForeground( new Color( 255,255,255 ) );      

        
        //positionnement
        panelProfil.add( this.lblNom , BorderLayout.CENTER );
        panelProfil.add( this.btnExit, BorderLayout.WEST   );

        this.add(panelProfil     ,  BorderLayout.NORTH  );
        this.add(this.panelUsers ,  BorderLayout.CENTER );

        //activation
        this.btnExit.addActionListener( this );
    }

    public void actionPerformed ( ActionEvent e ) {
        this.client.quitterServeur();
    }

    public void majPseudo() {
        this.lblNom.setText( Client.getUsername() );
    }

    public void majUsers( ArrayList<JLabel> alLbl ) {

        if ( alLbl.size() > 15 ) {
            this.panelUsers.setLayout( new GridLayout( alLbl.size(), 1) );


            for ( JLabel lbl : alLbl ) {
                lbl.setForeground( new Color( 16777215 ));
                Client.style(lbl, 'P');
                
                this.panelUsers.add( lbl );
            }


            JScrollPane spLbl = new JScrollPane( this.panelUsers, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
            this.panelUsers.setPreferredSize( new Dimension( 300, (int) (alLbl.size() * 66.7) ) );
            spLbl.setViewportView( this.panelUsers );
            
            this.add(   spLbl          );

        } else {
            this.panelUsers.setLayout( new GridLayout( 15, 1 ) );

            this.panelUsers.setPreferredSize( new Dimension( 200, 1500) );



            for ( JLabel lbl : alLbl ) {
                lbl.setForeground( new Color( 16777215 ));
                Client.style(lbl, 'P');

                this.panelUsers.add( lbl );
            }

            for ( int cpt = 0; cpt < 15 - alLbl.size(); cpt++ )
                this.panelUsers.add( new JLabel("") );
            
            
            this.add( this.panelUsers , BorderLayout.CENTER );
        }

        this.client.repaint();
    }
    
}
