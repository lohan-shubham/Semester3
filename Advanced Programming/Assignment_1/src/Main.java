import java.util.ArrayList;
import java.util.Scanner;

class Patient {
    private String name;
    private float body_temp;
    private int o_level;
    private int age;
    private int ID;
    private int recovery_day;
    private boolean admitted_status;
    private String admitted_institute;
    private boolean trash;

    Patient(String name, float body_temp, int o_level, int age, int ID) {
        this.name = name;
        this.body_temp = body_temp;
        this.o_level = o_level;
        this.age = age;
        this.ID = ID;
        this.admitted_status = false;
        this.admitted_institute = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBody_temp() {
        return body_temp;
    }

    public void setBody_temp(float body_temp) {
        this.body_temp = body_temp;
    }

    public int getO_level() {
        return o_level;
    }

    public void setO_level(int o_level) {
        this.o_level = o_level;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getRecovery_day() {
        return recovery_day;
    }

    public void setRecovery_day(int recovery_day) {
        this.recovery_day = recovery_day;
    }

    public boolean isAdmitted() {
        return admitted_status;
    }

    public void setAdmitted_status(boolean admitted_status) {
        this.admitted_status = admitted_status;
    }

    public String getAdmitted_institute() {
        return admitted_institute;
    }

    public void setAdmitted_institute(String admitted_institute) {
        this.admitted_institute = admitted_institute;
    }

    public boolean isTrash() {
        return trash;
    }

    public void setTrash(boolean trash) {
        this.trash = trash;
    }
}

class healthCareInstitute {
    private String name;
    private int min_o_level;
    private float max_temp;
    private int avail_beds;
    private boolean status;
    private ArrayList<Patient> admitted_patient;
    private boolean trash;

    healthCareInstitute(String name, float temp, int o_level, int beds) {
        this.name = name;
        this.max_temp = temp;
        this.min_o_level = o_level;
        this.avail_beds = beds;
        this.admitted_patient = new ArrayList<>();
        this.status = avail_beds == 0 ? false : true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMin_o_level() {
        return min_o_level;
    }

    public void setMin_o_level(int min_o_level) {
        this.min_o_level = min_o_level;
    }

    public float getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(float max_temp) {
        this.max_temp = max_temp;
    }

    public int getAvail_beds() {
        return avail_beds;
    }

    public void setAvail_beds(int avail_beds) {
        this.avail_beds = avail_beds;
    }

    public boolean Status() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<Patient> getAdmitted_patient() {
        return admitted_patient;
    }

    public void setAdmitted_patient(ArrayList<Patient> admitted_patient) {
        this.admitted_patient = admitted_patient;
    }

    public boolean isTrash() {
        return trash;
    }

    public void setTrash(boolean trash) {
        this.trash = trash;
    }

    public void reduced_beds() {
        this.avail_beds--;
        if (this.avail_beds == 0)
            this.status = false;
    }

    public void newInstituteDetails() {
        System.out.println("Added Institute Details:-->");
        System.out.println("Name : " + this.name);
        System.out.println("Maximum Body Temperature: " + this.max_temp);
        System.out.println("Minimum Oxygen Level: " + this.min_o_level);
        System.out.println("Number of Available Beds: " + this.avail_beds);
        System.out.println("Admission Status: OPEN");
    }

    public void admit_patient() {
        Scanner input = new Scanner(System.in);
        if (this.status) {
            for (int i = 0; i < Main.camp_patient.size() && this.status; i++) {
                Patient pat = Main.camp_patient.get(i);
                if (!pat.isTrash() && !pat.isAdmitted() && pat.getO_level() >= this.min_o_level) {
                    System.out.print("\nEnter recovery days for admitted patient ID " + pat.getID() + " : ");
                    int rec_days = input.nextInt();
                    Main.camp_patient.get(i).setRecovery_day(rec_days);
                    Main.camp_patient.get(i).setAdmitted_status(true);
                    Main.total_admitted++;
                    Main.camp_patient.get(i).setAdmitted_institute(this.name);
                    this.reduced_beds();
                    this.admitted_patient.add(pat);
                }
            }
            for (int i = 0; i < Main.camp_patient.size() && this.status; i++) {
                Patient pat = Main.camp_patient.get(i);
                if (!pat.isTrash() && !pat.isAdmitted() && pat.getBody_temp() <= this.max_temp) {
                    System.out.print("\nEnter recovery days for admitted patient ID " + pat.getID() + " : ");
                    int rec_days = input.nextInt();
                    Main.camp_patient.get(i).setRecovery_day(rec_days);
                    Main.camp_patient.get(i).setAdmitted_status(true);
                    Main.total_admitted++;
                    Main.camp_patient.get(i).setAdmitted_institute(this.name);
                    this.reduced_beds();
                    this.admitted_patient.add(pat);
                }
            }
        }
    }

}

class Main {
    static ArrayList<Patient> camp_patient;
    static int total_admitted = 0;
    static ArrayList<healthCareInstitute> institutes;

    public static void showDetails_Institute(healthCareInstitute inst) {
        System.out.println("Name: " + inst.getName());
        System.out.println("Eligiblity Criteria:--->");
        System.out.println("Minimum Oxygen Level: " + inst.getMin_o_level());
        System.out.println("Maximum Body Temperature: " + inst.getMax_temp());
        System.out.println("Number of Available Beds: " + inst.getAvail_beds());
        System.out.print("Admission Status: ");
        if (inst.Status())
            System.out.println("OPEN");
        else System.out.println("CLOSED");
    }

    public static void showDetails_Patient(Patient pat) {
        System.out.println("\n---------------------------------------------------------");
        System.out.println("ID No: " + pat.getID());
        System.out.println("Name: " + pat.getName());
        System.out.println("Body Temperature: " + pat.getBody_temp());
        System.out.println("Oxygen Level: " + pat.getO_level());
        System.out.print("Admission Status: ");
        if (pat.isAdmitted()) {
            System.out.println("Admiited");
            System.out.println("Health Care Institute: " + pat.getAdmitted_institute());
        } else
            System.out.println("Not Admitted");
        System.out.println("---------------------------------------------------------");

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        camp_patient = new ArrayList<>(n);
        institutes = new ArrayList<>();
        input.nextLine();
        for (int i = 1; i <= n; i++) {
//            Name(string) <space> Temperature(float) <space> oxygen levels(integer) <space>  age(integer).
            String data[] = input.nextLine().split(" ");
            Patient obj = new Patient(data[0], Float.parseFloat(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]), i);
//            camp_patient.put(i,obj);
            camp_patient.add(obj);

        }
        System.out.println("Enter your queries: ");
        System.out.println("1: Add Health Care Institute");
        System.out.println("2: Remove account of admitted patients");
        System.out.println("3: Remove account of Health care Institutes");
        System.out.println("4: Display no of patients in the Camop");
        System.out.println("5: Display no of OPEN health care institut");
        System.out.println("6: Display the details of Health care institute");
        System.out.println("7: Display details of particular patients");
        System.out.println("8: Display all patients");
        System.out.println("9: Display names of patients admitted in an Institute and their recovery time");
        while (total_admitted != camp_patient.size()) {
            System.out.print("\nEnter query no: ");
            int q = input.nextInt();
//           Add Health Care Institute
            if (q == 1) {
                input.nextLine();
                System.out.print("Enter Name of Institute: ");
                String name = input.nextLine();
                System.out.print("\nEnter Criteria to admit patient:--> ");
                System.out.print("\n(a) Maximum Temperature: ");
                float temp = input.nextFloat();
                System.out.print("\n(b) Minimum Oxygen Level: ");
                int o_level = input.nextInt();
                System.out.print("\nNo of available beds: ");
                int beds = input.nextInt();
                healthCareInstitute obj = new healthCareInstitute(name, temp, o_level, beds);
                obj.newInstituteDetails();
                obj.admit_patient();
                institutes.add(obj);

            }
//            Remove the accounts of admitted patients
            if (q == 2) {
                System.out.println("Account ID removed of admitted patients: ");
//                ArrayList<Integer> rm = new ArrayList<>();
                for (int i = 0; i < camp_patient.size(); i++) {
                    Patient pat = camp_patient.get(i);
                    if (pat.isAdmitted() && !pat.isTrash()) {
                        System.out.println(pat.getID());
                        camp_patient.get(i).setTrash(true);
                    }
                }
            }
//            Remove the accounts of health care institutes whose applications are closed
            if (q == 3) {
                System.out.println("Accounts removed of Institute whose admission is closed:");
                for (int i = 0; i < institutes.size(); i++) {
                    if (!institutes.get(i).Status() && !institutes.get(i).isTrash()) {
                        System.out.println(institutes.get(i).getName());
                        institutes.get(i).setStatus(true);
                    }
                }
            }
//            Display number of patients still in the camp (not admitted yet)
            if (q == 4) {
                int count = 0;
                for (Patient pat : camp_patient) {
                    if (!pat.isAdmitted() && !pat.isTrash()) {
                        count++;
                    }
                }
                System.out.println(count + " Patients are in the camp (not admitted yet)");
            }
//            Display number of health care institutes admitting patients currently (Open)
            if (q == 5) {
                int count = 0;
                for (healthCareInstitute inst : institutes) {
                    if (inst.Status() && !inst.isTrash()) {
                        count++;
                    }
                }
                System.out.println(count + " institutes are admitting patients currently");

            }
//            Display details of the Health Care Institute
            if (q == 6) {
                input.nextLine();
                System.out.print("Enter Health Care Institute name: ");
                String name = input.nextLine();
                for (healthCareInstitute inst :
                        institutes) {
                    if (inst.getName().equals(name) && !inst.isTrash()) {
                        showDetails_Institute(inst);
                    }
                }
            }
//            Display details of particular patient
            if (q == 7) {
                System.out.print("Enter ID of patient: ");
                int id = input.nextInt();
                System.out.println();
                for (Patient pat : camp_patient) {
                    if (pat.getID() == id && !pat.isTrash()) {
                        showDetails_Patient(pat);
                    }
                }
            }
//            Display all patients
            if (q == 8) {
                for (Patient pat : camp_patient) {
                    if (!pat.isTrash())
                        System.out.println("ID: " + pat.getID() + " Name: " + pat.getName());
                }
            }
//            Display names of patients admitted in an Institute and their recovery time
            if (q == 9) {
                input.nextLine();
                System.out.print("Enter Institute Name: ");
                String name = input.nextLine();
                for (healthCareInstitute inst : institutes) {
                    if (inst.getName().equals(name) && !inst.isTrash()) {
                        for (Patient pat : inst.getAdmitted_patient()) {
                            System.out.println("Name: " + pat.getName() + ", Recovery time: " + pat.getRecovery_day());
                        }
                    }
                }

            }
        }
    }
}


