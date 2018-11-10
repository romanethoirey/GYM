import controller.GymController;
import model.Seance;
import service.GymService;

public class Main {

    public static void main(String[] args) {
        GymController gymController = new GymController();
        gymController.initialisation();//Mock Data ?
        gymController.menuPrincipal();
    }

    public void loadMockData(){}
}
