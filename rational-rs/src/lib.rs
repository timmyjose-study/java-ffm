use std::fmt;

#[derive(Debug)]
#[repr(C)]
pub struct Rational {
    num: i32,
    denom: i32,
}

impl Rational {
    pub fn new(num: i32, denom: i32) -> Self {
        let g = gcd(num, denom);

        Rational {
            num: num / g,
            denom: denom / g,
        }
    }

    pub fn add(&self, other: &Rational) -> Self {
        let num = self.num * other.denom + self.denom * other.num;
        let denom = self.denom * other.denom;

        let g = gcd(num, denom);

        Rational {
            num: num / g,
            denom: denom / g,
        }
    }

    pub fn sub(&self, other: &Rational) -> Self {
        let num = self.num * other.denom - self.denom * other.num;
        let denom = self.denom * other.denom;

        let g = gcd(num, denom);
        Rational {
            num: num / g,
            denom: denom / g,
        }
    }

    pub fn mul(&self, other: &Rational) -> Self {
        let num = self.num * other.num;
        let denom = self.denom * other.denom;

        let g = gcd(num, denom);

        Rational {
            num: num / g,
            denom: denom / g,
        }
    }

    pub fn div(&self, other: &Rational) -> Self {
        let num = self.num * other.denom;
        let denom = self.denom * other.num;

        let g = gcd(num, denom);
        Rational {
            num: num / g,
            denom: denom / g,
        }
    }
}

impl fmt::Display for Rational {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        if self.denom == 1 {
            write!(f, "{}", self.num)
        } else {
            write!(f, "{} / {}", self.num, self.denom)
        }
    }
}

fn gcd(mut x: i32, mut y: i32) -> i32 {
    while y != 0 {
        let t = x % y;
        x = y;
        y = t;
    }
    x
}

#[no_mangle]
pub extern "C" fn rat_rs_init(x: i32, y: i32) -> *const Rational {
    let p = Box::new(Rational::new(x, y));
    Box::into_raw(p)
}

#[no_mangle]
pub extern "C" fn rat_rs_add(lhs: *const Rational, rhs: *const Rational) -> *const Rational {
    unsafe {
        let lhs = &*lhs;
        let rhs = &*rhs;
        let sum = Box::new(lhs.add(rhs));

        Box::into_raw(sum)
    }
}

#[no_mangle]
pub extern "C" fn rat_rs_sub(lhs: *const Rational, rhs: *const Rational) -> *const Rational {
    unsafe {
        let lhs = &*lhs;
        let rhs = &*rhs;
        let diff = Box::new(lhs.sub(rhs));

        Box::into_raw(diff)
    }
}

#[no_mangle]
pub extern "C" fn rat_rs_mul(lhs: *const Rational, rhs: *const Rational) -> *const Rational {
    unsafe {
        let lhs = &*lhs;
        let rhs = &*rhs;
        let prod = Box::new(lhs.mul(rhs));

        Box::into_raw(prod)
    }
}

#[no_mangle]
pub extern "C" fn rat_rs_div(lhs: *const Rational, rhs: *const Rational) -> *const Rational {
    unsafe {
        let lhs = &*lhs;
        let rhs = &*rhs;
        let quot = Box::new(lhs.div(rhs));

        Box::into_raw(quot)
    }
}

#[no_mangle]
pub extern "C" fn rat_rs_print(r: *const Rational) {
    unsafe {
        let r = &*r;
        println!("{}", r);
    }
}

#[no_mangle]
pub extern "C" fn rat_rs_free(r: *const Rational) {
    std::mem::drop(r);
}
