/**
 * Name: 		Patients Registration System
 * Author:  	Said Houdane
 * Created: 	12/11/2019
 * Course:  	CIS 152 Data Structure
 * Version: 	1.0
 * OS:			Windows 10
 * Copyright: 	This is my original work based on 
 * 			  	specifications issued by our instructor
 * Description: Patients Registration is an application used to register patients 
 * 				and add them into the queue, each patient has a priority based on their
 * 				medical condition. You can register, add, delete, sort and search patients.
 * Academic Honesty: I attest that this is my original work.
 * I have not used unauthorized source code, either modified or
 * unmodified. I have not given other fellow student(s) access to
 * my program.
 */

/**
 * The Class Patient is used to represent a patient
 */
public class patientInfo implements Comparable<patientInfo> {

	// conditions and their priorities
	public static final String[] CONDITIONS = { "Cold/Fever", "Chest Pain", "Back Pain", "Allergies", "Depression",
			"Stroke" };
	public static final int[] PRIORITIES = { 3, 1, 2, 1, 2, 1 };

	// patient details
	private String patientID;
	private String name;
	private int age;
	private String gender;
	private String address;
	private String phoneNumber;
	private int conditionId;
	private int queueNumber;

	/**
	 * Instantiates a new patient.
	 *
	 * @param pateintID   the patient ID
	 * @param name        the name
	 * @param age         the age
	 * @param gender      the gender
	 * @param address     the address
	 * @param phoneNumber the phone number
	 * @param conditionId the condition id
	 */
	public patientInfo(String patientID, String name, int age, String gender, String address, String phoneNumber,
			int conditionId) {
		this.patientID = patientID;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.conditionId = conditionId;
	}

	/**
	 * Gets the patient ID.
	 *
	 * @return the patient ID
	 */
	public String getPatientID() {
		return patientID;
	}

	/**
	 * Sets the patient ID.
	 *
	 * @param patientID the new patient ID
	 */
	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets the age.
	 *
	 * @param age the new age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the condition id.
	 *
	 * @return the condition id
	 */
	public int getConditionId() {
		return conditionId;
	}

	/**
	 * Sets the condition id.
	 *
	 * @param conditionId the new condition id
	 */
	public void setConditionId(int conditionId) {
		this.conditionId = conditionId;
	}

	/**
	 * Gets the queue number.
	 *
	 * @return the queue number
	 */
	public int getQueueNumber() {
		return queueNumber;
	}

	/**
	 * Sets the queue number.
	 *
	 * @param queueNumber the new queue number
	 */
	public void setQueueNumber(int queueNumber) {
		this.queueNumber = queueNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(patientInfo o) {
		int diff = PRIORITIES[this.conditionId] - PRIORITIES[o.conditionId];
		if (diff == 0)
			diff = this.queueNumber - o.queueNumber;

		return diff;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return patientID + " - " + name + "  (" + CONDITIONS[conditionId] + ")";
	}
}
