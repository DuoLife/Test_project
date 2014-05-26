package virtual_method;

public class VirtualMethod {

	public static void main(String[] args) {
		A a = new A();
		A b = new B();
		a.aMethod();
		b.aMethod();
	}
}
class A {
	public void aMethod() {
		System.out.println("A类的aMethod...");
	}
}
class B extends A {
	public void aMethod() {
		System.out.println("B类的aMethod...");
	}
}
