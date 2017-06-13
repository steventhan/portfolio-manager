import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class is a thin wrapper of java.util.Date, customized to work with StockSingle class.
 */
public class CustomDate implements Comparable<CustomDate> {
  private Date date;
  private Calendar cal;

  public CustomDate(String strDate) {
    try {
      this.date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
    } catch (Exception e) {
      throw new IllegalArgumentException("Unable to parse date: " + strDate);
    }
    this.cal = Calendar.getInstance();
    this.cal.setTime(this.date);
  }

  public CustomDate() {
    this.date = new Date();
    this.cal = Calendar.getInstance();
    this.cal.setTime(this.date);
  }

  public int getDay() {
    return this.cal.get(Calendar.DAY_OF_MONTH);
  }

  public int getMonth() {
    return this.cal.get(Calendar.MONTH) + 1;
  }

  public int getYear() {
    return this.cal.get(Calendar.YEAR);
  }

  public boolean equals(CustomDate d) {
    return this.getDay() == d.getDay()
            && this.getMonth() == d.getMonth() && this.getYear() == d.getYear();
  }

  @Override
  public int compareTo(CustomDate d) {
    if (this.getYear() != d.getYear()) {
      return this.getYear() - d.getYear();
    } else if (this.getMonth() != d.getMonth()) {
      return this.getMonth() - d.getMonth();
    } else if (this.getDay() != d.getDay()) {
      return this.getDay() - d.getDay();
    }
    return 0;
  }

  public int toKeyInt() {
    return Integer.parseInt(this.toString());
  }

  @Override
  public boolean equals(Object d) {
    return d == this || (d instanceof CustomDate && this.equals((CustomDate) d));
  }

  @Override
  public String toString() {
    return String.format("%04d", this.getYear())
            + String.format("%02d", this.getMonth()) + String.format("%02d", this.getDay());
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }
}

