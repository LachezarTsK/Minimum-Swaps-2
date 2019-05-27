import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int sizeOfArray = scanner.nextInt();
		/**
		 * Depending on the real life situation, retaining the original input array
		 * might or might not be necessary. Although this algorithm does not require it,
		 * the original input array is retained for better visualization.
		 */
		int[] inputArray = new int[sizeOfArray];
		MapValueToIndex[] valueToIndex = new MapValueToIndex[sizeOfArray];

		for (int i = 0; i < sizeOfArray; i++) {
			inputArray[i] = scanner.nextInt();
			valueToIndex[i] = new MapValueToIndex(inputArray[i], i);
		}
		scanner.close();

		int result = calculateMinimumSwaps(valueToIndex);
		System.out.println(result);
	}

	/**
	 * The method calculates the minimum swaps needed to sort the array in 
	 * ascending order.
	 * 
	 * @return Minimum swaps.
	 */
	private static int calculateMinimumSwaps(MapValueToIndex[] valueToIndex) {
		Arrays.sort(valueToIndex);
		int minimumSwaps = 0;

		for (int i = 0; i < valueToIndex.length; i++) {

			int nodes_newCycle = 0;
			int index_newCycle = i;

			while (valueToIndex[index_newCycle].visited == false) {
				valueToIndex[index_newCycle].visited = true;
				index_newCycle = valueToIndex[index_newCycle].index;
				nodes_newCycle++;
			}
			if (nodes_newCycle > 0) {
				minimumSwaps += nodes_newCycle - 1;
			}
		}
		return minimumSwaps;
	}
}

class MapValueToIndex implements Comparable<MapValueToIndex> {
	int value;
	int index;
	boolean visited;

	public MapValueToIndex(int value, int index) {
		this.value = value;
		this.index = index;
	}

	/**
	 * Sort in ascending order.
	 */
	@Override
	public int compareTo(MapValueToIndex arg0) {
		return this.value - arg0.value;
	}
}
