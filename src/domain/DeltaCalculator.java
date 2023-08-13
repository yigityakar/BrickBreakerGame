package domain;

public class DeltaCalculator {
	
	
	public DeltaCalculator() {
		
	}
	
	
	
	
public  double Tan(double DeltaX, double DeltaY, int count) {
		
		double degree = Math.PI/180 *count;
		double converted = Math.toDegrees(degree);
		double tan = DeltaY/DeltaX;
		double res = Math.toDegrees(Math.atan(tan));
		return 90-(converted+res);
	}

public double getDeltaX(double DeltaX, double DeltaY, int count) {
	double result = Tan(DeltaX,DeltaY,count);
	
	
		
		return (1*Math.cos(result));
	
	
	
}

public double getDeltaY(double DeltaX, double DeltaY, int count) {
	double result = Tan(DeltaX,DeltaY,count);
	return (1*Math.sin(result));
}

}
