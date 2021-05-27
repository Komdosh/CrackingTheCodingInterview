package main

import (
	"./delete_middle_node"
	"./get_last_kth_item"
	"./intersection"
	"./is_palindrome"
	"./loop_detection"
	"./partiton"
	"./remove_dups"
	"./sum_lists"
)

func main() {
	delete_middle.DeleteMiddleNode()
	loop.LoopDetection()
	kth_item.GetLastKthItem()
	intersection.FindIntersection()
	palindrome.IsPalindrome()
	partiton.Partition()
	remove_dups.RemoveDups()
	sum_lists.SumLists()
}
