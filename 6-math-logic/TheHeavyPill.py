def measure(bottles):
    return (bottles * (bottles+1))/bottles

bottlesCount = 20
knownFakeBottle = 12  # just for testing
fakeMeasure = measure(bottlesCount)+(knownFakeBottle/10)

result = (fakeMeasure-measure(bottlesCount))*10

print(round(result) == knownFakeBottle) # should be the same for testing
