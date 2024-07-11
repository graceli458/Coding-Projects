from __future__ import division
from __future__ import print_function

import sys
import math
import time
import numpy as np
import queue as Q
import resource


#### SKELETON CODE ####
## The Class that Represents the Puzzle
class PuzzleState(object):
    """
        The PuzzleState stores a board configuration and implements
        movement instructions to generate valid children.
    """
    def __lt__(self, state):
        self_cost = 0
        if self.action == "Up":
            self_cost = 4
        if self.action == "Down":
            self_cost = 3
        if self.action == "Left":
            self_cost = 2
        if self.action == "Right":
            self_cost = 1
        
        state_cost = 0
        if state.action == "Up":
            state_cost = 4
        if state.action == "Down":
            state_cost = 3
        if state.action == "Left":
            state_cost = 2
        if state.action == "Right":
            state_cost = 1
        
        return self_cost < state_cost
    
    def __init__(self, config, n, parent=None, action="Initial", cost=0):
        """
        :param config->List : Represents the n*n board, for e.g. [0,1,2,3,4,5,6,7,8] represents the goal state.
        :param n->int : Size of the board
        :param parent->PuzzleState
        :param action->string
        :param cost->int
        """
        if n*n != len(config) or n < 2:
            raise Exception("The length of config is not correct!")
        if set(config) != set(range(n*n)):
            raise Exception("Config contains invalid/duplicate entries : ", config)

        self.n        = n
        self.cost     = cost
        self.parent   = parent
        self.action   = action
        self.config   = config
        self.children = []

        # Get the index and (row, col) of empty block
        self.blank_index = self.config.index(0)

    def display(self):
        """ Display this Puzzle state as a n*n board """
        for i in range(self.n):
            print(self.config[3*i : 3*(i+1)])

    def move_up(self):
        """ 
        Moves the blank tile one row up.
        :return a PuzzleState with the new configuration
        """

        #get the index of the 0
        
        #check that a move_up is valid
        if self.blank_index < self.n:
            return None
        else:
            #make a copy of the puzzle
            new_puzzle_list = list(self.config)

            #swapping the elements
            swap_index = self.blank_index - self.n
            element_swap = self.config[swap_index]

            new_puzzle_list[self.blank_index] = element_swap
            new_puzzle_list[swap_index] = 0

            #increase the cost
            new_cost = self.cost + 1

            #create a new PuzzleState
            return PuzzleState(config=new_puzzle_list, n=self.n, parent=self, action="Up", cost=new_cost)

      
    def move_down(self):
        """
        Moves the blank tile one row down.
        :return a PuzzleState with the new configuration
        """
        

        #check that a move_down is valid
        if self.blank_index >= len(self.config)-self.n:
            return None
        else:
            #make a copy of the puzzle
            new_puzzle_list = list(self.config)

            #swapping the elements
            swap_index = self.blank_index + self.n
            element_swap = self.config[swap_index]

            new_puzzle_list[self.blank_index] = element_swap
            new_puzzle_list[swap_index] = 0

            #increase the cost
            new_cost = self.cost + 1

            #create a new PuzzleState
            return PuzzleState(config=new_puzzle_list, n=self.n, parent=self, action="Down", cost=new_cost)
      
    def move_left(self):
        """
        Moves the blank tile one column to the left.
        :return a PuzzleState with the new configuration
        """

        if self.blank_index%self.n == 0:
            return None

        else:
            #make a copy of the puzzle list
            new_puzzle_list = list(self.config)
            swap_index = self.blank_index - 1
            element_swap = self.config[swap_index]

            new_puzzle_list[self.blank_index] = element_swap
            new_puzzle_list[swap_index] = 0

            #increase the cost
            new_cost = self.cost + 1

            #create a new PuzzleState
            return PuzzleState(config=new_puzzle_list, n=self.n, parent=self, action="Left", cost=new_cost)

    def move_right(self):
        """
        Moves the blank tile one column to the right.
        :return a PuzzleState with the new configuration
        """

        if (self.blank_index+1)%self.n == 0:
            return None

        else:
            #make a copy of the puzzle list
            new_puzzle_list = list(self.config)
            swap_index = self.blank_index + 1
            element_swap = self.config[swap_index]

            new_puzzle_list[self.blank_index] = element_swap
            new_puzzle_list[swap_index] = 0

            #increase the cost
            new_cost = self.cost + 1

            #create a new PuzzleState
            return PuzzleState(config=new_puzzle_list, n=self.n, parent=self, action="Right", cost=new_cost)

      
    def expand(self):
        """ Generate the child nodes of this node """
        
        # Node has already been expanded
        if len(self.children) != 0:
            return self.children
        
        # Add child nodes in order of UDLR
        children = [
            self.move_up(),
            self.move_down(),
            self.move_left(),
            self.move_right()]

        # Compose self.children of all non-None children states
        self.children = [state for state in children if state is not None]
        return self.children


# Function that Writes to output.txt
def get_path_to_goal(state):
    path = []
    
    while state.action != "Initial":
        path.insert(0,state.action)
        state = state.parent
    
    return path

### Students need to change the method to have the corresponding parameters
def writeOutput(state, start_time, end_time, nodesExpanded, dfs_ram, max_depth):
    ### Student Code Goes here
    file = open("output.txt", "w")
    path = get_path_to_goal(state)


    contents = f"""path_to_goal: {path}
cost_of_path: {state.cost}
nodes_expanded: {nodesExpanded-1}
search_depth: {state.cost}
max_search_depth: {max_depth}
running_time: {end_time - start_time}
max_ram_usage: {dfs_ram}"""
    
    file.write(contents)
    file.close()
    return 
    

