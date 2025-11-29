// if n = 2^x or 0 then result is 0, 1 otherwise
// n and n-1 have no common bits set to 1
pub(crate) fn debugger(){
    let n = 32;
    println!("Is {} power of 2 or zero? - {}", n, (( n & ( n-1)) == 0));

    let n = 31;
    println!("Is {} power of 2 or zero? - {}", n, (( n & ( n-1)) == 0));
}