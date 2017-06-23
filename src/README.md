//TODO update this readme per the instructions
//TODO update TraderModelImpl to only keep track of necessary data
//TODO update application to be simultaneously plot different stock baskets with different ranges

//todo test view's horizontal and vertical scaling
//todo document and review all public methods
//todo make sure exceptions are handled

    // create a rotated transform and draw x-axis values
    Graphics2D gTransform = (Graphics2D) g;
    Font font = new Font(null, Font.PLAIN, 10);
    AffineTransform affineTransform = new AffineTransform();
    affineTransform.rotate(Math.toRadians(270), 0, 0);
    Font rotatedFont = font.deriveFont(affineTransform);
    gTransform.setFont(rotatedFont);

