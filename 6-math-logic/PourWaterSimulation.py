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
