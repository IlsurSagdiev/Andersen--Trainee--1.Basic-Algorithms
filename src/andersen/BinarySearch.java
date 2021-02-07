package andersen;

/** Binary Search, worked only sorted array**/
public class BinarySearch {

    public static void main(String[] args) {

    }

    /** sorted array**/
    private static int[] sortedArray = {2, 5, 7, 34, 56, 78, 89, 96, 105, 112, 143, 234, 600, 769, 956, 999};


    public static int binarySearch(int[] array, int number) {
        int minIndex = 0;
        int maxIndex = array.length;
        int index = -1;

        while (minIndex <= maxIndex) {
            int mid = (minIndex + maxIndex) / 2;

            if (array[mid] > number) {
                maxIndex = mid - 1;
            } else if (array[mid] < number) {
                minIndex = mid + 1;
            } else if (array[mid] == number) {
                index = mid;
                break;
            }
        }
        return index;
    }

}


