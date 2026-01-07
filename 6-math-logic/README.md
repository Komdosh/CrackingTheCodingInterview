# Math and Logic Puzzles `Python`

Completed tasks:

![70%](https://progress-bar.xyz/70)

## 1. The Heavy Pill

You have 20 bottles of pills. 19 bottles have 1.0 gram pills, but one has pills of weight 1.1 grams. Given a scale that provides an exact
measurement, how would you find the heavy bottle? You can only use the scale once.

<details>
<summary>Solution</summary>

We take a number of pills equal to the index of each bottle.
We don’t need an array here; we just have to compare the total weight with the normal weight (without any fake pills). 
The difference will identify the bottle containing the 1.1-gram pills.

 ```python
  def measure(bottles):
     return (bottles * (bottles+1))/bottles
 
  bottlesCount = 20
  knownFakeBottle = 12  # just for testing
  fakeMeasure = measure(bottlesCount)+(knownFakeBottle/10)
  
  result = (fakeMeasure-measure(bottlesCount))*10
  
  print(round(result) == knownFakeBottle) # should be the same for testing
 ```

</details>

<hr/>

## 2. Basketball

You have a basketball hoop and someone says that you can play one of two games.

Game 1: You get one shot to make the hoop.

Game 2: You get three shots, and you have to make two of three shots.

If `p` is the probability of making a particular shot, for which values of p should you pick one game or the other?

<details>
<summary>Solution</summary>

Actually we need to calculate probability of each game. For Game 1 is easy, it is just a `p`. 
But for Game 2 we need to calculate probability of cases: `exactly 2` and `all 3`. For `exactly 2` we need a binom `(3,2)p^2(1-p) = 3*p^2(1-p)`. All 3: `p^3`.
So for the Game 2 probability of wining is `3*p^2-2*p^3`. 
Then we need to solve two cases `Game 1 > Game 2` and `Game 2 > Game 1`. Let's dive in `Game 1 > Game 2` (another will be an inversion):
```
p > 3*p^2-2*p^3
1 > 3*p-2*p^2
2*p^2-3*p+1 > 0 # let's equal to 0 and solve this quadratic formula
p = 1 or p = 1/2 # so there is 2 intervals 0 < p < 1/2 and 1/2 < p < 1 (p in 0..1 so inf we ommit)
```

In this case if `p<50%` we have to select Game 1, if `0.5<p<1` - Game 2. If `p = 50%` doesn't matter what game to choose.

 ```python
def game1(p):
    return p

def game2(p):
    return 3*pow(p,2)-2*pow(p,3)

p = 0.4
if game1(p) > game2(p):
    print("Pick first game")
else:
    print("Pick second game")
 ```

</details>

<hr/>

## 3. Dominos

There is an 8x8 chessboard in which two diagonally opposite corners have been cut off. You are given 31 dominos, and a single domino can
cover exactly two squares. Can you use the 31 dominos to cover the entire board? Prove your answer (by providing an example or showing why
it's impossible).

<details>
<summary>Solution</summary>

It is impossible. In this problem, every placed domino covers two cells: one black and one white.
If we remove the two top corners, we are actually removing two cells of the same color, so the board becomes unbalanced (32 black and 30 white, or vice versa).

 ```python
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
 ```

</details>


<hr/>

## 4. Ants on a Triangle

There are three ants on different vertices of a triangle. What is the probability of collision (between any two or all of them) if they
start walking on the sides of the triangle? Assume that each ant randomly picks a direction, with either direction being equally likely to
be chosen, and that they walk at the same speed. Similarly, find the probability of collision with n ants on an n-vertex polygon.

<details>
<summary>Solution</summary>

The total number of possible direction combinations for triangle is 8 (each ant can go only one way left or right, so 2^3).
For Nth polygon we have 2^n total directions. 

A collision-free situation occurs only if all ants choose the same direction:
- All clockwise
- All counterclockwise

As we need to calculate collisions, we can use the complement approach. Even for a Nth polygon we have: 2/(2^n)=1/(2^(n-1)) collision-free outcomes.
To get the probability of collisions, we simply take the inverse probability:  1-1/(2^(n-1))

```python
def collision_probability(n):
    return 1 - 1/(2**(n-1))

print(collision_probability(3))  # 0.75
```

</details>

<hr/>

## 5. Jugs of Water

You have a five-quart jug, a three-quart jug, and an unlimited supply of water (but no measuring cups). How would you come up with exactly
four quarts of water? Note that the jugs are oddly shaped, such that filling up exactly "half" of the jug would be impossible.

<details>
<summary>Solution</summary>

The idea is to use the difference between the two jug sizes. In fact, three and five are relatively prime numbers, so we can obtain any amount from 1 to 5 quarts.

Steps:

1. Fill the 5 jug completely
2. Pour water from 5 to 3 (5-quart jug now has 2 quarts left, 3 is full)
3. Empty 3 jug
4. Pour the remaining 2 quarts from the 5 into the 3 (the 3 jug now has 2 quarts).
5. Fill the 5 jug completely
6. Pour water from the 5 jug into the 3 jug until it is full - the 5 will then contain exactly 4 quarts.

```python
five = 0
three = 0

five = 5
print(f"Fill 5-quart jug: five={five}, three={three}")

transfer = min(five, 3 - three)
five -= transfer
three += transfer
print(f"Pour into 3-quart jug: five={five}, three={three}")

three = 0
print(f"Empty 3-quart jug: five={five}, three={three}")

transfer = min(five, 3)
five -= transfer
three += transfer
print(f"Pour remaining into 3-quart jug: five={five}, three={three}")

five = 5
print(f"Refill 5-quart jug: five={five}, three={three}")

transfer = min(five, 3 - three)
five -= transfer
three += transfer
print(f"Final pour: five={five}, three={three}")

print("Result: 5-quart jug has exactly 4 quarts")
```

</details>


<hr/>

## 6. Blue-Eyed Island

A bunch of people are living on an island, when a visitor comes with a strange order: all blue-eyed people must leave the island as soon as
possible. There will be a flight out at 8:00 pm every evening. Each person can see everyone else's eye color, but they do not know their
own (nor is anyone allowed to tell them). Additionally, they do not know how many people have blue eyes, although they do know that at least
one person does. How many days will it take the blue-eyed people to leave?

<details>
<summary>Solution</summary>

Number of days equals the number of blue-eyed people minus one.

Why? On each day, every person calculates how many blue-eyed people are on the island.

Base case:

If only one person has blue eyes, he sees zero blue-eyed people and leaves the island on the first day.

If two blue-eyed people see each other, there are two possibilities: the base case (one blue-eyed person) or both of them being blue-eyed. Since no one leaves on the first day, both conclude they are blue-eyed and leave on the second day.

If there are three blue-eyed people, no one leaves on the first or second day.

The interesting part is what happens at the end. What if I am a green-eyed person but confused? This is not a problem. I calculate (for example) that there are five blue-eyed people. I might assume that I am blue-eyed as well, so my calculation suggests that the departure day would be day five. However, I observe that they all leave on day four, which is consistent with me being green-eyed.

```python
def simulate_blue_eyes(total_people, blue_eyed):
    days = 0
    remaining_blue = blue_eyed

    print(f"Initial state: {blue_eyed} blue-eyed people\n")

    while remaining_blue > 0:
        days += 1
        print(f"Day {days}:")

        if days == remaining_blue:
            print(f"  {remaining_blue} blue-eyed people leave the island")
            remaining_blue = 0
        else:
            print("  Nobody leaves")

    print(f"\nAll blue-eyed people left on day {days}")
    print(f"="*20)

simulate_blue_eyes(total_people=10, blue_eyed=1)
```

</details>

<hr/>

## 7. The Apocalypse

In the new post-apocalyptic world, the world queen is desperately concerned about the birth rate. Therefore, she decrees that all families
should ensure that they have one girl or else they face massive fines. If all families abide by this policy that is, they have continue to
have children until they have one girl, at which point they immediately stop-what will the gender ratio of the new generation be? (Assume
that the odds of someone having a boy or a girl on any given pregnancy is equal.) Solve this out logically and then write a computer
simulation of it.

<details>
<summary>Solution</summary>

Actually, the overall distribution will be the same, since the probability of having a girl or a boy is 50/50. Families with a large number of boys are offset by families with a small number of boys (who have a girl early).

```python
import random

def simulate_families(num_families=100_000):
    boys = 0
    girls = 0

    for _ in range(num_families):
        while True:
            if random.random() < 0.5:
                girls += 1
                break
            else:
                boys += 1

    return boys, girls

boys, girls = simulate_families()

total = boys + girls
print(f"Boys: {boys} ({boys/total:.2%})")
print(f"Girls: {girls} ({girls/total:.2%})")
```

</details>
<hr/>

## 8. The Egg Drop Problem

There is a building of 100 floors. If an egg drops from the Nth floor or above, it will break. If it's dropped from any floor below, it will
not break. You're given two eggs. Find N, while minimizing the number of drops for the worst case

<hr/>

## 9. 100 Lockers

There are 100 closed lockers in a hallway. A man begins by opening all 100 lockers. Next, he closes every second locker. Then, on his third
pass, he toggles every third locker (closes it if it is open or opens it if it is closed). This process continues for 100 passes, such that
on each pass i, the man toggles every ith locker. After his 100th pass in the hallway, in which he toggles only locker №100, how many
lockers are open?

<hr/>
 
## 10. Poison

You have 1000 bottles of soda, and exactly one is poisoned. You have 10 test strips which
can be used to detect poison. A single drop of poison will turn the test strip positive permanently.
You can put any number of drops on a test strip at once and you can reuse a test strip as many times
as you'd like (as long as the results are negative). However, you can only run tests once per day and
it takes seven days to return a result. How would you figure out the poisoned bottle in as few days
as possible? Write code to simulate your approach.

<hr/>
