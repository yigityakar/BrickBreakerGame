package domain.paths;

import java.awt.Point;
import java.awt.geom.Point2D;

public interface EnchantedPath {
	    public Point2D.Double nextPosition();
	    public Point2D.Double currentPosition();
}
