package javatestProject;

import java.util.Arrays;
import java.util.Comparator;
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