def bfs_search(initial_state):
    """BFS search"""
    ### STUDENT CODE GOES HERE ###
    #use an explicit queue
    start_time  = time.time()
    frontier = Q.Queue()
    frontier.put(initial_state)
    explored = set()
    nodesExpanded = 0
    max_depth = 0

    # calculating the RAM usage
    dfs_start_ram = resource.getrusage(resource.RUSAGE_SELF).ru_maxrss

    while frontier:
        nodesExpanded = nodesExpanded + 1
        state = frontier.get()
        explored.add(tuple(state.config))

        if test_goal(state):
            end_time = time.time()
            dfs_ram = (resource.getrusage(resource.RUSAGE_SELF).ru_maxrss - dfs_start_ram)/(2**20)
            writeOutput(state, start_time, end_time, nodesExpanded, dfs_ram, max_depth)
            return True

        
        for child in state.expand():
            if tuple(child.config) not in explored:
                explored.add(tuple(child.config))
                frontier.put(child)
                
                if child.cost > max_depth:
                    max_depth = child.cost
    

def dfs_search(initial_state):
    ### STUDENT CODE GOES HERE ###
    """DFS search"""
    start_time  = time.time()
    stack = []
    stack.append(initial_state)
    explored = set()
    nodesExpanded = 0
    max_depth = 0

    # calculating the RAM usage
    dfs_start_ram = resource.getrusage(resource.RUSAGE_SELF).ru_maxrss

    while stack:
        state = stack.pop()
        nodesExpanded = nodesExpanded + 1
        explored.add(tuple(state.config))

        if test_goal(state):
            end_time = time.time()
            dfs_ram = (resource.getrusage(resource.RUSAGE_SELF).ru_maxrss - dfs_start_ram)/(2**20)
            writeOutput(state, start_time, end_time, nodesExpanded, dfs_ram, max_depth)
            return True
        
        
        children = state.expand()
        for child in list(reversed(children)):
            if tuple(child.config) not in explored:
                stack.append(child)
                explored.add(tuple(child.config))

                if child.cost > max_depth:
                        max_depth = child.cost





def A_star_search(initial_state):
    """A * search"""
    print("A*")
    ### STUDENT CODE GOES HERE ###
    priortyqueue = Q.PriorityQueue()
    start_time  = time.time()
    explored = set()
    # frontier = set()
    nodesExpanded = 0
    max_depth = 0
    initial_state_cost = calculate_total_cost(initial_state)
    # (cost, PUZZLE)
    priortyqueue.put((calculate_total_cost(initial_state), initial_state))
    # frontier.add(tuple(initial_state.config))

    # calculating the RAM usage
    dfs_start_ram = resource.getrusage(resource.RUSAGE_SELF).ru_maxrss
    
    
    while priortyqueue:
        state = priortyqueue.get()
        nodesExpanded = nodesExpanded + 1
        explored.add(tuple(state[1].config))

        if test_goal(state[1]):
            end_time = time.time()
            dfs_ram = (resource.getrusage(resource.RUSAGE_SELF).ru_maxrss - dfs_start_ram)/(2**20)
            writeOutput(state[1], start_time, end_time, nodesExpanded, dfs_ram, max_depth)
            return True
        
        
        children = state[1].expand()
        for child in list(reversed(children)):
            if tuple(child.config) not in explored:
                priortyqueue.put((calculate_total_cost(child), child))
                explored.add(tuple(child.config))

                if child.cost > max_depth:
                        max_depth = child.cost
 
            

def calculate_total_cost(state):
    """calculate the total estimated cost of a state"""
    ### STUDENT CODE GOES HERE ###
    puzzle = state.config
    distance = 0
    for tile in puzzle:
        if tile != 0:
            idx = puzzle.index(tile)
            value = tile
            distance += calculate_manhattan_dist(idx, value, state.n)
    
    return distance + state.cost

def calculate_manhattan_dist(idx, value, n):
    """calculate the manhattan distance of a tile"""
    ### STUDENT CODE GOES HERE ###

    curr_coordinates = (int(idx/n), idx%3) 
    goal_coordinates = (int(value/n), value%3) 

    distance_x = abs(curr_coordinates[1]-goal_coordinates[1])
    distance_y = abs(curr_coordinates[0]-goal_coordinates[0])

    return distance_x + distance_y


def test_goal(puzzle_state):
    """test the state is the goal state or not"""
    ### STUDENT CODE GOES HERE ###
    if puzzle_state.config == [0,1,2,3,4,5,6,7,8]:
        return True
    else:
        return False

# Main Function that reads in Input and Runs corresponding Algorithm
def main():
    search_mode = sys.argv[1].lower()
    begin_state = sys.argv[2].split(",")
    begin_state = list(map(int, begin_state))
    board_size  = int(math.sqrt(len(begin_state)))
    hard_state  = PuzzleState(begin_state, board_size)
    start_time  = time.time()
    
    if   search_mode == "bfs": bfs_search(hard_state)
    elif search_mode == "dfs": dfs_search(hard_state)
    elif search_mode == "ast": A_star_search(hard_state)
    else: 
        print("Enter valid command arguments !")
        
    end_time = time.time()
    print("Program completed in %.3f second(s)"%(end_time-start_time))

if __name__ == '__main__':
    main()
