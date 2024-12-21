package main

import (
	"fmt"
	"math"
)

func getFirstEmpty(grid [][]int) (int, int) {
	for i := 0; i < 3; i++ {
		for j := 0; j < 3; j++ {
			if grid[i][j] == 0 {
				return i, j
			}
		}
	}
	return -1, -1
}

func candidates(grid [][]int) [][]int {
	cands := make([][]int, 0)
	for i := 0; i < 3; i++ {
		for j := 0; j < 3; j++ {
			if grid[i][j] > 1 {
				cands = append(cands, []int{i, j})
			}
		}
	}
	return cands
}

func abs(a int) int {
	if a < 0 {
		return -a
	}
	return a
}

func distance(toX, toY, fromX, fromY int) int {
	return abs(fromX-toX) + abs(fromY-toY)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func moves(grid [][]int, totalSoFar int) int {
	filled := true
	for i := 0; i < 3; i++ {
		for j := 0; j < 3; j++ {
			if grid[i][j] == 0 {
				filled = false
			}
		}
	}
	if filled {
		return totalSoFar
	}
	emptyX, emptyY := getFirstEmpty(grid)
	fmt.Printf("working for emptyX: %d, emptyY : %d\n", emptyX, emptyY)
	// try to fill this with possible candidates
	cands := candidates(grid)

	// minMoves
	minMoves := math.MaxInt

	for _, candidate := range cands {
		// assume current candidate
		dist := distance(emptyX, emptyY, candidate[0], candidate[1])
		// reduce count of candidate
		grid[candidate[0]][candidate[1]]--
		minMoves = min(minMoves, moves(grid, totalSoFar+dist))
		grid[candidate[0]][candidate[1]]++
	}
	return minMoves
}

func minimumMoves(grid [][]int) int {
	return moves(grid, 0)
}

func main() {
	fmt.Println(fmt.Sprintf("moves required : %d", minimumMoves([][]int{
		{1, 1, 1},
		{0, 1, 2},
		{1, 1, 1},
	})))
}
