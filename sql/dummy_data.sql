INSERT INTO Unit VALUES
	('ANHB4000', 'Honours human biology', 6),
	('NEUR4000', 'Honours neuro', 6),
	('PHYL4000', 'Honours physiology ', 12),
	('BIOM4000', 'Honours biomed', 12);
	
INSERT INTO Discipline VALUES
	('ANHB', 'ANHB4000', 'NEUR4000', 'PHYL4000', null),
	('BIOMS', 'ANHB4000', 'NEUR4000', 'BIOM4000', null);
	
INSERT INTO Student VALUES 
	(20001234,'Bob', 'Jones', 'How to succeed in business without really trying', 'ANHB', 0, null),
	(20005678,'Harry', 'Smith', 'The cultural effect of the paradigm of mental health in modern society', 'ANHB', 0, null),
	(20112233,'Lisa', 'George', 'How to make sick people better', 'BIOMS', 0, null);
			
INSERT INTO Staff VALUES
	(100012345,'Lee', 'Black'),
	(100067890,'Stephanie', 'Myer'),
	(100055555,'Ronald', 'Bruce'),
	(100066666,'Mean', 'Marker');
	
INSERT INTO Supervises VALUES
	(20001234, 100012345),
	(20005678, 100012345),
	(20112233, 100067890),
	(20112233, 100055555);
	
INSERT INTO Assessment VALUES
	(1, 'Essay', 'ANHB4000', 40),
	(2, 'Presentation', 'ANHB4000', 60),
	(3, 'Research assignment', 'NEUR4000', 30),
	(4, 'Examination', 'NEUR4000', 70),
	(5, 'Research project', 'PHYL4000', 50),
	(6, 'Dissertation', 'PHYL4000', 50),
	(7, 'Dissertation', 'BIOM4000', 100);
	
INSERT INTO SubAssessment VALUES
	(1, 'Essay', 1, 80, 25),
	(2, 'References', 1, 20, 20),
	(3, 'Content', 2, 70, 50),
	(4, 'Delivery', 2, 30, 30),
	(5, 'Assignment', 3, 50, 100),
	(6, 'References', 3, 50, 100),
	(7, 'Examination', 4, 100, 100),
	(8, 'Project', 5, 100, 100),
	(9, 'Dissertation', 6, 100, 100),
	(10, 'Dissertation', 7, 100, 100);
	
INSERT INTO SubAssessmentMark VALUES
	(1, 20, 100012345, 20112233, 1, 1),
	(2, 18, 100067890, 20001234, 1, 1),
	(3, 19, 100055555, 20001234, 1, 1),
	(4, 5, 100066666, 20001234, 1, 1),
	(5, 17, 100012345, 20112233, 2, 1),
	(6, 16, 100067890, 20001234, 2, 1),
	(7, 15, 100055555, 20001234, 2, 1),
	
	(8, 35, 100012345, 20112233, 3, 1),
	(9, 42, 100067890, 20001234, 3, 1),
	(10, 41, 100055555, 20001234, 3, 1),
	(11, 29, 100012345, 20112233, 4, 1),
	(12, 26, 100067890, 20001234, 4, 1),
	(13, 25, 100055555, 20001234, 4, 1),
	(14, 24, 100066666, 20001234, 4, 1),
	
	(15, 80, 100055555, 20001234, 5, 1),
	(16, 82, 100055555, 20001234, 6, 1),
	(17, 78, 100055555, 20001234, 7, 1),
	
	(18, 69, 100055555, 20001234, 8, 1),
	(19, 81, 100055555, 20001234, 9, 1),
	(20, 81, 100012345, 20112233, 10, 1);