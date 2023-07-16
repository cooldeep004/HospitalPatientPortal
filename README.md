
# HospitalPatientPortal
This is a patient portal. Where we can register staff members and create patients .Only Staff person have the access to create and manipulate the patient data.In this I use Basic Authentication, username and password(encoded) will be fetch from staff member table. These credentials will use for access any patient api request.

<p> 1. Install jdk , IDE, MySql, MySql work bench , Postman</p>

<p> 2. CLone this Project & import as maven </p>

<p> 3. Override application.properties file to configure database </p>

<p> 4. Run PatientApplication class </p>


<h1> Basic Features </h1>

<p> 1. Hospital staff to suignup/login into portal </p>

<p> 2. After staff login , staff can create/Admit new/Exsiting patient.  Note: Urls and schema are given below </p>

<p> 3. Fetch all patients admitted in hospital </p>

<p> 4.Fetch all discharged patient from hospital</p>

Note: Ignore Jwt classes that is to be added 

# STAFF CONTROLLER URLs

=>  http://localhost:8888/api/v1/staff/newstaff                POST-----to create new staff 
StaffSchema to create(All field are compulsory , if any one is missed , corresponding exception will be thrown and message will return)
user & email should be unique.
JSON

{
    "name": "kuldeep singh",
    "user": "singhk",
    "email": "singh@gmail.com",
    "password": "qwert",
    "contact": "asdfg",
    "staffType": "doctor"
}

=>  http://localhost:8888/api/v1/staff/allstaff                   GET ------to view all staff member

=>  http://localhost:8888/api/v1/staff/getstaff/{id}              GET-------to view staff with id

=>  http://localhost:8888/api/v1/staff/updatestaff/{id}           PUT------to update the staff Type(if any one promoted)

JSON
{
"staffType": "doctor"
}

=>  http://localhost:8888/api/v1/staff/deletestaff/{id}             DELETE ------to delete staff with id

=>  http://localhost:8888/api/v1/staff/deleteall                    DELETE ------to delete all staff


# PATIENTS CONTROLLER URLs

In postman use basic Authentication and provide user & password(password is encoded so remberer it) from staff database. to access patients url.
Patients Schema

    {
        "id": 20,       
        "name": "bob",
        "email": "bob@gmail.com",
        "age": 22,
        "contact": "sdfghj",
        "room": null,    
        "createdBy": "kuldeep singh",
        "updatedBy": "kuldeep singh",
        "lastUpdated": "2023-07-16T14:10:51.038+00:00",
        "createdDate": "2023-07-16T13:56:35.321+00:00",
        "admitDate": "2023-07-16T14:06:05.300+00:00",
        "expanse": 9999,
        "status": true
    }

=>  http://localhost:8888/api/v1/patient/newpatient                 POST----------to create a new patient

Json  : email should be unique

{
    "name":"bob",                
    "email":"bob@gmail.com",    
     "age":22,
    "contact":"1234567890"      
} 

//these fields are mandatory

// contact should b valid ten digit

room : default will be null

createdBy or updatedBy = upadte internally according to logged in staff member

createdDate & lastUpdate= fetch automatically

expanse & status are optional, we can update at the time of creation or we have different url to update.

=>  http://localhost:8888/api/v1/patient/allpatient                  GET----to fetch all patients

=>  http://localhost:8888/api/v1/patient/getpatient/{id}             GET-----to fetch patient with id        

=>  http://localhost:8888/api/v1/patient/deletepatient/{id}          DELETE---to delete patient record with id

=>  http://localhost:8888/api/v1/patient/deleteall                    DELETE -----to delete all record

=> http://localhost:8888/api/v1/patient/updatestatus/{id}            PUT-----to update status(admitted, discharged) true = admitted , false = discharged 

   if status is false means discharged. Bill will generate and values of room: "NA" , admitDate:null , expanse=0
   
{
    "room":"1d",
    "status": true
}


=> http://localhost:8888/api/v1/patient/updateBill/{id}                PUT-----to update bill , bill will be updated only if patient is admitted    

{
    "expanse": 99998
}

=>  http://localhost:8888/api/v1/patient/getadmitted                    GET-----to fetch all admitted patients

=>  http://localhost:8888/api/v1/patient/getdischarged                  GET------to fetch all discharged patients  

