package tableMVC;

/**
 *
 * @author puerta
 */
public class Controller {

    private static Controller instance;

    public static Controller getInstance() {
        return instance == null ? instance = new Controller() : instance;
    }

    public void ctrlDeck() {

    }

    public void ctrlComnination() {

    }

}
