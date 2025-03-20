import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;


public class TripCostCalculatorTest {
    @Test
    void testCalculateCost() {
        Assertions.assertEquals(40.0, ShoppingCartCalculator.calculateTotalCost(new HashMap<Double, Integer>(){{
            put(1.0, 2);
            put(2.0, 3);
            put(3.0, 4);
            put(4.0, 5);
        }
        }), 0.01);
    }

}
