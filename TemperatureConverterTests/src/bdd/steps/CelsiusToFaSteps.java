package bdd.steps;

import org.givwenzen.annotations.DomainStep;
import org.givwenzen.annotations.DomainSteps;

import com.example.xw.TC.TemperatureConverter;
@DomainSteps
public class CelsiusToFaSteps {
	
	private static final String CELSIUS="Celsius";
	private static final String FAHRENHEIT="Fahrenheit";
	private static final String ANY_TEMPERATURE="([-+]?\\d+(?:\\.\\d+)?)";
	private static final String UNIT="(C|F)";
	private static final String UNIT_NAME="("+CELSIUS+"|"+FAHRENHEIT+")";
	private static final double DELTA=0.01d;
	private double mValue=Double.NaN;
	
	@DomainStep("I'm using the CelsiusToFa")
	public void createTemperatureConverter(){
		System.out.println("I'm here");
	}
	
	@DomainStep("I enter "+ANY_TEMPERATURE+" into "+UNIT_NAME+" field")
	public void setField(double value,String unitName){
		mValue=value;
	}
	
	@DomainStep("I obtain "+ANY_TEMPERATURE+" in "+UNIT_NAME+" field")
	public boolean verifyConversion(double value,String unitName){
		try{
			final double t=(FAHRENHEIT.compareTo(unitName)==0?getFahrenheit():getCelsius());
			return Math.abs(t-value)<DELTA;
		}catch(RuntimeException e){
			return false;
		}
	}
	
	@DomainStep("Celsius")
	public double getCelsius(){
		return TemperatureConverter.fahrenheiToCelsius(mValue);
	}
	@DomainStep("Fahrenheit")
	public double getFahrenheit(){
		return TemperatureConverter.celsiusToFahrenhei(mValue);
	}
	
	@DomainStep("I obtain '(Invalid temperature: " + ANY_TEMPERATURE 
			+ UNIT + " below absolute zero)' exception")
			public boolean verifyException(String message, 
			String value, String unit) {
			try {
			if ( "C".compareTo(unit) == 0 ) {
			getFahrenheit();
			}
			else {
			getCelsius();
			}
			}
			catch (RuntimeException ex) {
			return ex.getMessage().contains(message);
			}
			return false;
	}
	
}
