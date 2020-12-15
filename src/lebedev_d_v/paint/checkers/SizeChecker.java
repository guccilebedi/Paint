package lebedev_d_v.paint.checkers;

import java.awt.*;

public class SizeChecker {

    public static FigureSize checkSize(Point p1, Point p2) {
        if (p2.x > p1.x) {
            if (p2.y > p1.y) {
                return FigureSize.P2_IS_FURTHER_AND_HIGHER;
            } else {
                return FigureSize.P2_IS_FURTHER_NOT_HIGHER;
            }
        } else {
            if (p2.y > p1.y) {
                return FigureSize.P2_IS_NOT_FURTHER_HIGHER;
            } else {
                return FigureSize.P2_IS_NOT_FURTHER_NOT_HIGHER;
            }
        }
    }
}
