Starting repository for `Data Structures` COMP20280 2025-2026

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
