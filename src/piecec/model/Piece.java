package piecec.model;

/**
 * Created by Simon on 18/12/2014.
 */
public abstract class Piece {
    private static int currentNumid = 1;
    private String nom;
    private int numid;

    public Piece (String nom) {
        this.numid = Piece.currentNumid;
        this.nom = nom;
        Piece.currentNumid += 1;
    }

    public String getNom() {
        return nom;
    }

    public abstract int computeComplexite();

    public abstract float computePrix();

    public abstract String toString();

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumid() {
        return this.numid;
    }

    public void setNumid(int numid) {
        this.numid = numid;
    }
}
