package com.coveros.exercise.creditcard;

/**
 * <code>BadCheckDigitException</code> is an exception when a credit card number has a bad check digit. This is
 * the last number on the card. This number is a code to match the rest of the numbers, and if it doesn't match,
 * then this exception is thrown.
 *
 * @author Valeri Spetgang Taylor
 */
public class BadCheckDigitException extends InvalidCardNumberException {

}
