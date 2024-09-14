#  二进制
## n位二进制数表示的大小
1. n位的无符号数能够表示大小范围是 0 ~ 2^n - 1，个数是 2^n 个

2. n位的有符号数能够表示大小范围是 -2^(n-1) ~ 2^(n-1) - 1，个数是 2^n 个
- 例如 4 位二进制数表示 有符号数
- -8(1000)  -7(1001)  ……  -1(1111)  0(0000)  ……  7(0111)
3. 负数的十进制和二进制是如何转换的
- 负数的十进制到二进制
- 例如 -8 如何表示为二进制数，分为以下几步
    - 1. 8 表示为二进制是 1000
    - 2. 将 1000 减 1 是 0111
    - 3. 将 0111 取反 即为 -8的二进制表示 1000
- 例如 -7 
    - 1. 7 -> 0111
    - 2. 0111 - 1 -> 0110
    - 3. 取反 1001
- 例如 -1 
    - 1. 1 -> 0001
    - 2. 0001 - 1 -> 0000
    - 3. 取反 1111
- 负数的二进制到十进制
- 例如 1000 是多少呢
    - 1. 首先确定符号位是 1  即为负数
    - 2. 那是几呢？ 整个二进制取反 0111 再加 1 得到1000
    - 3. 1000 表示 8 那 最后的结果就是 -8
- 例如 1001 是多少呢
    - 1. 符号位是1 
    - 2. 整个二进制数取反 0110 + 1 得到 0111
    - 3. 0111 表示 7 那 最后的结果就是 -7

# 16进制

1. 二进制和16进制转换
- 1位16进制可以转换成4位16进制，以下是对应关系
- 0000 -> 0 
- 0001 -> 1
- ……
- 1001 -> 9
- 1010 -> A
- 1011 -> B
- 1100 -> C
- 1101 -> D
- 1110 -> E
- 1111 -> F

# 进制运算

## 取反
- 在rust中 `!` 表示 取反， 在 java 和 js 中 `~` 表示 取反.
- 二进制运算中一个数的相反数等于它本身取反再加1.
- 在十进制中运算中一个数的相反数直接在前面加 `-`即可.
> Tips: 以 i32 为列子来讲， i32::MIN -2147483648 (0x80000000) 的相反数，
2147483648 已经超出了i32的范围  i32的最大值是2147483647， 最小值的相反数会发生溢出
```rust
pub fn main() {
    const   C: i32 = 0b1001110; // 78
    const  B: i32 = 0x4E; // 78
    const  A: i32 = !C; // -79 取反
    let e = !A + 1; // 79
    let d = !C + 1; // -78 一个数的相反数等于它本身取反再加1
    println!("{}", C); // 78
    println!("{}", B); // 78
    println!("{}", A); // -79
    println!("{}", d); // -78
    println!("{}", e); // 79
    println!("=====================");
    println!("{:b}", i32::MIN); // -2147483648 (0x80000000) 最小值的相反数会发生溢出 
    // 2147483648 已经超出了i32的范围  i32的最大值是2147483647
    println!("=====================");
    println!("{:b}", !i32::MIN);// 01111111111111111111111111111111
    println!("{}", !i32::MIN); // 2147483647
}
```