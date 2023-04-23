package app.core.domain.employee.grade;

import java.io.Serializable;

public enum EmployeeGrade implements Serializable {
    JUNIOR(0),
    MIDDLE(15),
    SENIOR(30);

    private final int additionalGradePercent;

    EmployeeGrade(int additionalGradePercent) {
        this.additionalGradePercent = additionalGradePercent;
    }

    public int getAdditionalGradePercent() {
        return additionalGradePercent;
    }
}
