
import repository.DadosRepository;
import service.GestaoService;
import ui.Menu;

public class MainFinal {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("  SISTEMA DE GESTÃO EDUCACIONAL");
        System.out.println("           EDUCONNECT");
        System.out.println("====================================");

        DadosRepository repository = new DadosRepository();

        GestaoService service = new GestaoService(repository);

        Menu menu = new Menu(service);

        menu.exibirMenu();

        System.out.println("Sistema finalizado com sucesso!");
    }
}
