package piecec.model;

import java.util.List;

/**
 * Created by Simon on 18/12/2014.
 */
public class PieceComposite extends Piece {
    private float coutAssemblage;
    private List<Piece> pieces;

    public PieceComposite(String nom, float coutAssemblage, List<Piece> pieces) {
        super(nom);
        this.coutAssemblage = coutAssemblage;
        for (Piece p:pieces) {
            p.setEstDansUnePiece(true);
        }
        this.pieces = pieces;
    }

    public int computeComplexite() {
        int complexite = 0;
        for (Piece p:pieces) {
            complexite += p.computeComplexite();
        }
        return complexite;
    }

    public float computePrix() {
        float prix = this.getCoutAssemblage();
        for (Piece p: pieces) {
            prix += p.computePrix();
        }
        return prix;
    }

    public float getCoutAssemblage() {
        return coutAssemblage;
    }

    public void setCoutAssemblage(float coutAssemblage) {
        this.coutAssemblage = coutAssemblage;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public String toString() {
        int complexite = this.computeComplexite();
        return "numid : " + this.getNumid() + ", nom : " + this.getNom() + ", type : composite, coutAssemblage : " + this.getCoutAssemblage() + ", complexite : " + complexite;
    }
}
