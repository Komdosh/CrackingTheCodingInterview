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

pub(crate) fn longest_sequence(){
    let n = 1775; // 11011101111
    println!("Longest sequence: {}", longest_sequence_processor(n));
}