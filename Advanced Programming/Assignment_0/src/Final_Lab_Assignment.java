import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class Patient {

    String name;
    int age;
    char tower;
    LocalDate report_date;
    LocalDate rec_Date;
    ArrayList<Patient> p;

    Patient() {
        p = new ArrayList<>(20);
    }

    Patient(String name, int age, char tower, String Date) {
        this.name = name;
        this.age = age;
        this.tower = tower;
        this.report_date = LocalDate.parse(Date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.rec_Date = this.report_date.plusDays(22);
    }

    String get_date_string(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    void survey_report() {
        p.add(new Patient("Flora", 6, 'A', "01/04/2020"));
        p.add(new Patient("Denys", 24, 'B', "01/04/2020"));
        p.add(new Patient("Jim", 42, 'C', "18/05/2020"));
        p.add(new Patient("Hazel", 87, 'D', "23/06/2020"));
        p.add(new Patient("Caery", 72, 'A', "01/06/2020"));
        p.add(new Patient("David", 7, 'B', "14/06/2020"));
        p.add(new Patient("Kevim", 37, 'D', "05/06/2020"));
        p.add(new Patient("Tom", 67, 'D', "20/06/2020"));
        p.add(new Patient("Bob", 74, 'A', "04/07/2020"));
        p.add(new Patient("Rachel", 48, 'C', "24/07/2020"));
        p.add(new Patient("Thomas", 21, 'C', "11/06/2020"));
        p.add(new Patient("Mary", 17, 'D', "21/06/2020"));
        p.add(new Patient("Smith", 89, 'A', "07/08/2020"));
        p.add(new Patient("Pearson", 47, 'B', "04/06/2020"));
        p.add(new Patient("Anderson", 62, 'B', "27/07/2020"));
        p.add(new Patient("Johnson", 10, 'D', "01/08/2020"));
        p.add(new Patient("Robertz", 50, 'A', "09/08/2020"));
        p.add(new Patient("Julie", 86, 'B', "02/05/2020"));
        p.add(new Patient("Edith", 42, 'D', "07/06/2020"));
        p.add(new Patient("John", 95, 'D', "01/06/2020"));

    }

    void show_data() {
        for (int i = 0; i < p.size(); i++) {
            System.out.println(p.get(i).name + "\t" + p.get(i).age + "\t" +
                    get_date_string(p.get(i).report_date) + "\t" + get_date_string(p.get(i).rec_Date));
        }
    }

}

public class Final_Lab_Assignment {
    public static void main(String[] args) {
//        Patient patient = new Patient();
//        patient.survey_report();
//        patient.show_data();
        GUI obj = new GUI();
    }


}

class GUI extends JFrame {
    JFrame frame = new JFrame("Survey Result");
    JCheckBox checkBox1 = new JCheckBox("A");
    JCheckBox checkBox2 = new JCheckBox("B");
    JCheckBox checkBox3 = new JCheckBox("C");
    JCheckBox checkBox4 = new JCheckBox("D");
    JTextField textField = new JTextField(10);
    JButton button = new JButton("Submit");
    JLabel tower_head,header;
    JLabel ans1 = new JLabel();
    JLabel ans2 = new JLabel();
    JLabel ans3 = new JLabel();
    JLabel ans4 = new JLabel();

    public GUI() {
        Patient patient = new Patient();
//        patient.survey_report();
//        patient.show_data();

        frame.add(checkBox1);
        frame.add(checkBox2);
        frame.add(checkBox3);
        frame.add(checkBox4);
        frame.add(textField);
        frame.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = textField.getText();
                if (checkBox1.isSelected()) {
                    ans1.setText(do_something('A', date));
                    frame.add(ans1);

                }
                if (checkBox2.isSelected()) {
                    ans2.setText(do_something('B', date));
                    frame.add(ans2);
                }
                if (checkBox3.isSelected()) {
                    ans3.setText(do_something('C', date));
                    frame.add(ans3);
                }
                if (checkBox4.isSelected()) {
                    ans4.setText(do_something('D', date));
                    frame.add(ans4);
                }
            }
        });
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));    //flow grid null
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    String do_something(char tower, String input_date) {
        Patient patient = new Patient();
        patient.survey_report();
//        patient.show_data();
        tower_head = new JLabel("<html><br><h3 style=\"color:teal;\">-------------Details of Tower " + tower + " --------------------</h3><br></html>");
        header = new JLabel("Name   Age     Reported Date   Recovery Date");
        frame.add(tower_head);
        frame.add(header);
        StringBuilder answer = new StringBuilder("<html>");
        int active = 0, recover = 0;
        LocalDate tempdate = LocalDate.parse(input_date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//        System.out.println(tempdate);
        for (Patient data : patient.p) {
            if (data.tower == tower && (tempdate.isAfter(data.report_date) || tempdate.isEqual(data.report_date))) {
//                            System.out.println("Report date " + data.report_date);
                if (data.rec_Date.isAfter(tempdate)) {
//                                System.out.println("active " + data.rec_Date);
                    answer.append(data.name + "&nbsp;&nbsp;&nbsp;&nbsp;" + data.age + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + patient.get_date_string(data.report_date) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + patient.get_date_string(data.rec_Date) + "<p style=\"color:red;\">Active</p>");
                    active++;
                } else {
//                                System.out.println("recover " + data.rec_Date);
                    answer.append(data.name + "&nbsp;&nbsp;&nbsp;&nbsp;" + data.age + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + patient.get_date_string(data.report_date) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + patient.get_date_string(data.rec_Date) + "<p style=\"color:green;\">Recovered</p>");
                    recover++;
                }

            }
        }
        answer.append("<p style=\"color:blue;\">Total Active Case: " + active + "       Total Recovered Case: " + recover + "</p></html>");
        return String.valueOf(answer);

    }

}
