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
