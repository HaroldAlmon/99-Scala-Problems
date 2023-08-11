package com.translationdata;

//public class SieveOfEratosthenes {
//}
//
//package com.translationdata.util;

// How to use this class: Just create a new instance of the class and pass in the n variable.
// The class will build an array of all the primes up to n, then call isPrime(n) to determine if n is prime.

public class SieveOfEratosthenes {
    private boolean[] primes;
    // Generate all the primes <= n
    // Returns the number of primes found.
    public int generatePrimes(int n) {
        primes = new boolean[n + 1];

        for (int position = 2; position <= n; position += 1) {
            primes[position] = true;
        }

        int upperBound = (int) Math.floor(Math.sqrt( n ));

        for (int primeNumber = 2; primeNumber <= upperBound; primeNumber += 1) {
            if( primes[primeNumber] == true) {
                for( int notPrime = primeNumber * primeNumber; notPrime <= n; notPrime += primeNumber)
                    primes[notPrime] = false;
            }
        }

        int count = 0;
        for( int position = 2; position <= n; position += 1) {
            if(primes[position] == true) {
                // System.out.printf("%d%n", position);
                count += 1;
            }
        }
        System.out.printf("Generated %d primes%n", count);
        return count;
    }

    public SieveOfEratosthenes() {
    }
    public SieveOfEratosthenes(int n) {
        generatePrimes(n);
    }

    public boolean isPrime(int n) {
        return primes[n];
    }
}
