package donovan.fr;

import java.util.ArrayList;
import java.util.List;

import donovan.fr.repository.ObservationRepository;
import donovan.fr.repository.PatientRepository;
import donovan.fr.repository.StatementRepository;

public class Main {
	private static List<MenuChoices> menus = new ArrayList<MenuChoices>();
	private static boolean isInMenu = true;
	public static void main(String[] args) {
		menu();
	}
	
	public static void menu() {
		initMenu();
		do {
			printMenu();
			try {
				Integer command = Integer.parseInt(Utils.scanner.nextLine());
				menus.get(command-1).getMethod().invoke(null);
			} catch (Exception e) {
//				e.printStackTrace();
				System.err.println(" Ce choix n'est pas valide");
			}
		} while (isInMenu);
	}
	
	private static void initMenu() {
		try {
			menus.add(new MenuChoices("Ajouter patient", PatientRepository.class.getMethod("userCreateNewPatient")));
			menus.add(new MenuChoices("Modifier le nom d'un patient", PatientRepository.class.getMethod("wantRenameAPatient")));
			menus.add(new MenuChoices("Voir tout les patients avec un age donnée", PatientRepository.class.getMethod("printPatientsFromAge")));
			menus.add(new MenuChoices("Voir tout les relevé du plus recent au plus ancient", StatementRepository.class.getMethod("printSortedStatements")));
			menus.add(new MenuChoices("Ajouter un relevé", StatementRepository.class.getMethod("userCreateNewStatement")));
			menus.add(new MenuChoices("Supprimer un releve", StatementRepository.class.getMethod("userDeleteStatement")));
			menus.add(new MenuChoices("Voir tout les patients", PatientRepository.class.getMethod("printPatients")));
			menus.add(new MenuChoices("Ajouter une observation", ObservationRepository.class.getMethod("userCreateNewObservation")));
			menus.add(new MenuChoices("Quitté", Main.class.getMethod("quitMenu")));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public static void quitMenu() {
		isInMenu = false;
	}
	
	private static void printMenu() {
		System.out.println("Menu Principal");
		menus.forEach(System.out::println);
	}
	
	
	private static void name() {
		System.out.println("MENU PRINCIPAL");
		System.out.println("1. Ajouter patient");
		System.out.println("2. Modifier le nom d'un patient");
		System.out.println("3. Voir tout les patients avec un age donnée");
		System.out.println("4. Voir tout les relevé du plus recent au plus ancient");
		System.out.println("5. Ajouter un relevé");
		System.out.println("6. Supprimer un releve");
		System.out.println("7. Voir tout les patients");
		System.out.println("8. Quitté");
		String command = Utils.scanner.next();
		switch (command) {
		case "1":
			PatientRepository.userCreateNewPatient();
			break;
		case "2":
			PatientRepository.wantRenameAPatient();
			break;
		case "3":
			PatientRepository.printPatientsFromAge();
			break;
		case "4":
			StatementRepository.printSortedStatements();
			break;
		case "5":
			StatementRepository.userCreateNewStatement();
			break;
		case "6":
			StatementRepository.userDeleteStatement();
			break;
		case "7":
			PatientRepository.printPatients();
			break;
		case "8":
			break;
		default:
			break;
		}
	}
}
