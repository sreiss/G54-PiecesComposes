package piecec.view;

import piecec.controller.GestionnairePieces;
import piecec.model.Piece;
import piecec.model.PieceBase;
import piecec.model.PieceComposite;

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
            System.out.println("Voici la piece :");
            String type = "base";
            if (p instanceof PieceComposite)
                type = "compose";
            System.out.println(MessageFormat.format(
                    "nom: {0}, type: {1}, complexite: {2}, prix: {3}",
                    p.getNom(),
                    type,
                    p.computeComplexite(),
                    p.computePrix()
            ));
        } else {
            System.out.println("ERREUR : Cette piece n'existe pas.");
        }
    }

    public void afficherValorisationStock() {
        int valorisationStock = gestionnairePieces.computeValorisationStock();

        System.out.println(MessageFormat.format("Voici la valorisation du stock : {0}", valorisationStock));
    }

    public void listerPiecesAVendre() {
        List<Piece> piecesAVendre = gestionnairePieces.getPiecesAVendre();

        if (piecesAVendre.size() > 0) {
            System.out.println("Voici les pièces disponibles à la vente :");
            for (Piece p : piecesAVendre) {
                System.out.println(p.toString());
            }
        } else {
            System.out.println("Aucune piece disponible à la vente.");
        }
    }
}
