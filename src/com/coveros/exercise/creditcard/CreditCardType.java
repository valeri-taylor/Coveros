package com.coveros.exercise.creditcard;

/**
 * Enumeration of the possible credit cards available
 * <p><ul>
 * <li>VISA: begins with a "4", can be 13 or 16 digits long
 *
 * <li>MASTERCARD: begins with "50", "51", "52", "53", "54", "55", must be 16 digits long
 *
 * <li>AMERICAN_EXPRESS: begins with a "34" or "37", must be 15 digits long
 *
 * <li>DISCOVER_CARD: begins with "6011" or "65", must be 16 digits long
 *
 * <li>UNKNOWN: must be 12-19 digits long, and are considered UNKNOWN.
 * </ul>
 *
 * @author Valeri Spetgang Taylor
 */
public enum CreditCardType {
    VISA,
    MASTERCARD,
    DISCOVER_CARD,
    AMERICAN_EXPRESS,
    UNKNOWN
}
