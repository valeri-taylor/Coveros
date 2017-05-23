package com.coveros.exercise.creditcard;

/**
 * <code>InvalidCardLengthException</code> is an exception when a given credit card has an invalid number
 * of digits. Either in general (less than 12 or more than 19), or an invalid number of digits for the specific
 * company.
 *
 * @author Valeri Spetgang Taylor
 */
public class InvalidCardLengthException extends InvalidCardNumberException {
}
