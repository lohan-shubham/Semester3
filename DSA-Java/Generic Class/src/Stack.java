
public class Stack<T> {

	Node ptr;

	class Node {
		T data;
		Node next;
		

		Node(T data) {
			this.data = data;
		}
	}

	void push(T data) {
		Node node=  new Node(data);
		if(ptr==null) {
			ptr=node;
			return;
		}
		node.next=ptr;
		ptr=node;
	}
	T pop() {
		T t=ptr.data;
		ptr=ptr.next;
		return t;
	}
	void print() {
		Node temp=ptr;
		while(temp!=null) {
			System.out.print(temp.data+" ");
			temp=temp.next;
		}
	}
}
