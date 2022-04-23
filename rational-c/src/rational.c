#include "../include/rational.h"
#include <stdio.h>
#include <stdlib.h>

int gcd(int x, int y) {
  int t;
  
  while (y != 0) {
    t = x % y;
    x = y;
    y = t;
  }

  return x;
}

Rational *rat_init(int num, int denom) {
  Rational *r = malloc(sizeof(Rational));

  int g = gcd(num, denom);
  r->num = num / g;
  r->denom = denom / g;

  return r;
}

Rational* rat_add(Rational *r1, Rational *r2) {
  int num = r1->num * r2->denom + r1->denom * r2->num;
  int denom = r1->denom * r2->denom;

  Rational *sum = malloc(sizeof(Rational));

  int g = gcd(num, denom);
  sum->num = num / g;
  sum->denom = denom /g;

  return sum;
}

Rational *rat_sub(Rational *r1, Rational *r2) {
  int num = r1->num * r2->denom - r1->denom * r2->num;
  int denom = r1->denom * r2->denom;

  int g = gcd(num, denom);
  Rational *diff= malloc(sizeof(Rational));
  diff->num = num / g;
  diff->denom = denom / g;

  return diff;
}

Rational *rat_mul(Rational *r1, Rational *r2) {
  int num = r1->num * r2->num;
  int denom = r1->denom * r2->denom;

  int g = gcd(num, denom);
  Rational *prod = malloc(sizeof(Rational));
  prod->num = num / g;
  prod->denom = denom / g;

  return prod;
}

Rational *rat_div(Rational *r1, Rational *r2) {
  int num = r1->num * r2->denom;
  int denom = r1->denom * r2->num;

  int g = gcd(num, denom);
  Rational *quot = malloc(sizeof(Rational));
  quot->num = num / g;
  quot->denom = denom / g;

  return quot;
}

void rat_print(Rational *r) {
  if (r->denom == 1) {
    printf("%d\n", r->num);
  } else {
    printf("%d / %d\n", r->num, r->denom);
  }
}

void rat_free(Rational *r) {
  if (r) {
    free(r);
  }
}
