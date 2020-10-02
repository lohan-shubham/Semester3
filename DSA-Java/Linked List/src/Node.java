import java.util.Scanner;


class Node {
    int data;
    Node next;
    Node head;

    public void print() {
        Node n = head;
        while (n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
        System.out.println();
    }

    public void printNode(int pos) {
        Node n = head;
        if (pos <= 0)
            return;
        for (int i = 0; n != null && i < pos - 1; i++) {
            n = n.next;

        }
        if (n == null)
            System.out.println("Not Found!");
        else
            System.out.println("Data at Node " + pos + " is " + n.data);
    }

    public void push(int data) {
        Node new_node = new Node();
        new_node.data = data;
        new_node.next = head;
        head = new_node;
    }

    public void insert(Node prev_node, int data) {
        if (prev_node == null) {
            System.out.println("Previous Node cant be Null ");
            return;
        }
        Node new_node = new Node();
        new_node.data = data;
        new_node.next = prev_node.next;
        prev_node.next = new_node;

    }

    public void insert(int data) {
        Node new_node = new Node();
        new_node.data = data;
        if (head == null)
            head = new_node;
        else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new_node;

        }
    }

    public void append(int data) {
        Node new_node = new Node();
        if (head == null) {
            head = new Node();
            new_node.data = data;
        }
        new_node.next = null;
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = new_node;

    }

    public void delete(int key) {
        Node temp = head;
        Node prev = null;
        if (temp != null && temp.data == key) {
            head = temp.next;
            return;
        }

        while (temp != null && temp.data != key) {
            prev = temp;
            temp = temp.next;
        }
        if (temp == null)
            System.out.println("Deletion Failed");

        else
            prev.next = temp.next;
    }

    public void deleteNode(int pos) {
        if (head == null)
            return;
        Node temp = head;
        if (pos == 0) {
            head = temp.next;
            return;
        }
        if (pos < 0)
            return;
        for (int i = 0; temp != null && i < pos - 1; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) {
            return;
        }
        Node n = temp.next.next;
        temp.next = n;
    }

    public void deleteoccur(int key) {
        Node n = head;

        while (n.next != null) {
            if (n.next.data == key)
                n.next = n.next.next;
            else
                n = n.next;
        }

    }

    public void deleteLinkedList() {
        head = null;
    }

    public int countIt() {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }

    public int countRec(Node temp) {
        if (temp == null)
            return 0;
        return 1 + countRec(temp.next);

    }

    public int countRec() {
        return countRec(head);
    }

    public int search(int data) {
        int pos = 0;
        Node temp = head;
        while (temp.data != data && temp.next != null) {
            temp = temp.next;
            pos++;
        }
        if (temp.data == data)
            return pos + 1;
        return -1;
    }

    public int searchRec(int data) {
        int x = searchRec(head, data, 0);
        return x < 0 ? -1 : 1 + x;
    }

    public int searchRec(Node n, int d, int c) {
        if (n.next == null)
            return -1;
        if (n.data == d)
            return c;
        return searchRec(n.next, d, ++c);


    }

    public void findMid() {
        int mid = 0;
        Node n1 = head;
        Node n2 = head;
        while (n2 != null && n2.next != null) {
            n2 = n2.next.next;

            n1 = n1.next;
            mid++;
        }
        System.out.println("Mid " + (mid + 1));
    }

    public void EndNode(int n) {
        int x = countIt();

        if (x < n)
            return;
        else {
            Node temp = head;
            int i = 0;
            while (temp.next != null && i < (x - n)) {
                temp = temp.next;
                i++;
            }
            System.out.println(temp.data);
        }
    }

    public int EndNodeRec(int n) {
        int x = countIt();

        if (n <= x && n > 0) {
            Node n1 = head;
            Node n2 = head;
            for (int i = 0; n2 != null && i < n; i++) {
                n2 = n2.next;
            }
            return EndNodeRec(n1, n2);
        }
        return -1;
    }

    public int EndNodeRec(Node n1, Node n2) {
        if (n2 == null)
            return n1.data;
        return EndNodeRec(n1.next, n2.next);
    }

    public void sort() {
        Node n = head;
        int x = n.data;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Node temp = new Node();
        while (n > 0) {
            temp.insert(input.nextInt());
            ;
            n--;
        }
        temp.print();
//		temp.push(0);
//		temp.print();
//
//		temp.delete(30);
//		temp.deleteNode(0);
//		temp.print();
//		temp.deleteLinkedList();
//		temp.printNode(1);
//		temp.deleteoccur(2);
//		temp.print();
//
//		System.out.println("\n"+temp.countIt());
//		System.out.println(temp.countRec());
//		System.out.println("\n"+temp.search(2));
//		System.out.println(temp.search(1));
//		System.out.println(temp.searchRec(3));
//		temp.findMid();
//		temp.EndNode(2);
//		System.out.println(temp.EndNodeRec(input.nextInt()));
    }

}