package donovan.fr.entity;

import java.time.LocalDate;

public abstract class Observation {
	private Long id;
	private LocalDate createdAt;
	private static Long countId = 0L;
	private Patient patient;

	public Observation() {
		this.id = Observation.countId;
		Observation.countId++;
	}
	public Observation(LocalDate createdAt, Patient patient) {
		this();
		this.createdAt = createdAt;
		this.patient = patient;
		patient.addObservation(this);
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String simpleToString() {
		return this.getClass().getSimpleName() + " id=" + id + " createdAt=" + createdAt;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "\n id=" + id + "\n createdAt=" + createdAt;
	}	
}
