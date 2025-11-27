def game1(p):
    return p

def game2(p):
    return 3*pow(p,2)-2*pow(p,3)

p = 0.5
if game1(p) > game2(p):
    print("Pick first game")
else:
    print("Pick second game")
