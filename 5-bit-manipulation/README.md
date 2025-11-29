# Bit Manipulation `Rust`

Completed tasks:

![72%](https://progress-bar.xyz/72)

## 1. Insertion

You are given two 32-bit numbers, `N` and `M`, and two bit positions, `i` and `j`. Write a method to insert `M` into `N` such that `M`
starts at bit `j` and ends at bit `i`. You can assume that the bits `j` through `i` have enough space to fit all of `M`. That is,
if `M = 10011`, you can assume that there are at least 5 bits between `j` and `i`. You would not, for example, have `j = 3` and `i = 2`,
because `M` could not fully fit between bit `3` and bit `2`.

### Example

```
Input: 
 N = 10000000000, M = 10011, i = 2, j = 6

Output: 
 N = 10001001100
```

<details>
<summary>Solution</summary>

```rust
fn insert_bits(n: u32, m: u32, i: u32, j: u32) -> u32 {
    let all_ones = !0u32; // create a 0 u32 and invert all the bits
    let left = all_ones << (j + 1);  // set zeroes for j bit positions 

    let right = (1u32 << i) - 1; // set ones for first i positions
    let mask = left | right; // resulting zeroes mask for range j..i

    let n_cleared = n & mask;  // set zeroes bits for range in source N
    let m_shifted = m << i;  // shift M to i positions
    n_cleared | m_shifted
}
```
</details>
<hr/>

## 2. Binary to String

Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double, print the binary representation. If the number cannot be
represented accurately in binary with at most 32 characters, print "ERROR"

<details>
<summary>Solution</summary>

```rust
fn print_double_binary(mut x: f64) -> String {
    if x <= 0.0 || x >= 1.0 {
        return "ERROR".to_string();
    }

    let mut result = String::from("0.");

    while x > 0.0 {
        if result.len() >= 34 { // "0." + 32 chars = 34 total length
            return "ERROR".to_string();
        }

        x *= 2.0;
        if x >= 1.0 {
            result.push('1');
            x -= 1.0;
        } else {
            result.push('0');
        }
    }

    result
}
```
</details>

<details>
<summary>Another Solution</summary>

Solution with fraction (0.5, 0.25, 0.625 etc.) check

```rust
fn print_double_binary_frac(mut x: f64) -> String {
    if x <= 0.0 || x >= 1.0 {
        return "ERROR".to_string();
    }

    let mut result = String::from("0.");

    let mut frac = 0.5;
    while x > 0.0 {
        if result.len() >= 34 { // "0." + 32 chars = 34 total length
            return "ERROR".to_string();
        }

        if x >= frac {
            result.push('1');
            x -= frac;
        } else {
            result.push('0');
        }
        frac /= 2.0;
    }

    result
}
```
</details>
<hr/>

## 3. Flip Bit to Win

You have an integer, and you can flip exactly one bit from a 0 to a 1. Write code to find the length of the longest sequence of `1s` you
could create.

### Example

```
Input: 
 N = 1775 (or: 11011101111)

Output: 
 8
```

<details>
<summary>Solution</summary>

```rust
fn longest_sequence_processor(mut n: u32) -> u32 {
    if !n == 0 { return 32; } // all ones -> already max

    let mut current_length = 0;
    let mut previous_length = 0;
    let mut max_length = 1; // we can always flip one bit

    while n != 0 {
        if n & 1 == 1 {
            current_length += 1;
        } else {
            // if pre-previous bit is 0 (like 10100) then do not concatenate them
            previous_length = if n & 2 == 0 { 0 } else { current_length };
            current_length = 0;
        }
        max_length = max_length.max(previous_length + 1 + current_length);
        n >>= 1;
    }

    max_length
}
```
</details>
<hr/>

## 4. Next Number

Given a positive integer, print the next smallest and the next largest number that have the same number of 1 bits in their binary
representation.

<details>
<summary>Solution</summary>

```rust
fn count_trailing_ones(mut n: u32) -> (u32, u32) {
    let mut count = 0;
    while n & 1 == 1 {
        count += 1;
        n >>= 1;
    }
    (count, n) // also return shifted number
}

fn count_trailing_zeros(mut n: u32) -> (u32, u32) {
    let mut count = 0;
    while n & 1 == 0 && n != 0 {
        count += 1;
        n >>= 1;
    }
    (count, n)
}

fn get_next(n: u32) -> Option<u32> {
    let (c0, c) = count_trailing_zeros(n);
    let (c1, _) = count_trailing_ones(c);

    let p = c0 + c1;

    // if all ones or all zeroes -> None
    if p == 31 || p == 0 {
        return None;
    }

    let mut result = n | (1 << p);
    result &= !((1 << p) - 1);
    result |= (1 << (c1 - 1)) - 1;
    Some(result)
}

fn get_prev(n: u32) -> Option<u32> {
    let (c1, c) = count_trailing_ones(n);
    let (c0, _) = count_trailing_zeros(c);

    if c == 0 {
        return None;
    }

    let p = c0 + c1;
    let mut result = n & (!0 << (p + 1));
    let mask = (1 << (c1 + 1)) - 1;
    result |= mask << (c0 - 1);
    Some(result)
}
```
</details>

<hr/>

## 5. Debugger

Explain what the following code does: `((n & (n-1)) == 0)`.

<details>
<summary>Solution</summary>

if n = 2^x or 0 then result is 0, 1 otherwise
n and n-1 have no common bits set to 1

```rust
pub(crate) fn debugger(){
    let n = 32;
    println!("Is {} power of 2 or zero? - {}", n, ((n & (n-1)) == 0));

    let n = 31;
    println!("Is {} power of 2 or zero? - {}", n, ((n & (n-1)) == 0));
}
```
</details>
<hr/>

## 6. Conversion

Write a function to determine the number of bits you would need to flip to convert integer A to integer B.

<details>
<summary>Solution</summary>

if n = 2^x or 0 then result is 0, 1 otherwise
n and n-1 have no common bits set to 1

```rust
pub(crate) fn bits_diff() {
    let a = 5; // 101
    let b = 7; // 111

    let mut diff = a ^ b;

    let mut count = 0;
    while diff > 0 {
        diff >>= 1;
        count += diff & 1;
    }

    println!("{} bits difference", count);
}
```
</details>

<hr/>

## 7. Pairwise Swap

Write a program to swap odd and even bits in an integer with as few instructions as possible (e.g., bit 0 and bit 1 are swapped, bit 2 and
bit 3 are swapped, and so on).

<details>
<summary>Solution</summary>

```rust
const fn odd_bits_mask() -> u32 {
    let mut x = 0xAA; // 1010_1010
    x = x | (x << 8);
    x = x | (x << 16);
    x
}
const fn even_bits_mask() -> u32 {
    let mut x = 0x55; // 0101_0101
    x = x | (x << 8);
    x = x | (x << 16);
    x
}

pub(crate) fn swap_odd_even_bits() {
    let n = 5; // 101

    let odd_mask = odd_bits_mask();
    let even_mask = even_bits_mask();
    let result = (n & odd_mask) >> 1 | (n & even_mask) << 1;

    println!("Original {:b}", n);
    println!("Result {:b} ", result);
}
```
</details>

<hr/>

## 8. Draw Line

A monochrome screen is stored as a single array of bytes, allowing eight consecutive pixels to be stored in one byte. The screen has width
w, where w is divisible by 8 (that is, no byte will be split across rows). The height of the screen, of course, can be derived from the
length of the array and the width. Implement a function that draws a horizontal line from ( xl, y) to ( x2, y).

The method signature should look something like:

```java
drawline(byte[]screen,int width,int xl,int x2,int y)
```

<hr/>
