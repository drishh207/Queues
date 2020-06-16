package circular_queue;
import java.util.*;

public class queue {
    
    employee[] queue;
    int front, rear;
    
    queue(int capacity)
    {
        queue = new employee[capacity];
    }
    
    public int size()
    {
        if(front <= rear)
            return rear - front;
        else
            return rear - front + queue.length;
    }
    
    public void enqueue(employee emp)
    {
        //1st scenario      2nd scenario        3rd scenario        4th scenario
        //0 - jake -front   0 - null            0 - null    -back   0 - amy 
        //1 - amy           1 - amy     -front  1 - null            1 - null    -back
        //2 -      -back    2 - terry           2 - terry   -front  2 - terry   -front
        //3 -               3 - charles         3 - charles         3 - charles
        //4 -               4 -         -back   4 - mac             4 - mac
        if(size() == queue.length-1)
        {
            int numitems = size();
            employee[] newarray = new employee[2 * queue.length];
            
            if(front <= rear)
            {
                System.arraycopy(queue, 0, newarray, 0, queue.length);
                queue = newarray;
            }
            
            else
            {
                System.arraycopy(queue, front, newarray, 0, queue.length);
                System.arraycopy(queue, 0, newarray, queue.length-front, rear);
                queue = newarray;
            }
            
            front = 0;
            rear = numitems;
        }
        
        queue[rear] = emp;
        if(rear < queue.length-1)
            rear++;
        else
            rear = 0;
        System.out.println(size());
    }
    
    public employee dequeue()
    {
        employee emp;
        if(size() == 0)
            throw new NoSuchElementException();
        if(size() == 1)
        {
            emp = queue[front];
            front = rear = 0;            
        }
        
        else
        {
            emp = queue[front];
            front++;
        }
        System.out.println(size());
            return emp;
    }
    
    
    
    public void printlist()
    {
        if(front <= rear)
        {
            for(int i = front; i < rear; i++)
                System.out.println(queue[i]);
        }
        
        else{
            for(int i = front; i < queue.length; i++)
                System.out.println(queue[i]);
            for( int i = 0; i < rear; i++)
                System.out.println(queue[i]);
        }
    }
    
    public employee peek()
    {
        if(size() == 0)
            throw new NoSuchElementException();
        return queue[front];
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
        
        q.enqueue(emp4);   
        q.printlist();
        
        q.enqueue(emp5);
        q.printlist();
        
        q.dequeue();
        q.dequeue();
        q.printlist();
          
        q.dequeue();
        q.printlist();
                 
        System.out.println("Front element: "+q.peek());
    }
}
