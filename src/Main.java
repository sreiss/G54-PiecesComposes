import piecec.view.IHMGestionnaireStock;
import piecec.view.IHMServiceCommercial;

/**
 * Created by Simon on 08/01/2015.
 */
public class Main {
    public static void main(String[] args) {
        if (args[0].equals("gestionnaireStock")) {
            IHMGestionnaireStock gestStock = new IHMGestionnaireStock();
            gestStock.afficherMenu();
        } else if (args[0].equals("serviceCommercial")) {
            IHMServiceCommercial servCom = new IHMServiceCommercial();
        }
    }
}
