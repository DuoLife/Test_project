package sort;

public class QuickSort {

	public static void main(String[] args) {
		int[] a = {59, 1, 22, 45, 21, 65, 33, 76};
		sort( a, 0, a.length-1);
		for(int i: a) {
			System.out.print(i +" ");
		}
	}
	
	public static void sort(int[] array,int start, int end) {
		int i, j;
		i = start;
		j = end;
		while(i<j) {
			while(i<j && array[i] <= array[j]) {
				j--;
			}
			if(i<j) {
				int temp = array[j];
				array[j] = array[i];
				array[i] = temp;
			}
			while(i<j && array[i] < array[j]) {
				i++;
			}
			if(i<j) {
				int temp = array[j];
				array[j] = array[i];
				array[i] = temp;
			}
			if(i-start>1) {              //不要想得太复杂，就是判断关键值之前是否有两个以上元素，有则递归调用，进行排序
				sort(array, start, i-1);
			}
			if(end-i>1) {                //判断关键值之后是否有两个以上元素，有则递归调用，进行排序
				sort(array, i+1, end);
			}
		}
	}
}
