import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LinkedList {

    Node head;
    int size = 0;
    static class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    void insert(String s) {
        if (head == null) {
            head = new Node(s);
        } else {
            Node latest = getLastNode();
            latest.next = new Node(s);
        }
        size++;
    }
    void delete(String s) {
        Node dummy = head;
        if (dummy.data.equals(s)) head = head.next;
        else {
            while(dummy.next != null) {
                if (dummy.next.data.equals(s)) {
                    dummy.next = dummy.next.next;
                    break;
                }
                dummy = dummy.next;
            }
        }
        size--;
    }
    boolean contains(String s) {
        Node dummy = head;
        while (dummy != null) {
            if(dummy.data.equals(s)) return true;
            dummy = dummy.next;
        }
        return false;
    }
    String getFromBeginning(int n) {
        Node dummy = head;
        int idx = 1;
        while (dummy != null) {
            if(idx == n) return dummy.data;
            dummy = dummy.next;
            idx++;
        }
        return "null";
    }
    String getFromEnd(int n) {
        Node slow = head, fast = head;
        int count = 0;
        while (fast != null) {
            if (count >= n) {
                slow = slow.next;
            }
            fast = fast.next;
            count++;
        }

        return (slow != null) ? slow.data : "null";
    }
    String middle() {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return (slow != null) ? slow.data : "null";
    }
    int countOccurences(String s) {
        Node dummy = head;
        int count = 0;
        while (dummy != null) {
            if (dummy.data.equals(s)){
                count++;
            }
            dummy = dummy.next;
        }
        return count;
    }

    void parse(String s) {
        String[] commands = s.split(" ");
        switch (commands[0]) {
            case "insert":
                insert(commands[1]);
                break;
            case "delete":
                delete(commands[1]);
                break;
            case "print":
                printLinkedList();
                break;
            case "size":
                printSize();
                break;
            case "contains":
                System.out.println(contains(commands[1]));
                break;
            case "getFromBeginning":
                System.out.println(getFromBeginning(Integer.parseInt(commands[1])));
                break;
            case "getFromEnd":
                System.out.println(getFromEnd(Integer.parseInt(commands[1])));
                break;
            case "middle":
                System.out.println(middle());
            case "CountTimes":
                String secondArg = commands[commands.length - 1];
                int numTimes = countOccurences(secondArg);
                System.out.println(secondArg + " occurs " + numTimes + " times");
                break;


        }
    }
    Node getLastNode() {
        Node dummy = head;
        while (dummy != null && dummy.next != null) {
            dummy = dummy.next;
        }
        return dummy;
    }
    void printLinkedList() {
        Node dummy = head;
        StringBuilder sb = new StringBuilder();
        while (dummy != null) {
            sb.append(dummy.data);
            sb.append("->");
            dummy = dummy.next;
        }

        System.out.println(sb.toString().substring(0, sb.toString().length() - 2));
    }
    void printSize() {
        System.out.println(size);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./resources/ll_test.txt"));
        LinkedList ll = new LinkedList();
        while (sc.hasNextLine()) {
            ll.parse(sc.nextLine());
        }
    }



}
