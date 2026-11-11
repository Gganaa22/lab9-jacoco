package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

        // ---- letterGrade ----
    @Test void letterGradeC() { assertEquals("C", calc.letterGrade(75)); }
    @Test void letterGradeD() { assertEquals("D", calc.letterGrade(65)); }
    @Test void letterGradeF() { assertEquals("F", calc.letterGrade(30)); }
    @Test void letterGradeBelowZeroThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1));
    }
    @Test void letterGradeAbove100Throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101));
    }

    // ---- gpa ----
    @Test void gpaNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(null));
    }
    @Test void gpaEmptyThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(new double[]{}));
    }
    @Test void gpaAllGrades() {
        assertEquals(2.0, calc.gpa(new double[]{95, 85, 75, 65, 30}), 0.001);
    }

    // ---- scholarship ----
    @Test void scholarship70() { assertEquals(70, calc.scholarship(3.5, 12, false)); }
    @Test void scholarshipHighGpaLowCredits() { assertEquals(70, calc.scholarship(3.9, 12, false)); }
    @Test void scholarshipMidGpaLowCredits() { assertEquals(50, calc.scholarship(3.5, 10, false)); }
    @Test void scholarshipLowGpaZero() { assertEquals(0, calc.scholarship(2.5, 16, false)); }
    @Test void scholarshipViolationZero() { assertEquals(0, calc.scholarship(4.0, 20, true)); }
    @Test void scholarshipGpaNegativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(-0.1, 10, false));
    }
    @Test void scholarshipGpaAbove4Throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(4.1, 10, false));
    }
    @Test void scholarshipCreditsNegativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(3.0, -1, false));
    }

        // ---- Part C: loop 0 / 1 / 2+ iterations ----
    // 0 iterations: gpaNullThrows, gpaEmptyThrows (Part B)
    @Test void gpaSingleGrade() { assertEquals(4.0, calc.gpa(new double[]{95}), 0.001); }
    @Test void gpaManyGrades() { assertEquals(2.5, calc.gpa(new double[]{95, 85, 75, 65}), 0.001); }


        // ---- Part D: boundary values (spec: 60-69.99 is D) ----
    @ParameterizedTest(name = "score {0} -> {1}")
    @CsvSource({"0,F", "59.99,F", "60,D", "69.99,D", "89.99,B", "90,A", "100,A"})
    void letterGradeBoundaries(double score, String expected) {
        assertEquals(expected, calc.letterGrade(score));
    }
}
