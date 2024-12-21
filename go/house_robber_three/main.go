package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
)

type TreeNode struct {
	Val int
	Left *TreeNode
	Right *TreeNode
}

var dp map[*TreeNode]int

func getSkipChildren(root *TreeNode) []*TreeNode {
	children := make([]*TreeNode, 0)
	if root.Left != nil {
		children = append(children, root.Left.Left)
		children = append(children, root.Right.Right)
	}
	if root.Right != nil {
		children = append(children, root.Right.Left)
		children = append(children, root.Right.Right)
	}
	return children
}

func maxSteal(root *TreeNode) (int) {
	if val, ok := dp[root]; ok {
		return val
	}
	// else we need to compute this dp
	if root == nil {
		return 0
	}
	coinsWithoutRoot := maxSteal(root.Left) + maxSteal(root.Right)
	coinsWithRoot := root.Val
	skipChildren := getSkipChildren(root)
	for _, child := range skipChildren {
		coinsWithRoot += maxSteal(child)
	}
	return int(math.Max(float64(coinsWithRoot), float64(coinsWithoutRoot)))
}

func rob(root *TreeNode) (int) {
	// we can store the max we can steal if we start from a particular node
	dp = make(map[*TreeNode]int)
	return maxSteal(root)
}

func main() {
	f, err := os.Open("house_robber_three/in.txt")
	if err != nil {
		fmt.Println(err.Error())
		f = os.Stdin
	}
	_ = bufio.NewReader(f)
}