# Testing

Completed tasks:

![16%](https://progress-bar.xyz/16)

## 1. Mistake

Find the mistake(s) in the following code:

```c
unsigned int i;
for(i = 100; i >= 0; --i)
    printf("%d\n",i);
```

<details>
<summary>Solution</summary>

it will never stop. Unsigned int always `>= 0`. Also in formatted printing we should use `%u` instead of `%d`, because
values after 2^31 will be interpreted as negative, in case of overflow.

```c
unsigned int i;
for(i = 100; i >= 0; --i)
    printf("%u\n",i);

printf("0"); // zero is out of for loop
```

</details>

<hr/>

## 2. Random Crashes

You are given the source to an application which crashes when it is run. After running, it ten times in a debugger, you
find it never crashes in the same place. The application is single-threaded and uses only the C standard library. What
programming errors could be causing this crash? How would you test each one?

<hr/>

## 3. ChessTest

We have the following method used in a chess game: boolean canMoveTo(int x, int y). This method is part of the Piece
class and returns whether or not the piece can move to position (x, y). Explain how you would test this method.

<hr/>

## 4. No Test Tools

How would you load test a webpage without using any test tools?

<hr/>

## 5. Test a Pen

How would you test a pen?

<hr/>

## 6. Test an ATM

How would you test an ATM in a distributed banking system?

<hr/>
