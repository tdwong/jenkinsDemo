package com.integrallis

import org.apache.commons.lang.StringUtils

/**
 * This is used to determine the type for a credit card number.
 *
 * Code based off of this URL http://cuinl.tripod.com/Tips/o-1.htm
 */
class CreditCardTypeConverter {

  def static final String UNKNOWN = "Unknown"
  def static final String AMEX = "American Express"
  def static final String DINERS = "Diners Club"
  def static final String BLANCHE = "Carte Blanche"
  def static final String ENROUTE = "EnRoute"
  def static final String JCB = "JCB"
  def static final String DISCOVER = "Discover"
  def static final String MASTER = "Master Card"
  def static final String VISA = "Visa"
  def static final String AMER_DINERS = "American Diners Club"

  public static def determineCCType(String ccNumber) {
    // In case nothing is found
    def creditCardType = UNKNOWN

    //Remove all spaces and dashes from the passed string
    def cardNo = StringUtils.strip(ccNumber, "-.")

    // 'Check that the minimum length of the string isn't less
    // 'than fourteen characters and -is- numeric
    if (cardNo?.length() >= 14) {
      // now lets check the digits
      switch(cardNo.substring(0,2)) {
        case ['34', '37'] :
          creditCardType = AMEX
          break
        case '36':
          creditCardType = DINERS
          break
        case '38':
          creditCardType = BLANCHE
          break
        case ['51','52','53','54','55']:
          creditCardType = MASTER
          break
        default :
          switch(cardNo.substring(0,4)) {
            case ['2014', '2149'] :
              creditCardType = ENROUTE
              break
            case ['2131', '1800'] :
              creditCardType = JCB
              break
            case '6011' :
               creditCardType = DISCOVER
               break
            default :
              switch(cardNo.substring(0,3)) {
                case ['300', '301', '302', '303', '304', '305'] :
                  creditCardType = AMER_DINERS
                  break
                default :
                  switch(cardNo.substring(0,1)) {
                    case '3' :
                      creditCardType = MASTER
                      break
                    case '4' :
                      creditCardType = VISA
                      break
                  }
              }
          }
      }
    }

    return creditCardType
  }
}