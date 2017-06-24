
public class Permutation {
	public static void main(String[] args) {
		RandomizedQueue d = new RandomizedQueue();
		d.addFirst("AA");
		d.addLast("BB");
		d.addLast("CC");
		d.addFirst("DD");
		d.addLast("EE");
		//d.showItems();
		for (int i = 0; i < 5; i++) {
			System.out.println(d.iterator().next());
		}
	}
}
