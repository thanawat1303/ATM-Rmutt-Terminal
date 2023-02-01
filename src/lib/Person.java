package lib;

public class Person {
    private String idPerson;
    private String fullname;
    private String gender;

    /**
     * @return String return the idPerson
     */
    public String getIdPerson() {
        return idPerson;
    }

    /**
     * @param idPerson the idPerson to set
     */
    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    /**
     * @return String return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return String return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

}