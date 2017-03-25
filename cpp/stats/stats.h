/*
 * Created by Dmitry Berezhnoy on 3/10/17.
 *
 * stats.h
 */

#ifndef __STATS_H__
#define __STATS_H__

/*
 * Sorts data array and writes statistics into global variables.
 *
 * data: pointer to data array
 * size: array size
 *
 * returns: nothing
 */
void stats(char *data, unsigned int size);

/*
 * Prints data array and statistics from global variables.
 *
 * data: pointer to data array
 * size: array size
 *
 * returns: nothing
 */
void print_stats(char *data, unsigned int size);

/*
 * Initializes data array and invokes stats() and print_stats() methods.
 *
 * returns: nothing
 */
void main();

#endif /* __STATS_H__ */
