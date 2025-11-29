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
