package main

const MOD = 1000000007

func findGoodStrings(n int, s1 string, s2 string, evil string) int {
	// create a dp table

	dp := make([][]int, n)
	nEvil := len(evil)

	for i, _ := range dp {
		dp[i] = make([]int, nEvil)
	}

    
}

func main() {

}