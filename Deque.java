import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	public static void main(String[] args) {
		Deque<String> s = new Deque<String>();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (item.contains("+f"))
				s.addFirst(item.substring(2));
			else if (item.contains("+l"))
				s.addLast(item.substring(2));
			else if (item.contains("-f"))
				s.removeFirst();
			else if (item.contains("-l"))
				s.removeLast();

			for (String vString : s) {
				StdOut.print(vString);
			}
			StdOut.println();
		}
		StdOut.println("(" + s.size() + " left on stack)");

	}

	private Note firstNote;
	private Note lastNote;
	private int N;

	private class Note {
		private Item item;
		private Note next;
		private Note previous;
	}

	public Deque() {
		firstNote = null;
		lastNote = null;
		N = 0;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public void addFirst(Item item) {
		
		if (item == null)
			throw new NullPointerException();
		
		Note oldFirst = firstNote;
		firstNote = new Note();
		firstNote.item = item;
		firstNote.next = oldFirst;
		if (oldFirst != null)
			oldFirst.previous = firstNote;
		else
			lastNote = firstNote;
		N++;
	}

	public void addLast(Item item) {
		
		if (item == null)
			throw new NullPointerException();
		
		Note oldLast = lastNote;
		lastNote = new Note();
		lastNote.item = item;
		lastNote.next = null;
		lastNote.previous = oldLast;
		if (oldLast != null)
			oldLast.next = lastNote;
		else
			firstNote = lastNote;
		N++;
	}

	public Item removeFirst() {
		
		checkEmpty();
		
		Item firstValue = firstNote.item;
		firstNote = firstNote.next;
		if (N == 1)
			lastNote = null;
		else
			firstNote.previous = null;
		N--;
		return firstValue;
	}

	public Item removeLast() {
		
		checkEmpty();
		
		Item lastValue = lastNote.item;
		lastNote = lastNote.previous;
		if (N == 1)
			firstNote = null;
		else
			lastNote.next = null;
		N--;
		return lastValue;
	}

	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {

		Note Current = firstNote;

		public boolean hasNext() {
			return Current != null;
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = Current.item;
			Current = Current.next;
			return item;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	private void checkEmpty() {
		if (isEmpty())
			throw new NoSuchElementException();
	}

}
