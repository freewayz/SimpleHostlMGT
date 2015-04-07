/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fgc_kano.beansfiles;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author root
 */
public class StudentDatabaseAccess implements Allocateable {

    private Connection db_conn;

    /*
     PrepareStatement for inserting 
     */
    private final PreparedStatement _std_profile,
            _hostel_profile,
            _guardian_profile,
            _class_profile,
            sqlFindByLastName,
            sqlFindByAdmissionNo,
            sql_get_last_insert_id,
            print_allocation,
            update_student_profile,
            update_class_profile,
            update_hostel_profile,
            update_gurdian_profile,
            delete_student,
            delete_hostel,
            delete_class,
            delete_guardian;

    private final String insert_student_profile = "INSERT INTO student_profile (admission_no, surname, firstname,"
            + " othername, dob, state, lga, religion,address, sex, tel_no)"
            + " VALUES (?, ?, ?,?, ?, ?, ?, ?, ?,?,?);",
            insert_hostel_profile = "insert into hostel_profile values(?,?,?,?);",
            insert_guardian_profile = "insert into guardian_profile values(?,?,?,?);",
            insert_class_profile = "insert into class_profile values(?,?,?,?)",
            //Query To Fetch Search Informaation
            sql_find_adm = "select student_profile.admission_no, student_profile.firstname,\n"
            + "student_profile.surname, student_profile.sex, class_profile.class,\n"
            + "class_profile.level, guardian_profile.guardian_name, \n"
            + "hostel_profile.hostel_name, hostel_profile.floor_no, class_profile.image\n"
            + "from student_profile, class_profile, guardian_profile, hostel_profile\n"
            + "where student_profile.admission_no = ?",
            sql_find_by_ln = "select student_profile.admission_no, student_profile.firstname,\n"
            + "student_profile.surname, student_profile.sex, class_profile.class,\n"
            + "class_profile.level, guardian_profile.guardian_name, \n"
            + "hostel_profile.hostel_name, hostel_profile.floor_no, class_profile.image\n"
            + "from student_profile, class_profile, guardian_profile, hostel_profile\n"
            + "where student_profile.surname = ?",
            update_student = "update student_profile \n"
            + "set surname = ?,firstname = ?,\n"
            + "othername = ?, religion = ?,\n"
            + "address = ?, tel_no= ?\n"
            + "where admission_no = ? ;",
            update_gurdian = " update guardian_profile set guardian_name = ?, guardian_add = ?, guardian_tel_no = ? where admission_no = ?;",
            update_hostel = " update hostel_profile set  hostel_name = ?, floor_no = ? where admission_no = ?;",
            update_class = "update class_profile set level = ?, class = ? where admission_no = ?; ",
            last_id = "select * from student_profile where std_id = (select count(*) from student_profile)",
            sql_print_allocation = "",
            delete_student_profile = "delete from student_profile where admission_no = ?;",
            delete_class_profile = "delete from class_profile where admission_no = ?;",
            delete_guardian_profile = "delete from guardian_profile where admission_no = ?;",
            delete_hostel_profile = "delete from hostel_profile where admission_no = ?;";

    public StudentDatabaseAccess() throws Exception {

        //Connet the database
        try {
            Class.forName("com.mysql.jdbc.Driver");

            db_conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/fgc_kano", "root", "toor");
            db_conn.setAutoCommit(false);
        } catch (Exception e) {

            e.printStackTrace();
        }

        _std_profile = db_conn.prepareStatement(insert_student_profile);
        _hostel_profile = db_conn.prepareStatement(insert_hostel_profile);
        _guardian_profile = db_conn.prepareStatement(insert_guardian_profile);
        _class_profile = db_conn.prepareStatement(insert_class_profile);
        sqlFindByAdmissionNo = db_conn.prepareStatement(sql_find_adm);
        sqlFindByLastName = db_conn.prepareStatement(sql_find_by_ln);
        sql_get_last_insert_id = db_conn.prepareStatement(last_id);
        print_allocation = db_conn.prepareStatement(sql_print_allocation);
        update_class_profile = db_conn.prepareStatement(update_class);
        update_gurdian_profile = db_conn.prepareStatement(update_gurdian);
        update_student_profile = db_conn.prepareStatement(update_student);
        update_hostel_profile = db_conn.prepareStatement(update_hostel);
        delete_class = db_conn.prepareStatement(delete_class_profile);
        delete_guardian = db_conn.prepareStatement(delete_guardian_profile);
        delete_hostel = db_conn.prepareStatement(delete_hostel_profile);
        delete_student = db_conn.prepareStatement(delete_student_profile);

    }

