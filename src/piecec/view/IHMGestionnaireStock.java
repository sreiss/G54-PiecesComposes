package piecec.view;

import com.sun.xml.internal.stream.Entity;
import piecec.controller.GestionnairePieces;
import piecec.model.Piece;
import piecec.model.PieceBase;
import piecec.model.PieceComposite;

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
public class IHMGestionnaireStock {
    private GestionnairePieces gestionnairePieces;

    public IHMGestionnaireStock() {
        this.gestionnairePieces = gestionnairePieces.getInstance();
    }

    private String readInput(String defaultValue) {
        String s = defaultValue;
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        try {
            s = buffer.readLine();
        } catch (IOException e) {
            System.out.println("Reading crash...");
        }

        return s;
    }

    public void afficherMenu() {
        String[] actionsDesc = {
                "1. Ajouter une nouvelle piece",
                "2. Ajouter une piece composite",
                "3. Lister toutes les pieces référencées",
                "4. Afficher les caractéristiques d'une pièce",
                "5. Afficher la pièce la plus complexe"
        };
        System.out.println("Bienvenue, gestionnaire de stock ! Voici les actions que vous pouvez effectuer :");
        for (String aDesc:actionsDesc) {
            System.out.println(aDesc);
        }

        System.out.println("Que voulez-vous faire ?");
        String action = this.readInput("1");
        int actionInt = Integer.parseInt(action);
        switch (actionInt) {
            case 1:
                this.saisirPieceBase();
                break;
            case 2:
                this.saisirPieceComposite();
                break;
            case 3:
                this.listerPieces();
                break;
            case 4:
                this.afficherCaracteristiques();
                break;
            case 5:
                this.afficherPieceLaPlusComplexe();
                break;
            default:
                System.out.print("Action non reconnue.");
                break;
        }
        afficherMenu();
    }

    public void afficherPieceLaPlusComplexe() {
        Piece p = this.gestionnairePieces.getPieceLaPlusComplexe();
        System.out.println(MessageFormat.format("Piece la plus complexe : ", p.toString()));
    }

    public void afficherCaracteristiques() {
        System.out.println("Veuillez entrer le numid de la piece :");
        String numidS = this.readInput("-1");
        int numid = Integer.parseInt(numidS);
        Piece p = this.gestionnairePieces.getPieceById(numid);
        System.out.println(p.toString());
        if (p instanceof PieceComposite) {
            System.out.println("composée de :");
            for (Piece sp : ((PieceComposite) p).getPieces()) {
                System.out.println(sp.getNom());
            }
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
        float prixAchat = Float.parseFloat(prixAchatS);

        int numid = this.gestionnairePieces.addPieceBase(nom, prixAchat);

        if (numid < 0) {
            System.out.println("ERREUR : la piece n'a pas pu être ajouté.");
        } else {
            System.out.println(MessageFormat.format("La pièce a bien été ajoutée, elle porte le numid {0}", numid));
        }
    }

    public void saisirPieceComposite() {
        System.out.println("Veuillez entrer le nom de la piece :");
        String nom = this.readInput("");

        System.out.println("Veuillez entrer le cout d'assemblace de la piece (ex: 2.0) en €");
        String coutAssemblageS = this.readInput("0");
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
    }
}
