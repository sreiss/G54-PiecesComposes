package piecec.view;

import piecec.controller.GestionnairePieces;
import piecec.model.Piece;
import piecec.model.PieceBase;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by Simon on 19/12/2014.
 */
public class IHMServiceCommercial extends Vue {
    private GestionnairePieces gestionnairePieces;

    public IHMServiceCommercial () {
        super();
        this.gestionnairePieces = GestionnairePieces.getInstance();
    }

    public void  afficherCaracteristiques() {
        System.out.println("Veuillez entrer le numid de la piece");
        String numidS;
        int numid;
        numidS = this.readInput("-1");
        try {
            numid = Integer.parseInt(numidS);
        } catch (NumberFormatException e) {
            numid = 0;
        }

        Piece p = gestionnairePieces.getPieceById(numid);
        if (p != null) {
            System.out.println(MessageFormat.format("Voici la piece : {0}", p));
        } else {
            System.out.println("Cette piece n'existe pas.");
        }
    }

    public void afficherValorisationStock() {
        int valorisationStock = gestionnairePieces.computeValorisationStock();

        System.out.println(MessageFormat.format("Voici la valorisation du stock : {0}", valorisationStock));
    }

    public void listerPiecesAVendre() {
        List<Piece> piecesAVendre = gestionnairePieces.getPiecesAVendre();

        System.out.println("Voici les pièces disponibles à la vente :");
        for (Piece p: piecesAVendre) {
            System.out.println(p.toString());
        }
    }
}
