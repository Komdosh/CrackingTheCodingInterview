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
