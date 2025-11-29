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

pub(crate) fn near_bits() {
    let n = 13948; // 11011001111100

    if let Some(next) = get_next(n) {
        println!("Next larger: {} ({:b})", next, next);
    } else {
        println!("No next larger number.");
    }

    if let Some(prev) = get_prev(n) {
        println!("Next smaller: {} ({:b})", prev, prev);
    } else {
        println!("No next smaller number.");
    }
}
