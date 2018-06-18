## Features
* create two views and a controller for your stock model
* each view should still emanate from a common interface
* put all your view classes in the view.trader package,
* put all your model classes in the model.trader package
* and your model code from the previous assignment in the model.trader package
* do not move the util package classes

* print a menu of options
    * allow the user to choose an option
    * provide additional data depending on the chosen option
    * process the input
    * display the results, if any
    * reprint the menu

* *the menu options:*
    * Create a new basket and give it a specific name.
    * Add shares of stocks to an existing basket using its ticker symbol.
    * Print the contents and value of an existing basket.
    * Ask for the trend of a particular stock within a specific date range.
    * Quit the program

* test the controller by sending it inputs then checking that the correct data is sent to the view
* test the view by looking at it verifying that it prints what you expect it to

* more menu options for the graphical view:
    * create a blank graphical view
    * remove a plot
    * plot the closing prices of one or more stocks/baskets simultaneously
    * plot the 50-day and/or 200-day averages of one or more stocks/baskets with the plot of their closing prices

* each plot should have a different color
* NOTE: &quot;plots of the prices vs. dates in a specified date range&quot;
* there should be a legend showing the meaning of each color
    * stock ticker symbol or basket name
    * closing prices, or number of day average
* changes to what the graphical view shows should cause it to automatically refresh the plots

## TODO List
* TODO document and review all public methods
* todo make sure exceptions are handled correctly
* todo if time permits draw an x-axis
* todo if time permits update tests for model classes

    <pre>// create a rotated transform and draw x-axis values
    Graphics2D gTransform = (Graphics2D) g;
    Font font = new Font(null, Font.PLAIN, 10);
    AffineTransform affineTransform = new AffineTransform();
    affineTransform.rotate(Math.toRadians(270), 0, 0);
    Font rotatedFont = font.deriveFont(affineTransform);
    gTransform.setFont(rotatedFont);</pre>

