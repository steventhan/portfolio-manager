# neu-cs5004-hw5
## Assignment 5: Investing Application
:moneybag: :dollar: :pound: :euro: :yen: :money_with_wings:

### General Functions

We could possibly have a class called *StockDataQuery*. A static member of that class would be the singleton *util.StockDataRetriever*. Members of *StockDataQuery* could be like *String queryString*, *Map<Integer, PriceRecord> priceRecords*, *Integer startDate*, and *Integer endDate*.

* Look up the price of a stock on a certain day
    * Possibly a method *Integer getPriceOnDay()* that throws an exception of date is out of range.
* Determine if there is a buying opportunity for a certain stock on a certain day
    * Pick any day in the range for this query, and determine whether there is a buying opportunity
    * Should there be a default *StockDataQuery* that works using the default investment strategy described in the instructions? Should we then have other classes with different investment strategies extend *StockDataQuery*?
* Get historical (closing) prices for a stock for a certain date range. Historical prices are available only for business days.
* Create a basket of stocks comprising of shares of one or more stocks and then determine its price on a certain date.
    * Oou. How should we do this? Should another member be added to the *StockDataQuery* class? Should the basket (wherever it is) contain just the symbols, or both the symbols and the price records at all times?
* Determine if a stock or a basket trends up during a certain date range.
    * *getTrend()* overloaded for basket parameters and stock parameters
* Try out different investment strategies: periodically investing a fixed amount of money, buying and selling to maintain a specific balance in a basket, etc.
    * Is this a required feature of the program?

### Other Notes

* Queries should work with anything that implements the *StockDataRetriever* interface.

### TODO

- [] Split workload with steventhan
- [] Decide how to do the 5 things
- [] Implement StockDataQuery like Expression from assignment 4, i.e. cannot change query once it is made, you would need to make a new query
- [] Actually query something!
- [] steventhan to look at JUnit documentation about synchronicity
- [X] Make interface from model to controller (IStockDataQuery)
- [] Implement how to implement getPriceOnDay()

### Other Other Notes

* :+1: or :ok_hand: or :bowtie: or :fist: working code
* :recycle: major refactoring
* :black_nib: documentation, style edits, and logistics
* :exclamation: or :warning: or :construction: work in progress, broken or unfinished, untested code
* :thought_balloon: brainstorming or "There may be a better way to do this."
* :trollface: "I like my way better"
* :scream: or :fire: panic mode commits
* :godmode: god mode... self explanatory
* :zzz: signing off for the day

### Cool IntelliJ Shortcuts
sout
pvsm




