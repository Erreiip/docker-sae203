package client;

import client.ihm.*;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;

import java.util.ArrayList;

import java.net.*;
import java.io.*;


public class Client extends Thread{

    private static final int    PORT = 6969; 
    private static final String HOST = "di-docker";     


    private Frame        ihm;
    private FrameServeur ihmServeur;


    private Socket         toServer;  
    private BufferedReader in;
    private PrintWriter    out;

    public Client() {
             
        this.ihm        = new Frame       (this);
        this.ihmServeur = new FrameServeur(this);


        this.in = null;
        this.out = null;


        this.start();
    }

    

    public static String getUsername() {

        String pseudo = "";

        try {
            Scanner sc = new Scanner ( new FileReader( "client/infos/pseudo.txt" ) );

            pseudo = sc.nextLine();

            sc.close();
        }catch (Exception e) {}

        return pseudo;
    }


    public void setPseudo( String pseudo ) {

        try {

            PrintWriter pr = new PrintWriter( "./client/infos/pseudo.txt");
            pr.print(pseudo);
            pr.close();

            ihmServeur.majPseudo();
            ihm       .majPseudo();
        } catch (Exception e) {
            System.out.println(e);
        }
    }




    public static boolean nbCo() {

        try {
            String nbConexions = "";

            Scanner sc = new Scanner ( new FileReader( "./client/infos/nombreConnexions.txt" ));

            if ( sc.hasNextLine() ) 
                nbConexions  = sc.nextLine();
            

            if ( nbConexions.equals("0") ) {
                
                PrintWriter pr = new PrintWriter( "./client/infos/nombreConnexions.txt" );
                pr.print("1");
                pr.close();
                sc.close();

                return true;
            }

            sc.close();
            

        } catch (Exception e ) {
            System.out.println(e);
        }
        
        return false;
    }


    public static void style( Component c, char type) {
        if ( type == 'T') {
            c.setFont( new Font("Time News Roman", Font.PLAIN, 18) );
        } else {
            c.setFont( new Font("Verdana", Font.PLAIN, 15) );
        }
    }


    

    public void mettreVisible() {
        this.ihm.setVisible( true );
    }

    public void repaint() {
        this.ihm       .setSize(this.ihm.getWidth()-1, this.ihm.getHeight()  );
        this.ihmServeur.setSize(this.ihm.getWidth()-1, this.ihm.getHeight()  );

    }
    public void changerFrame( int num) {
        
        if ( num == 1 ) {
            this.ihm.setVisible( true );
            this.ihmServeur.setVisible( false );
        } else {
            this.ihmServeur.setVisible( true );
            this.ihm.setVisible( false );
        }

    }

    //---------------------------//
    // Contact avec le serveur   //
    //---------------------------//
    public void creerServeur( String nom, String mdp ) {
        
        this.out.println( 
                          "004: " + Client.getUsername() + "/" 
                                  + nom                  + "/"
                                  + mdp     
                        );
    }


    public void envoyerMessage ( String txt ) {

        this.out.println( "003: " + txt );
    }


    public void rejoindreGuild ( String serveur, String mdp ) {

        this.out.println( 
                          "002: " + Client.getUsername() + "/" 
                                  + serveur              + "/"
                                  + mdp     
                        );
    }

    public void quitterServeur () {

        this.out.println( "069: " );
    }


    public void run() {

        try {
            this.toServer = new Socket(Client.HOST, Client.PORT);

            this.out = new PrintWriter(toServer.getOutputStream(), true);
            this.in  = new BufferedReader( new InputStreamReader(toServer.getInputStream()));

            while ( true ) {

                String   lignes = this.in.readLine();
                String   code   = lignes.substring(0,5);

                if ( lignes == null ) { this.ihmServeur.dispose(); this.ihm.dispose(); }
                
                lignes = lignes.substring(5);

                System.out.println(code);
                System.out.println(lignes);

                if ( code.equals("006: ") ) {

                    ArrayList<JLabel> alLbl = new ArrayList<JLabel>();
                    
                    String[] ligne = lignes.split("&r3u");
                    String[] tabInfos;
                    for ( int cpt =0; cpt < ligne.length; cpt++) {

                        tabInfos = ligne[cpt].split("/");
                        System.out.println( "Info [" + tabInfos[0] + ";" + tabInfos[1] + "]");

                        JLabel lbl = new JLabel( tabInfos[1] );
                        lbl.setName            ( tabInfos[0] + " à " + tabInfos[2] );

                        alLbl.add( lbl );
                    }

                    this.ihmServeur.majMessages( alLbl );
                }

                if ( code.equals("007: ") ) {

                    ArrayList<JLabel> alLbl = new ArrayList<JLabel>();

                    String[] ligne = lignes.split("&r3u");
                    for ( int cpt =0; cpt < ligne.length; cpt++) {

                        JLabel lbl = new JLabel( "     " + ligne[ cpt ] + "          " );

                        alLbl.add( lbl );
                    }

                    this.ihmServeur.majUsers( alLbl );
                }

                if ( code.equals("002: ") ) {

                    if (lignes.equals( "OK" ) ) {
                        this.ihmServeur.setVisible( true );
                        this.ihm.setVisible( false );
                    }
                }

                if ( code.equals("004: ") ) {

                    if (lignes.equals( "OK" ) ) {
                        this.ihmServeur.setVisible( true );
                        this.ihm.setVisible( false );
                    }
                }

                if ( code.equals("069: ") ) {

                    this.ihmServeur.setVisible( false );
                    this.ihmServeur.clear     ();
                    this.ihm.setVisible       ( true );
                }

                    
            }

        } catch (Exception e ) {}
    }

    

    public static void main (String[] args) {
        new Client();
    }

}
