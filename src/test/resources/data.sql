INSERT INTO TRANSACTION
(`USER_ID`, `CURRENCY_FROM`, `CURRENCY_TO`, `AMOUNT_SELL`, `AMOUNT_BUY`, `RATE`, `TIME_PLACED`, `ORIGINATING_COUNTRY`)
values
(1, 'EUR', 'GBP', 10000.0, 7000.0, 0.7, PARSEDATETIME ('24-JAN-15 10:27:44', 'dd-MMM-yy HH:mm:ss'), 'FR');