/*
 * Copyright 2018-2021 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.gchq.syntheticdatagenerator.types;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import uk.gov.gchq.syntheticdatagenerator.utils.DateHelper;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.StringJoiner;

public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int MIN_MANGERS_TREE_HEIGHT = 2;
    private static final int EXTRA_MANAGERS_TREE_HEIGHT_RANGE = 3;
    private static final int MIN_MATRICULA = 900;
    private static final int EXTRA_MATRICULA_RANGE = 1_000;
    private static final int BECA_BONUS = 2_500;
    private static final String TAX_CODE = "11500L";

    private String uid;
    private String name;
    private String dateOfBirth;
    private PhoneNumber[] contactNumbers;
    private EmergencyContact[] emergencyContacts;
    private Address address;
    private BankDetails bankDetails;
    private String taxCode;
    private Nationality nationality;
    private Manager[] manager;
    private String entradaULLDate;
    private Grade grade;
    private Campus campus;
    private int matriculaAmount;
    private int becaBonus;
    private BirthLocation birthLocation;
    private Sex sex;


    public static Alumno generate(final Random random) {
        Alumno Alumno = new Alumno();
        //Faker faker = ThreadLocalFaker.getFaker(random);
        //Genera datos que solo son de españoles, funciona medio raro
        Faker faker = new Faker(new Locale("es"));
        Alumno.setUid(generateUID(random));
        Name AlumnoName = faker.name();
        Alumno.setName(AlumnoName.firstName() + " " + AlumnoName.lastName()); // we are storing name as a string not a Name
        Alumno.setDateOfBirth(DateHelper.generateDateOfBirth(random));
        Alumno.setContactNumbers(PhoneNumber.generateMany(random));
        Alumno.setEmergencyContacts(EmergencyContact.generateMany(faker, random));
        Alumno.setAddress(Address.generate(faker, random));
        Alumno.setBankDetails(BankDetails.generate(random));
        Alumno.setTaxCode(generateTaxCode());
        Alumno.setNationality(Nationality.generate(random));
        Alumno.setManager(Manager.generateMany(random, MIN_MANGERS_TREE_HEIGHT + random.nextInt(EXTRA_MANAGERS_TREE_HEIGHT_RANGE)));
        Alumno.setEntradaULLDate(DateHelper.generateHireDate(Alumno.dateOfBirth, random));
        Alumno.setGrade(Grade.generate(random));
        Alumno.setCampus(Campus.generate(random));
        Alumno.setMatriculaAmount(MIN_MATRICULA + random.nextInt(EXTRA_MATRICULA_RANGE));
        Alumno.setBecaBonus(random.nextInt(BECA_BONUS));
        Alumno.setBirthLocation(BirthLocation.generate(faker, random));
        Alumno.setSex(Sex.generate(random));

        return Alumno;
    }

    public static String generateUID(final Random random) {
        return "alu" + random.nextInt(Integer.MAX_VALUE);
    }

    private static String generateTaxCode() {
        return TAX_CODE;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(final String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public PhoneNumber[] getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(final PhoneNumber[] contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public EmergencyContact[] getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(final EmergencyContact[] emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public BankDetails getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(final BankDetails bankDetails) {
        this.bankDetails = bankDetails;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(final String taxCode) {
        this.taxCode = taxCode;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(final Nationality nationality) {
        this.nationality = nationality;
    }

    public Manager[] getManager() {
        return manager;
    }

    public void setManager(final Manager[] manager) {
        this.manager = manager;
    }

    public String getEntradaULLDate() {
        return entradaULLDate;
    }

    public void setEntradaULLDate(final String entradaULLDate) {
        this.entradaULLDate = entradaULLDate;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(final Grade grade) {
        this.grade = grade;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(final Campus campus) {
        this.campus = campus;
    }

    public int getMatriculaAmount() {
        return matriculaAmount;
    }

    public void setMatriculaAmount(final int matriculaAmount) {
        this.matriculaAmount = matriculaAmount;
    }

    public int getBecaBonus() {
        return becaBonus;
    }

    public void setBecaBonus(final int becaBonus) {
        this.becaBonus = becaBonus;
    }

    public BirthLocation getBirthLocation() {
        return birthLocation;
    }

    public void setBirthLocation(final BirthLocation workLocation) {
        this.birthLocation = workLocation;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(final Sex sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Alumno.class.getSimpleName() + "[", "]")
                .add("uid=" + uid)
                .add("name='" + name + "'")
                .add("dateOfBirth='" + dateOfBirth + "'")
                .add("contactNumbers=" + Arrays.toString(contactNumbers))
                .add("emergencyContacts=" + Arrays.toString(emergencyContacts))
                .add("address=" + address)
                .add("bankDetails=" + bankDetails)
                .add("taxCode='" + taxCode + "'")
                .add("nationality=" + nationality)
                .add("manager=" + Arrays.toString(manager))
                .add("entradaULLDate='" + entradaULLDate + "'")
                .add("grade=" + grade)
                .add("campus=" + campus)
                .add("matriculaAmount=" + matriculaAmount)
                .add("becaBonus=" + becaBonus)
                .add("birthLocation=" + birthLocation)
                .add("sex=" + sex)
                .toString();
    }
}