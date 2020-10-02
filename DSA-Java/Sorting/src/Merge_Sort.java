import java.util.Scanner;

public class Merge_Sort {

	static void mergeSort(int Arr[], int start, int end) {

		if(start < end) {
			int mid = (start + end) / 2;
			mergeSort(Arr, start, mid);
			mergeSort(Arr, mid+1, end);
			merge(Arr, start, end);
		}
	}
	
	static void merge(int Arr[], int start, int end) {
		int mid=(start+end)/2;
		int temp[] = new int[end - start + 1];
		int i = start, j = mid+1, k = 0;
		while(i <= mid && j <= end) {
			if(Arr[i] <= Arr[j]) {
				temp[k] = Arr[i];
				i++;
			}
			else {
				temp[k] = Arr[j];
				j++;
			}
			k++;
		}
	 
		while(i <= mid) {
			temp[k] = Arr[i];
			k++;
			i++;
		}
		while(j <= end) {
			temp[k] = Arr[j];
			k++;
			j++;
		}
	
		for(i = start; i <= end; i += 1) {
			Arr[i] = temp[i - start];
		}
	}

	
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		int[] arr=new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=input.nextInt();
		}
		mergeSort(arr, 0, arr.length-1);
		for(int i:arr)
			System.out.print(i+" ");
	}
}
