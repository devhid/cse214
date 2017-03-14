package hw4;

public class StudentQueue {
    private Node front, rear;
    private int size;

    public StudentQueue() {
        this.front = null;
        this.rear = null;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(final Student student) {
        Node node = new Node(student);

        if(isEmpty()) {
            front = node;
            rear = node;
        } else {
            Node current = front;
            while(current.getNext() != null) {
                if(current.getData().getCourse().getCourseNumber() < student.getCourse().getCourseNumber()) {
                    node.setPrevious(current.getPrevious());
                    node.setNext(current);

                    current.getPrevious().setNext(node);
                    current.setPrevious(node);
                    break;
                }

                current = current.getNext();
            }
        }

        size++;
    }

    public Student dequeue() throws EmptyQueueException {
        if(isEmpty()) {
            throw new EmptyQueueException("The queue is empty.");
        }

        Student student = this.peek();

        if(front.getNext() != null) {
            front = front.getNext();
            front.setPrevious(null);
        } else {
            front = null;
        }

        size--;
        return student;
    }

    public Student peek() throws EmptyQueueException {
        if(isEmpty()) {
            throw new EmptyQueueException("The queue is empty.");
        }

        return front.getData();
    }
}
