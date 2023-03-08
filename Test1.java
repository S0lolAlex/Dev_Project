package Comparation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test1 {
	
	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>();
		
		list.add("Zayr");
		list.add("Bob");
		list.add("Mary");
		
		System.out.println("Before sorting");
		System.out.println(list);
		System.out.println("After sorting");
		Collections.sort(list);  // сортировка по заглавным буквам
		System.out.println(list);
	}

}
