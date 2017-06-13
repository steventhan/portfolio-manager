import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * JUnit tests for CustomDate.
 */
public class CustomDateTest {
  private CustomDate date1;
  private CustomDate date2;
  private CustomDate date3;
  private CustomDate date4;
  private CustomDate date5;
  private CustomDate date6;

  /**
   * Creates CustomDate objects for testing
   */
  @Before
  public void setUp() throws Exception {
    this.date1 = new CustomDate("2000-01-01");
    this.date2 = new CustomDate("2005-01-01");
    this.date3 = new CustomDate("2000-02-01");
    this.date4 = new CustomDate("2000-01-17");
    this.date5 = new CustomDate("2007-12-22");
    this.date6 = new CustomDate("2016-02-29");
  }

  @Test
  public void testGetYear() throws Exception {
    Assert.assertEquals(2000, this.date1.getYear());
    Assert.assertEquals(2005, this.date2.getYear());
    Assert.assertEquals(2000, this.date3.getYear());
    Assert.assertEquals(2000, this.date4.getYear());
    Assert.assertEquals(2007, this.date5.getYear());
  }

  @Test
  public void testGetMonth() throws Exception {
    Assert.assertEquals(01, this.date1.getMonth());
    Assert.assertEquals(01, this.date2.getMonth());
    Assert.assertEquals(02, this.date3.getMonth());
    Assert.assertEquals(01, this.date4.getMonth());
    Assert.assertEquals(12, this.date5.getMonth());
  }

  @Test
  public void testGetDay() throws Exception {
    Assert.assertEquals(01, this.date1.getDay());
    Assert.assertEquals(01, this.date2.getDay());
    Assert.assertEquals(01, this.date3.getDay());
    Assert.assertEquals(17, this.date4.getDay());
    Assert.assertEquals(22, this.date5.getDay());
  }

  @Test
  public void testEquals() throws Exception {
    Assert.assertTrue(this.date1.equals(new CustomDate("2000-01-01")));
    Assert.assertTrue(this.date3.equals(new CustomDate("2000-02-01")));
    Assert.assertTrue(this.date5.equals(new CustomDate("2007-12-22")));

    Assert.assertFalse(this.date2.equals(new CustomDate("1999-01-01")));
    Assert.assertFalse(this.date3.equals(new CustomDate("2000-07-01")));
    Assert.assertFalse(this.date4.equals(new CustomDate("2000-01-22")));
  }

  @Test
  public void testCompareTo() throws Exception {
    Assert.assertEquals(0, this.date1.compareTo(new CustomDate("2000-01-01")));
    Assert.assertEquals(0, this.date4.compareTo(new CustomDate("2000-01-17")));

    Assert.assertTrue(this.date1.compareTo(new CustomDate("1999-12-30")) > 0);
    Assert.assertTrue(this.date1.compareTo(new CustomDate("2001-01-01")) < 0);
    Assert.assertTrue(this.date1.compareTo(new CustomDate("2000-02-01")) < 0);
    Assert.assertTrue(this.date1.compareTo(new CustomDate("2000-01-02")) < 0);
    Assert.assertTrue(this.date5.compareTo(new CustomDate("2007-11-02")) > 0);
  }

  @Test
  public void testToKeyInt() throws Exception {
    Assert.assertEquals(20000101, this.date1.toKeyInt());
    Assert.assertEquals(20050101, this.date2.toKeyInt());
    Assert.assertEquals(20000201, this.date3.toKeyInt());
    Assert.assertEquals(20000117, this.date4.toKeyInt());
    Assert.assertEquals(20071222, this.date5.toKeyInt());
  }

  @Test
  public void testGetXDaysAgo() throws Exception {
    Assert.assertEquals("1999-11-12", this.date1.getXDaysBeforeOrAfter(-50).toString());
    Assert.assertEquals("2004-06-15", this.date2.getXDaysBeforeOrAfter(-200).toString());
    Assert.assertEquals("2007-02-25", this.date5.getXDaysBeforeOrAfter(-300).toString());
    Assert.assertEquals("2016-02-19", this.date6.getXDaysBeforeOrAfter(-10).toString());
  }

}