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

pub(crate) fn double_to_binary(){
    println!("{}", print_double_binary(0.5));     // 0.1
    println!("{}", print_double_binary(0.625));   // 0.101
    println!("{}", print_double_binary(0.72));    // ERROR inf sequence
}
