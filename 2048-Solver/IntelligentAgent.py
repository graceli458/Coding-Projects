# The IntelligentAgent class should inherit from BaseAI. 
# The getMove() function to implement must return a number 
# that indicates the player’s action. In particular, 0 stands for ”Up”, 1 stands for ”Down”, 2 stands for ”Left”, and 3 stands for ”Right”. 
# This is where your player-optimizing logic lives and is executed. 

# Grace Li, gl2676 code was written for a class to play 2048. all the code was written by me 

import random
import math
import time
from BaseAI import BaseAI

#hueristics to implement: 
#granting "bonuses" for open squares and for having large values on the edge. 
#https://stackoverflow.com/questions/22342854/what-is-the-optimal-algorithm-for-the-game-2048/23853848#23853848 

def h1_empty_squares(grid):
    # print("h1 grid: ", grid)
    board = grid.map
    count = 0
    for row in board:
        for element in row:
            if element == 0:
                count +=1
    return count

def h2_sum_of_board(grid):
    board = grid.map
    sum = 0
    for row in board:
        for element in row:
            sum += element
    return sum

def h3_max_tile(grid):
    return grid.getMaxTile()

def absolute_value(grid):
    #difference between two connecting tiles
    board = grid.map
    abs_sum = 0

    #calculating the horizontal adjacencies
    for row in range(3):
        for cell in range(3):
            # print("horizontal: ", row, cell, row, cell+1)
            abs_sum += abs(board[row][cell]-board[row][cell+1])
            abs_sum += abs(board[row][cell]-board[row+1][cell])
            # print("vertical: ", cell, row, cell+1, row)
            # abs_sum += abs(board[cell][row]-board[cell+1][row])
        abs_sum += abs(board[row][3]-board[row+1][3])

    return abs_sum

def max_in_corner(grid):
    board = grid.map
    max_tile =grid.getMaxTile()

    corners = [(0,1), (0,3), (3,0), (3,3)]

    for corner in corners:
        if max_tile == grid.getCellValue(corner):
            return 500
    
    return 0

def snake(grid):
    board = grid.map
    snake = [
        [2**15, 2**14, 2**13, 2**12],
        [2**8, 2**9, 2**10, 2**11],
        [2**7, 2**6, 2**5, 2**4],
        [2**0, 2**1, 2**2, 2**3]
    ]
    running_sum = 0

    for row in range(4):
        for item in range(4):
            running_sum += board[row][item] + snake[row][item]

    return running_sum



def get_utility(grid):

    weights = [50, 2, 3, -1, .0001]
    # print("snake(grid): ", snake(grid))
    sum = weights[0]*h1_empty_squares(grid) + weights[1]*h2_sum_of_board(grid) + weights[2]*h3_max_tile(grid) +weights[3]*absolute_value(grid) +max_in_corner(grid)+weights[4]*snake(grid)
    
    return sum

#grant reward for when large tiles are on the edge
# def position_of_large_tiles(grid):
#     board = grid.map
#     top_3_tiles = []

    #get top 3 tiles + corresponding position


# expectiminimax
# use insert tile
# case 1: insert 2 
#   prob = 90%
# case 2: insert 4
#   prob = 10%
# need to get available

# minimize of MINIMAX -> change to expected minimax; empty cell in board simulate inserting a 2 or 4 and calculate utility for each insertion; total utility of a board
# 90% chance of inserting a 2; 
#need to keep track of the tile (2, 4) that you inserted
def minimize(state, alpha, beta, max_depth, current_depth, move, tile_inserted, start_time):
    if current_depth == max_depth:
        return (state, get_utility(state), move)
    
    if time.process_time() - start_time > 0.19:
        return (state, get_utility(state), move)

    #set minimum child + utility
    min_child = None
    min_utility = float("inf")
    max_move = move

    #iterate through all possible cells that you can insert 2/4
    for child in state.getAvailableCells():
        cloned_state = state.clone()
        cloned_state.insertTile(child, tile_inserted)
        
        output = maximize(cloned_state, alpha, beta, max_depth, current_depth+1, child[0], time.process_time())
        min_child = output[0]
        utility = output[1]
        move = child[0]

        if utility < min_utility:
            min_child = child
            min_utility = utility
            max_move = move

        if min_utility <= alpha:
            break

        if min_utility < beta:
            beta = min_utility
        
    return (min_child, min_utility, max_move)

#call minimize twice: once for when tile = 2 and when tile = 4
#get the probability for each of the ^^ two cases

# maximize step of MINIMAX
# set the max depth, return eval function 
def maximize(state, alpha, beta, max_depth, current_depth, move, start_time):
    if current_depth == max_depth:
        return (state, get_utility(state), move)
    if time.process_time() - start_time > 0.19:
        return (state, get_utility(state), move)

    #set minimum child + utility
    max_child = None
    max_utility = float("-inf")
    max_move = move

    #iterate through all possible moves from current state
    for child in state.getAvailableMoves():
        #inside the for loop
        # move consisters of (move, grid)
        output_2 = minimize(child[1], alpha, beta, max_depth, current_depth+1, child[0], 2, time.process_time())
        output_4 = minimize(child[1], alpha, beta, max_depth, current_depth+1, child[0], 4, time.process_time())
        
        max_child = output_2[0]
        utility = output_4[1]*0.1 + output_2[1]*0.9
        move = child[0]

        if utility > max_utility:
            max_child = child
            max_utility = utility
            max_move = move

        if max_utility >= beta:
            break

        if max_utility > alpha:
            alpha = max_utility
        
    return (max_child, max_utility, max_move)

def decision(state, max_depth):
    current_depth = 0
    output = maximize(state, float("-inf"), float("inf"), max_depth, current_depth, 0, time.process_time())
    return output
    


            

class IntelligentAgent(BaseAI):
     
     # implement expectminimax algorithm -> START w/ minimax -> change to expectminimax

     # create a hueristic to evaluate how good the board is: 
     # 1. higher the tile the better
     # 2. tiles of the same number close to each other is good
     # 3. reward when combining things 
     # 4. number of empty square 

     

     # implement alpha beta pruning

     # use heuristic functions

     # use heuristic weights



     def getMove(self, grid):
       # Selects a random move and returns it

        max_depth = 4
        for i in range(max_depth):
            best = decision(grid, i)
        return best[2]
   

