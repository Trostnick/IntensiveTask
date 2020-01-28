package com.company.tech.leaders;

/*
cos x=-1-x^2/2!+x^4/4!-x^6/6!.........
*/

public class CosCalculator {

    public static double calculateWithTermsCount(int x, int count) {
        double result = 1D;
        Double nextElement = 1D;
        double numerator = 1D;
        double denominator = 1D;

        for (int i = 1; i < count; i++) {
            numerator *= (-1) * x * x;
            denominator *= (2 * i) * (2 * i - 1);
            nextElement *= numerator / denominator;
            /*
            В процессе вычислений с большим количеством слагаемых следующий элемент может стать слишком большим для типа
            double, и он будет равен infinity
            */
            if (nextElement.isInfinite()) {
                System.out.println("Too mach terms");
                System.exit(0);
            } else {
                result += nextElement;
            }
        }

        return result;
    }
}
