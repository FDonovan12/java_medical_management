package donovan.fr.entity;

import java.time.LocalDate;

public class TextObservation extends Observation {
	private String content;

	public TextObservation(LocalDate createdAt, Patient patient) {
		super(createdAt, patient);
	}
	public TextObservation(LocalDate createdAt, Patient patient, String content) {
		this(createdAt, patient);
		this.content = content;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\n content=" + content;
	}
}
