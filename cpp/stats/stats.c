/*
 * Created by Dmitry Berezhnoy on 3/10/17.
 *
 * stats.c
 */

#include <stdio.h>
#include "stats.h"

int mean;
int median;
int maximum;
int minimum;

void stats(unsigned char *data, int size) {
    int i = 0;
    int sum = 0;
    while (i < size) {
        sum += data[i];
        int j = i;
        while (j > 0) {
            if (data[j] <= data[j - 1]) {
                break;
            } else {
                unsigned char temp = data[j - 1];
                data[j - 1] = data[j];
                data[j] = temp;
            }
            j--;
        }
        i++;
    }
    mean = sum / size;
    int m = size / 2;
    if (m % 2 == 0) {
        median = (data[m - 1] + data[m]) / 2;
    } else {
        median = data[m - 1] / 2;
    }
    maximum = data[0];
    minimum = data[size - 1];
}

void print_stats(unsigned char *data, int size) {
    int i = 0;
    while (i < size) {
        printf("data[%.2d]: %4d\n", i, data[i]);
        i++;
    }
    printf("Mean: %d\n", mean);
    printf("Median: %d\n", median);
    printf("Maximum: %d\n", maximum);
    printf("Minimum: %d\n", minimum);
}

void main() {
    unsigned char test[40] = {
            34, 201, 190, 154, 8, 194, 2, 6, 114, 88,
            45, 76, 123, 87, 25, 23, 200, 122, 150, 90,
            92, 87, 177, 244, 201, 6, 12, 60, 8, 2,
            5, 67, 7, 87, 250, 230, 99, 3, 100, 90
    };
    stats(test, 40);
    print_stats(test, 40);
}
