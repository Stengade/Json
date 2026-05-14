package json;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Json {
	public static String stringfy(Object obj) {
		if(obj == null)return "null";
		if(obj instanceof String)return '"'+obj.toString()+'"';
		if(isNumber(obj))return obj.toString();
		if(obj instanceof Boolean)return (boolean)obj ? "true" : "false";
		
		StringBuilder builder = new StringBuilder();
		
		if(obj instanceof Object[]) {
			builder.append('[');
			for(Object current : (Object[])obj) {
				if(builder.length() > 1)builder.append(',');
				builder.append(stringfy(current));
			}
			builder.append(']');
			return builder.toString();
		}
		
		Class<?> c = obj.getClass();
		builder.append("{");
		while(c != null) {
			for(Field field : c.getDeclaredFields()) {
				if(builder.length() > 1)builder.append(",");
				try {
					builder.append('"').append(field.getName()).append('"').append(':').append(stringfy(field.get(obj)));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			
			c = c.getSuperclass();
		}
		builder.append("}");
		return builder.toString();
	}
	
	private static boolean isNumber(Object obj) {
		return obj instanceof Integer || obj instanceof Double || obj instanceof Long;
	}
}
