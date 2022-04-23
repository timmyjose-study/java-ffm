#pragma once

#ifdef __cplusplus
extern "C" {
#endif

typedef struct {
  int num;
  int denom;
} Rational;

Rational *rat_init(int, int);
void rat_free(Rational *);
void rat_print(Rational *);
Rational *rat_add(Rational *, Rational *);
Rational *rat_sub(Rational *, Rational *);
Rational *rat_mul(Rational *, Rational *);
Rational *rat_div(Rational *, Rational *);

#ifdef __cplusplus
}
#endif