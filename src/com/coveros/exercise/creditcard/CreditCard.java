package com.coveros.exercise.creditcard;

/**
 * <code>CreditCard</code> holds the number and type of credit card. It does checking on the rules of the individual
 * credit card companies with their starting numbers and their digit length. It also does a checking digit test
 * using the last digit of the number.
 *
 * @author Valeri Spetgang Taylor
 */
public class CreditCard {

    private CreditCardType type;

    private String creditCardNumber;

    /**
     * <code>CreditCard</code> Constructs a new <code>CreditCard</code> with the given credit card string.
     *
     * @param creditCard the string that is the credit card number. This string will possibly have characters other
     *                   than numbers in it. For example: 1234-3456-7890-1234.
     *
     * @throws InvalidCardNumberException If the credit card number is less than 12 or more than 19, the constructor
     * will immediately throw an <code>InvalidCardLengthException</code>. Other exceptions will be thrown from
     * methods within this method.
     */
    public CreditCard(String creditCard) throws InvalidCardNumberException {
        this.creditCardNumber = creditCard;

        creditCardNumber = creditCardNumber.replaceAll("[^0-9]", "");

        if (creditCardNumber.length() < 12 || creditCardNumber.length() > 19)
                throw new InvalidCardLengthException();
        this.type = getCardType();
    }

    /**
     * <code>getCreditCardNumber</code> returns the credit card number in the form of a String.
     *
     * @return <code>String</code>: the credit card number
     */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     *
     * <code>getCardType</code> returns the enumeration <code>CreditCardType</code> that is the type of credit card.
     *
     * To do this it uses the following rules to figure out what possible credit cards are avaible.
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
     * @throws InvalidCardLengthException if there is an unknown credit card number that is less
     * than 12 digits, or more than 19 digits, or if one of the above specific cards have a length other than specified
     * above, the <code>InvalidCardLengthException</code> will be thrown.
     *
     * @throws BadCheckDigitException if there is a mismatch between the calculated code from the digits
     * in the credit card number and the check digit in the last position.
     *
     * @return <code>CreditCardType</code>: the type of credit card. This is an enumeration.
     */
    public CreditCardType getCardType() throws InvalidCardNumberException
    {
        int creditCardLength = creditCardNumber.length();
        int checkDigit = calculateCheckDigit(creditCardNumber);
        if (!(creditCardNumber.substring(creditCardLength - 1).equals(""+checkDigit)))
            throw new BadCheckDigitException();
        String twoDigits = creditCardNumber.substring(0, 2);
        String fourDigits = creditCardNumber.substring(0, 4);
        int twoInt = Integer.parseInt(twoDigits);

        if (creditCardNumber.substring(0, 1).equals("4")) {
            if (creditCardLength == 13 || creditCardLength == 16) {
                type = CreditCardType.VISA;
            } else {
                throw new InvalidCardLengthException();
            }
        } else if (fourDigits.equals("6011") || twoDigits.equals("65")) {
            if (creditCardLength == 16) {
                type = CreditCardType.DISCOVER_CARD;
            } else {
                throw new InvalidCardLengthException();
            }
        } else if (twoInt >= 50 && twoInt <= 55) {  // Checking for the first two digits between "50" and "55"
            if (creditCardLength == 16) {
                type = CreditCardType.MASTERCARD;
            } else {
                throw new InvalidCardLengthException();
            }
        } else if (twoDigits.equals("34") || twoDigits.equals("37")) {
            if (creditCardLength == 15) {
                type = CreditCardType.AMERICAN_EXPRESS;
            } else {
                throw new InvalidCardLengthException();
            }
        } else {
            type = CreditCardType.UNKNOWN;
        }

        return type;
    }

    /**
     * <code>calculateCheckDigit</code> will calculate what the check digit is from the given credit card
     * string. It uses the Luhn algorithm briefly explained here:
     * <p>
     * The Luhn algorithm works by adding the digits starting with the 2nd to last digit and moving left
     * (towards the first digit). Double the value of the 2nd to last and then every 2nd digit from there on.
     * If the doubled value is greater than 9, add those digits. The check digit will be the digit that when
     * added makes the resulting sum a multiple of 10.
     * </p>
     *
     * @param creditCard The credit card number in the form of a <code>String</code>.
     * @return <code>int</code>
     *
     * @see <a href="http://en.wikipedia.org/wiki/Luhn_algorithm">http://en.wikipedia.org/wiki/Luhn_algrothm</a>
     */
    public static int calculateCheckDigit(String creditCard) {
        StringBuilder sb = new StringBuilder(creditCard.replaceAll("[^0-9]", ""));
        char[] digits = sb.reverse().toString().toCharArray();
        int sum = 0;
        for (int i = 1; i < digits.length; i++) {
            int value = Character.getNumericValue(digits[i]);
            if (i%2 != 0) {
                if (value * 2 >= 10) {
                    char[] twoDigits = ("" + value * 2).toCharArray();
                    sum += Character.getNumericValue(twoDigits[0] + Character.getNumericValue(twoDigits[1]));
                } else {
                    sum += value * 2;
                }
            } else {
                sum += value;
            }
        }

        if (sum >= 10) {
            char[] sumDigits = ("" + sum).toCharArray();
            return 10 - Character.getNumericValue(sumDigits[sumDigits.length - 1]);
        }
        return 10 - sum;
    }
}
