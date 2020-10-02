
class Queue<T> {
	Node front, rear;

	class Node {
		T data;
		Node next;

		Node(T data) {
			this.data = data;
		}
	}

	void enqueue(T data) {
		Node temp = new Node(data);

		if (rear == null) {
			rear = front = temp;
			return;
		}

		rear.next = temp;
		rear = temp;
	}

	T dequeue() {

		Node temp = front;
		front = front.next;
		if (front == null)
			rear = null;
		return temp.data;
	}

}

