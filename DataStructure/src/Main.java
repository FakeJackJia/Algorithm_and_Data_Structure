import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] arg){
//        int[] arr = new int[10];
//
//        for(int i = 0; i < arr.length; i++){
//            arr[i] = i;
//        }
//
//        int[] scores = new int[]{100, 99, 66};
//        for(int i = 0; i < scores.length; i++){
//            System.out.println(scores[i]);
//        }
//
//        for(int score:scores){
//            System.out.println(score);
//        }

        Array<Integer> array = new Array<>(11);
        for (int i = 0; i < 10; i++){
            array.addLast(i);
        }

        System.out.println(array);

        array.insert(1,100);
        System.out.println(array);

        array.remove(2);
        System.out.println(array);

        array.removeElement(4);
        System.out.println(array);

        array.removeFirst();
        System.out.println(array);

        array.addFirst(2);
        array.addFirst(3);
        array.addFirst(4);
        array.addFirst(5);
        System.out.println(array);

        array.removeFirst();
        array.removeFirst();
        System.out.println(array);

//        Array<Student> student = new Array<>();
//        student.addLast(new Student("Alice", 100));
//        student.addLast(new Student("Charles",80));
//        student.addLast(new Student("Bobo", 90));
//        System.out.println(student);


        ArrayStack<Integer> stack = new ArrayStack<>();

        for(int i = 0; i < 5; i++){
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);

        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for(int i = 0; i < 5; i++){
            queue.enqueue(i);
            System.out.println(queue);
        }

        queue.dequeue();
        System.out.println(queue);

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        for(int i = 0; i < 10; i++){
            loopQueue.enqueue(i);
            System.out.println(loopQueue);
        }

        System.out.println(loopQueue.getCapacity());
        loopQueue.enqueue(12);
        System.out.println(loopQueue.getCapacity());

        loopQueue.dequeue();
        System.out.println(loopQueue);

        Deque<Integer> t = new Deque<>();
        for(int i = 0; i < 10; i++){
            t.addLast(i);
            System.out.println(t);
            System.out.println(t.getCapacity());
        }

        System.out.println(t.getSize());
        t.addFirst(12);
        System.out.println(t.getSize());
        System.out.println(t);
        t.removeLast();
        t.removeFirst();
        t.addFirst(3);
        t.addLast(4);
        System.out.println(t);
        System.out.println(t.getLast());


        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2,666);
        System.out.println(linkedList);

        linkedList.remove(1);
        linkedList.removeLast();
        System.out.println(linkedList);
        System.out.println(linkedList.get(3));

        LinkedListStack<Integer> listStack = new LinkedListStack<>();

        for(int i = 0; i < 5; i++){
            listStack.push(i);
            System.out.println(listStack);
        }

        listStack.pop();
        System.out.println(listStack);

        LinkedListQueue<Integer> listQueue = new LinkedListQueue<>();

        for(int i = 0; i < 5; i++){
            listQueue.enqueue(i);
            System.out.println(listQueue);
        }

        listQueue.dequeue();
        System.out.println(listQueue);

        LinkedListRecursion<Integer> test = new LinkedListRecursion<>();
        for(int i = 0; i < 5; i++){
            test.addLast(i);
            System.out.println(test);
        }

        System.out.println(test.get(2));
        System.out.println(test.remove(0));
        System.out.println(test);
        System.out.println(test.contains(0));
        test.set(0, 4);
        System.out.println(test);


        int a = 42;
        System.out.println(((Integer)a).hashCode());

        int b = -42;
        System.out.println(((Integer)b).hashCode());

        double c = 3.1415926;
        System.out.println(((Double)c).hashCode());

        Student student = new Student("JACK", 100);
        System.out.println(student.hashCode());

        HashSet<Student> set = new HashSet<>();
        set.add(student);

        HashMap<Student, Integer> scores = new HashMap<>();
        scores.put(student, 100);

    }
}