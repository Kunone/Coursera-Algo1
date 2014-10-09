import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomizedQueue<String> s = new RandomizedQueue<String>();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (item.contains("-"))
				StdOut.println("out: " + s.dequeue());
			else if (item.contains("*"))
				s.sample();
			else
				s.enqueue(item);

			for (String vString : s) {
				StdOut.print(vString == null ? "*" : vString);
			}
			StdOut.println();
			StdOut.println("last index: " + s.lastIndex);
		}
	}

	private Item[] items;
	private int N; // number of items
	private int lastIndex; // index of the last item

	public RandomizedQueue() {
		N = 0;
		lastIndex = -1;
		items = (Item[]) new Object[2];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public void enqueue(Item item) {

		if (item == null)
			throw new NullPointerException();

		if (lastIndex == items.length - 1)
			resize(2 * items.length);
		items[++lastIndex] = item;
		N++;
	}

	// delete and return a random item
	public Item dequeue() {
		checkEmpty();

		int randomIndex = getRandomValueIndex();

		Item item = items[randomIndex];
		items[randomIndex] = null;
		N--;
		if (randomIndex == lastIndex)
			lastIndex--;

		// shrink size of array if necessary
		if (N > 0 && N == items.length / 4)
			resize(items.length / 2);
		return item;
	}

	// return (but do not delete) a random item
	public Item sample() {
		checkEmpty();
		int randomIndex = getRandomValueIndex();
		return items[randomIndex];
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {

		private Item[] shuffleItems;
		private int shuffleIndex;

		private RandomizedQueueIterator() {
			shuffleIndex = N;
			shuffleItems = (Item[]) new Object[N];
			int j = 0;
			for (int i = 0; i < lastIndex + 1; i++) {
				if (items[i] != null)
					shuffleItems[j++] = items[i];
			}
			StdRandom.shuffle(shuffleItems);
		}

		public boolean hasNext() {
			return shuffleIndex > 0;
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return shuffleItems[--shuffleIndex];
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private void resize(int capacity) {
		assert capacity >= lastIndex + 1;
		Item[] temp = (Item[]) new Object[capacity];
		int tempIndex = 0;
		for (Item item : items) {
			if (item != null)
				temp[tempIndex++] = item;
		}
		items = temp;
		lastIndex = tempIndex - 1;
	}

	private int getRandomValueIndex() {
		int randomIndex = 0;

		do {
			randomIndex = StdRandom.uniform(0, lastIndex + 1);
		} while (items[randomIndex] == null);

		return randomIndex;
	}

	private void checkEmpty() {
		if (isEmpty())
			throw new NoSuchElementException();
	}

}
