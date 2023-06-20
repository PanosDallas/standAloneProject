package model;

import java.text.ParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

public class DateLabelFormatter extends AbstractFormatter {

	@Override
	public Object stringToValue(String text) throws ParseException {
		// TODO Auto-generated method stub
		return Main.dfrm.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		// TODO Auto-generated method stub
		if(value != null) {
			Calendar cal = (Calendar)value;
			return Main.dfrm.format(cal.getTime());
		}
		return null;
	}

}
