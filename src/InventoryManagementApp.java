import com.music4you.business.api.Administration;
import com.music4you.business.impl.AdministrationImpl;
import com.music4you.persister.api.Persister;
import com.music4you.persister.impl.PersisterImpl;
import com.music4you.ui.MainMenu;

public class InventoryManagementApp {
    public static void main(String[] args) {

        Persister persister = new PersisterImpl();

        Administration administration = new AdministrationImpl(persister);

        MainMenu menu = new MainMenu(administration);

        menu.showMain();

    }
}
