# NxM-Tile-Puzzle
Extended of the game tile puzzle in AI course with search algorithms :mag_right:

## General
This program implements a search engine that supports a number of search algorithms to solve the NxM Tile Puzzle game.
The implemented algorithms are:
BFS
DFID
A*
IDA*
DFBnB


The project's goal is to solve NxM tile puzzle using the above graph search algorithms.
The problem consists of a NxM sized matrix which contains numbers and two or one  empty places as follows:


| 3 | 7 | 1 | 2 |
| ------------- | ------------- | ------------- | ------------- |   
| 9  |  | 10 | 6 |
| 8  | 5 |   | 4 |

The final answer would be the moves up/down/left/right until we reach a sorted matrix in the follwoing way:

| 1 | 2 | 3 | 4 |
| ------------- | ------------- | ------------- | ------------- |
| 5  | 6 | 7 | 8 |
| 9  | 10 |   |  |


