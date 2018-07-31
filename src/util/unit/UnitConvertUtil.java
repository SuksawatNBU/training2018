package util.unit;

public class UnitConvertUtil {

	public static final int ONE_MINUTE_OF_SECOND = 60;

	public static int convertMinuteToSecond(int minute) {
		return minute * ONE_MINUTE_OF_SECOND;
	}

	public static double convertSecondToMinute(int second) {
		return (double)second / (double)ONE_MINUTE_OF_SECOND;
	}
}
