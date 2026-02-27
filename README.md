Starting repository for `Data Structures` COMP20280 2025-2026

Wk2:

Q5.
All the tests run, however the test coverage is poor. The tests do not cover all edge cases and  branches. They also fail to cover sortedMerge, reverse and clone methods (although they are only written later in the assignment).

Q6.
Circularly linked lists loop around themselves. So, the last element (tail) of a circularly linked list has the first element of the list (head) as its next element. In a singly linked list, however, the last element has its next element set to NULL.

Q7.
It can be easier to use linked lists as opposed to arrays when implementing queues.
Linked lists can be more space efficient as well, especially when the needed array/list size is not known or when the size of the array/list changes a lot through the course of the program.
If there is a need to insert/delete elements into/from the middle of the array, it could be better to use a linked list, as it is easier to insert/delete elements, when they are in the middle, in linked lists. (You would have to shift all subsequent elements in an array each time you add/remove an element).

Q8.
1. Circularly linked lists can be used as queues. To move the queue forward, you only need to rotate the list. 
2. Circularly linked lists are also useful when managing time-shared processes. They can be used to switch from one process to the next indefinitely, without the need to worry about reaching the end of the array and restarting it.

Wk3:

Q2.
```
void enqueue(E e){
    stack1.addLast(e);
    size++;
}

E dequeue(){
    for(int i=0; i<size-1; ++i){
        stack2.addLast(stack1.removeLast());
    }
    E removed = stack1.removeLast();
    --size;
    for(int i=0; i<size; ++i){
        stack1.addLast(stack2.removeLast());
    }
    return removed;
}
```
Q3.
```
void reverse(){
    for(int i=0; i<size; ++i){
        stack2.addLast(stack1.removeLast());
    }
    // stack2 is reversed stack1

    for(int i=0; isize; ++i){
        stack3.addLast(stack2.removeLast());
    }
    // stack3 is as stack1 was (reversed stack2)

   for(int i=0; i<size; ++i){
        stack1.addLast(stack3.removeLast());
    }
    // stack1 is reversed
}

```

Wk4:

Q2.
Q3.
Q4.
Q5.

Wk5:

Q5.
```
global diameter; // the result will be stored in this variable

int getDiameter(node){
    if(node == null){
        return 0
    }
    height left = getDiameter(node.left)
    height right = getDiameter(node.right)

    diameter = max(diameter, height left + height right)

    return max(height left, height right) + 1;
}
```

Wk6

Q5.
(a) Prints the given decimal number in binary.
(b) 10010001101000

Q6.

(a)
```
void reverse(list){
    if(last){
        end
    }
    reverse(list.next);
    print(list.first);
}
```

Q7.

(a)
```
LinkedList recursiveCopy(LinkedList original, Node next){
    if(original is empty){
        root = next;
        return root;
    }
    recursiveCopy(original.next, new Node(original.first.element, next))
}
```