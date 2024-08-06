package donovan.fr.entity;


public class Parameter {
	private Long id;
	private String name;
	private static Long countId = 0L;

	public Parameter() {
		this.id = Parameter.countId;
		Parameter.countId++;
	}

	public Parameter(String name) {
		this();
		this.name = name;
	}

	public void simpleToString() {
		System.out.println("id=" + id + ", name=" + name);
	}

	@Override
	public String toString() {
		return "Parameter\n id=" + id + "\n name=" + name;
	}

	public Long getId() {
		return this.id;
	}

	public String getname() {
		return this.name;
	}
}
