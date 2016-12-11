import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;


public class AnimalShelter1
{

	LinkedList<Dog> dogs = new LinkedList<Dog>();
	LinkedList<Cat> cats = new LinkedList<Cat>();

	public void enque(Animal animal) throws Exception
	{
		if(animal == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			animal.setEntryTime(ZonedDateTime.now());
		}


		if(animal instanceof Dog)
		{
			dogs.add((Dog)animal);
		}
		else if(animal instanceof Cat)
		{
			cats.add((Cat)animal);
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	public Animal dequeAny() throws Exception
	{
		Animal ret = null;
		if(dogs.isEmpty() && cats.isEmpty())
		{
			/** 
			* Empty queue exception. Or should a special value be returned?
			* Or should rely on the exceptions thrown by Queue?
			*/
			throw new NoSuchElementException(); 
		}
		
		if (!dogs.isEmpty() && !cats.isEmpty())
		{
			if((dogs.element().getEntryTime()).compareTo(cats.element().getEntryTime()) < 0 )
			{
				ret = dequeDog();
			}
			else
			{
				ret = dequeCat();
			}
		}
		else if (!cats.isEmpty())
		{
			ret = dequeCat();
		}
		else if (!dogs.isEmpty())
		{
			ret = dequeDog();
		}

		return ret;
	}

	public Dog dequeDog() throws Exception
	{
		return dogs.remove();
	}

	public Cat dequeCat() throws Exception
	{
		return cats.remove();
	}



	public static class Animal
	{
		private ZonedDateTime entryTime;

		public void setEntryTime(ZonedDateTime time_now)
		{
			entryTime = time_now;
		}

		public ZonedDateTime getEntryTime()
		{
			return entryTime;
		}

		public String toString()
		{
			return entryTime.toString();
		}
	}

	public static class Dog extends Animal
	{

	}

	public static class Cat extends Animal
	{

	}

	public String toString()
	{
		StringBuffer sb = new StringBuffer();

		sb.append("Dogs:: \n");
		for (Dog dog : dogs)
		{
			sb.append(dog.toString());
			sb.append(",\n");
		}

		sb.append("\nCats:: ");
		for(Cat cat : cats)
		{
			sb.append(cat.toString());
			sb.append(",\n");
		}
		sb.append("\n");

		return sb.toString();

	}
	
	public static void main(String[] args) throws Exception
	{
		AnimalShelter1 as1 = new AnimalShelter1();
		as1.enque(new Dog());
		as1.enque(new Cat());

		as1.enque(new Dog());
		as1.enque(new Cat());

		as1.enque(new Dog());

		System.out.println(as1);
	}
}