package repository;
import patient.Patient;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class PatientRepositoryFile extends AbstractRepository<Patient, Integer>{
    private String filename;
    public PatientRepositoryFile(String filename){
        this.filename=filename;
        readFromFile();
    }
    private void readFromFile(){
        try(BufferedReader reader=new BufferedReader(new FileReader(filename))){
            List<String> list = new ArrayList<>();
            list = reader.lines().collect(Collectors.toList());
            for (String line : list) {
                String[] el=line.split(";");
                if(el.length!=9){
                    System.err.println("Not a valid number of atributes " + line);
                    continue;
                }
                try{
                    int Id=Integer.parseInt(el[0]);
                    boolean hasapp = Boolean.parseBoolean(el[6]);
                    int age = Integer.parseInt(el[7]);

                    Patient p = new Patient(Id,el[1],el[2],el[3],el[4],el[5],hasapp, age, el[7]);
                    super.add(p);
                }catch(NumberFormatException n){
                    System.err.println("The ID is not a valid number " + el[0]);
                }
            }
        }catch(IOException ex){
            throw new RepositoryException("Error reading" + ex);
        }
    }
    @Override
    public void add(Patient obj) {
        try{
            super.add(obj);
            writeToFile();
        }
        catch(RuntimeException e){
            throw new RepositoryException("Object wasn�t added " + e + " " + obj);
        }
    }
    private void writeToFile() {
        try(PrintWriter pw = new PrintWriter(filename)) {
            for(Patient el : findAll()) {
                String line = el.getID() + ";" + el.getClientName() + ";" + el.getClientAddress() +
                        ";" + el.getPhoneNumber() + ";" + el.getDate() + ";" + el.getProblemDescription() + ";" + el.hasApp() + ";" + el.getAge() + ";" + el.getApp_date();
                //System.out.println(line);
                pw.println(line);
            }
        }catch(IOException ex) {
            throw new RepositoryException("Error writing" + ex);
        }
    }
    @Override
    public void delete(Patient obj){
        try{
            super.delete(obj);
            writeToFile();
        }
        catch(RuntimeException ex){
            throw new RepositoryException("Object was not deleted " + ex + " "  + obj);
        }
    }
    @Override
    public void update(Patient obj, Integer Id){
        try{
            super.update(obj, Id);
            writeToFile();
        }
        catch(RuntimeException ex){
            throw new RepositoryException("Object was not updated " + ex + " " + obj);
        }
    }
}