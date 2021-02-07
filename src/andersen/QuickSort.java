package andersen;

/**Quick Sort Algorithm**/

public class QuickSort {
    public static void main(String[] args) {
    }

    /**unsorted array**/
    private static int[] unsortedArray = {23, 53, 2, 35, 775, 34, 52, 4, 6, 7, 12, -4, 2, 123, -67, 232, -11, 2322, -56, 23, 1};

    public static void quickSort(int[] array, int begin, int end) {

        if (begin < end) {
            int leftPoint = begin;
            int rightPoint = end;
            int pivot = array[(leftPoint + rightPoint) / 2];

            while (leftPoint <= rightPoint) {
                while (array[leftPoint] < pivot) {
                    leftPoint++;
                }
                while (array[rightPoint] > pivot) {
                    rightPoint--;
                }

                if (leftPoint <= rightPoint) {
                    int temp = array[leftPoint];
                    array[leftPoint] = array[rightPoint];
                    array[rightPoint] = temp;
                    leftPoint++;
                    rightPoint--;
                }
            }
            quickSort(array, begin, leftPoint - 1);
            quickSort(array, leftPoint, end);
        }
    }

}
