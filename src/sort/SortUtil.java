package sort;

public class SortUtil {

	public static void quickSort(int a[], int start, int end) {
		int i, j, len, temp;
		i = start;
		j = end;
		len = a.length;
		while( i<j ) {
			while( i<j && a[i] <= a[j]) {
				j--;
			}
			if( i<j) {
				temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				i++;
			}
			while( i<j && a[i] < a[j]) {
				i++;
			}
			if( i<j) {
				temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				j--;
			}
			if( i-start>1) {
				quickSort( a, start, i-1);
			}
			if( end-i>1) {
				quickSort( a, i+1, end);
			}
		}
	}
	
	public static void main(String[] args) {
		int[] a = {59, 1, 22, 45, 21, 65, 33, 76};
		//int[] a = {4,3,2,1,0};
		quickSort(a, 0, a.length-1);
		for(int i: a){
			System.out.print(i+" ");
		}
	}
}
