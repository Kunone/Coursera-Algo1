import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue2<Item> implements Iterable<Item> {

	private Item[] q; // RandomizedQueue array
	private int N = 0; // size of the RandomizedQueue

	// construct an empty randomized queue
	public RandomizedQueue2() {
		q = (Item[]) new Object[2];
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
		if (q.length == N)
			resize(q.length * 2);
		q[N++] = item;
	}

	// delete and return a random item
	public Item dequeue() {
		checkEmpty();
		int rand = StdRandom.uniform(N--);
		Item item = q[rand];
		if (rand != N) {
			q[rand] = q[N];
		}
		q[N] = null;
		if (N > 0 && N == q.length / 4)
			resize(q.length / 2);
		return item;
	}

	// return (but do not delete) a random item
	public Item sample() {
		checkEmpty();

		return q[StdRandom.uniform(N)];
	}

	// Returns an iterator that iterates over the items in this queue in FIFO
	// order.
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator implements Iterator<Item> {
		private int n;
		private int[] shuffledIndex;

		public ListIterator() {
			n = N;
			shuffledIndex = new int[N];
			for (int i = 0; i < N; i++)
				shuffledIndex[i] = i;
			StdRandom.shuffle(shuffledIndex);
		}

		public boolean hasNext() {
			return n > 0;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return q[shuffledIndex[--n]];
		}
	}

	private void checkEmpty() {
		if (isEmpty())
			throw new NoSuchElementException();
	}

	// resize the underlying array holding the elements
	private void resize(int capacity) {
		assert capacity >= N;
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			temp[i] = q[i];
		}
		q = temp;
	}
}
