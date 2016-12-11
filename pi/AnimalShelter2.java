import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;


public class AnimalShelter2
{

	LinkedList<Animal> dogs = new LinkedList<Animal>();
	LinkedList<Animal> cats = new LinkedList<Animal>();


	public void enque(Animal animal) throws Exception
	{
		if(animal == null)
			throw new NullPointerException();

		animal.setEntryTime( ZonedDateTime.now() );

		if (animal.type == AnimalType.DOG)
		{
			dogs.add(animal);
		}
		else
		{
			cats.add(animal);
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

	public Animal dequeDog() throws Exception
	{
		return dogs.remove();
	}

	public Animal dequeCat() throws Exception
	{
		return cats.remove();
	}



	public static class Animal
	{
		public final AnimalType type;

		private ZonedDateTime entryTime;

		public Animal(AnimalType type)
		{
			this.type = type;
		}

		public String toString()
		{
			return entryTime.toString();
		}

		public void setEntryTime(ZonedDateTime time)
		{
			this.entryTime = time;
		}

		public ZonedDateTime getEntryTime()
		{
			return this.entryTime;
		}
	}

	public static enum AnimalType
	{
		DOG, CAT
	}

	public String toString()
	{
		StringBuffer sb = new StringBuffer();

		sb.append("Dogs:: \n");
		for (Animal dog : dogs)
		{
			sb.append(dog.toString());
			sb.append(",\n");
		}

		sb.append("\nCats:: ");
		for(Animal cat : cats)
		{
			sb.append(cat.toString());
			sb.append(",\n");
		}
		sb.append("\n");

		return sb.toString();

	}

	public static void main(String[] args) throws Exception
	{
		AnimalShelter2 as1 = new AnimalShelter2();
		as1.enque(new Animal(AnimalType.DOG));
		as1.enque(new Animal(AnimalType.CAT));

		as1.enque(new Animal(AnimalType.DOG));
		as1.enque(new Animal(AnimalType.CAT));

		as1.enque(new Animal(AnimalType.DOG));
		as1.enque(new Animal(AnimalType.CAT));

		System.out.println(as1);
	}
	
}