package java8.ex02;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Exercice 02 - Map
 */
public class Lambda_02_Test {

	// tag::GenericMapper[]
	interface GenericMapper<T, E> {
		E map(Person p);
	}
	// end::GenericMapper[]

	// tag::map[]
	private <T, E> List<E> map(List<Person> personList, GenericMapper<T, E> mapper) {
		// TODO implémenter la méthode pour transformer une liste de personnes en liste de comptes
		List<E> genericList = new ArrayList<>();
		for (Person person : personList) {
			genericList.add(mapper.map(person));
		}
		return genericList;
	}
	// end::map[]

	// tag::test_map_person_to_account[]
	@Test
	public void test_map_person_to_account() throws Exception {

		List<Person> personList = Data.buildPersonList(100);
		
		// TODO transformer la liste de personnes en liste de comptes
		// TODO tous les objets comptes ont un solde à 100 par défaut
		List<Account> result = map(personList, person -> {
			Account account= new Account();
			account.setBalance(100);
			account.setOwner(person);	
			return account;
		});

		assert result.size() == personList.size();
		for (Account account : result) {
			assert account.getBalance().equals(100);
			assert account.getOwner() != null;
		}
	}
	// end::test_map_person_to_account[]

	// tag::test_map_person_to_firstname[]
	@Test
	public void test_map_person_to_firstname() throws Exception {

		List<Person> personList = Data.buildPersonList(100);

		// TODO transformer la liste de personnes en liste de prénoms
		List<String> result = map(personList, person -> person.getFirstname());

		assert result.size() == personList.size();
		for (String firstname : result) {
			assert firstname.startsWith("first");
		}
	}
	// end::test_map_person_to_firstname[]
}
