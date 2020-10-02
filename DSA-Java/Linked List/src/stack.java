import java.util.Scanner;


public class stack {
    static Node ptr;

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }

    }

    public static void push(int data) {
        Node temp = new Node(data);
        if (ptr == null) {
            ptr = temp;
            return;
        }
        temp.next = ptr;
        ptr = temp;

    }

    public static int pop() {
        int x = ptr.data;
        ptr = ptr.next;

        return x;
    }

    public static void print() {
        Node temp = ptr;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public static void reverse() {
        Node prev, cur, succ;
        cur = prev = ptr;
        cur = cur.next;
        prev.next = null;
        while (cur != null) {
            succ = cur.next;
            cur.next = prev;
            prev = cur;
            cur = succ;
        }
        ptr = prev;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            push(input.nextInt());
        }
        print();
        System.out.println();
        reverse();
        print();
    }
}
