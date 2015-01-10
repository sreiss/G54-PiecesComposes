package piecec.controller;

import piecec.model.Piece;
import piecec.model.PieceBase;
import piecec.model.PieceComposite;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 18/12/2014.
 */
public class GestionnairePieces {
    private static GestionnairePieces instance;

    private List<Piece> pieces;

    private  GestionnairePieces () {
        this.pieces = new ArrayList<Piece>();
    }

    public List<Piece> getAllPieces() {
        return this.pieces;
    }

    public static GestionnairePieces getInstance() {
        if (instance == null) {
            GestionnairePieces.instance = new GestionnairePieces();
        }
        return GestionnairePieces.instance;
    }

    public int addPieceBase (String nom, float prixAchat) {
        PieceBase piece = new PieceBase(nom, prixAchat);
        if (piece != null) {
            pieces.add(piece);
            return piece.getNumid();
        }

        return -1;
    }

    public int addPieceComposite (String nom, float coutAssemblage, List<Integer> pieceNumids) {
        List<Piece> piecesComposantes = new ArrayList<Piece>();
        int errorCode = 0;

        for (int numid : pieceNumids) {
            Piece piece = this.getPieceById(numid);
            if (piece != null) {
                if (!piece.estDansUnePiece()) {
                    if (!piecesComposantes.contains(piece)) {
                        piecesComposantes.add(piece);
                    }
                } else {
                    errorCode = -2;
                }
            } else {
                errorCode = -1;
            }
        }

        int result = errorCode;
        if (errorCode == 0) {
            PieceComposite pieceComposite = new PieceComposite(nom, coutAssemblage, piecesComposantes);
            pieces.add(pieceComposite);
            result = pieceComposite.getNumid();
        }

        return result;
    }

    public Piece getPieceById(int id) {
        for (Piece p:this.pieces) {
            if (p.getNumid() == id) {
                return p;
            }
        }

        return null;
    }

    public Piece getPieceLaPlusComplexe() {
        Piece pieceLaPlusComplexe = null;
        for (Piece p:pieces) {
            if (pieceLaPlusComplexe == null) {
                pieceLaPlusComplexe = p;
            } else if (p.computeComplexite() > pieceLaPlusComplexe.computeComplexite()) {
                pieceLaPlusComplexe = p;
            }
        }

        return pieceLaPlusComplexe;
    }

    public int computeValorisationStock () {
        int valorisationNiveau = 0;
        for (Piece p: pieces) {
            valorisationNiveau += p.computePrix();
        }
        return valorisationNiveau;
    }

    public List<Piece> getPiecesAVendre() {
        List<Piece> piecesAVendre = new ArrayList<Piece>();
        for (Piece p:pieces) {
            if (!p.estDansUnePiece())
                piecesAVendre.add(p);
        }
        return piecesAVendre;
    }

    public void chargerJeuDessai() {
        for (int i = 1; i < 10; i++) {
            this.addPieceBase("B" + i, (float)i * 10);
        }
        System.out.println("Jeu d'essai chargé");
    }
}
