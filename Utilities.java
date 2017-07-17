package mathUtilities;

import java.math.BigInteger;
import java.util.*;

class Utilities {

    /**
    *   Print out whatever is passed in as an argument.
    *   @param output What needs to be printed.
    */
    protected static void log(String output) {
        System.out.println(output);
    }

    protected static void log(int output) {
        System.out.println(output);
    }

    protected static void log(boolean output) {
        System.out.println(output);
    }

    protected static void log(long output) {
        System.out.println(output);
    }

    protected static void log(double output) {
        System.out.println(output);
    }

    protected static void log(int ... output) {
        System.out.println();
        for (int i = 0; i < output.length - 1; i++) {
            System.out.print(output[i] + ", ");
        }
        System.out.println();
    }

    protected static void log(String ... output) {
        System.out.println();
        for (int i = 0; i < output.length - 1; i++) {
            System.out.print(output[i] + ", ");
        }
        System.out.println();
    }

    /**
    *   This method is used to test whether an integer is prime or not.
    *   Source: Wikipedia Article - Primality Test
    *   @param n The number to be tested for primality.
    *   @return Boolean indicating whether n is prime or not.
    */
    protected static boolean isPrime(long n) {
        if (n <= 1) { // Everything less than or equal to 1 is NOT prime.
            return false;
        } else if (n <= 3) { // Will always equal either 2 or 3. Both are prime.
            return true;
        } else if (n % 2 == 0 || n % 3 == 0) { // If an integer is divisible by 2 or 3 it is NOT prime.
            return false;
        } 

        int i = 5;
        while(i * i <= n) {
            if (n % i == 0 || n % (i+2) == 0) {
                return false;
            } else {
                i += 6;
            }
        }

        return true;
    }

    /**
    *   This method is used to calculate the nth triangular number.
    *   @param n The index of the triangular number to be calculated.
    *   @return The triangular number at index n.
    */
    protected static int nthTriangularNumber(int n) {
        return (n * (n + 1)) / 2;
    }

    /**
    *   This method is used to calculate the number of unique divisors an integer has.
    *   @param n The number whose unique divisors needs to be counted.
    *   @return The number of unique divisors the n has.
    */
    protected static int numberOfDivisors(int n) {
        int numDivisors = 0;

        if (isPrime(n)) {
            return 2;
        }

        for (int i = 1; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                numDivisors++;
            }
        }

