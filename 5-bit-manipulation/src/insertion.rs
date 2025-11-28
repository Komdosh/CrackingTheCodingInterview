use crate::binary_string::to_binary_string;

fn insert_bits(n: u32, m: u32, i: u32, j: u32) -> u32 {
    let all_ones = !0u32; // create a 0 u32 and invert all the bits
    let left = all_ones << (j + 1);  // set zeroes for j bit positions 

    let right = (1u32 << i) - 1; // set ones for first i positions
    let mask = left | right; // resulting zeroes mask for range j..i

    let n_cleared = n & mask;  // set zeroes bits for range in source N
    let m_shifted = m << i;  // shift M to i positions
    n_cleared | m_shifted
}

pub(crate) fn insertion() {
    let n = 1024;
    let m = 19;
    let i = 2;
    let j = 6;

    let result = insert_bits(n, m, i, j);

    println!("N:       {}", to_binary_string(n));
    println!("M:       {}", to_binary_string(m));
    println!("Result:  {}", to_binary_string(result));
}
