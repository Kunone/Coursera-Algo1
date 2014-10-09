public class Subset {

	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		if (k == 0)
			return;

		RandomizedQueue<String> rq = new RandomizedQueue<String>();

		while (!StdIn.isEmpty()) {
			if (rq.size() == k) {
				rq.dequeue();
			}
			rq.enqueue(StdIn.readString());
		}

		for (int i = 0; i < k; i++)
			StdOut.println(rq.dequeue());
	}

}
