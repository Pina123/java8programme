package javatestProject;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.xml.stream.events.Characters;

public class Java8GroupBy {

	public static void main(String[] args) {

		String str = "applea";
		Map<Character, Long> m = str.chars().mapToObj(i -> Character.toLowerCase(Character.valueOf((char) i)))
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

		System.out.println(m); // count of charecter
		Character c1 = m.entrySet().stream().filter(i -> i.getValue() > 1).findFirst().map(e -> e.getKey()).get();
		System.out.println(c1); // first repeating character

		Character c2 = m.entrySet().stream().filter(i -> i.getValue() == 1).findFirst().map(e -> e.getKey()).get();
		System.out.println(c2); // first nonrepeating character

		Character c3 = str.chars().mapToObj(ch -> Character.valueOf((char) ch))
				.collect(Collectors.groupingBy(ch -> ch, LinkedHashMap::new, Collectors.counting())).entrySet().stream()
				.filter(i -> i.getValue() == 1).map(en -> en.getKey()).findFirst().get();

		Person alex = new Person("Alex", 23);
		Person john = new Person("John", 40);
		Person peter = new Person("Peter", 32);
		List<Person> people = Arrays.asList(alex, john, peter);

		Person minByAge = people.stream().min(Comparator.comparing(Person::getAge))
				.orElseThrow(NoSuchElementException::new);

		System.out.println(minByAge.toString());
		Set<Character> s = new HashSet<>();

		str.chars().mapToObj(i -> Character.valueOf((char) i)).filter(ch -> !s.add(ch)).forEach(System.out::print);
		;
		
		
		
		
		////////////////////////////
		
		
		 Map<String, Integer> map1 = new HashMap<>();
	        map1.put("anil", 1000);
	        map1.put("bhavna", 1300);
	        map1.put("micael", 1500);
	        map1.put("tom", 1600);//output
	        map1.put("ankit", 1200);
	        map1.put("daniel", 1700);
	        map1.put("james", 1400);

	        Map.Entry<String, Integer> results = getNthHighestSalary(4, map1);
	        System.out.println(results);

	        Map<String, Integer> map2 = new HashMap<>();
	        map2.put("anil", 1000);
	        map2.put("ankit", 1200);
	        map2.put("bhavna", 1200);
	        map2.put("james", 1200);
	        map2.put("micael", 1000);
	        map2.put("tom", 1300);
	        map2.put("daniel", 1300);

	        //System.out.println(getNthHighestSalary(2, map2));


	        System.out.println(getDynamicNthHighestSalary(3, map1));

	}
	
	 public static Map.Entry<String, Integer> getNthHighestSalary(int num, Map<String, Integer> map) {
	        return map.entrySet().stream()
	                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
	                .collect(Collectors.toList())
	                .get(num - 1);
	    }

	    public static Map.Entry<Integer, List<String>> getDynamicNthHighestSalary(int num, Map<String, Integer> map) {
	        return map.entrySet()
	                .stream()
	                .collect(Collectors.groupingBy(Map.Entry::getValue,
	                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())
	                ))
	                .entrySet()
	                .stream()
	                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
	                .collect(Collectors.toList())
	                .get(num - 1);
	    }

}

class Person {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	String name;
	Integer age;

	public Person(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", getName()=" + getName() + ", getAge()=" + getAge()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	// standard constructors, getters and setters
}
