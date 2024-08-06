package donovan.fr.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import donovan.fr.Utils;
import donovan.fr.entity.Parameter;
import donovan.fr.entity.Patient;
import donovan.fr.entity.Statement;

public class StatementRepository extends Database {
	private static List<Statement> statements = new ArrayList<Statement>();

	public static Statement userCreateNewStatement() {
		System.out.println(" Debut de la creation d'un relevé");
		Parameter parameter = ParameterRepository.userFindParameter();
		Patient patient = PatientRepository.userFindPatient();
		LocalDate createdAt = Utils.userWriteADate(" Quelle est la date du relevé ? (dd/mm/yyyy)");
		float value = Utils.questionScannerFloat(" Quelle est la valeur du releve ?");
		Statement statement = new Statement(createdAt, value, patient, parameter);
		return addStatement(statement);
	}

	private static Statement addStatement(Statement statement) {
		if (statements.stream().filter(par -> par.getId() == statement.getId()).count() == 0) {
			statements.add(statement);
		}
		return statement;
	}

	public static void userDeleteStatement() {
		Statement statement = userFindStatement();
		deleteStatement(statement);
	}

	private static void deleteStatement(Statement statement) {
		statements = statements.stream().filter(par -> par.getId() != statement.getId()).toList();
		
//		List<Statement> newStatement = new ArrayList<Statement>();
//		for (Statement statement2 : statements) {
//			if (statement2.getId() != statement.getId()) {
//				newStatement.add(statement2);
//			}
//		}
//		statements = newStatement;
		
		statement.getPatient().deleteStatement(statement);
	}

	public static Statement userFindStatement() {
		if (statements.isEmpty()) {
			System.out.println(" Il n'y as pas encore de relevé.");
			return userCreateNewStatement();
		}
		System.out.println(" Voici la liste des relevés : ");
		printSimpleStatements();
		boolean isResponseLong = false;
		Long statementId = null;
		do {
			try {
				statementId = Utils.questionScannerLong(" Quelle est le relevé que vous voulez");
				isResponseLong = true;
			} catch (Exception e) {
				System.err.println(" La valeur entree n'est pas acceptable donner juste l'id en chiffre.");
			}
		} while (!isResponseLong);
		return findStatement(statementId);
	}

	private static Statement findStatement(Long id) {
		try {
			return getStatement(id);
		} catch (Exception e) {
			System.err.println(" Ce releve n'existe pas.");
			return userCreateNewStatement();
		}
	}

	private static Statement getStatement(Long id) {
		return statements.stream().filter(pat -> pat.getId() == id).toList().get(0);
	}

	public static void printSimpleStatements() {
		statements.forEach(Statement::simpleToString);
	}

	public static void printStatements() {
		statements.forEach(System.out::println);
	}

	
	public static void printSortedStatements() {
		statements.stream().sorted((s1, s2) -> s2.getCreatedAt().compareTo(s1.getCreatedAt())).toList()
		.forEach(System.out::println);
	}

}
