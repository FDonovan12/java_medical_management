package donovan.fr.repository;

import java.util.ArrayList;
import java.util.List;

import donovan.fr.Utils;
import donovan.fr.entity.Parameter;

public class ParameterRepository extends Database {
	private static List<Parameter> parameters = new ArrayList<Parameter>();

	public static Parameter userCreateNewParameter() {
		System.out.println(" Debut de la creation d'un parametre");
		String name = Utils.questionScanner(" Quelle est le nom du parametre ?");
		Parameter parameter = new Parameter(name);
		return addParameter(parameter);
	}

	private static Parameter addParameter(Parameter parameter) {
		if (parameters.stream().filter(par -> par.getId() == parameter.getId()).count() == 0) {
			parameters.add(parameter);
		}
		return parameter;
	}

	public static Parameter userFindParameter() {
		if (parameters.isEmpty()) {
			System.out.println(" Il n'y as pas encore de parametre");
			return userCreateNewParameter();
		}
		System.out.println(" Voici la liste des parametre : ");
		printSimpleParameter();
		boolean isResponseLong = false;
		Long parameterId = null;
		do {
			try {
				parameterId = Utils.questionScannerLong(" Quelle est le parametre que vous voulez");
				isResponseLong = true;
			} catch (Exception e) {
				System.err.println(" La valeur entree n'est pas acceptable donner juste l'id en chiffre.");
			}
		} while (!isResponseLong);
		return findParameter(parameterId);
	}

	private static Parameter findParameter(Long id) {
		try {
			return getParameter(id);
		} catch (Exception e) {
			System.err.println(" Ce parametre n'existe pas.");
			return userCreateNewParameter();
		}
	}

	private static Parameter getParameter(Long id) {
		return parameters.stream().filter(par -> par.getId() == id).toList().get(0);
	}

	public static void printSimpleParameter() {
		parameters.forEach(Parameter::simpleToString);
	}

}
