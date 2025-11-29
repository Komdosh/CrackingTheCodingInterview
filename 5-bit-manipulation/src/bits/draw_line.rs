fn draw_horizontal_line(screen: &mut [u8], width: usize, x1: usize, x2: usize, y: usize) {
    assert_eq!(width % 8, 0);
    let bytes_per_row = width / 8;

    let row_start = y * bytes_per_row;
    let first_byte = x1 / 8;
    let last_byte  = x2 / 8;

    for b in (first_byte + 1)..last_byte {
        screen[row_start + b] = 0xFF;
    }

    let start_offset = x1 % 8;
    let end_offset   = x2 % 8;
    let right_byte_offset = 7 - end_offset;

    let left_byte_to_change = row_start + first_byte;
    if first_byte == last_byte {
        // x1 and x2 fit within same byte
        let mask = (0xFF >> start_offset) & (0xFF << right_byte_offset);

        screen[left_byte_to_change] |= mask;
    } else {
        // left byte to change (partial)
        let mask = 0xFF >> start_offset;
        screen[left_byte_to_change] |= mask;

        // right byte to change (partial)
        let mask = 0xFF << right_byte_offset;
        screen[row_start + last_byte] |= mask;
    }
}

pub(crate) fn draw_line() {
    const ROWS: usize = 4;
    let width = ROWS * 4; // 2 bytes per row
    let mut screen = [0u8; ROWS * 2];

    draw_horizontal_line(&mut screen, width, 2, 24, 0); // draw 2..24 in row 0

    for byte in screen {
        print!("{:08b} ", byte);
    }
}
