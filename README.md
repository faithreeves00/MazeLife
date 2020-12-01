# Maze Life
Allows user to create a grid to simulate Conway's "Game of Life" and then use the final grid generation as a maze. An "explorer" is placed in the top left corner of the maze where it attempts to make its way to the "gold" in the bottom right corner.

# Input
User can enter "v" for verbose output (show every step) or "s" for silent output (show only first and final steps). User inputs how many generations they want to be simulated, the dimensions of the grid, and which cells are empty or occupied (1 = occupied, 0 = empty).

# Output
"|" represents the edges of the maze, "*" represents occuppied cells, "$" respresents empty cells, "E" represents the explorer, "G" represents the gold, "#" represents cells that the explorer has been in, and "X" represents cells that the explorer has revisited and considers dead ends.
