package piecec.model;

/**
 * Created by Simon on 18/12/2014.
 */
public class PieceBase extends Piece {
    private float prixAchat;

    public PieceBase(String nom, float prixAchat) {
        super(nom);
        this.prixAchat = prixAchat;
    }

    public float getPrixAchat() {
        return prixAchat;
    }

    public int computeComplexite() {
        return 1;
    }

    public float computePrix() {
        return this.prixAchat;
    }

    public void setPrixAchat(float prixAchat) {
        this.prixAchat = prixAchat;
    }

    public String toString() {
        return "numid : " + this.getNumid() + ", nom : " + this.getNom() + ", type : base, prixAchat : " + this.getPrixAchat();
    }
}
