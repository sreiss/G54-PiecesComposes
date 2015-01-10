package piecec.view;

import com.sun.xml.internal.stream.Entity;
import piecec.controller.GestionnairePieces;
import piecec.model.Piece;
import piecec.model.PieceBase;
import piecec.model.PieceComposite;
import sun.misc.FloatingDecimal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Simon on 19/12/2014.
 */
public class IHMGestionnaireStock extends Vue {
    private GestionnairePieces gestionnairePieces;

    public IHMGestionnaireStock() {
        super();
        this.gestionnairePieces = gestionnairePieces.getInstance();
    }

    public void afficherPieceLaPlusComplexe() {
        Piece pieceLaPlusComplexe = this.gestionnairePieces.getPieceLaPlusComplexe();

        if (pieceLaPlusComplexe != null) {
            System.out.println(MessageFormat.format("La piece la plus complexe est : {0}", pieceLaPlusComplexe.toString()));
        } else {
            System.out.println("Il n'y a pas de piece en stock !");
        }
    }

    public void afficherCaracteristiques() {
        System.out.println("Veuillez entrer le numid de la piece :");
        String numidS = this.readInput("-1");
        int numid = Integer.parseInt(numidS);
        Piece p = this.gestionnairePieces.getPieceById(numid);

        if (p != null) {
            System.out.println(p.toString());
            if (p instanceof PieceComposite) {
                System.out.println("composée de :");
                for (Piece sp : ((PieceComposite) p).getPieces()) {
                    System.out.println(sp);
                }
            }
        } else {
            System.out.println("La piece n'existe pas !");
        }
    }

    public void listerPieces() {
        List<Piece> pieces = gestionnairePieces.getAllPieces();
        for (Piece p:pieces) {
            System.out.println(p.toString());
        }
    }

    public void saisirPieceBase() {
        System.out.println("Veuillez entrer le nom de la piece :");
        String nom = this.readInput("");

        System.out.println("Veuillez entrer le prix d'achat de la piece (ex: 2.0) en €");
        String prixAchatS = this.readInput("0");
        try {
            float prixAchat = Float.parseFloat(prixAchatS);

            int numid = this.gestionnairePieces.addPieceBase(nom, prixAchat);

            if (numid < 0) {
                System.out.println("ERREUR : la piece n'a pas pu être ajouté.");
            } else {
                System.out.println(MessageFormat.format("La pièce a bien été ajoutée, elle porte le numid {0}", numid));
            }
        } catch (NumberFormatException e) {
            System.out.println("Les prix des pieces doivent être au format suivant : 1.0, vous avez probablement mis une virgule.");
            System.out.println("Veuillez réessayer d'ajouter la piece.");
        }
    }

    public void saisirPieceComposite() {
        System.out.println("Veuillez entrer le nom de la piece :");
        String nom = this.readInput("");

        System.out.println("Veuillez entrer le cout d'assemblace de la piece (ex: 2.0) en €");
        String coutAssemblageS = this.readInput("0");
        try {
            float coutAssemblage = Float.parseFloat(coutAssemblageS);

            System.out.println("Veuillez entrer le numid des pièces qui compose celle-ci (séparées par une virgule)");
            String piecesS = this.readInput("");
            String[] pieceNumidsArray;
            ArrayList<Integer> pieceNumids = new ArrayList<Integer>();
            List<Piece> pieces = new ArrayList<Piece>();
            if (piecesS.length() > 0) {
                pieceNumidsArray = piecesS.split(",");
                for (String pieceS : pieceNumidsArray) {
                    pieceNumids.add(Integer.parseInt(pieceS));
                }
            }

            int numid = this.gestionnairePieces.addPieceComposite(nom, coutAssemblage, pieceNumids);

            if (numid < 0) {
                System.out.println("ERREUR : la piece n'a pas pu être ajouté.");
            } else {
                System.out.println(MessageFormat.format("Pièce ajoutée, numid : {0}", numid));
            }
        } catch (NumberFormatException e) {
            System.out.println("Les prix des pieces doivent être au format suivant : 1.0, vous avez probablement mis une virgule.");
            System.out.println("Veuillez réessayer d'ajouter la piece.");
        }
    }
}
