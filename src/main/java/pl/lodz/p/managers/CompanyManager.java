package pl.lodz.p.managers;

import pl.lodz.p.model.Company;
import pl.lodz.p.neuralNetwork.Point;

import java.util.List;

/**
 * Created by Kaltair on 2016-08-09.
 */
public interface CompanyManager {

     void addCompany (Company company);
     List<Point> findMaxIncrease();
     List<Point> findMaxDecrease();
}
