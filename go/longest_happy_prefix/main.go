package main

import (
	"fmt"
	"os"
)


func longestPrefix(s string) string {
	// what will be th length of the proper prefix which is also a suffix
    // tbl[i] = length of a proper prefix of string[0...i]
    // so_far = tbl[i-1]
    // 
    // if char[so_far] + 1 == str[i], 
    //    tbl[i] = tbl[so_far]
    //  else if so_far == 0
    //     tbl[i] = 0
    //  else 
    //   so_far = tbl[so_far]
    // 
    n := len(s)
    tbl := make([]int, n)
    // length of proper prefix of 0th character is 0
    tbl[0] = 0

    for i:=1; i<n; i++ {
		so_far := tbl[i-1]
		for {
            if s[so_far] == s[i] {
                tbl[i] = so_far+1
                break
            } else if so_far == 0 {
                tbl[i] = 0
                break
            } else {
                so_far = tbl[so_far - 1]
            }
        }
    }
    preLen := tbl[n - 1]
    if preLen == 0 {
        return ""
    }
    return s[:preLen]
}

func main() {
	input := os.Args[1]
	fmt.Println(longestPrefix(input))
}
