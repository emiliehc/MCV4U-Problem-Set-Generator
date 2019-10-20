/*
 * The MIT License
 *
 * Copyright 2019 njche.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package mcv4u.problem.set.generator.problems;

import mcv4u.problem.set.generator.Problem;
import VMath.*;
import javax.swing.JOptionPane;

/**
 *
 * @author njche
 */
public class LineAndLine implements Problem {

    private Line l1;
    private Line l2;
    private int type;

    public LineAndLine() {
        /* generate a random type of problem
         * 0: parallel
         * 1: skew
         * 2: intersect
         * 3: coincident
         */
        this.type = (int) (Math.random() * 4);
        System.out.println(type);
        for (;;) {
            Point p = new Point((int) (Math.random() * 20) - 10, (int) (Math.random() * 20) - 10, (int) (Math.random() * 20) - 10);
            Point q = new Point((int) (Math.random() * 20) - 10, (int) (Math.random() * 20) - 10, (int) (Math.random() * 20) - 10);
            Vector m1 = new Vector((int) (Math.random() * 20) - 10, (int) (Math.random() * 20) - 10, (int) (Math.random() * 20) - 10);
            Vector m2 = new Vector((int) (Math.random() * 20) - 10, (int) (Math.random() * 20) - 10, (int) (Math.random() * 20) - 10);
            l1 = new Line(p, m1);
            l2 = new Line(q, m2);
            switch (type) {
                case 0:
                    if (l1.isParallelTo(l2)) {
                        return;
                    }
                    break;
                case 1:
                    if (l1.isSkewWith(l2)) {
                        return;
                    }
                    break;
                case 2:
                    if (l1.intersectsWith(l2)) {
                        return;
                    }
                    break;
                case 3:
                    if (l1.coincidesWith(l2)) {
                        return;
                    }
                    break;
            }
        }
    }

    @Override
    public boolean checkAnswer(int type) {
        if (this.type != type) {
            return false;
        }
        switch (type) {
            case 0:
            case 1:
                // parallel or skew
                double usrAns = Double.parseDouble(JOptionPane.showInputDialog("What is the distance between the two lines?"));
                if (Math.abs(usrAns - l1.getDistanceTo(l2)) < 0.1) {
                    return true;
                }
                return false;
            case 2:
            /*
                // poi
                double x = Double.parseDouble(JOptionPane.showInputDialog("What is the x-value of the POI?"));
                double y = Double.parseDouble(JOptionPane.showInputDialog("What is the y-value of the POI?"));
                double z = Double.parseDouble(JOptionPane.showInputDialog("What is the z-value of the POI?"));
                Point p1 = new Point(x, y, z);
                // if the distancec is between 1, then return true
             */
        }
        return true;
    }

    @Override
    public String toString() {
        return "Consider the following two lines\n" + l1 + "\n" + l2 + "\n"
                + "How do they intersect?\n"
                + "0: parallel\n"
                + "1: skew\n"
                + "2: intersect\n"
                + "3: coincident";
    }
}
