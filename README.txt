# README.txt

_This file explains some of the design choices made in our design._

## Adhering to the O in SOLID

The provided files did not strictly implement the singleton design pattern for WebStockDatRetriever. In order to implement a true singleton, our team created WebRetrieverSingleton. Also, in order to prevent the use of the old web retriever in our design, we created a new interface, NewStockRetriever, that extends StockDataRetriever. Anything that implements the new interface may be used in our design. It is our intention that everything that implements the new interface will be a true singleton. This approach was taken as to not alter the code provided to us.