        if (isSquare((long) n)) {
            return ((numDivisors + 1) * 2) - 1; // (- 1): Since squares always have an uneven amount of divisors.
        } else {
            return numDivisors * 2;
        }
    }

    /**
    *   Determines whether a Long is a square.
    *   @param x Long to be checked.
    *   @return Boolean Indicating whether input is square(true) or not(false).
    */
    // Source: http://stackoverflow.com/questions/295579/fastest-way-to-determine-if-an-integers-square-root-is-an-integer
    protected static boolean isSquare(long x) {
        long goodmask = 0; // 0xC840C04048404040 Computed below.

        for (int i = 0; i < 64; i++) {
            goodmask |= Long.MIN_VALUE >>> (i * i);
        }

        // This tests if the 6 least significant bits are right.
        // Moving the to be tested bit to the highest position saves us masking.
        if (goodmask << x >= 0) {
            return false;
        }

        final int numberOfTrailingZeros = Long.numberOfTrailingZeros(x);

        // Each square ends with an even number of zeros.
        if ((numberOfTrailingZeros & 1) != 0) {
            return false;
        }

        x >>= numberOfTrailingZeros;

        // Now x is either 0 or odd.
        // In binary search each odd square ends with 001.
        // Postpone the sign test until no; handle zero in the branch
        if ((x & 7) != 1 | x <= 0) {
            return x == 0;
        }

        // Do it in the classical way.
        // The correcness is not trivial as the conversion from long to double is lossy.
        final long tst = (long) Math.sqrt(x);
        return tst * tst == x;
    }

    /**
    *   Used to calculate the number of possible paths through a grid.
    *   From one corner to another.
    *   @param n Ths size of the grid as in n*n.
    *   @return BigInteger The number of paths through the grid.
    */
    protected static BigInteger latticePaths(BigInteger n) {
        
        BigInteger num = factorial(n.multiply(BigInteger.valueOf(2))).divide(factorial(n).multiply(factorial(n)));
        
        return num;
    }
    
    /**
    *   This method is used to calculate the factorial of an integer(n!).
    *   @param value The integer whose factorial value is needed.
    *   @return The factorial value of the integer passed in.
    */
    protected static BigInteger factorial(BigInteger value) {
        if (value.equals(BigInteger.ONE) || value.equals(BigInteger.ZERO)) return BigInteger.ONE;
        return value.multiply( factorial(value.subtract(BigInteger.ONE)) );
    }

    protected static long factorial(int value) {
        if (value == 1) return 1;
        return value * factorial(value - 1);
    }

    protected static int sumOfShortestPathTriangle(int triangle[][], int size) {

        int sum = 0, counter = 0;

        for (int i = 0; i < size; i++) {
            if (triangle[i][counter] > triangle[i][counter + 1]) {
                sum += triangle[i][counter];
            } else {
                sum += triangle[i][counter+1];
                counter++;
            }
        }

        return sum;
    }

    /**
    *   This method calculates the sum of the unique divisors of an integer.
    *   @param num Ths number whose divisor sum needs to be calculated.
    *   @return The sum of all unique divisors of num.
    */
    protected static int sumOfDivisors(int num) {
        // num / num = 1.
        // Since num itself isn't counted as part of the proper divisor sum.
        // sum is set to 1 to avoid checking whether the divisor being tested equals num.
        int sum = 1;

        for (int i = 2; i <= Math.ceil(Math.sqrt(num)); i++) {
            if (num % i == 0 && (i+1) != (num/i)) {
                sum += i;
                if (num/i != i) {
                    sum += num/i;
                }
            }
        }

        return sum;
    }

    protected static int sumOfDigits(int num) {
        String strRep[] = String.valueOf(num).split("");
        int sum = 0;

        for (String digit: strRep) {
            sum += Integer.parseInt(digit);
        }

        return sum;
    }

    /**
    *   This method calculates the length of a collatz sequence from the 
    *   starting integer provided.
    *   @param start The starting integer of the sequence.
    *   @return Integer The length of collatz sequence.
    */
    protected static int collatzSequenceLength(int start) {
        int sequenceLength = 1; // Since start is already the beginning.
        int currentNumber = start;

        while (currentNumber != 1 && currentNumber > 0) {

            if (currentNumber % 2 == 0) { // If currentNumber is even.
                currentNumber /= 2;
            } else { // If currentNumber is odd.
                currentNumber = 3 * currentNumber + 1;
            }

            sequenceLength++;
        }
        
        return sequenceLength;
    }

    /**
    *   This method is used to swap two values in the same array.
    *   Currently only works for array of type int.
    *   @param v Ths array in which the values need to be swapped.
    *   @param i The index of one of the values that need to be swapped.
    *   @param j The index of another value.
    *   @return Doesn't return a anything.
    */
    protected static void swap(int[] v, int i, int j) {
        int t = v[i]; // Temp variable.
        v[i] = v[j];
        v[j] = t;
    }

    /**
    *   This method is used to test whether a number is abundant.
    *   @param num The number to be tested whether it is abundant or not.
    *   @return Boolean Indicates whether num is abundant or not.
    */
    protected static boolean isAbundant(int num) {
        int sum = 0;

        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }

        return sum > num;
    }

    /**
    *   This method finds the length of the reciprocal when the fraction is
    *   written in decimal.
    *   @param num Numerator of the fraction.
    *   @param denom Denominator of the fraction.
    *   @return The length of the reciprocal of the fraction.
    */
    protected static int lengthOfRepetend(int num, int denom) {
        String nines = "9";
        BigInteger ninesBig = new BigInteger(nines);
        int numerator = num; // Not used yet.
        int denominator = denom;

        // Remove all factors of 2 and 5 from denominator.
        while ((denominator % 2 == 0 || denominator % 5 == 0) && denominator > 0) {
            if (denominator % 5 == 0) {
                denominator -= 5;
            } else if (denominator % 2 == 0) {
                denominator -= 2;
            }
        }

        int repetendLength = 0;

        while (true && denominator != 0) {
            if (ninesBig.mod(BigInteger.valueOf(denominator)).compareTo(BigInteger.ZERO) == 0) {
                repetendLength++;
                break;
            } else {
                nines += "9";
                ninesBig = new BigInteger(nines);
            }
            repetendLength++;
        }

        return repetendLength;
    }

    /**
    *   Note: This is a brute force solution.
    *   This method is used to calculate the sum of all diagonal number of a
    *   spiral of any size. e.g: 21 22 23 24 25
    *                            20  7  8  9 10
    *                            19  6  1  2 11
    *                            18  5  4  3 12
    *                            17 16 15 14 13
    *   @param n Size of spiral. One side of spiral.
    *   @return The sum of the diagonal values of the spiral.
    */
    protected static long diagonalSumInSpiral(int n) {

        int sum = 0;

        // Values in corners are sperated by sequence [1, 3, 4, 5, ...]. 
        // Start at 2 to avoid skipping to the next index.
        int skip = 2;
        int skipCounter = 0;

        for (int i = 0; i < (n * n); i += skip) {
            if (skipCounter == 4) { // Since the spiral has 4 corners.
                skip += 2; 
                skipCounter = 0;
            }

            sum += i + 1;

            skipCounter++;
        }

        return sum;

    }

    /**
    *   Narcissistic Number is a number whose sum of it's digits, raised to the
    *   power of the number of digits, equals the number.
    *   @param n The integer to be tested.
    *   @param Boolean True if n is a Narcissistic Number.
    *   @return Boolean Indicating whether a number is Narcissistic number(true) or not(false);
    */
    protected static boolean isNarcissisticNumber(int n) {

        String[] strRepresentation = String.valueOf(n).split("");
        int sum = 0;

        for (String a: strRepresentation) {
            sum += Math.pow(Integer.valueOf(a), strRepresentation.length);
        }

        return sum == n;

    }

    /**
    *   This method returns the sum of the digits of an integer provided raised
    *   to a power provided.
    *   @param n The integer whose digits have to be raised to a power.
    *   @param power The power to which each digit of n is raised.
    *   @return The sum of all digits after their raised to power.
    */
    protected static int sumOfDigitsRaisedToPow(int n, int power) {
        String[] strRepresentation = String.valueOf(n).split("");
        int sum = 0;

        for (String a: strRepresentation) {
            sum += Math.pow(Integer.valueOf(a), power);
        }

        return sum;
    }

    /**
    *   This method is used to check whether and integer is a factorion.
    *   @param n Integer to be checked.
    *   @return Boolean value indication whether integer is factorion or not.
    */
    protected static boolean isFactorion(long n) {

        BigInteger sumFact = BigInteger.ZERO;

        String[] strRep = String.valueOf(n).split("");
        int[] digits = new int[strRep.length];

        for (int i = 0; i < strRep.length; i++) {
            digits[i] = Integer.valueOf(strRep[i]);
        }

        BigInteger temp = BigInteger.ZERO;
        
        for (int v: digits) {
            temp = factorial(BigInteger.valueOf(v));
            sumFact = sumFact.add(temp);
        }

        return sumFact.compareTo(BigInteger.valueOf(n)) == 0;

    }

    /**
    *   This algorithm rotates a sequence supplied by an order.
    *   Source: http://codereview.stackexchange.com/questions/69299/rotating-an-array-by-position-n
    *   @param arr The sequence to be rotated.
    *   @param order The order to which the arr must be rotated.
    *   @return Nothing. Sequence is pass-by-reference.
    */
    protected static void rotate(int[] arr, int order) {

        if (arr == null || order < 0) {
            throw new IllegalArgumentException("The array must be non-null and the order must be non-negative.");
        }

        int offset = arr.length - order % arr.length;

        if (offset > 0) {
            int[] copy = arr.clone();

            for (int i = 0; i < arr.length; i++) {
                int j = (i + offset) % arr.length;
                arr[i] = copy[j];
            }
        }

    }

    protected static void rotate(String[] arr, int order) {

        if (arr == null || order < 0) {
            throw new IllegalArgumentException("The array must be non-null and the order must be non-negative.");
        }

        int offset = arr.length - order % arr.length;

        if (offset > 0) {
            String[] copy = arr.clone();

            for (int i = 0; i < arr.length; i++) {
                int j = (i + offset) % arr.length;
                arr[i] = copy[j];
            }
        }

    }

    /**
    *   Circular Prime: A prime integer of which all rotations are also prime.
    *   isCircularPrime(n) checks whether an integer is a circular prime.
    *   @param n Integer to be checked.
    *   @return Boolean value indicating whether n is a circular prime of not.
    */
    protected static boolean isCircularPrime(int n) {
        if (!isPrime(n)) {
            return false;
        }

        ArrayList<Integer> permutations = new ArrayList<>();

        int temp = n;
        while ((temp = rotateInteger(temp)) != n) {
            if (!isPrime(temp)) {
                return false;
            }
        }

        return true;
    }

    /**
    *   Rotates and integer provided by one to the right.
    *   @param n Integer to be rotated.
    *   @return Rotated value of n.
    */
    protected static int rotateInteger(int n) {

        StringBuilder num = new StringBuilder();
        String[] strRep = String.valueOf(n).split("");

        rotate(strRep, 1);
        
        for (String val: strRep) {
            num.append(val);
        }

        return Integer.valueOf(num.toString());

    }

    /**
    *   Reverse a string.
    *   @param str String to be reversed.
    *   @return Reversed string of str.
    */
    protected static String reverseString(String str) {
        String characters[] = str.split("");
        StringBuilder reverse = new StringBuilder();
        for (int i = characters.length - 1; i > -1; i--) {
            reverse.append(characters[i]);
        }

        return reverse.toString();
    }

    /**
    *   Checks whether a number is a palindrome or not.
    *   @param num Integer to check if it's a palindrome.
    *   @return Boolean value indicating whether or not num is a palindrome.
    */
    protected static boolean isPalindrome(long num) {
        String strRepresentation = String.valueOf(num);
        return !!(strRepresentation.equals(reverseString(strRepresentation)));
    }

    protected static boolean isPalindrome(String str) {
        return !!(str.equals(reverseString(str)));
    }

    protected static boolean isPalindrome(BigInteger num) {
        String strRepresentation = num.toString();
        return !!(strRepresentation.equals(reverseString(strRepresentation)));
    }

    /**
    *   Calculates a list of fibonacci values below a specified value.
    *   @param n Integer specifying the upper limit.
    *   @return An array containing all fibonacci value below n;
    */
    protected static Long[] fibonacciSequenceBelow(int n) {
        ArrayList<Long> sequence = new ArrayList<>();
        long lo = 1, hi = 1;
        sequence.add(0, lo);
        sequence.add(1, hi);

        for (int i = 3; (hi = lo + hi) < n; i++) {

            sequence.add(i - 1, hi);
            lo = hi - lo;
            
        }

        System.out.println(sequence.toString());

        Long answer[] = new Long[sequence.size()];
        answer = sequence.toArray(answer);

        return answer;

    }

    /**
    *   Calculates the nth Fibonacci number.
    *   @param index Index of the value to be calculated.
    *   @return The value at index in the Fibonacci sequence.
    */
    protected static int nthFibonacciNumber(int index) {
        if (index == 1 || index == 2) {
            return 1;
        }

        return nthFibonacciNumber (index-1) + nthFibonacciNumber (index-2); // Tail recursion
    }

    /**
    *   This method removes the right most digit of an integer and returns the
    *   new integer.
    *   @param n An integer to be altered.
    *   @param Altered version of n.
    *   @return long Altered integer.
    */
    protected static long removeDigitRight(long n) {
        String strRep = String.valueOf(n);

        if (strRep.length() <= 1) {
            return -1;
        }

        // parseInt(): returns an long. valueOf(): return an Long.
        return Long.parseLong(strRep.substring(0, (strRep.length() - 1))); 
    }

    /**
    *   This method removes the left most digit of an integer and returns the
    *   new integer.
    *   @param n An integer to be altered.
    *   @param Altered version of n.
    */
    protected static long removeDigitLeft(long n) {
        String strRep = String.valueOf(n);

        if (strRep.length() <= 1) {
            return -1;
        }

        // parseInt(): returns a long. valueOf(): returns a Long.
        return Long.parseLong(strRep.substring(1)); 
    }

    /**
    *   Checks whether a given number is a left truncatable prime.
    *   @param n Prime number to be checked.
    *   @return Boolean value indicating whether n is a left truncatable prime.
    */
    protected static boolean isLeftTruncatablePrime(long n) {

        if (!isPrime(n)) return false;

        long temp = n;

        while( (temp = removeDigitLeft(temp)) != -1 ) {
            if (!isPrime(temp)) {
                return false;
            }
        }

        return true;
    }

    /**
    *   Checks whether a given number is a right truncatable prime.
    *   @param n Prime number to be checked.
    *   @return Boolean value indicating whether n is a right truncatable prime.
    */
    protected static boolean isRightTruncatablePrime(long n) {

        if (!isPrime(n)) return false;

        long temp = n;

        while ( (temp = removeDigitRight(temp)) != -1 ) {
            if (!isPrime(temp)) {
                return false;
            }
        }

        return true;
    }

    /**
    *   Checks whether an integer is pandigital for the range provided.
    *   @param num Integer to check.
    *   @param start Start of range of digits to check for.
    *   @param end End of range of digits to check for.
    *   @return Boolean Boolean indicating whether or not integer is pandigital for the range provided.
    */
    protected static boolean isPandigital(long num, int start, int end) {

        String[] testSubject = String.valueOf(num).split("");

        int[] answer = new int[10];
        Arrays.fill(answer, 0);

        for (String value: testSubject) {
            answer[Integer.valueOf(value)]++;
        }

        if (start > 0) {
            for (int i = 0; i < start; i++) {
                if (answer[i] != 0) {
                    return false;
                }
            }
        } 

        if (end < 9) {
            for (int j = end + 1; j < 10; j++) {
                if (answer[j] != 0) {
                    return false;
                }
            }
        }

        for (int i = start; i <= end; i++) {
            if (answer[i] != 1) {
                return false;
            }
        }

        return true;
    }

    /**
    *   This method uses the Pythagorean Theorem to calculate the hypoteneuse.
    *   @param a One side of the triangle.
    *   @param b Another side of the triangle.
    *   @return The value of the hypoteneuse.
    */
    protected static double calculateHypoteneuse(int a, int b) {
        return Math.sqrt(Math.pow(a,2) + Math.pow(b, 2));
    }

    /**
    *   Calculates the sum of all the letters's numerical indices in the alphabet.
    *   @param word String representation of a word.
    *   @return The sum of all letters's numerical indices.
    */
    protected static int valueOfWord(String word) {
        char[] letters = word.toCharArray();
        int sum = 0;

        for (char ch: letters) {
            sum += valueOfLetter(ch);
        }

        return sum;
    }

    /**
    *   This method calculates the numberical index of a letter in the alphabet.
    *   @param character Letter to calculate the index for.
    *   @return Numberical index of character in the latin alphabet.
    */
    protected static int valueOfLetter(char character) {

        return ((int) Character.toLowerCase(character)) - 96;

    }

    /**
     *  Source: http://codereview.stackexchange.com/questions/29609/sieve-of-eratosthenes-in-java
     * @param upperLimit
     * @return a list of prime numbers from 0 to upperLimit inclusive
     * @throws IllegalArgumentException if upperLimit is less than 0 
     */
    protected static List<Integer> generatePrimes(int upperLimit) {

        if(upperLimit < 0) 
            throw new IllegalArgumentException("Negative size");

        // at first all are numbers (0<=i<=n) not composite
        boolean[] isComposite = new boolean[upperLimit + 1]; 
        for (int i = 2; i * i <= upperLimit; i++) {
            if (!isComposite [i]) {
                // populate all multiples as composite numbers
                for (int j = 2 * i; j <= upperLimit; j += i) {
                    isComposite [j] = true;
                }
            }
        }

        List<Integer> primeList = new ArrayList<>();

        // make a list of all non-composite numbers(prime numbers)
        int arrLength = isComposite.length;
        for(int index = 2; index < arrLength; index++) {
            if(!isComposite[index]) {
                primeList.add(new Integer(index));
            }
        }
        return primeList;
    }

    /**
    *   Generate the nth square number.
    *   @param n The index of the square number in the series.
    *   @return The square number at index n.
    */
    protected static long nthSquare(int n) {
        return (long) Math.pow(n,2);
    }

    /**
    *   Check whether an integer is a square number.
    *   @param num Number to check.
    *   @return Boolean value indicating whether num is square or not.
    */
    protected static boolean isSquare(int num) {
        double root = Math.sqrt(num);
        return root % Math.floor(root) == 0;
    }

    /**
    *   This method generates the nth pentagonal number.
    *   @param n Index of the required pentagonal number.
    *   @return The pentagonal number at index n.
    */
    protected static long nthPentagonalNumber(int n) {
        return (long) ((3 * Math.pow(n,2)) - n) / 2;
    }

    /**
    *   Checks whether a given integer is a pentagonal number.
    *   @param x Integer to check.
    *   @return Boolean value indicating whether or not x is a pentagonal number.
    */
    protected static boolean isPentagonalNumber(long x) {
        double n = (Math.sqrt(24 * x + 1) + 1 ) / 6;
        return n % Math.floor(n) == 0;
    }

    /**
    *   Generate the nth hexagonal number.
    *   @param n The index of the hexagonal number in the series.
    *   @return The hexagonal number at index n.
    */
    protected static long nthHexagonalNumber(int n) {
        return n * (2 * n - 1);
    }

    /**
    *   Checks whether a given integer is a hexagonal number.
    *   @param x Integer to check.
    *   @return Boolean value indicating whether or not x is a hexagonal number.
    */
    protected static boolean isHexagonalNumber(long x) {
        double n = ( Math.sqrt(8 * x + 1) + 1 ) / 4;
        return n % Math.floor(n) == 0;
    }

    /**
    *   Checks whether a given integer is a triangular number.
    *   @param x Integer to check.
    *   @return Boolean value indicating whether or not x is a triangular number.
    */
    protected static boolean isTriangularNumber(long x) {
        double n = (Math.sqrt(8 * x + 1) - 1) / 2;
        return n % Math.floor(n) == 0;
    }

    /**
    *   This method takes an array of type int and returns the concatenated 
    *   long value of the integers.
    *   @param ar Array of integers that are the digits of a larger number.
    *   @return The long value of the array of digits.
    */
    protected static long longValue(int[] ar) {
            
        StringBuilder str = new StringBuilder();

        for (int digit: ar) {
            str.append(String.valueOf(digit));
        }

        return Long.parseLong(str.toString());

    }

    /**
    *   This method finds the range of prime numbers whose sum 
    *   equals an integer.
    *   @param n The integer too look for.
    *   @param primes An array of prime numbers sorted from small to large.
    *   @return The number of primes that make up n.
    */
    protected static int findRangeOfPrimes(int n, List<Integer> primes) {
        int sum = 2; // Smallest Prime
        int firstIndex = 0;
        int lastIndex = 0;

        while (true) {
            //System.out.println(sum + " : " + firstIndex + " : " + lastIndex);
            if (sum == n) {
                //System.out.println(sum + " : " + firstIndex + " : " + lastIndex);
                return (lastIndex - firstIndex) + 1;
            }

            if (sum < n) {
                lastIndex++;
                sum += primes.get(lastIndex);
                if (primes.get(lastIndex) > n) {
                    return -1;
                }
            }

            if (sum > n) {
                sum -= primes.get(firstIndex);
                firstIndex++;
            }
        }

    }

    /**
    *   Checks whether one integer is a permutation of the other.
    *   @param a An integer to check.
    *   @param b Integer to check if it's a permutation of a.
    *   @return Boolean value indicating whether b is a permutation of a.
    */
    protected static boolean isPermutation(int a, int b) {
        String[] strRepA = String.valueOf(a).split("");
        String[] strRepB = String.valueOf(b).split("");
        int[] checker = new int[10];
        Arrays.fill(checker, 0);

        for (int i = 0; i < strRepA.length; i++) {
            checker[Integer.parseInt(strRepA[i])]++;
        }

        for (int i = 0; i < strRepB.length; i++) {
            checker[Integer.parseInt(strRepB[i])]--;
        }

        for (int val: checker) {
            if (val != 0) {
                return false;
            }
        }

        return true;

    }

    /**
    *   Count the number of distinct prime factors of an integer.
    *   @param n An integer to factorize.
    *   @param primes List of primes to check when factorizing.
    *   @return The number of distinct primes n has.
    */
    protected static int countDistinctPrimeFactors(int n, List<Integer> primes) {

        int temp = n;
        List<Integer> primeFactors = new ArrayList<>();
        //List<Integer> primes = generatePrimes(9099);

        check: while (!isPrime(temp)) {
            for (int i = 0; i < primes.size(); i++) {
                if (temp % primes.get(i) == 0) {
                    //System.out.println(temp);
                    primeFactors.add(primes.get(i));
                    temp /= primes.get(i);
                } else if (i == primes.size() - 1) {
                    break check;
                }
            }
        }
        return primeFactors.size();

    }

    /**
    *   Generate the next greater permutation of an array.
    *   @param array Array integers to permutate.
    *   @return Boolean value indicating whether or not the permutation was a success.
    */
    protected static boolean nextPermutation(int[] array) {
        // Find the longest non-increasing suffix.
        int i = array.length - 1;

        while (i > 0 && array[i - 1] >= array[i]) {
            i--;
        }

        // Now i is the head index of the suffix.

        // Are we at the last permutation already?
        if (i <= 0) return false;

        // Let array[i - 1] be the pivot.
        // Find the rightmost element that exceeds the pivot.
        int j = array.length - 1;
        while (array[j] <= array[i - 1]) {
            j--;
        }

        // Now the value array[j] will become the next pivot.
        // Assertion: j >= i

        // Swap the pivot with j.
        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        // Reverse the suffix.
        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }

        // Successfully computed the next permutation.
        return true;
    }

    /**
    *   Generates a permutation of the array passed in to a certain order that
    *   is provided.
    *   @param v Array to be permutated.
    *   @param n Order by which array needs to be permuted.
    */
    protected static void generatePermutation(int[] v, int n) {
        ArrayList<Integer> c = new ArrayList<>();

        if (n == 1) {
            //System.out.println(Arrays.toString(v));
        } else {

            for (int i = 0; i < n; i++) {
                System.out.println(Arrays.toString(v));
                generatePermutation(v, n - 1);

                if (n % 2 == 1) {
                    swap(v, 0, n - 1);
                } else {
                    swap(v, i, n -1);
                }
            }

        }
    }

    /**
    *   Finds the number of primes when changing the repeating digit 
    *   within a number.
    *   @param n Number with repeating digit.
    *   @return The number of prime values created by permutating n.
    */
    protected static int numPrimesInFamily(int n) {
        ArrayList<Integer> indicesOfRepeatingDigit = new ArrayList<>();
        String[] strRep = String.valueOf(n).split("");
        int familyTotal = 0;
        int repeatingDigit = repeatingNum(n);

        for (int k = 0; k < strRep.length; k++) {
            if (repeatingDigit == Integer.parseInt(strRep[k])) {
                indicesOfRepeatingDigit.add(k);
            }
        }
        
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < indicesOfRepeatingDigit.size(); i++) {
                strRep[indicesOfRepeatingDigit.get(i)] = String.valueOf(j);
            }

            if (isPrime(intValue(strRep)) && !strRep[0].equals("0")) {
                familyTotal++;
            }
        }

        return familyTotal;
    }

    /**
    *   Finds the digit that repeats the most within a number.
    *   @param n Number to check for repeating digit.
    *   @return The repeating digit within n.
    */
    protected static int repeatingNum(int n) {
        String[] digits = String.valueOf(n).split("");
        int[] numDigits = new int[10];
        Arrays.fill(numDigits, 0);

        for (int i = 0; i < digits.length; i++) {
            numDigits[Integer.parseInt(digits[i])]++;
        }

        int max = 0;
        int dig = 0;
        for (int i = 0; i < numDigits.length; i++) {
            if (numDigits[i] > max) {
                max = numDigits[i];
                dig = i;
            }
        }

        if (dig != 0) {
            return dig;
        }

        return -1;
    }

    /**
    *   This method takes an array of type String and returns the concatenated 
    *   integer value of the Strings.
    *   @param ar Array of Strings that are the digits of a larger number.
    *   @return The int value of the array of digits.
    */
    protected static int intValue(String[] ar) {
            
        StringBuilder str = new StringBuilder();

        for (String digit: ar) {
            if (!digit.equals("0")) {
                str.append(digit);
            }
        }

        if (ar[0].equals("0")) return -1;

        return Integer.parseInt(str.toString());

    }

    /**
    *   This method finds all the indices where a repeating digit occurs 
    *   within a number.
    *   @param n Number to check for occurences of repeating digit.
    *   @return Array of integers indicating the indices where the repeating 
    *           digit occurs.
    */
    protected static Integer[] repeatingDigits(int n) {
        String[] strRep = String.valueOf(n).split("");
        int[] digitCount = new int[10];
        Arrays.fill(digitCount, 0);

        for (int i = 0; i < strRep.length; i++) {
            digitCount[Integer.parseInt(strRep[i])]++;
        }

        ArrayList<Integer> answer = new ArrayList<>();
        //answer.add(strRep.length);
        for (int i = 0; i < strRep.length; i++) {
            for (int j = i+1; j < strRep.length; j++) {
                if (strRep[i].equals(strRep[j])) {
                    if (!answer.contains(i)) answer.add(i);
                    if (!answer.contains(j)) answer.add(j);
                }
            }
        }

        Integer[] returnValue = new Integer[answer.size()];
        returnValue = answer.toArray(returnValue);
        return returnValue;
    }

    /**
    *   This method checks whether an integer has a repeating digit.
    *   @param n Integer to check for repeating digit.
    *   @return Boolean value indicating whether n has any repeating digits.
    */
    protected static boolean hasRepeatingDigits(int n) {
        String[] strRep = String.valueOf(n).split("");
        int[] digitCount = new int[10];
        Arrays.fill(digitCount, 0);

        for (int i = 0; i < strRep.length; i++) {
            digitCount[Integer.parseInt(strRep[i])]++;
        }

        for (int count: digitCount) {
            if (count > 1) {
                return true;
            }
        }

        return false;
    }

    /**
    *   This method returns the possible of combinations that can be made by 
    *   selecting r from n.
    *   @param n Integer indicating the number of unique values in the set.
    *   @param r Number of items selected from n each time.
    *   @return The number of possible combinations that can be made by selecting r from n.
    */
    protected static BigInteger numberOfCombinations(int n, int r) {
        BigInteger numerator = factorial(BigInteger.valueOf(n));
        BigInteger denominator = factorial(BigInteger.valueOf(r)).multiply(factorial(BigInteger.valueOf(n).subtract(BigInteger.valueOf(r))));
        return numerator.divide(denominator);
    }

    /**
    *   reverseLong() takes and integer or BigInteger and returns the reverse
    *   value.
    *   @param n Integer or BigInteger whose reverse value is to be returned.
    *   @return The reverse value of n.
    */
    protected static long reverseLong(long n) {
        String[] characters = String.valueOf(n).split("");

        StringBuilder reverse = new StringBuilder();
        for (int i = characters.length - 1; i > -1; i--) {
            reverse.append(characters[i]);
        }

        return Long.parseLong(reverse.toString());
    }

    protected static BigInteger reverseBigInteger(BigInteger n) {
        String[] characters = n.toString().split("");

        StringBuilder reverse = new StringBuilder();
        for (int i = characters.length - 1; i > -1; i--) {
            reverse.append(characters[i]);
        }

        return new BigInteger(reverse.toString());
    }

}