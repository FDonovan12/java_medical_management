package donovan.fr.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import donovan.fr.Utils;
import donovan.fr.entity.AudioObservation;
import donovan.fr.entity.Observation;
import donovan.fr.entity.Patient;
import donovan.fr.entity.TextObservation;

public class ObservationRepository extends Database {
	private static List<Observation> observations = new ArrayList<Observation>();
	
	public static Observation userCreateNewObservation() {
		Observation observation;
		System.out.println(" Debut de la creation d'une observation");
		Patient patient = PatientRepository.userFindPatient();
		String isTextObservation = Utils.questionScanner(" Est ce que c'est une observation ecrite ? (Y)");
		LocalDate createdAt = Utils.userWriteADate(" Quelle est la date de l'observation ? (dd/mm/yyyy)");
		if (isTextObservation.equals("Y")) {
			String content = Utils.questionScanner(" Quelle est le contenue de l'observation ?");
			observation = new TextObservation(createdAt, patient, content);
		} else {
			String path = Utils.questionScanner(" Quelle est le chemin pour l'audio de l'observation ?");
			Long duration = Utils.questionScannerLong(" Quelle est la duree de l'observation ?");
			observation = new AudioObservation(createdAt, patient, path, duration);

		}
		return addObservation(observation);
	}

	private static Observation addObservation(Observation observation) {
		if (observations.stream().filter(obs -> obs.getId() == observation.getId()).count() == 0) {
			observations.add(observation);
		}
		return observation;
	}

	public static Observation userFindObservation() {
		if (observations.isEmpty()) {
			System.out.println(" Il n'y as pas encore de observations");
			return userCreateNewObservation();
		}
		System.out.println(" Voici la liste des observations : ");
		printSimpleObservations();
		boolean isResponseLong = false;
		Long observationId = null;
		do {
			try {
				observationId = Utils.questionScannerLong(" Quelle est le observation que vous voulez");
				isResponseLong = true;
			} catch (Exception e) {
				System.err.println(" La valeur entree n'est pas acceptable donner juste l'id en chiffre.");
			}
		} while (!isResponseLong);
		return findObservation(observationId);
	}

	private static Observation findObservation(Long id) {
		try {
			return getObservation(id);
		} catch (Exception e) {
			System.err.println(" Ce observation n'existe pas.");
			return userCreateNewObservation();
		}
	}

	private static Observation getObservation(Long id) {
		return observations.stream().filter(observation -> observation.getId() == id).toList().get(0);
	}

	public static void printSimpleObservations() {
		observations.stream().map(Observation::simpleToString).toList().forEach(System.out::println);
	}

	public static void printObservations() {
		observations.forEach(System.out::println);
	}
}
