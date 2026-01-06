def collision_probability(n):
    return 1 - 1/(2**(n-1))

print(collision_probability(3))
print(collision_probability(5))
