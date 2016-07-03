package pl.lodz.p.neuralNetwork;

import java.lang.Exception;

/**
 * Created by kasia on 7/3/16.
 */
public class ConfigurationException extends Exception{
    public ConfigurationException() {
        super();
    }

    public ConfigurationException(String message) {
        super(message);
    }
}
