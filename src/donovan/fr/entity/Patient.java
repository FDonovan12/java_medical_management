package donovan.fr.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Patient {
	private Long id;
	private LocalDate birthDate;
	private String name;
	private String lastName;
	private static Long countId = 0L;
	private List<Observation> observations = new ArrayList<Observation>();
	private List<Statement> statements = new ArrayList<Statement>();
	
	
	public Patient() {
		this.id = Patient.countId;
		Patient.countId++;
	}
	public Patient(LocalDate birthDate, String name, String lastName) {
		this();
		this.birthDate = birthDate;
		this.name = name;
		this.lastName = lastName;
	}
	
	public void addObservation(Observation observation) {
		this.observations.add(observation);
	}
	public void addStatement(Statement statement) {
		this.statements.add(statement);
	}
	
	public void deleteStatement(Statement statement) {
		this.statements = this.statements.stream().filter(st -> st.getId() != statement.getId()).toList();
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLastName() {
		return lastName;
	}
	public String simpleToString() {
		return ("id=" + id + ", name=" + name + ", lastName=" + lastName);
	}
	
	@Override
	public String toString() {
		return "Patient\n id=" + id + "\n birthDate=" + birthDate + "\n getAge=" + getAge() + "\n name=" + name + "\n lastName=" + lastName
				+ "\n observation= \n{" + observations + "\n}\n statement= \n{" + statements + "\n}";
	}
	public long getAge() {
		long daysBetween = ChronoUnit.YEARS.between(birthDate, LocalDate.now());
		return daysBetween;
	}
	
	
}
