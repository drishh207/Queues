package array_implementation;
import java.util.*;

public class queue 
{
    employee[] queue;
    int front, rear;
    
    queue(int capacity)
    {
        queue = new employee[capacity];
    }
    
    public void enqueue(employee emp)
    {
        //this condition has a problem that if many elements are removed, front is incremented and reaches near to rear
        //but many spaces are left before but if we add an element, it will resize the array
        //e.g. _ _ _ _ ->front 2 3 4 <-back
        //    these spaces will not be filled and the array will be resized
        if(rear == queue.length)        //resizing array if full
        {
            employee[] newarray = new employee[2* queue.length];
            System.arraycopy(queue, 0, newarray, 0, queue.length);
            queue = newarray;
        }
        
        queue[rear++] = emp;
    }
    
    public employee dequeue()
    {
        if(size() == 0)
            throw new NoSuchElementException();
        
        employee emp = queue[front];
        queue[front] = null;
        front++;
        return emp;
    }
    
    public int size()
    {
        return rear - front;
    }
    
    public employee peek()
    {
        if(size() == 0)
            throw new NoSuchElementException();
        return queue[front];
    }
    
    public void printlist()
    {
        for(int i = front; i < rear; i++)
        {
            System.out.println(queue[i]);
        }
    }
    
    public static void main(String args[])
    {
        queue q = new queue(3);
        employee emp1 = new employee("Jake",1);
        employee emp2 = new employee("Amy",2);
        employee emp3 = new employee("Terry",3);
        employee emp4 = new employee("Rosa",4);
        employee emp5 = new employee("Charles",5);
        
        q.enqueue(emp1);
        q.enqueue(emp2);
        q.enqueue(emp3);
        q.printlist();
        
        System.out.println("Removed element: "+q.dequeue());
        q.printlist();
        
        q.enqueue(emp4);   //resizing of array taking place even when spaces are available in the array
        q.printlist();
        
        System.out.println("Front element: "+q.peek());
    }
}
