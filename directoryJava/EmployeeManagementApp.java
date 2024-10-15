import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class EmployeeManagementApp extends JFrame {
    private DefaultListModel<Employee> employeeListModel;
    private JList<Employee> employeeList;
    private JButton addButton;
    private EmployeeTeam team;

    public EmployeeManagementApp() {
        super("Employee Management");

        employeeListModel = new DefaultListModel<>();
        employeeList = new JList<>(employeeListModel);
        employeeList.setCellRenderer(new EmployeeCellRenderer());

        addButton = new JButton("ADD");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee selectedEmployee = employeeList.getSelectedValue();
                if (selectedEmployee != null) {
                    team.addEmployee(selectedEmployee);
                    employeeListModel.removeElement(selectedEmployee);
                    updateButtonStates();
                }
            }
        });

        team = new EmployeeTeam();

        JPanel employeePanel = new JPanel(new BorderLayout());
        employeePanel.add(new JScrollPane(employeeList), BorderLayout.CENTER);
        employeePanel.add(addButton, BorderLayout.SOUTH);

        JPanel teamPanel = new JPanel(new BorderLayout());
        teamPanel.add(new JScrollPane(new JList<>(team.getEmployees())), BorderLayout.CENTER);
        teamPanel.add(new JButton("REMOVE") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee selectedEmployee = team.getEmployees().get(teamPanel.getComponent(0).getSelectedIndex());
                if (selectedEmployee != null) {
                    team.removeEmployee(selectedEmployee);
                    employeeListModel.addElement(selectedEmployee);
                    updateButtonStates();
                }
            }
        }, BorderLayout.SOUTH);
        teamPanel.add(new JLabel("Average Age: " + team.getAverageAge()), BorderLayout.NORTH);
        teamPanel.add(new JButton("SORT BY AGE") {
            @Override
            public void actionPerformed(ActionEvent e) {
                team.sortByAge();
                teamPanel.getComponent(0).repaint();
            }
        }, BorderLayout.WEST);

        getContentPane().setLayout(new GridLayout(1, 2));
        getContentPane().add(employeePanel);
        getContentPane().add(teamPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);

        // Sample employee list
        setEmployees(Arrays.asList(
                new Employee("John Doe", 30),
                new Employee("Jane Smith", 25),
                new Employee("Alice Johnson", 35),
                new Employee("Bob Williams", 28)
        ));
    }

    private void setEmployees(List<Employee> employees) {
        employeeListModel.clear();
        employeeListModel.addAll(employees);
    }

    private void updateButtonStates() {
        for (int i = 0; i < employeeListModel.size(); i++) {
            Employee employee = employeeListModel.getElementAt(i);
            employee.setAvailable(true);
        }

        for (Employee employee : team.getEmployees()) {
            employee.setAvailable(false);
        }

        employeeList.repaint();
    }

    public static void main(String[] args) {
        new EmployeeManagementApp();
    }
}

class Employee {
    private String name;
    private int age;
    private boolean available;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
        this.available = true;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class EmployeeTeam {
    private List<Employee> employees;

    public EmployeeTeam() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public double getAverageAge() {
        if (employees.isEmpty()) {
            return 0;
        }

        int totalAge = 0;
        for (Employee employee : employees) {
            totalAge += employee.getAge();
        }

        return (double) totalAge / employees.size();
    }

    public void sortByAge() {
        Collections.sort(employees, Comparator.comparingInt(Employee::getAge));
    }
}

class EmployeeCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof Employee) {
            Employee employee = (Employee) value;
            if (!employee.isAvailable()) {
                setForeground(Color.GRAY);
            } else {
                setForeground(Color.BLACK);
            }
        }

        return component;
    }
}