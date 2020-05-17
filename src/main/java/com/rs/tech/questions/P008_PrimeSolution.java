package com.rs.tech.questions;

public class P008_PrimeSolution {
    public static void main(String[] args) {
        Prime prime = new Prime();
        prime.checkPrime(2);
        System.out.println();
        prime.checkPrime(1, 2, 3, 4, 5);


    }

    static class Prime {
        public void checkPrime(int... n) {
            //IntStream.of(n).filter(i -> isPrime(i)).forEach(j -> System.out.printf("%d ", j));
            for (int i : n) {
                if (isPrime(i)) {
                    System.out.printf("%d ", i);
                }
            }
            System.out.println();
        }

        private boolean isPrime(int n) {
            if (n < 2) {
                return false;
            }

            if (n == 2) {
                return true;
            }

            if (n % 2 == 0) {
                return false;
            }

            int sqrt = (int) Math.sqrt(n);
            for (int i = 3; i <= sqrt; i += 2) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}


