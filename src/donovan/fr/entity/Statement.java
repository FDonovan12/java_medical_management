package donovan.fr.entity;

import java.time.LocalDate;

public class Statement {
	private Long id;
	private LocalDate createdAt;
	private float value;
	private static Long countId = 0L;
	private Patient patient;
	private Parameter parameter;

	public Statement() {
		this.id = Statement.countId;
		Statement.countId++;
	}
	public Statement(LocalDate createdAt, float value, Patient patient, Parameter parameter) {
		this();
		this.createdAt = createdAt;
		this.value = value;
		this.patient = patient;
		this.parameter = parameter;
		this.patient.addStatement(this);
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	
	public void simpleToString() {
		System.out.println("id=" + id + "\n patient=" +patient.simpleToString() +"\n createdAt=" + createdAt + ", value=" + parameter.getname() +" : " +value );
	}
	
	@Override
	public String toString() {
		return "Statement\n id=" + id + "\n createdAt=" + createdAt + "\n value=" + value + "\n parameter=\n{" + parameter + "\n}";
	}
	public Long getId() {
		return this.id;
	}
	
	
}
