package statisticker;

import java.util.HashMap;
import java.util.List;

public class Statistics 
{static Float average,min,max,empty_NAN_value;
    public static Statistics getStatistics(List<Float> numbers) 
    {
        //implement the computation of statistics here
        float sum = (float) 0.0;
        min = numbers.get(0);
        max = numbers.get(0);
        boolean check_empty_array = numbers.isEmpty();
        if (check_empty_array == true)
        {
            empty_NAN_value = 0f / 0f;
        }
        else
        {
            for(int iterator = 0 ; iterator < numbers.size() ; iterator++)
            {
                sum = sum + numbers.get(iterator);
                if(numbers.get(iterator) < min)
                {
                    min = numbers.get(iterator);
                }
                if(numbers.get(iterator) > max)
                {
                    max = numbers.get(iterator);
                }
            }
            average = sum/2;
        }
        return null;
    }
    
}

