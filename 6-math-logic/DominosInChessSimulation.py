# 0 = empty cell, 1 = domino placed
N = 8
board = [[0]*N for _ in range(N)]

board[0][0] = -1  # removed top-left corner
board[N-1][N-1] = -1  # removed bottom-right corner

MAX_DOMINOES = 31

# Directions to place domino: right (0,1) or down (1,0)
# It will cover all the possible solutions
directions = [(0,1), (1,0)]

def is_valid(x, y):
    return 0 <= x < N and 0 <= y < N and board[x][y] == 0

def place_dominoes(count=0):
    if count == MAX_DOMINOES:
        return True

    # Find first empty square
    for i in range(N):
        for j in range(N):
            if board[i][j] == 0:
                for dx, dy in directions:
                    ni, nj = i+dx, j+dy
                    if is_valid(ni, nj):
                        # Place domino
                        board[i][j] = 1
                        board[ni][nj] = 1
                        if place_dominoes(count + 1):
                            return True
                        # Backtrack (remove domino)
                        board[i][j] = board[ni][nj] = 0
                # If no domino can be placed here, backtrack
                return False
    return True

if place_dominoes():
    print("Found a solution!") 
else:
    print(f"No solution exists. Impossible to cover the board with {MAX_DOMINOES} dominoes.")
