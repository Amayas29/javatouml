import java.util.ArrayList;

public class TestClass {

	private int number;
	public String name;
	protected ArrayList<Object> list;
	Object mutex;

	public TestClass(int number, String name, ArrayList<Object> list, Object mutex) {
		this.number = number;
		this.name = name;
		this.list = list;
		this.mutex = mutex;
	}

	public ArrayList<Object> getList() {
		return list;
	}

	public Object getMutex() {
		return mutex;
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public void setList(ArrayList<Object> list) {
		this.list = list;
	}

	public void setMutex(Object mutex) {
		this.mutex = mutex;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "";
	}

	private int inc() {
		return ++number;
	}

	public doThing() {
		inc();
		if (number == 0) {
			System.out.println("number");
		}

		for (int i = 0; i < number; i++) {
			if (mutex != null) {
				if (list != null) {
					if (list.isEmpty()) {
						name = "ok";
					}
				}
			}
		}
	}
}
