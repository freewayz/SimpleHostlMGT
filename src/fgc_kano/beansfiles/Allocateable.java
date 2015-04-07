/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fgc_kano.beansfiles;

/**
 *
 * @author root
 */
public interface Allocateable{
    
    /**
     * Method to find a student by their admission no
     * @param admission_no
     * @return 
     */
    public StundentAllocationEntry searchStudentByADNo(String admission_no);
    
    /**
     * Method to search for a student by thier last Name
     * @param lastName
     * @return 
     */
    public StundentAllocationEntry searchStudentByLN(String lastName);
    
    /**
     * <b>Method to add new Student</b>
     * @param student
     * @return
     * @throws DatabaseException 
     */
    public boolean  allocateStudent(StundentAllocationEntry student) throws DatabaseException;
    
    /**
     * <b>Method to update student record</b>
     * @param student
     * @return
     * @throws DatabaseException 
     */
    public boolean updateStudent(StundentAllocationEntry student) throws DatabaseException;
    
    
    public boolean deleteStudentInformation(StundentAllocationEntry student) throws DatabaseException;
    
    public void close();
    
}
