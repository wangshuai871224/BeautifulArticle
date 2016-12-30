package com.example.dllo.hodgepodge.mine.gobang;

import android.graphics.Point;
import android.util.Log;

import java.util.List;

/**
 * 庭
 * Created by Ting on 16/12/29.
 */
public class GoBangUtil {
    // 每行上的最大数目
    public static final int MAX_COUNT_IN_LINE = 5;

    /**
     * 检查是否五子连珠
     *
     * @param points
     * @return
     */
    public static boolean checkFiveInLine(List<Point> points) {
        for (Point point : points) {
            int x = point.x;
            int y = point.y;
            boolean win = checkHorizontal(x, y, points) || checkVertical(x, y, points) ||
                    checkLeftDiagonal(x, y, points) || checkRightDiagonal(x, y, points);
            if (win) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断x, y位置的棋子是否横向五个一致
     *
     * @param x
     * @param y
     * @param points
     * @return
     */
    private static boolean checkHorizontal(int x, int y, List<Point> points) {
        int count = 0;
        // 此x 为最右侧
        for (int i = 0; i < MAX_COUNT_IN_LINE; i++) {
            if (points.contains(new Point(x - i, y))) {
                count++;
                Log.d("GoBangUtil", "横count:" + count);
            } else {
                break;
            }
        }

        if (count == MAX_COUNT_IN_LINE) {
            return true;
        }
        count = 0;
        for (int i = 0; i < MAX_COUNT_IN_LINE; i++) {
            if (points.contains(new Point(x + i, y))) {
                count++;
                Log.d("GoBangUtil", "横count:" + count);
            } else {
                break;
            }
        }

        if (count == MAX_COUNT_IN_LINE) {
            return true;
        }
        return false;
    }

    /**
     * 判断x, y位置的棋子是否竖直方向五个一致
     *
     * @param x
     * @param y
     * @param points
     * @return
     */
    private static boolean checkVertical(int x, int y, List<Point> points) {
        int count = 0;
        // 此x 为最右侧
        for (int i = 0; i < MAX_COUNT_IN_LINE; i++) {
            if (points.contains(new Point(x, y - i))) {
                count++;
            } else {
                break;
            }
        }

        if (count == MAX_COUNT_IN_LINE) {
            return true;
        }
        count = 0;
        for (int i = 0; i < MAX_COUNT_IN_LINE; i++) {
            if (points.contains(new Point(x, y + i))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_COUNT_IN_LINE) {
            return true;
        }
        return false;
    }

    /**
     * 判断左斜方是否在一条直线
     *
     * @param x
     * @param y
     * @param points
     * @return
     */
    private static boolean checkLeftDiagonal(int x, int y, List<Point> points) {
        int count = 0;
        // 此x 为最右侧
        for (int i = 0; i < MAX_COUNT_IN_LINE; i++) {
            if (points.contains(new Point(x - i, y + i))) {
                count++;
            } else {
                break;
            }
        }

        if (count == MAX_COUNT_IN_LINE) {
            return true;
        }
        count = 0;
        for (int i = 0; i < MAX_COUNT_IN_LINE; i++) {
            if (points.contains(new Point(x + i, y - i))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_COUNT_IN_LINE) {
            return true;
        }
        return false;
    }


    /**
     * 判断右斜方是否在一条直线
     *
     * @param x
     * @param y
     * @param points
     * @return
     */
    private static boolean checkRightDiagonal(int x, int y, List<Point> points) {
        int count = 0;
        // 此x 为最右侧
        for (int i = 0; i < MAX_COUNT_IN_LINE; i++) {
            if (points.contains(new Point(x + i, y + i))) {
                count++;
            } else {
                break;
            }
        }

        if (count == MAX_COUNT_IN_LINE) {
            return true;
        }
        count = 0;
        for (int i = 0; i < MAX_COUNT_IN_LINE; i++) {
            if (points.contains(new Point(x - i, y - i))) {
                count++;
            } else {
                break;
            }
        }
        if (count == MAX_COUNT_IN_LINE) {
            return true;
        }
        return false;
    }
}
