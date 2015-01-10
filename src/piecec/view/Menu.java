package piecec.view;

import piecec.controller.GestionnairePieces;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Andesite on 1/10/2015.
 */
public class Menu extends Vue {
    public void afficher() {
        IHMGestionnaireStock ihmGs = new IHMGestionnaireStock();
        IHMServiceCommercial ihmSc = new IHMServiceCommercial();

        String[] actionsDesc = {
                "0. Charger le jeu d'essai",
                "1. Ajouter une nouvelle piece",
                "2. Ajouter une piece composite",
                "3. Lister toutes les pieces référencées",
                "4. Afficher les caractéristiques d'une pièce",
                "5. Afficher la pièce la plus complexe",
                "6. Afficher une piece",
                "7. Lister les pieces à vendre",
                "8. Afficher la valorisation du stock",
                "10. Quitter"
        };

        System.out.println("Bienvenue sur l'application Pieces Composes, que voulez vous faire ?");
        for (String aDesc:actionsDesc) {
            System.out.println(aDesc);
        }

        String action = this.readInput("3");
        int actionInt;
        try {
            actionInt = Integer.parseInt(action);
        } catch (NumberFormatException e) {
            actionInt = -1;
        }

        switch (actionInt) {
            case 0:
                this.chargerJeuDessai();
                break;
            case 1:
                ihmGs.saisirPieceBase();
                break;
            case 2:
                ihmGs.saisirPieceComposite();
                break;
            case 3:
                ihmGs.listerPieces();
                break;
            case 4:
                ihmGs.afficherCaracteristiques();
                break;
            case 5:
                ihmGs.afficherPieceLaPlusComplexe();
                break;
            case 6:
                ihmSc.afficherCaracteristiques();
                break;
            case 7:
                ihmSc.listerPiecesAVendre();
                break;
            case 8:
                ihmSc.afficherValorisationStock();
                break;
            case 10:
                System.out.println("Merci beaucoup d'avoir utilisé ce programme, à bientôt !");
                System.exit(0);
                break;
            default:
                System.out.println("Action non reconnue.");
                break;
        }
        System.out.println("==============================================================================");

        if (actionInt != 10)
            afficher();
    }

    private void chargerJeuDessai() {
        GestionnairePieces gestionnairePieces = GestionnairePieces.getInstance();
        gestionnairePieces.chargerJeuDessai();
    }
}
