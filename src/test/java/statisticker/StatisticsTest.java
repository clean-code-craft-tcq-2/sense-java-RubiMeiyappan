package statisticker;

import static org.junit.Assert.*;
//import jdk.nashorn.internal.AssertsEnabled;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import statisticker.Statistics;
public class StatisticsTest 
{public int check = 0;
    @Test
    public void reportsAverageMinMaxx()
    {
        Float[] numbers = {1.5f, 8.9f, 3.2f, 4.5f};
        List<Float> numberList = Arrays.asList(numbers);

        Statistics s = Statistics.getStatistics(numberList);
        
        float epsilon = 0.001f;
        assertEquals(s.average, 4.525f, epsilon);
        assertEquals(s.min, 1.5f, epsilon);
        assertEquals(s.max, 8.9f, epsilon);
    }
    @Test
    public void reportsNaNForEmptyInput()
    {
        List<Float> emptyList = new ArrayList<>();

        Statistics s = Statistics.getStatistics(emptyList);
        
        Float NaN = 0f / 0f;
        assertEquals(s.empty_NAN_value, NaN);
        //All fields of computedStats (average, max, min) must be
        //Float.NaN (not-a-number), as described in
        //https://www.geeksforgeeks.org/nan-not-number-java/
        //Design the asserts here and implement accordingly.
    }
    @Test
    public void reportsAlertsIfMaxIsMoreThanThreshold()
    {
        float maxThreshold = 10.2f;
        StatsChecker checker = new StatsChecker(maxThreshold);

        Float[] numbers = {11.5f, 6.9f, 7.5f, 6.6f};
        List<Float> numberList = Arrays.asList(numbers);
        checker.checkAndAlert(numberList);
        if (check==1)
        {
            EmailAlert emailAlerter = new EmailAlert();
            LEDAlert ledAlerter = new LEDAlert();
            assertTrue(emailAlerter.emailSent);
            assertTrue(ledAlerter.ledGlows);
        }
    }

    /**
     * Test of getStatistics method, of class Statistics.
     */
    @org.junit.Test
    public void testGetStatistics() {
    }
    public static class StatsChecker extends StatisticsTest 
    {
        public float maxThreshold;
        public StatsChecker(float maxThreshold)
        {
            this.maxThreshold = maxThreshold;
        }
        public void checkAndAlert(List<Float> numbers) 
        {
           for(int iterator = 0 ; iterator < numbers.size() ; iterator++)
           {
               if(numbers.get(iterator) > maxThreshold)
               {
                    check = 1;
               }
           }
        }
    }

    private static class LEDAlert 
    {

        private boolean ledGlows;
        public LEDAlert() 
        {
            ledGlows = true;
        }
    }

    private static class EmailAlert 
    {

        private boolean emailSent;
        public EmailAlert() 
        {
            emailSent = true;
        }
    }
}