/**
 * 
 * @author Shashwat Koranne
 *
 */
public class InsertionSort {
	public static int[] insertSort(int[] array) {
		int length = array.length;
		for (int i = 1; i < length; i++) {
			for (int j = i; j > 0; j--) {
				if (array[j] >= array[j-1]) {
					break;
				} else {
					array = swap(array, j, j-1);
				}
			}
		}
		return array;
	}
	
	public static int[] swap(int[] array, int a, int b) {
		int temp = array[b];
		array[b] = array[a];
		array[a] = temp;
		return array;
	}
	
	public static void main(String[] args) {
		int[] array = {5, 2, 1, 4, 3, 6};
		int[] sortedArray = insertSort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.println(sortedArray[i]);
		}
	}
}
