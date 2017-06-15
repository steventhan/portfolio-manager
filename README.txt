# README.txt

_This file explains some of the design choices made in our design._

## Adhering to the O in SOLID

The provided files did not strictly implement the singleton design pattern for WebStockDatRetriever. In order to implement a true singleton, our team created WebRetrieverSingleton. Also, in order to prevent the use of the old web retriever in our design, we created a new interface, NewStockRetriever, that extends StockDataRetriever. Anything that implements the new interface may be used in our design. It is our intention that everything that implements the new interface will be a true singleton. This approach was taken as to not alter the code provided to us.

To avoid potential pitfalls parsing dates ourselves, we opted to go with the standard java library utilities for date and time.

To handle conditions in which a stock price cannot be retrieved from the server, the StockPriceNotFound has been created.
