package com.integrallis

import spock.lang.Specification

// Run by : grails test-app unit:spock
class CreditCardTypeConverterSpec extends Specification {
    def "test credit cards"() {
        expect :
          CreditCardTypeConverter.determineCCType(number) == type

        where:
          number  | type
          "4111111111111111" | "Visa"
          "3713XXXXXXX1013"  | "American Express"
          "5555555555554444" | "Master Card"
          "378734493671000"  | "American Express"
          "378282246310005"  | "American Express"
          "371449635398431"  | "American Express"
    }

}