import java.util.Scanner;

public class CircularLinkedList{
	public static class Node{
		int data;
		Node next;
	}
	public void insert(Node head,int data) {
		
		Node temp=new Node();
		temp=head;
		temp.data=data;
		temp.next=null;
		
	}
	
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		CircularLinkedList C=new CircularLinkedList();
		Node head=new Node();
		head.data=1;
		head.next=null;
		int n=input.nextInt();
		for(int i=0;i<n;i++) {

			C.insert(head,input.nextInt());
}
		}
	
}