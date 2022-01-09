package repository;

import patient.*;
import repository.PatientRepositoryFile;

public class PatientRepository extends AbstractRepository<Patient, Integer> {
    public PatientRepository() {
    };

    public PatientRepository(PatientRepositoryFile current_repo) {
        for(Patient patient : current_repo.findAll())
            this.add(patient);
    }
    public boolean Hasapp(Integer ID, String Date) {
        if(this.findById(ID).hasApp() == true) {
            System.out.println("!!! Can not have an appointment on this date because someone else has !!!");
            return false;
        }
        else {
            this.findById(ID).setIfApp(true);
            this.findById(ID).setApp_date(Date);
            return true;
        }
    }

    public boolean RemoveApp(Integer ID) {
        if(this.findById(ID).hasApp() == false) {
            System.out.println("!!! Can not remove rent because car is not rented !!!");
            return false;
        }
        else {
            this.findById(ID).setIfApp(false);
            this.findById(ID).setApp_date("");
            return true;
        }
    }

    public Patient YoungestPatient() {
        float young_age = 999999999;
        Patient youngest_patient = null;
        for (Patient patient : this.findAll())
            if(young_age > patient.getAge()) {
                young_age = patient.getAge();
                youngest_patient = patient;
            }
        return youngest_patient;
    }

    public void AppPatients() {
        for (Patient patient : this.findAll())
            if(patient.hasApp() == true) {
                System.out.println(patient);
            }
    }
};