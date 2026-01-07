import random

def find_poisoned_bottle(num_bottles=1000, num_strips=10):
    poisoned = random.randint(0, num_bottles - 1)
    
    # Convert bottle numbers to binary. 0 = negative, 1 = positive
    test_results = [0] * num_strips 

    for bottle in range(num_bottles):
        binary = format(bottle, f'0{num_strips}b')
        if bottle == poisoned:
            # Put drops on strips corresponding to 1s in binary
            for i, bit in enumerate(binary):
                if bit == '1':
                    test_results[i] = 1  # Strip turns positive


    binary_result = ''.join(str(bit) for bit in test_results)
    identified = int(binary_result, 2)

    return poisoned, identified

poisoned, identified = find_poisoned_bottle()
print(f"Poisoned bottle: {poisoned}")
print(f"Identified bottle: {identified}")
print(f"Success: {poisoned == identified}")
