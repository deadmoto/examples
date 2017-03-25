/*
 * Created by Dmitry Berezhnoy on 3/10/17.
 *
 * stats.c
 */

#include <printf.h>
#include "stats.h"

char Mean;
char Median;
char Maximum;
char Minimum;

void stats(char *data, unsigned int size) {
    int i = 0;
    int sum = 0;
    while (i < size) {
        sum += data[i];
        int j = i;
        while (j > 0) {
            if (data[j] <= data[j - 1]) {
                break;
            } else {
                char temp = data[j - 1];
                data[j - 1] = data[j];
                data[j] = temp;
            }
            j--;
        }
        i++;
    }
    Mean = sum / size;
    int m = size / 2;
    if (m % 2 == 0) {
        Median = (data[m - 1] + data[m]) / 2;
    } else {
        Median = data[m - 1] / 2;
    }
    Maximum = data[0];
    Minimum = data[size - 1];
}

void print_stats(char *data, unsigned int size) {
    int i = 0;
    while (i < size) {
        printf("data[%.2d]: %4d\n", i, data[i]);
        i++;
    }
    printf("Mean: %d\n", Mean);
    printf("Median: %d\n", Median);
    printf("Maximum: %d\n", Maximum);
    printf("Minimum: %d\n", Minimum);
}

void main() {
    char test[40] = {
            34, 201, 190, 154, 8, 194, 2, 6, 114, 88,
            45, 76, 123, 87, 25, 23, 200, 122, 150, 90,
            92, 87, 177, 244, 201, 6, 12, 60, 8, 2,
            5, 67, 7, 87, 250, 230, 99, 3, 100, 90
    };
    stats(test, 40);
    print_stats(test, 40);
}
