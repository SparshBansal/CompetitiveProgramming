package main

import (
	"bufio"
	"fmt"
	"os"
)


func minSwaps(s string) int {
	// find the max length balanced substring
	lenSubs := 0

	open := 0
	for _, v := range s {
		if v == '[' {
			open++
		} else if v == ']' {
			if open > 0 {
				open--
				lenSubs += 2
			}
		}
	}
	// now we figure out the remaining chars
	unbalanced := (len(s) - lenSubs)/2
	if unbalanced % 2 == 0 {
		return unbalanced / 2
	} else {
		return (unbalanced / 2) + 1
	}
	
}

func main() {
	f, err := os.Open("min_swaps_to_make_balanced/in.txt")
	if err != nil {
		fmt.Println(err.Error())
		f = os.Stdin
	}

	reader := bufio.NewReader(f)
	var input string
	fmt.Fscanln(reader, &input)
	fmt.Println(input)
	fmt.Println(minSwaps(input))
}