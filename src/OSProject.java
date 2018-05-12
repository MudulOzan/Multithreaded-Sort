import java.util.Arrays;
import java.util.Random;

public class OSProject {
   public static void main(String args[]) {
	   // Array of random integers
	   Random rand = new Random();
	   int[] original = new int[10000000];
	   for (int i=0; i<original.length; i++) {
		   original[i] = rand.nextInt(1000);
	   }	
	
	   // Array divided to 2
	   long startTime = System.currentTimeMillis();
	   int[] subArr1 = new int[original.length/2];
	   int[] subArr2 = new int[original.length - original.length/2];
	   int[] mergedArr = new int[original.length];
	   System.arraycopy(original, 0, subArr1, 0, original.length/2);
	   System.arraycopy(original, original.length/2, subArr2, 0, original.length - original.length/2);
	
	   // Arrays are sent to different threads and sorted in there
	   Thread1 T1 = new Thread1(subArr1);
	   Thread2 T2 = new Thread2(subArr2);
	   T1.start();
	   T2.start();
	   // Arrays by using get method merged in other method and passed to new array
	   mergedArr = finalMerge(T1.getInternal(), T2.getInternal());
	   long endTime = System.currentTimeMillis();
	   // Time passed:
	   System.out.println("It took " + (endTime - startTime) + " miliseconds");
   }
   // Merging the arrays
   public static int[] finalMerge(int [] arr1, int [] arr2) {
	   int [] arr = new int [arr1.length + arr2.length];
	   System.arraycopy(arr1, 0, arr, 0, arr1.length);
	   System.arraycopy(arr2, 0, arr, arr1.length, arr2.length);
	   Arrays.sort(arr);
	   return arr;
   }
   // One thread merges the first half
   private static class Thread1 extends Thread {
	   private int[] intArr;
	   Thread1(int[] arr) { this.intArr = arr; }
	   public int[] getInternal() { return intArr; }
	   public void run() {
		   Arrays.sort(intArr);
	   }
   }
   // One thread merges the other half
   private static class Thread2 extends Thread {
	   private int[] intArr;
	   Thread2(int[] arr) { this.intArr = arr; }
	   public int[] getInternal() { return intArr; }
	   public void run() {
		   Arrays.sort(intArr);
	   }
   } 
}