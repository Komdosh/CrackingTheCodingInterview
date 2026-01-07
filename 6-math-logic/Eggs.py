def optimal_step_size(max_floors):
    total = 0
    k = 0

    while total < max_floors:
        k += 1
        total += k

    return k

def find_breaking_floor(break_floor, max_floors):
    drops = 0
    current_floor = 0
    step = optimal_step_size(max_floors)
    
    while current_floor + step <= max_floors:
        current_floor += step
        drops += 1

        if current_floor >= break_floor:
            current_floor -= step
            break

        step -= 1

    for floor in range(current_floor + 1, break_floor):
        drops += 1

    return drops

worst_case = 0
worst_floor = None

floors = 106

for n in range(1, floors+1):
    attempts = find_breaking_floor(n, floors)
    if attempts > worst_case:
        worst_case = attempts
        worst_floor = n

print(f"Worst case drops: {worst_case}")
print(f"Worst case occurs at floor: {worst_floor}")
