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
        this.pieces = pieces;
    }

    public int computeComplexite() {
        return 0;
    }

    public float computePrix() {
        return 0;
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
