import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class tempHeap {
	static int HeapSum=0;
	static ArrayList<Integer> maxHeap = new ArrayList<Integer>();

	public static void insert(Integer newValue) {
		maxHeap.add(newValue);
		HeapSum+=newValue;
		if (maxHeap.size() == 1)
			return;
		int insertedNodeIndex = maxHeap.size() - 1;
		int parentNodeIndex = (int) (insertedNodeIndex - 1) / 2;
		while (maxHeap.get(insertedNodeIndex) > maxHeap.get(parentNodeIndex)) {
			swap(insertedNodeIndex, parentNodeIndex);
			insertedNodeIndex = parentNodeIndex;
			if (insertedNodeIndex == 0)
				break;
	
			parentNodeIndex = (int) (insertedNodeIndex - 1) / 2;
		}
	}
	public static int removeMax() {
		
		if (maxHeap.size() == 1)
			return maxHeap.remove(0);

		int currentMax = maxHeap.get(0);
		maxHeap.set(0, maxHeap.remove(maxHeap.size() - 1));
		int currentIndex = 0, leftChild = 2 * currentIndex + 1, rightChild = 2 * currentIndex + 2, swapIndex;
	
		while (leftChild < maxHeap.size() || rightChild < maxHeap.size()) {

			if ((2 * currentIndex + 2) < maxHeap.size()) {
				if (maxHeap.get(leftChild) > maxHeap.get(rightChild))
					swapIndex = leftChild;
				else
					swapIndex = rightChild;
				if (maxHeap.get(swapIndex) > maxHeap.get(currentIndex)) {
					swap(swapIndex, currentIndex);
					currentIndex = swapIndex;
					leftChild = 2 * currentIndex + 1;
					rightChild = 2 * currentIndex + 2;
				} else {
					break;
				}
			}
			else {
				
				if (maxHeap.get(leftChild) > maxHeap.get(currentIndex)) {
					swap(leftChild, currentIndex);
				}
				break;
			}
		}
		return currentMax;
	}
	public static void swap(int i, int j) {
		int temp1 = maxHeap.get(i);
		int temp2 = maxHeap.get(j);
		maxHeap.set(j, temp1);
		maxHeap.set(i, temp2);
	}
	public static void sort(double ratios[], int[] skills) {
		int n = ratios.length;

		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(ratios, n, i, skills);

		for (int i = n - 1; i >= 0; i--) {
			double temp = ratios[0]; 
			ratios[0] = ratios[i]; 
			ratios[i] = temp; 

			 int temp1=skills[0];
			skills[0]=skills[i];
			skills[i]=temp1;
			
			heapify(ratios, i, 0, skills);
		}
	}

	static void heapify(double ratios[], int n, int i, int[] skills) {
		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;

		if (l < n && ratios[l] > ratios[largest])
			largest = l;

		if (r < n && ratios[r] > ratios[largest])
			largest = r;
		
		if (largest != i) {
			ratios[i]+=ratios[largest];
			ratios[largest]=ratios[i]-ratios[largest];
			ratios[i]-=ratios[largest];

			skills[i]+=skills[largest];
			skills[largest]=skills[i]-skills[largest];
			skills[i]-=skills[largest];
			
			heapify(ratios, n, largest, skills);
		}
	}
	
	static class Reader {
		static BufferedReader reader;
		static StringTokenizer tokenizer;

		static void init(InputStream input) {
			reader = new BufferedReader(new InputStreamReader(input));
			tokenizer = new StringTokenizer("");
		}

		static String next() throws IOException {
			while (!tokenizer.hasMoreTokens()) {
				tokenizer = new StringTokenizer(reader.readLine());
			}
			return tokenizer.nextToken();
		}

		static int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		static double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}
	}
	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		int n = Reader.nextInt();
		int k = Reader.nextInt();
		int[] skills = new int[n];
		for (int i = 0; i < n; i++) {
			skills[i] = Reader.nextInt();
		}
		long[] demand = new long[n];
		double[] ratios = new double[n];
		double amount=Double.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			demand[i] = Reader.nextInt();
			ratios[i] = (double) demand[i] / skills[i];
		}
		sort(ratios,skills);
		for (int i = 0; i < k; i++) {
			insert(skills[i]);

		}
		amount = HeapSum * ratios[k - 1];
		for (int i = k; k < n; k++) {
			insert(skills[i]);
			HeapSum-=removeMax();
			amount = Math.min(amount, HeapSum * ratios[i]);
		}
		System.out.println((long) Math.ceil(amount));
	}
}