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
simulate_blue_eyes(total_people=10, blue_eyed=2)
simulate_blue_eyes(total_people=10, blue_eyed=5)
