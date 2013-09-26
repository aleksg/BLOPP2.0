
#LogModel
SELECT POLLEN_SPREAD.spread, POLLEN_STATES.name
FROM POLLEN_SPREAD, POLLEN_STATES, POLLEN
WHERE
	POLLEN.date = "?"
	AND POLLEN_SPREAD.id = POLLEN.pollen_spread_id
	AND POLLEN_STATES.id = POLLEN.pollen_state_id;

#Skal brukes i kalenderen for å fargelegge celler. 
SELECT HS.label
FROM HEALTH_STATES AS HS,
	CHILDREN_LOG_DAYS AS CLD
WHERE
	CLD.health_state_id = HS.id
	AND CLD.date = "?"
;


# For notificaions use.
SELECT M.id, M.name, M.instructions_id, DMD.time, DMD.taken
FROM DAY_MEDICINE_DOSES AS DMD, MEDICINES AS M
WHERE
	DMD.medicine_id = M.id 
	AND DMD.taken = false
	AND DMD.planned = true;
;
#Insert a taken medicine.
INSERT INTO DAY_MEDICINE_DOSES AS DMD (reward, planned, taken, 
time, day_date, child_id, medicine_id)
VALUES(?,?,?,?,?,?,?);


# Select instruction by ID 
SELECT * 
FROM MEDICINE_INSTRUCTIONS
WHERE 
	id = "?"

# Select all instructions
SELECT *
FROM MEDICINE_INSTRUCTIONS

# Select Instruction images URL by id
SELECT url
FROM MEDICINE_INSTRUCTIONS
WHERE
	id = "?"
	
# Select medicine by id
SELECT * 
FROM MEDICINES 
WHERE 
	id= "?"

#Select all medicines
SELECT * 
FROM MEDICINES

# Update the medicines
UPDATE MEDICINES 
SET 
	name = "?"
AND 
	instructions_id = "?"

# Select the medication plan by id
SELECT * 
FROM MEDICAL_PLANS 
WHERE 
	id="?"
	
# Select all the medication plans
SELECT * 
FROM MEDICAL_PLANS




