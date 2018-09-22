package main

//Given an array of integers, find if the array contains any duplicates.
//Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
//
//Example 1:
//	Input: [1,2,3,1]
//	Output: true
//
//Example 2:
//	Input: [1,2,3,4]
//	Output: false
// created by zhizi on 9/11/18

func main() {
	nums := []int{1, 2, 3, 1}
	println(containsDuplicate(nums))
}

/* 28ms / %85 */
func containsDuplicate(nums []int) bool {
	m := map[int]bool{}

	for i := 0; i < len(nums); i++ {
		if !m[nums[i]] {
			m[nums[i]] = true
		} else {
			return true
		}
	}
	return false
}
