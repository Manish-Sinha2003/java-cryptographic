package com.iispl.jca;

public class MergeArrayEx {
	
	public static void main(String[] args) {
		String[] arr1= {"one","two","three"};
		String[] arr2= {"four","five","six"};
		
		String[] arr3=new String[6];
		
		System.arraycopy(arr1, 0, arr3, 0, arr1.length);
		System.arraycopy(arr2, 0, arr3, arr1.length, arr2.length);
		
		for(String arr:arr3) {
			System.out.println(arr);
		}
		
	}
}
