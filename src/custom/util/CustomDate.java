package custom.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class is a thin wrapper of java.util.Date and java.util.Calendar, customized to work with
 * Stock classes.
 */
public class CustomDate implements Comparable<CustomDate> {
  private Date date;
  private Calendar cal;

  /**
   * Constructs a new CustomDate object by providing a String date.
   * Date should be in format of YYYY-MM-DD or YYYYMMDD.
   *
   * @param strDate the date as String.
   */
  public CustomDate(String strDate) {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    df.setLenient(false);
    try {
      this.date = df.parse(strDate);
    } catch (ParseException e) {
      try {
        df.applyPattern("yyyyMMdd");
        this.date = df.parse(strDate);
      } catch (ParseException ex) {
        throw new IllegalArgumentException("Unable to parse date: " + strDate);
      }
    }
    this.cal = Calendar.getInstance();
    this.cal.setTime(this.date);
  }

  /**
   * Constructs a new CustomDate object dated to today date.
   */
  public CustomDate() {
    this(new Date());
  }

  /**
   * Constructs a new CustomDate object by providing a java.util.Date object.
   *
   * @param date the date as Date object.
   */
  public CustomDate(Date date) {
    this.date = date;
    this.cal = Calendar.getInstance();
    this.cal.setTime(this.date);
  }

  /**
   * Gets a new CustomDate object x days before or after this date.
   *
   * @param days number of days before or after.
   * @return new CustomDate object dated x days before or after this date
   */
  public CustomDate getXDaysBeforeOrAfter(int days) {
    Calendar c = Calendar.getInstance();
    c.setTime(this.date);
    c.add(Calendar.DAY_OF_MONTH, days);
    return new CustomDate(c.getTime());
  }

  /**
   * Gets day of the CustomDate object.
   *
   * @return the day as int
   */
  public int getDay() {
    return this.cal.get(Calendar.DAY_OF_MONTH);
  }

  /**
   * Gets month of the CustomDate object.
   *
   * @return the month as int
   */
  public int getMonth() {
    return this.cal.get(Calendar.MONTH) + 1;
  }

  /**
   * Gets month of the CustomDate object.
   *
   * @return the month as int
   */
  public int getYear() {
    return this.cal.get(Calendar.YEAR);
  }

  /**
   * Converts this date to a int type date format of YYYYMMDD
   *
   * @return this date as int.
   */
  public int toKeyInt() {
    return Integer.parseInt(String.format("%04d", this.getYear())
            + String.format("%02d", this.getMonth()) + String.format("%02d", this.getDay()));
  }

  /**
   * Determines if this CustomDate is equal to another Object object.
   *
   * @param d the CustomDate object to be compared to.
   * @return true if they are equal, false otherwise.
   */
  @Override
  public boolean equals(Object d) {
    return d == this || (d instanceof CustomDate && this.sameDate((CustomDate) d));
  }

  /**
   * Determines if this CustomDate is equal to another CustomDate object.
   *
   * @param d the CustomDate object to be compared to.
   * @return true if they are equal, false otherwise.
   */
  public boolean sameDate(CustomDate d) {
    return this.getDay() == d.getDay()
            && this.getMonth() == d.getMonth() && this.getYear() == d.getYear();
  }

  /**
   * Compares 2 CustomDate objects.
   *
   * @param d the other CustomDate object to be compared against
   * @return 0 if they are same date, negative int if the other Date is after this date, positive
   *         int if the other Date is before this date.
   */
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

  /**
   * Gets a String representation of this CustomDate.
   *
   * @return a String representation.
   */
  @Override
  public String toString() {
    return new SimpleDateFormat("yyyy-MM-dd").format(this.date);
  }

  /**
   * Gets a this CustomDate String representation's hashcode.
   *
   * @return a String representation's hashcode as int.
   */
  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }
}

