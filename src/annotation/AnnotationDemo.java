package annotation;
@MyAnnotation1("this is annotation1")
public class AnnotationDemo {
	public static void sayHello() {
		System.out.println("Hello World!");
	}
	public static void main(String[] args) {
		sayHello();
	}
}
