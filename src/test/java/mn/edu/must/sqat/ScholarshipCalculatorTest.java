package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calc = new ScholarshipCalculator();

    @Test
    void letterGradeA() { assertEquals("A", calc.letterGrade(95)); }

    @Test
    void letterGradeB() { assertEquals("B", calc.letterGrade(85)); }

    @Test
    void gpaTwoGrades() { assertEquals(3.5, calc.gpa(new double[]{95, 85}), 0.001); }

    @Test
    void scholarship100() { assertEquals(100, calc.scholarship(3.9, 16, false)); }

    @Test
    void scholarship50() { assertEquals(50, calc.scholarship(3.1, 10, false)); }
}
