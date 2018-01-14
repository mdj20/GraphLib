package testutilities;

import java.math.BigDecimal;
import java.math.BigInteger;

public class OverloadNumberTest {

	
	public static <V extends Number> V overLoadTest(V v1 , V v2){
		V ret = null;
	//	ret = v1.intValue()+v2.intValue();
		
		return null;
	}
	
	public static void main(String args[]) {
		Integer i = 1, j=4, k = -1;
		Double d1= 1.43,d2= 7.85, d3 = null;
		k = overloadAdd(i,j);
		d3 = overloadAdd(d1,d2);
		System.out.println(k);
		System.out.println(d3);
		
		BigInteger big1 = BigInteger.valueOf(5000);
		BigInteger big2 = BigInteger.valueOf(1500);
		BigInteger big3 = overloadAdd(big1,big2);
		System.out.println("BIG3 "+overloadAdd(big1,big2)+" "+big3);
		
		Byte byte1 = -4;
		Byte byte2 = 7;
		Byte byte3 = overloadAdd(byte1,byte2);
		System.out.println(byte3);

		System.out.println(i.shortValue()+j.shortValue());
		
		BigDecimal bd1 = new BigDecimal(big1);
		BigDecimal bd2 = new BigDecimal(big2);
		BigDecimal bd3 =  overloadAdd(bd1,bd2);
		System.out.println("HERE "+bd1.add(bd2)+" "+bd3);
		
	}
	
	
	@SuppressWarnings("unchecked")
	private static <V extends Number> V overloadAdd(V v1, V v2) {
		if(!v1.getClass().equals(v2.getClass())) {
			throw new IllegalArgumentException("args must be same class type");
		}
		if(v1.getClass().equals(Integer.class)) {
			Integer i = v1.intValue()+v2.intValue();
			return (V)i;
		}
		else if(v1.getClass().equals(Double.class)) {
			Double d = v1.doubleValue()+v2.doubleValue();
			return (V)d;
		}
		else if(v1.getClass().equals(Float.class)) {
			Float d = v1.floatValue()+v2.floatValue();
			return (V)d;
		}
		else if (v1.getClass().equals(Long.class)) {
			Long d = v1.longValue()+v2.longValue();
			return (V)d;
		}
		else if (v1.getClass().equals(Short.class)) {
			Short d = (short) (v1.shortValue()+v2.shortValue());
			return (V)d;
		}
		else if (v1.getClass().equals(Byte.class)) {
			Byte d =  (byte) ((byte) v1.byteValue()+v2.byteValue());
			return (V)d;
		}
		else if (v1.getClass().equals(BigInteger.class)) {
			BigInteger b1 = (BigInteger) v1;
			BigInteger b2 = (BigInteger) v2;
			BigInteger d = b1.add(b2);
			return (V)d;
		}
		else if (v1.getClass().equals(BigDecimal.class)) {
			throw new IllegalArgumentException("BigDecimal Type not supported");
		}
		else {
			return null;
		}
	}
	
	
}
