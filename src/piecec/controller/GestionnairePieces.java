package piecec.controller;

import piecec.model.Piece;
import piecec.model.PieceBase;
import piecec.model.PieceComposite;

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
        List<Piece> pieces = new ArrayList<Piece>();
        boolean canBeAdded = true;
        for (int numid:pieceNumids) {
            Piece piece = this.getPieceById(numid);
            if (piece != null) {
                pieces.add(piece);
            } else {
                canBeAdded = false;
            }
        }

        if (canBeAdded) {
            PieceComposite piece = new PieceComposite(nom, coutAssemblage, pieces);
            pieces.add(piece);
            return piece.getNumid();
        }

        return -1;
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
        return this.computePieceLaPlusComplete(this.pieces, 0);
    }

    private Piece computePieceLaPlusComplete(List<Piece> pieces, int profondeur) {
        Piece pieceLaPlusComplexe = null;
        int profondeurActuelle = 0;
        for (Piece p : pieces) {
            if (profondeurActuelle >= profondeur) {
                if (p instanceof PieceBase) {
                    pieceLaPlusComplexe = p;
                } else if (p instanceof PieceComposite) {
                    profondeurActuelle += 1;
                    pieceLaPlusComplexe = this.computePieceLaPlusComplete(((PieceComposite) p).getPieces(), profondeurActuelle);
                }
            }
        }

        return pieceLaPlusComplexe;
    }

    public int computeValorisationStock () {
        return 0;
    }

    public List<Piece> getPiecesAVendre() {
        return null;
    }
}
