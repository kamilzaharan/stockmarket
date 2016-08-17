package pl.lodz.p;

import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;
import pl.lodz.p.controllers.AngularController;
import pl.lodz.p.neuralNetwork.Approximation;
import pl.lodz.p.neuralNetwork.ConfigurationException;
import pl.lodz.p.neuralNetwork.Point;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by kasia on 7/3/16.
 */

public class AngularControllerTest {

    private AngularController ang;

    @Before
    public void createAngularContr() {
        ang = new AngularController();

    }

  /*  @Test
    public void shouldShowApproximation() {

        double numberOfResults = 1000;

        AngularController ang = new AngularController();
        String jsonObject = ang.showApproximation();

        String[] sp = jsonObject.split("},\\{");

        assertThat(numberOfResults).isEqualTo(sp.length);
    }*/

    @Test
    public void shouldGetExchangeRateDate() {
        String date = new Date().toString();

      //  assertThat(date).isEqualTo(ang.getExchangeRateDate());
    }
}
