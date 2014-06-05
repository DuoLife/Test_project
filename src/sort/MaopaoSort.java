package sort;

public class MaopaoSort {

	public static void main(String[] args) {
		int[] a = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
		for(int i: a) {
			System.out.print(i+",");
		}
		System.out.println();
		sort(a);
		for(int i: a) {
			System.out.print(i+",");
		}
	}
	public static void sort(int[] array) {
		int size = array.length;
		int temp;
		for(int i = 0; i < (size-1); i++) {
			for(int j = 0; j < (size-i); j++) {
				if(array[j]>array[j+1]) {
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
	}
}
