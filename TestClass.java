import java.util.ArrayList;

enum State {
	EMPTY, FULL;
}

public class TestClass implements TestInterface {

	public String name;
	protected ArrayList<Object> list;
	private static int cpt = 0;
	private final int id;
	Object mutex;

	public TestClass(String name, ArrayList<Object> list) {
		this.name = name;
		this.list = list;
		this.mutex = new Object();
		id = cpt++;
	}

	public final ArrayList<Object> getList() {
		return list;
	}

	public Object getMutex() {
		return mutex;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		doThing(0, 0);
		return "TestClass";
	}

	private void doThing(int a, int b, Object... args) {
		// do something
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

	@Override
	public Object str(int index) {
		return null;
	}

}

class OtherClass extends TestClass {
	public OtherClass(String name) {
		super(name, null);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public int compareTo(Object o) {
		return 1;
	}
}

interface TestInterface extends Comparable<Object> {

	public Object str(int index);

	default public void print() {
		System.out.println("default");
	}
}