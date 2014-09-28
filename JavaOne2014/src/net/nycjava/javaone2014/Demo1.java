package net.nycjava.javaone2014;

import static java.lang.String.format;

/**
 * Demonstrates:
 * - default methods
 */
public class Demo1 {

    private static interface WorkerDetailsService {
        String getFirstName(String employeeId);
        String getLastName(String employeeId);

        // This is the *old* way of evolving an interface
        // and would require that all implementors of the
        // interface add the new method

//        String getIntials(String employeeId);

        // This is the *new* way of evolving an interface
        // and does *not* require that all implementors of
        // the interface add the new method

        default String getInitials(String employeeId) {
            return getFirstName(employeeId).substring(0,1) +
                    getLastName(employeeId).substring(0,1);
        }
    }

    private static class EmployeeDetailsService
            implements WorkerDetailsService {
        @Override
        public String getFirstName(String employeeId) {
            return "Joe";
        }
        @Override
        public String getLastName(String employeeId) {
            return "Johnson";
        }
    }

    private static class ContractorDetailsService
            implements WorkerDetailsService {
        @Override
        public String getFirstName(String employeeId) {
            return "Sarah";
        }
        @Override
        public String getLastName(String employeeId) {
            return "Smith";
        }
    }

    public static void main(String argv[]) {
        String employeeId = "E1";

        WorkerDetailsService service =
                new EmployeeDetailsService();

        System.out.println(format(
                "Name of employee %s is %s %s",
                employeeId,
                service.getFirstName(employeeId),
                service.getLastName(employeeId)));

        System.out.println(
                format(
                        "Initials of employee %s are %s",
                        employeeId,
                        service.getInitials(employeeId)));
    }
}
