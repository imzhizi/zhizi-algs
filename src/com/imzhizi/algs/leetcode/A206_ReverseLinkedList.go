package main

// Reverse a singly linked list.
// Example:
//	Input: 1->2->3->4->5->NULL
//	Output: 5->4->3->2->1->NULL

type ListNode struct {
	Val  int
	Next *ListNode
}

// 题目分析：
// 	没事学了 Go 语言，所以就拿来玩玩，感觉还不错
// 	题目也很简单，就是链表的翻转，用学过数据结构的都知道头插法遍历一遍就行了
// 运行时间：0ms / 100%（不知道是题目太简单，懒得给用例还是 没人用 Go 写，总之是 beat 100%）
func reverseList(head *ListNode) *ListNode {
	node := new(ListNode)
	for head != nil {
		temp := ListNode{head.Val, node.Next}
		node.Next = &temp
		head = head.Next
	}
	return node.Next
}

func main() {
	node := ListNode{1, &ListNode{2, &ListNode{3, nil}}}
	result := reverseList(&node)
	for result != nil {
		println(result.Val)
		result = result.Next
	}
}