    private Connection dbConnect() throws Exception {
        Properties properties = new Properties();
        FileInputStream properties_file = new FileInputStream(
                "C:\\Users\\root\\Documents\\NetBeansProjects\\FGC_KANO\\src\\fgc_kano\\db.properties");
        properties.load(properties_file);
        properties_file.close();
        String drivers = properties.getProperty("jdbc.drivers");
        if (drivers != null) {
            System.setProperty("jdbc.drivers", drivers);
        }

        String url = properties.getProperty("jdbc.url");
        String user = properties.getProperty("jdbc.user");
        String pass = properties.getProperty("jdbc:pass");

        return DriverManager.getConnection(url, user, pass);
    }

    @Override
    public StundentAllocationEntry searchStudentByADNo(String admission_no) {
        String img_path = "C:\\Users\\root\\Documents\\NetBeansProjects\\FGC_KANO\\src\\fgc_kano\\slides\\temp.jpg";
        try {

            //set query parameter
            sqlFindByAdmissionNo.setString(1, admission_no);
            ResultSet resultSet = sqlFindByAdmissionNo.executeQuery();

            //If no record found, return immediately
            if (!resultSet.next()) {
                return null;
            }

            StundentAllocationEntry allocationEntry = new StundentAllocationEntry();
            allocationEntry.setAdmission_no(resultSet.getString(1));
            allocationEntry.setF_name(resultSet.getString(2));
            allocationEntry.setS_name(resultSet.getString(3));
            allocationEntry.setSex(resultSet.getString(4));
            allocationEntry.setStd_class(resultSet.getString(5));
            allocationEntry.setStd_lvl(resultSet.getString(6));
            allocationEntry.setGuardian_name(resultSet.getString(7));
            allocationEntry.setHostel_name(resultSet.getString(8));
            allocationEntry.setFloor_number(resultSet.getString(9));

            Blob img = resultSet.getBlob(10);
            InputStream is = img.getBinaryStream();
            OutputStream os = new FileOutputStream(img_path);
            int byte_read = -1;
            byte[] buffer = new byte[4096];
            while ((byte_read = is.read(buffer)) != -1) {
                os.write(buffer, 0, byte_read);
            }

            is.close();
            os.close();
            allocationEntry.setImage(img_path);
            return allocationEntry;

        } catch (Exception error) {
            error.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean allocateStudent(StundentAllocationEntry student) throws DatabaseException {

        //allocate a new student a room from the hostel database
        try {
            int query = 0;

            //insert Student Profile 
            _std_profile.setString(1, student.getAdmission_no());
            _std_profile.setString(2, student.getS_name());
            _std_profile.setString(3, student.getF_name());
            _std_profile.setString(4, student.getO_name());
            _std_profile.setString(5, student.getDob());
            _std_profile.setString(6, student.getState());
            _std_profile.setString(7, student.getLga());
            _std_profile.setString(8, student.getReligion());
            _std_profile.setString(9, student.getAddress());
            _std_profile.setString(10, student.getSex());
            _std_profile.setString(11, student.getTel_no());

            query = _std_profile.executeUpdate();

            //If Insertion fails rollback and discontine
            if (query == 0) {
                db_conn.rollback();
                return false;
            }

            //determine new Hostel added to the database
//            ResultSet rs_std_id = _std_id.executeQuery();
            //Add new Hostel Profile to the hostel_profile Table
            _hostel_profile.setString(1, student.getAdmission_no());
            _hostel_profile.setString(2, student.getHostel_type());
            _hostel_profile.setString(3, student.getHostel_name());
            _hostel_profile.setString(4, student.getFloor_number());

            query = _hostel_profile.executeUpdate();
            if (query == 0) {
                db_conn.rollback();
                return false;
            }

            //Insert new Guardian Profile into the Guardian Table
            _guardian_profile.setString(1, student.getAdmission_no());
            _guardian_profile.setString(2, student.getGuardian_name());
            _guardian_profile.setString(3, student.getGuardian_add());
            _guardian_profile.setString(4, student.getGuadian_tel_no());
            query = _guardian_profile.executeUpdate();

            if (query == 0) {
                db_conn.rollback();
                return false;
            }

            //insert class Profile
            _class_profile.setString(1, student.getAdmission_no());
            _class_profile.setString(2, student.getStd_lvl());
            _class_profile.setString(3, student.getStd_class());
            _class_profile.setBinaryStream(4, student.getFis(), (int) student.getFile().length());
            query = _class_profile.executeUpdate();
            if (query == 0) {
                db_conn.rollback();
                return false;
            }

            db_conn.commit();
            return true;
        } catch (SQLException error) {
            //detect problem
            error.printStackTrace();
            try {
                db_conn.rollback();
                return false;
            } catch (SQLException e) {
                throw new DatabaseException(e);
            }
        }
    }

    @Override
    public boolean updateStudent(StundentAllocationEntry student) throws DatabaseException {
        //update the student_ profile record
        try {

            int result = 0;
            update_student_profile.setString(1, student.getS_name());
            update_student_profile.setString(2, student.getF_name());
            update_student_profile.setString(3, student.getO_name());
            update_student_profile.setString(4, student.getReligion());
            update_student_profile.setString(5, student.getAddress());
            update_student_profile.setString(6, student.getTel_no());
            update_student_profile.setString(7, student.getAdmission_no());
            result = update_student_profile.executeUpdate();

            //If Update Student Fail rollback the Connection
            if (result == 0) {
                db_conn.rollback();
                return false;
            }

            //update Student Class Profile
            update_class_profile.setString(1, student.getStd_lvl());
            update_class_profile.setString(2, student.getStd_class());
            update_class_profile.setString(3, student.getAdmission_no());
            result = update_class_profile.executeUpdate();

            //If Update Student Class Profile fails rollback the connection
            if (result == 0) {
                db_conn.rollback();
                return false;
            }

            //Update Gurdian Profile 
            update_gurdian_profile.setString(1, student.getGuardian_name());
            update_gurdian_profile.setString(2, student.getGuardian_add());
            update_gurdian_profile.setString(3, student.getGuadian_tel_no());
            update_gurdian_profile.setString(4, student.getAdmission_no());
            result = update_gurdian_profile.executeUpdate();

            //If Update Student Guradian Fails rollback connection
            if (result == 0) {
                db_conn.rollback();
                return false;
            }

            //Update Student Class Profile
            update_hostel_profile.setString(1, student.getHostel_name());
            update_hostel_profile.setString(2, student.getFloor_number());
            update_hostel_profile.setString(3, student.getAdmission_no());
            result = update_hostel_profile.executeUpdate();

            //If Update Student Hostel Fails the rollback connection
            if (result == 0) {
                db_conn.rollback();
                return false;
            }

            db_conn.commit();
            return true;

        } catch (SQLException error) {
            error.printStackTrace();
            try {
                db_conn.rollback();
                return false;
            } catch (Exception e) {
                throw new DatabaseException(e);
            }
        }
    }

    @Override
    public void close() {

    }

    @Override
    public boolean deleteStudentInformation(StundentAllocationEntry student) throws DatabaseException {

        try {

            int result = 0;
            delete_class.setString(1, student.getAdmission_no());
            result = delete_class.executeUpdate();

            if (result == 0) {
                db_conn.rollback();
                return false;
            }

            delete_guardian.setString(1, student.getAdmission_no());
            result = delete_guardian.executeUpdate();
            if (result == 0) {
                db_conn.rollback();
                return false;
            }

            delete_hostel.setString(1, student.getAdmission_no());
            result = delete_hostel.executeUpdate();
            if (result == 0) {
                db_conn.rollback();
                return false;
            }

            delete_student.setString(1, student.getAdmission_no());
            result = delete_student.executeUpdate();
            if (result == 0) {
                db_conn.rollback();
                return false;
            }

            db_conn.commit();
            return true;
        } catch (SQLException error) {
            error.printStackTrace();
            try {
                db_conn.rollback();
                return false;

            } catch (SQLException db_error) {
                
                db_error.printStackTrace();

                throw new DatabaseException(error);
            }
        }

    }

    @Override
    public StundentAllocationEntry searchStudentByLN(String lastName) {
        String img_path = "C:\\Users\\root\\Documents\\NetBeansProjects\\FGC_KANO\\src\\fgc_kano\\slides\\temp.jpg";
        try {

            //set query parameter
            sqlFindByLastName.setString(1, lastName);
            ResultSet resultSet = sqlFindByLastName.executeQuery();

            //If no record found, return immediately
            if (!resultSet.next()) {
                return null;
            }

            StundentAllocationEntry allocationEntry = new StundentAllocationEntry();
            allocationEntry.setAdmission_no(resultSet.getString(1));
            allocationEntry.setF_name(resultSet.getString(2));
            allocationEntry.setS_name(resultSet.getString(3));
            allocationEntry.setSex(resultSet.getString(4));
            allocationEntry.setStd_class(resultSet.getString(5));
            allocationEntry.setStd_lvl(resultSet.getString(6));
            allocationEntry.setGuardian_name(resultSet.getString(7));
            allocationEntry.setHostel_name(resultSet.getString(8));
            allocationEntry.setFloor_number(resultSet.getString(9));

            Blob img = resultSet.getBlob(10);
            InputStream is = img.getBinaryStream();
            OutputStream os = new FileOutputStream(img_path);
            int byte_read = -1;
            byte[] buffer = new byte[4096];
            while ((byte_read = is.read(buffer)) != -1) {
                os.write(buffer, 0, byte_read);
            }

            is.close();
            os.close();
            allocationEntry.setImage(img_path);
            return allocationEntry;

        } catch (Exception error) {
            error.printStackTrace();
            return null;
        }

    }

}
