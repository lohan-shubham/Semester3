
class Queue {
    Node front, rear;

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    void enqueue(int data) {
        Node temp = new Node(data);

        if (rear == null) {
            rear = front = temp;
            return;
        }

        rear.next = temp;
        rear = temp;
    }

    int dequeue() {
        if (this.front == null)
            return -1;
        Node temp = front;
        front = front.next;
        if (front == null)
            rear = null;
        return temp.data;
    }

    public static void main(String[] args) {
        Queue q = new Queue();
        q.enqueue(5);
        q.dequeue();
        q.enqueue(2000);
        System.out.println(q.dequeue());
    }
} 
