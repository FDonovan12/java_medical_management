package donovan.fr.entity;

import java.time.LocalDate;

public class AudioObservation extends Observation{
	private String path;
	private long duration;

//	public AudioObservation() {
//		super();
//	}
	public AudioObservation(LocalDate createdAt, Patient patient) {
		super(createdAt, patient);
	}
	public AudioObservation(LocalDate createdAt, Patient patient, String path, long duration) {
		this(createdAt, patient);
		this.path = path;
		this.duration = duration;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\n path=" + path + "\\n duration=" + duration;
	}
}
