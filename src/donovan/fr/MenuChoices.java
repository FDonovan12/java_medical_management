package donovan.fr;

import java.lang.reflect.Method;

public class MenuChoices {
	private int id;
	private String name;
	private Method method;
	private static int count = 1;
	
	public MenuChoices(String name, Method method) {
		super();
		this.id = count++;
		this.name = name;
		this.method = method;
	}
	
	public Method getMethod() {
		return this.method;
	}
	
	@Override
	public String toString() {
		return this.id + ". " + this.name;
	}
}
