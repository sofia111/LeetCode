package Sort;

import java.util.Arrays;

/**
*@Author: Sofia
*@Email: feng-sofia@foxmail.com
*@Date: 2019/5/16 17:01
*@Description: 堆排序，按升序或降序需求建大顶堆或小顶堆，
 * 大顶堆举例：将堆顶元素与末尾元素交换，将最大元素沉到数组末端，重新堆化，再交换，直到为有序序列，
*/
public class HeapSort {

    public static void main(String[] args) {

        int[] array = {1,4,6,3,6,90,4,21};
        sort(array);
        System.out.println(Arrays.toString(array));

    }

    public static void sort(int[] array){
        for (int i = array.length/2 - 1; i >= 0; i-- ){
            adjustHeap(array,i, array.length);

        } System.out.println(Arrays.toString(array));
        for (int j = array.length - 1; j > 0; j--){
            swap(array,0,j);
            adjustHeap(array,0,j);
        }
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void adjustHeap(int[] arr, int i, int len){
        int temp = arr[i];
        for (int k = 2 * i + 1; k < len; k  = 2 * k + 1){
            if ( k+1 < len && arr[k] < arr[k+1]){
                k++;
            }
            if (arr[k] > temp){
                arr[i]  = arr[k];
                i = k;
            }else{
                break;
            }
            arr[i] = temp;
        }
    }
}
