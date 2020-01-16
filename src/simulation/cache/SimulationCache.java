/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation.cache;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Math.pow;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedago
 */
public class SimulationCache {

    /**
     * @param args the command line arguments
     */
    
    public static final int TAILLEBLOC = 32;
    
    public static final int N = 4;
    
    public static final int NBLIGNES = (int) pow(2,N);
    
    public static void main(String[] args) {
        
        int cache[] = new int[NBLIGNES];
        
        boolean visite[] = new boolean[NBLIGNES];
        
        for (int i = 0 ; i < visite.length ; i++){
            visite[i] = false; 
        }
        
        int nbBitsDeplacement = 5;
        
        int nbBitsNumLigne = N;
        int nbSucces = 0;
        int nbEchecs = 0;
        try {
            Scanner scan = new Scanner(new File("matrice10.txt"));
            while (scan.hasNextLine()) { 
                String s = scan.nextLine();
                String[] ligne = s.split(":");
                int valeur = Integer.parseInt(ligne[0]);
                String acces = ligne[1];
                int deplacement = (int) (valeur % pow(2,nbBitsDeplacement));
                int reste = (int) (valeur / pow(2,nbBitsDeplacement));
                int numLigne = (int) (reste % pow(2,nbBitsNumLigne));
                int etiquette = (int) (reste / pow(2,nbBitsNumLigne));
                System.out.println("adresse: "+valeur);
                System.out.println("dep: "+deplacement);
                System.out.println("numLigne: "+numLigne);
                System.out.println("etiquette: "+etiquette);
                if (visite[numLigne] == false || cache[numLigne] != etiquette) {
                    visite[numLigne] = true;
                    nbEchecs++;;
                    cache[numLigne] = etiquette;
                } else {
                    nbSucces++;
                } 
            } 
            scan.close();
            System.out.println("nbSucces: "+nbSucces);
            System.out.println("nbEchecs: "+nbEchecs);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SimulationCache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
