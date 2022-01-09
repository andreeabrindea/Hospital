package controller;

import patient.Patient;
import repository.PatientRepository;
import repository.PatientRepositoryFile;
import repository.PatientRepositorySerialization;
import java.util.*;

public class Controller{
    PatientRepositoryFile fileRepo = new PatientRepositoryFile("patients.txt");
    PatientRepository repo = new PatientRepository(fileRepo);
    PatientRepositorySerialization serRepo = new PatientRepositorySerialization(fileRepo, "patientsBytes.txt");

    private List<Patient> getPatientList(PatientRepository current_repo) {
        List<Patient> patientList =  new ArrayList<Patient>();
        for(Patient patient : current_repo.findAll())
            patientList.add(patient);

        return patientList;
    }

    public static Patient readPatient(boolean newPatient, PatientRepository current_repo) {
        Scanner keyboard = new Scanner(System.in);

        int ID;
        String clientName;
        String clientAddress;
        String phoneNumber;
        String date;
        String problemDescription;
        boolean hasapp = false;
        int age;
        String app_date = "n/a";

        if(newPatient == true)
            while(true) {
                System.out.println("ID: ");
                ID = keyboard.nextInt();
                if(current_repo.checkID(ID) == false)
                    break;
                else System.out.println("!!! ID already in list, please write another one !!!");
            }
        else {
            while(true) {
                System.out.println("ID: ");
                ID = keyboard.nextInt();
                if(current_repo.checkID(ID) == true)
                    break;
                else System.out.println("!!! ID not in list, please write another one !!!");
            }
        }
        System.out.println("Name: ");
        clientName = keyboard.nextLine();
        keyboard.nextLine();
        System.out.println("Address: ");
        clientAddress = keyboard.nextLine();
        keyboard.nextLine();
        System.out.println("Phone Number: ");
        phoneNumber = keyboard.nextLine();
        keyboard.nextLine();
        System.out.println("Date: ");
        date = keyboard.nextLine();
        keyboard.nextLine();
        System.out.println("Problem: ");
        problemDescription = keyboard.nextLine();
        keyboard.nextLine();
        System.out.println("Age: ");
        age = keyboard.nextInt();

        return new Patient(ID, clientName, clientAddress, phoneNumber, date, problemDescription, hasapp, age, app_date);
    }

    public boolean addPatient() {
        Patient patient = readPatient(true, repo);
        try {
            repo.add(patient);
            fileRepo.add(patient);
            serRepo.add(patient);
        }
        catch(Exception e) {
            System.out.println("Error " + e + " when adding Patient to repository");
            return false;
        }
        return true;
    }

    public boolean updatePatient() {
        Patient patient = readPatient(false, repo);
        try {
            repo.add(patient);
            fileRepo.add(patient);
            serRepo.add(patient);
        }
        catch(Exception e) {
            System.out.println("Error " + e + " when adding Patient to repository");
            return false;
        }
        return true;
    }

    public boolean removePatient(int ID) {
        if(repo.checkID(ID) == true) {
            Patient current_patient = repo.findById(ID);

            repo.delete(current_patient);
            fileRepo.delete(current_patient);
            serRepo.delete(current_patient);

            System.out.println("Removed patient: " + current_patient);
            return true;
        }
        else {
            System.out.println("patient not in list");
            return false;
        }
    }

    public Patient getPatient(int ID) {
        if(repo.checkID(ID) == true)
            return null;
        else return repo.findById(ID);
    }

    public void printPatient() {
        for(Patient patient : repo.findAll())
            System.out.println(patient);
    }

    public void printPatientFromName(String clientName) {
        List<Patient> patientList = this.getPatientList(repo);

        patientList.stream()
                .filter(p -> p.getClientName().startsWith(clientName))
                .forEach(System.out::println);
    }

    public void printPatientNewerOrOlderThan(int age, boolean newer) {
        List<Patient> patientsList = this.getPatientList(repo);

        if(newer)
            patientsList.stream()
                    .filter(p -> p.getAge() > age)
                    .forEach(System.out::println);
        else
            patientsList.stream()
                    .filter(p -> p.getAge() < age)
                    .forEach(System.out::println);
    }


    public void printAgesOfPatientFromName(String clientName) {
        List<Patient>patientsList = this.getPatientList(repo);

        patientsList.stream()
                .filter(p -> p.getClientName().startsWith(clientName))
                .map(pn -> pn.getAge())
                .forEach(System.out::println);
    }

    public void printAgeAddress(String clientAddress) {
        List<Patient> patientsList = this.getPatientList(repo);

        patientsList.stream()
                .filter(p -> p.getClientAddress().startsWith(clientAddress))
                .map(pn -> pn.getAge())
                .forEach(System.out::println);
    }

    public void printAllNames() {
        List<Patient> patientList = this.getPatientList(repo);

        patientList.stream()
                .map(pn -> pn.getClientName())
                .distinct()
                .forEach(System.out::println);
    }

    public void printYoungestPatient() {

        List<Patient> patientList = this.getPatientList(repo);

        Patient patient = patientList.stream()
                .min((p1, p2) -> (int)p1.getAge() - (int)p2.getAge())
                .orElseThrow(NoSuchElementException::new);

        System.out.println(patient);
    }
}