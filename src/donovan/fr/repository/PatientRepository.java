package donovan.fr.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import donovan.fr.Utils;
import donovan.fr.entity.Patient;

public class PatientRepository extends Database {
	private static List<Patient> patients = new ArrayList<Patient>();
	
	public static Patient userCreateNewPatient() {
		System.out.println(" Debut de la creation d'un patient");
		String firstName = Utils.questionScanner(" Quelle est le prenom du patient ?");
		String lastName = Utils.questionScanner(" Quelle est le nom de famille du patient ?");
		LocalDate birthdate = Utils.userWriteADate(" Quelle est sa date de naissance ? (dd/mm/yyyy)");
		Patient patient = new Patient(birthdate, firstName, lastName);
		return addPatient(patient);
	}

	private static Patient addPatient(Patient patient) {
		if (patients.stream().filter(pat -> pat.getId() == patient.getId()).count() == 0) {
			patients.add(patient);
		}
		return patient;
	}

	public static Patient userFindPatient() {
		if (patients.isEmpty()) {
			System.out.println(" Il n'y as pas encore de patients");
			return userCreateNewPatient();
		}
		System.out.println(" Voici la liste des patients : ");
		printSimplePatients();
		boolean isResponseLong = false;
		Long patientId = null;
		do {
			try {
				patientId = Utils.questionScannerLong(" Quelle est le patient que vous voulez");
				isResponseLong = true;
			} catch (Exception e) {
				System.err.println(" La valeur entree n'est pas acceptable donner juste l'id en chiffre.");
			}
		} while (!isResponseLong);
		return findPatient(patientId);
	}

	private static Patient findPatient(Long id) {
		try {
			return getPatient(id);
		} catch (Exception e) {
			System.err.println(" Ce patient n'existe pas.");
			return userCreateNewPatient();
		}
	}

	private static Patient getPatient(Long id) {
		return patients.stream().filter(patient -> patient.getId() == id).toList().get(0);
	}

	public static void printSimplePatients() {
		patients.stream().map(Patient::simpleToString).toList().forEach(System.out::println);
	}

	public static void printPatients() {
		patients.forEach(System.out::println);
	}

	
	public static Patient wantRenameAPatient() {
		Patient patient = userFindPatient();
		return changeNamePatient(patient);
	}
	
	private static Patient changeNamePatient(Patient patient) {
		String firstName = Utils.questionScanner(" Quelle est le nouveau prenom du patient ?");
		String lastName = Utils.questionScanner(" Quelle est le nouveau nom de famille du patient ?");
		patient.setName(firstName);
		patient.setLastName(lastName);
		return patient;
	}
	
	public static void printPatientsFromAge() {
		long age = Utils.questionScannerLong(" Quelle est l'age ?");
		patients.stream().filter(patient -> patient.getAge() == age).toList().forEach(System.out::println);
		for(Patient patient : patients) {
			if (patient.getAge() == age) {
				System.out.println(patient);
			}
		}
	}

}
