import controller.GymController;
import service.GymService;

public class Main {

    public static void main(String[] args) {
        GymController gymController = new GymController();
        gymController.menuPrincipal();
    }

    public void loadMockData(){}
}
