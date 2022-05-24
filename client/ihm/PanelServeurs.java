package client.ihm;

import client.Client;

import javax.swing.*;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.*;


public class PanelServeurs  extends JPanel implements ActionListener{
 
    private Client     client;

    private JTextField txtServeur;
    private JTextField txtMdp;

    private JButton    btnValider;
    private JButton    btnCreer;

    private JLabel lblPseudo;

    public PanelServeurs (  Client c) {
        this.client = c;

        this.setLayout( new BorderLayout() );

        //création
        JPanel panelInfo = new JPanel( new BorderLayout() );
        panelInfo.setBackground( new Color(  2895667 ) );        
        
        this.lblPseudo = new JLabel( Client.getUsername(), JLabel.CENTER );
        lblPseudo.setForeground( new Color( 16777215 ) );
        
        JPanel panelSaisie = new JPanel( null );
        panelSaisie.setBackground( new Color(  2895667 ) );

        this.txtServeur   = new JTextField( "Nom du Servuer", 10);
        this.txtServeur.setBorder         ( null               );
        this.txtServeur.setForeground     ( new Color( 16777215 ) );
        this.txtServeur.setBackground     ( new Color(  5332841 ) );

        this.txtMdp   = new JTextField( "MDP du Servuer", 10);
        this.txtMdp.setBorder         ( null               );
        this.txtMdp.setForeground     ( new Color( 16777215 ) );
        this.txtMdp.setBackground     ( new Color(  5332841 ) );

        this.btnValider   = new JButton( "Rejoindre" );
        this.btnValider.setBorder         ( null               );
        this.btnValider.setForeground     ( new Color( 16777215 ) );
        this.btnValider.setBackground     ( new Color(  5332841 ) );

        this.btnCreer   = new JButton( "Créer" );
        this.btnCreer.setBorder         ( null               );
        this.btnCreer.setForeground     ( new Color( 16777215 ) );
        this.btnCreer.setBackground     ( new Color(  5332841 ) );

        FrameConnexion.positionner(this.txtServeur , 750 - 175/2, 250 , 175, 30 );
        FrameConnexion.positionner(this.txtMdp     , 750 - 175/2, 290 , 175, 30);
        FrameConnexion.positionner(this.btnValider , 750  + 20  , 350 , 100, 50);
        FrameConnexion.positionner(this.btnCreer   , 750 - 120  , 350 , 100, 50);



        
        //positionnement
        panelSaisie.add( this.txtServeur );
        panelSaisie.add( this.txtMdp     );
        panelSaisie.add( this.btnValider );
        panelSaisie.add( this.btnCreer   );

        panelInfo.add( lblPseudo, BorderLayout.CENTER );

        this.add( panelInfo  , BorderLayout.NORTH  );
        this.add( panelSaisie, BorderLayout.CENTER );

        //activation
        this.btnValider.addActionListener( this );
        this.btnCreer  .addActionListener( this );


    }

    public void majPseudo() {
        this.lblPseudo.setText( Client.getUsername() );
    }

    public void actionPerformed ( ActionEvent e ) {

        if ( e.getSource() == this.btnValider) {
            if ( !this.txtServeur.equals("") && !this.txtMdp.equals("") ) {

                this.client.rejoindreGuild( this.txtServeur.getText(), this.txtMdp.getText() );
            }
        } else {
            if ( !this.txtServeur.equals("") && !this.txtMdp.equals("") ) {

                this.client.creerServeur( this.txtServeur.getText(), this.txtMdp.getText() );
            }
        }
    }

}
