package main

/**
Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
created by zhizi on 9/18/18
*/
func main() {
	obj := Constructor()
	obj.Push(10)
	println(obj.Top())
	println(obj.Pop())
	println(obj.Empty())
}

type MyStack struct {
	queue [100]int
	top   int
}

/** Initialize your data structure here. */
func Constructor() MyStack {
	stack := MyStack{[100]int{}, -1}
	return stack
}

/** Push element x onto stack. */
func (this *MyStack) Push(x int) {
	this.top++
	this.queue[this.top] = x
}

/** Removes the element on top of the stack and returns that element. */
func (this *MyStack) Pop() int {
	this.top--
	return this.queue[this.top+1]
}

/** Get the top element. */
func (this *MyStack) Top() int {
	return this.queue[this.top]
}

/** Returns whether the stack is empty. */
func (this *MyStack) Empty() bool {
	return this.top == -1
}
