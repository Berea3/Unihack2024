--
--INSERT INTO user (id, email, password, roles) VALUES
--('1', 'doctor1@example.com', 'password123', 'DOCTOR'),
--('2', 'doctor2@example.com', 'password456', 'DOCTOR'),
--('3', 'patient1@example.com', 'password789', 'PATIENT');
--
---- Insert sample Cases
--INSERT INTO cases (id, case_name, case_description, case_status, case_category, case_date, case_result) VALUES
--('c1', 'Diabetes Checkup', 'Routine checkup for diabetes management', 'Open', 'Endocrinology', '2024-11-01', 'Pending'),
--('c2', 'Hypertension Monitoring', 'Regular monitoring for high blood pressure', 'Closed', 'Cardiology', '2024-10-15', 'Stable'),
--('c3', 'Post-Surgery Follow-up', 'Follow-up after knee surgery', 'Open', 'Orthopedics', '2024-10-25', 'Needs further observation');

---- Insert sample Reports associated with Cases
--INSERT INTO report (id, report_name, report_description, report_priority, report_date, case_id) VALUES
--('r1', 'Initial Diabetes Checkup', 'Blood sugar levels within acceptable range', 'High', '2024-11-02', 'c1'),
--('r2', 'Blood Pressure Update', 'Blood pressure slightly elevated', 'Medium', '2024-10-16', 'c2'),
--('r3', 'Post-Surgery Mobility', 'Patient showing improvement in mobility', 'Low', '2024-10-26', 'c3');

-- Link Cases to Users in the LinkedCases table
-- Assume each doctor is associated with specific cases
INSERT INTO linked_cases (case_id, user_id) VALUES
('c1', '1'),  -- Doctor 1 linked to Diabetes Checkup
('c2', '2'),  -- Doctor 2 linked to Hypertension Monitoring
('c3', '1'),  -- Doctor 1 linked to Post-Surgery Follow-up
('c3', '3');  -- Patient 1 also associated with Post-Surgery Follow-up
