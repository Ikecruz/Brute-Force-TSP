# Travelling Salesman - Brute Force Approach

## Introduction

This report documents the implementation and testing of a Java program designed to solve the Travelling Salesman Problem (TSP). The TSP is a classical combinatorial optimization problem where the objective is to find the shortest possible path that visits each city exactly once and returns to the starting city.

The solution employs a brute-force algorithm to generate all possible permutations of cities and calculate the total distance for each permutation.

## Implementation Details

**Programming Language**: Java

**Algorithm Used**: Brute-Force

**File Input Format**: Eachline represents a city with the city number, X-coordinate,and Y-coordinate separated by spaces or tabs.


## Algorithm Used - Brute Force

The brute-force algorithm used involves exhaustively generating all possible permutations of cities, where each permutation signifies a distinct order of city visitation. For each permutation, the total path distance is calculated by summing up the Euclidean distances between consecutive cities, determined by the formula $d=\sqrt{[(x2 - x1)^2 + (y2 - y1)^2]}$ . Throughout this process, the algorithm keeps track of the permutation resulting in the shortest total distance. The time complexity of the brute-force approach is $0(n!)$, where $n$ is the number of cities, as it explores every possible permutation.

## Test Cases

The program has been tested with the following test cases, each representing a different set of cities:

1. **Sample 1 (train1.txt):** A small set of 4 cities.
2. **Sample 2 (train2.txt):** Another small set of 8 cities.
3. **Sample 3 (train3.txt):** A meduim-size set of 22 cities.


## Test Results

### Test Case 1: Sample 1 (train1.txt)

**Execution Time:** 2341180 nanoseconds.

**Total Distance:** 24.

### Test Case 2: Sample 2 (train2.txt)

**Execution Time:** 26290347 nanoseconds.

**Total Distance:** 65.

### Test Case 3: Sample 3 (train3.txt)

**Execution Time:** 84389970 nanoseconds.

**Total Distance:** 229.