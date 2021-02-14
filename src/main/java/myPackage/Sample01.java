package myPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sample01 {

	public static void main(String[] args) {

		int arr[] = {12,34,67,74,10,90,45};
		Integer arr2[] = {33,65,83,93,76,19};
		
		System.out.println("Before Sort \n");
		for(int num:arr) {
			System.out.print(num+" ");
		  }
	for(int j=0;j<arr.length;j++) {	
		for(int i=0;i<(arr.length)-1;i++) {
			if(arr[i]>arr[i+1]) {
				int temp = arr[i];
				arr[i] = arr[i+1];
				arr[i+1] = temp;
		}
	  }
	}
				
		System.out.print("\n After Sort \n");
		for(int num:arr) {
			System.out.print(num+" ");
		}
		System.out.print("\n Before Selection Sort \n");
//		List<Integer> ls = Arrays.asList(arr2);
//		System.out.println("\n List Before Sort \n");
//		System.out.println(ls);
//		Collections.sort(ls);
//		System.out.println("\n List After Sort Ascending order \n");
//		System.out.println(ls);
//		Collections.sort(ls,Collections.reverseOrder());
//		System.out.println("\n List After Sort Descending order");
//		System.out.println(ls);
		for(Integer num:arr2) {
			System.out.print(num+" ");
		}
		int key; 
		 for(int i=0;i<arr2.length;i++) {
			 for(int j=i+1;j<arr2.length;j++) {
				 if(arr2[i]>arr2[j]) {
					 key=arr2[i];
					 arr2[i] = arr2[j];
					 arr2[j]=key;
				 }
			 }
		 }
		 System.out.println("After Selection Sort \n");
		for(Integer num:arr2) {
			System.out.print(num+" ");
		}
  }
}	
