def open_lockers(n=100):
    lockers = [False] * (n + 1)  # False = closed, True = open

    for step in range(1, n + 1):
        for locker in range(step, n + 1, step):
            lockers[locker] = not lockers[locker]

    return [i for i in range(1, n + 1) if lockers[i]]

open = open_lockers()
print(open)
print(f"Number of open lockers: {len(open)}")
